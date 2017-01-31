/**
 * Created by jerrywang on 2017/1/30.
 */
$.fn.extend({
    page:function (data) {
        return this.each(function () {
            var _this = $(this);

            _this.addClass("page-bar").attr("aria-label","Page navigation");
            var ul = "<ul class='pagination pagination-sm'>";
            ul += "<li><a aria-label='Previous'><span aria-hidden='true'>&laquo;</span></a></li>";
            for(var i=1;i<=(data.total/data.pageSize+1);i++){
                if(i == data.pageNum)
                    ul += "<li class='active'><a>"+i+"</a></li>";
                else
                    ul += "<li><a>"+i+"</a></li>";
            }
            ul += "<li><a aria-label='Next'><span aria-hidden='true'>&raquo;</span></a></li>";
            ul += "</ul>";
            _this.html(ul);
            if(data.pageNum == 1){
                _this.find("li:first").addClass("disabled");
            }
            if(data.pageNum == parseInt(data.total/data.pageSize+1)){
                _this.find("li:last").addClass("disabled");
            }
            // 数字按钮绑定
            _this.find("li:not(:first,:last)").each(function () {
               $(this).click(function () {
                   comm.js.hashAppand("pn",$(this).find("a").text());
               });
            });
            // 向前按钮绑定
            _this.find("li:first").click(function () {
                if(data.pageNum > 1){
                    comm.js.hashAppand("pn",data.pageNum - 1);
                }
            });
            // 向后按钮绑定
            _this.find("li:last").click(function () {
                if(data.pageNum < parseInt(data.total/data.pageSize+1)){
                    comm.js.hashAppand("pn",data.pageNum + 1);
                }
            });
        });
    }

});
