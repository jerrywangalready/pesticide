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
            })

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
   dict:function (properties,value) {
       console.info(comm.js.dictionary);
   } 
});