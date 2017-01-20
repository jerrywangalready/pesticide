/**
 * Created by jerrywang on 2017/1/18.
 */
jQuery.namespace("creation");
$(function () {
    creation.js.init();
});

creation.js = {};
creation.js.init = function () {
    //实例化编辑器
    var um = UM.getEditor('myEditor');

    um.addListener('blur',function(){
        $('#edit_box').children().removeClass("edit_focus");
    });
    um.addListener('focus',function(){
        $('#edit_box').children().addClass("edit_focus");
    });

    $("#test").enter(function () {
        alert(9999);
    }).focus(function(){console.info(19343)});

    $("#model_select").dict({table:"s_code_list",type:"YN",where:"",order:"desc"});

};

