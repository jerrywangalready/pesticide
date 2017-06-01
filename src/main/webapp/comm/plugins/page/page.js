/**
 * Created by jerrywang on 2017/1/30.
 */
$.fn.extend({
    page:function (data) {
        return this.each(function () {
            var _this = $(this);

            _this.addClass("page-bar").attr("aria-label","Page navigation");
            _this.html("<ul class='pagination pagination-sm left'><li><a>10</a></li><li><a>20</a></li><li><a>50</a></li><li><a>100</a></li></ul><span class='badge    page-bar-total'>"+data.total+"</span>")

            var ul = "<ul class='pagination pagination-sm right'>";
            ul += "<li><a aria-label='Previous'><span aria-hidden='true'>&laquo;</span></a></li>";
            // var size = data.total%data.pageSize == 0 && data.total != 0 ? data.total/data.pageSize : data.total/data.pageSize + 1;
            var pn = parseInt((data.total-1)/data.pageSize) + 1;
            var size = data.total == 0 ? 1 : pn;
            for(var i=1;i<=size;i++){
                if(i == data.pageNum)
                    ul += "<li class='active'><a>"+i+"</a></li>";
                else
                    ul += "<li><a>"+i+"</a></li>";
            }
            ul += "<li><a aria-label='Next'><span aria-hidden='true'>&raquo;</span></a></li>";
            ul += "</ul>";
            _this.append(ul);
            if(data.pageNum == 1){
                _this.find("ul:eq(1) li:first").addClass("disabled");
            }
            if(data.pageNum == pn){
                _this.find("ul:eq(1) li:last").addClass("disabled");
            }
            // 数字按钮绑定
            _this.find("ul:eq(1) li:not(:first,:last)").each(function () {
               $(this).click(function () {
                   comm.js.hashAppand("pn",$(this).find("a").text());
               });
            });
            // 向前按钮绑定
            _this.find("ul:eq(1) li:first").click(function () {
                if(data.pageNum > 1){
                    comm.js.hashAppand("pn",data.pageNum - 1);
                }
            });
            // 向后按钮绑定
            _this.find("ul:eq(1) li:last").click(function () {
                if(data.pageNum < pn){
                    comm.js.hashAppand("pn",data.pageNum + 1);
                }
            });
            // 初始化每页显示条数
            _this.find("ul:eq(0) a").click(function () {
                comm.js.hashAppand({"ps":$(this).text(),"pn":1});
            }).each(function () {
                if(data.pageSize == $(this).text()) {
                    $(this).parent().addClass("active");
                }
            });
        });
    }

});
