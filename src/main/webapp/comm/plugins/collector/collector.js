/**
 * Created by jerrywang on 2017/1/30.
 */
$.fn.extend({
    collector : function () {

        var hash = location.hash;
        var pn = getParameter(hash,"pn","1");
        var ps = getParameter(hash,"ps","10");


        var obj = {};
        this.each(function () {
            var _this = $(this);
            _this.find("input,select").each(function () {
                var n = $(this).attr("name");
                var v = getParameter(hash,n,"");
                if(v != ""){
                    obj[n] = v;
                    // 为搜索组件赋值
                    $(this).val(v);
                }

            });
        });

        obj.pageNum = pn;
        obj.pageSize = ps;
        return obj;
    }
});