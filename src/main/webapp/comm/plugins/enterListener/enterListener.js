/**
 * Created by jerrywang on 2017/1/19.
 */
$.fn.extend({
    enter:function (func) {
        return this.each(function () {
            $(this).keyup(function (e) {
                if(e.keyCode == 13){
                    func();
                }
            });
        });
    }
});
