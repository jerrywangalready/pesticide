/**
 * Created by jerrywang on 2017/1/19.
 */
$.fn.extend({
    dict:function (properties) {
        return this.each(function () {
            // 获取原select中的选项

            // 从后台获取字典数据
            // $.post(path+"/dict/getDict.do",JSON.stringify(properties),function(data){
            //     // 遍历list,封装option
            //     console.info(JSON.stringify(data[0]))
            // });
            var options = "";
            var _this = $(this);
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