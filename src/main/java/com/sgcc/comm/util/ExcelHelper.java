package com.sgcc.comm.util;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jerrywang
 * @create 2019/9/28.
 */
public class ExcelHelper {

    /**
     * 表头背景色
     * {R, G, B}
     */
    private static final byte[] TITLE_FOREGROUND_COLOR = new byte[]{(byte) 68, (byte) 210, (byte) 210};

    /**
     * 每个sheet页最大条数
     * 超过之后新创建一个sheet页
     */
    private final int PER_PAGE_ITEM_NUM = 20000;

    /**
     * 导出
     *
     * @param excelName 生成的文件名
     * @param title     表头及字段对应信息
     * @param dataList  数据list
     * @param <T>       bean对象
     */
    public static <T> void export(String excelName, Object[][] title, List<T> dataList) {
        String tempFilePath = createExcelFile(title, dataList);
        outputExcel(excelName, tempFilePath);
    }

    /**
     * 创建Excel文件
     *
     * @param title    表头及字段对应信息
     * @param dataList 数据list
     * @param <T>      bean对象
     * @return
     */
    private static <T> String createExcelFile(Object[][] title, List<T> dataList) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Sheet 1");

        Map<String, XSSFCellStyle> styleMap = getStyle(workbook);

        makeTitle(sheet, styleMap, title);
        makeData(sheet, styleMap, title, dataList);

        return writeInFile(workbook);
    }

    /**
     * 写入表头
     *
     * @param sheet    sheet页对象
     * @param styleMap 样式map
     * @param title    表头及字段对应信息
     */
    private static void makeTitle(XSSFSheet sheet, Map<String, XSSFCellStyle> styleMap, Object[][] title) {
        XSSFRow titleRow = sheet.createRow(0);
        titleRow.setHeight((short) 420);
        // 循环表头
        for (int i = 0; i < title.length; i++) {
            XSSFCell cell = titleRow.createCell(i);
            cell.setCellValue(title[i][0].toString());
            cell.setCellStyle(styleMap.get("title"));
            sheet.setColumnWidth(i, (Integer) title[i][2] * 100);
        }
    }

    /**
     * 写入数据
     *
     * @param sheet    sheet页对象
     * @param styleMap 样式map
     * @param title    表头及字段对应信息
     * @param dataList 数据list
     * @param <T>      bean对象
     */
    private static <T> void makeData(XSSFSheet sheet, Map<String, XSSFCellStyle> styleMap, Object[][] title, List<T> dataList) {
        // 循环塞入数据
        for (int j = 1; j <= dataList.size(); j++) {
            T data = dataList.get(j - 1);
            XSSFRow row = sheet.createRow(j);
            for (int i = 0; i < title.length; i++) {
                XSSFCell cell = row.createCell(i);
                String property = title[i][1].toString();
                if ("".equals(property)) {
                    cell.setCellValue(j);
                    cell.setCellStyle(styleMap.get("center"));
                } else {
                    String value = getProperty(data, property);
                    cell.setCellValue(value);
                    String alignment = title[i][3].toString();
                    cell.setCellStyle(styleMap.get(alignment));
                }
            }
        }
    }

    /**
     * 将内容写入Excel文件
     *
     * @param workbook 工作表
     * @return
     */
    private static String writeInFile(XSSFWorkbook workbook) {
        String tempFileName = CommUtil.getUUID();
        String filePath = CommUtil.getInstance().PROPERTIES.get("file") + tempFileName + ".xlsx";
        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream(filePath);
            workbook.write(fileOut);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (fileOut != null) {
                try {
                    fileOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return filePath;
    }

    /**
     * styles
     *
     * @param workbook This workbook object
     * @return styleMap
     */
    private static Map<String, XSSFCellStyle> getStyle(XSSFWorkbook workbook) {
        Map<String, XSSFCellStyle> styleMap = new HashMap<>(5);

        /*--- baseStyle ---*/
        XSSFCellStyle baseStyle = workbook.createCellStyle();
        baseStyle.setBorderBottom(BorderStyle.THIN);
        baseStyle.setBorderTop(BorderStyle.THIN);
        baseStyle.setBorderLeft(BorderStyle.THIN);
        baseStyle.setBorderRight(BorderStyle.THIN);
        baseStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        styleMap.put("base", baseStyle);

        /*--- titleStyle ---*/
        XSSFCellStyle titleStyle = workbook.createCellStyle();
        titleStyle.cloneStyleFrom(baseStyle);
        titleStyle.setAlignment(HorizontalAlignment.CENTER);

        XSSFColor xssfColor = new XSSFColor(TITLE_FOREGROUND_COLOR, new DefaultIndexedColorMap());
        titleStyle.setFillForegroundColor(xssfColor);
        titleStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 14);
        font.setFontName("黑体");
        titleStyle.setFont(font);
        styleMap.put("title", titleStyle);

        /*-- leftStyle ---*/
        XSSFCellStyle leftStyle = workbook.createCellStyle();
        leftStyle.cloneStyleFrom(baseStyle);
        leftStyle.setAlignment(HorizontalAlignment.LEFT);
        styleMap.put("left", leftStyle);

        /*-- centerStyle ---*/
        XSSFCellStyle centerStyle = workbook.createCellStyle();
        centerStyle.cloneStyleFrom(baseStyle);
        centerStyle.setAlignment(HorizontalAlignment.CENTER);
        styleMap.put("center", centerStyle);

        /*--- rightStyle ---*/
        XSSFCellStyle rightStyle = workbook.createCellStyle();
        rightStyle.cloneStyleFrom(baseStyle);
        rightStyle.setAlignment(HorizontalAlignment.RIGHT);
        styleMap.put("right", rightStyle);

        return styleMap;
    }

    public static <T> void export(String excelName, Object[][][] title, List<T> dataList) {

    }

    private static HttpServletResponse initResponseAndExcelName(String excelName) {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletResponse response = ((ServletRequestAttributes) requestAttributes).getResponse();
        //设置响应头和客户端保存文件名
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        try {
            String contentDisposition = "attachment;fileName=" + new String(excelName.getBytes("UTF-8"),
                    "ISO8859-1") + ".xlsx";
            response.setHeader("Content-Disposition", contentDisposition);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * 导出Excel
     *
     * @param excelName    Excel文件名
     * @param tempFilePath 临时文件路径
     */
    private static void outputExcel(String excelName, String tempFilePath) {
        HttpServletResponse response = initResponseAndExcelName(excelName);
        //激活下载操作
        OutputStream os;
        try {
            InputStream inputStream = new FileInputStream(tempFilePath);
            os = response.getOutputStream();
            // 循环写入输出流
            byte[] b = new byte[2048];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }
            // 关闭。
            os.close();
            inputStream.close();
            // 删除临时文件
            deleteTemplateFile(tempFilePath);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 删除临时文件
     *
     * @param tempFilePath 临时文件路径
     */
    private static void deleteTemplateFile(String tempFilePath) {
        File file = new File(tempFilePath);
        file.delete();
    }

    private static String getProperty(Object bean, String name) {
        try {
            return BeanUtils.getProperty(bean, name);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
            ex.printStackTrace();
            return "";
        }
    }


}
