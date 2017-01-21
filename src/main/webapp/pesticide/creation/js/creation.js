/**
 * Created by jerrywang on 2017/1/18.
 */
jQuery.namespace("creation");
$(function () {
    creation.js.init();
});

creation.js = {};
creation.js.init = function () {

    CKEDITOR.replace('edit1');
    var editor = CKEDITOR.instances.edit1;
    editor.focus(function () {
        alert();
        // $('#cke_edit1').children().addClass("edit_focus");
    });

    // um.addListener('blur',function(){
    //     $('#edit_box').children().removeClass("edit_focus");
    // });
    // um.addListener('focus',function(){
    //     $('#edit_box').children().addClass("edit_focus");
    // });

    $("#test").enter(function () {
        alert(9999);
    }).focus(function(){console.info(19343)});

    $("#model_select").dict({table:"s_code_list",type:"YN",where:"",order:"desc"});

    // var editor = CKEDITOR.instances.editor1;
    //
    // editor.focus();

};

