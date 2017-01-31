/**
 * Created by jerrywang on 2017/1/31.
 */
$.fn.extend({
    query:function () {
        return this.each(function () {
            _this = $(this);

            var obj = {};
            _this.find("input,select").each(function () {
                var n = $(this).attr("name");
                var v = $(this).val();
                // if(v != ""){
                    obj[n] = v;
                // }
            });
            obj.pn = "1";
            comm.js.hashAppand(obj);
        })
    }
});