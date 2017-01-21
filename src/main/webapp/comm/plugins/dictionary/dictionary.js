/**
 * Created by jerrywang on 2017/1/19.
 */
$.fn.extend({
    dict:function (properties) {
        return this.each(function () {
            // 获取原select中的选项

            // 从后台获取字典数据
            var options = "";
            var _this = $(this);
            // $.post(path+"/dict/getDict.do",JSON.stringify(properties),function(data){
            //     // 遍历list,封装option
            //     console.info(JSON.stringify(data[0]))
            // },'application/json');
            $.ajax({
                type:'POST',
                url:path+"/dict/getDict.do",
                contentType:'application/json',
                data:JSON.stringify(properties),
                success:function (data) {
                    for(var i=0;i<data.length;i++){
                        options += "<option value='"+data[i].CODE_KEY+"'>"+data[i].CODE_VALUE+"</option>";
                    }
                    $(_this).append(options);
                }
            });

        });
    }

});
/*
 * 使用说明:
 * $("#test").dict({table:"",type:"",where:"",order:""});
 * @table 必须(表名)
 * @type 必须(字典类型)
 * @where 非必须(过滤条件),默认无过滤条件
 * @order 非必须(排序),默认正序
 *
 */

$.extend({
    dictInit:function (propertiesArray) {
        $.ajax({
            type:'POST',
            url:path+"/dict/getDictByArray.do",
            contentType:'application/json',
            data:JSON.stringify(propertiesArray),
            success:function (data) {
                comm.js.dictionary = data;
            }
        });
    },
    dict:function (value,table_type) {
        if(comm.js.dictionary.hasOwnProperty(table_type)){
            return comm.js.dictionary[table_type][value];
        }else {
            return value;
        }
    }
});

/*
 * 使用说明:
 * S1.
 *  在js加载前部,初始化字典表.
 *  | $.dictInit([{table:"",type:"",where:"",order:""},{table:"",type:"",where:"",order:""},...]);
 * @table 必须(表名)
 * @type 必须(字典类型)
 * @where 非必须(过滤条件),默认无过滤条件
 * @order 非必须(排序),默认正序
 *
 * S2.
 *  在jsp中的artTemplate模板中,调用方法取值
 *  | <td>{{value | dict:'s_code_list_YN'}}</td>
 *  @value 必须(需要转码的值)
 *  @dict 必须(对应的字典名称,表名+code_type值)
 *
 */