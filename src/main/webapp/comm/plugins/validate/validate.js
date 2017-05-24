/*
 * jQuery validate plugin 1.0
 * 2016/3/8 create by wangjingyu
 */

$.fn.extend({
    validate:function (formObj) {
        var rules = {
            required: [/[^\s]/, "是必填项"],// 非空
            letters: [/^([a-z]+)*$/i, "只能输入字母"], // 纯字母
            tel: [/^((?:(?:0\d{2,3}[-]?[1-9]\d{6,7})|(?:[48]00[-]?[1-9]\d{6})))*$/, "电话格式不正确"], // 办公或家庭电话
            mobile: [/^(1[3-9]\d{9})*$/, "手机号格式不正确"], // 移动电话
            email: [/^((?:[a-z0-9]+[_\-+.]?)*[a-z0-9]+@(?:([a-z0-9]+-?)*[a-z0-9]+\.)+([a-z]{2,})+)*$/i, "邮箱格式不正确"],
            qq: [/^([1-9]\d{4,})*$/, "QQ号格式不正确，只能由长度大于4的数字组成"],
            date: [/^(\d{4}-\d{1,2}-\d{1,2})*$/, "请输入正确的日期,例:yyyy-mm-dd"],
            time: [/^(([01]\d|2[0-3])(:[0-5]\d){1,2})*$/, "请输入正确的时间,例:14:30或14:30:00"],
            yyyy: [/^(\d{4})*$/, "请输入正确的年份,例:2015"],
            yyyyMM:[/^((\d{4})-(0\d{1}|1[0-2]))*$/, "请输入正确的年月,例:2015-01"],
            yyyyMMddhh:[/^((\d{4})-(\d{2})-(\d{2}) (\d{2}))*$/,"请输入正确的年月日时,例:2015-01-01 10"],
            yyyyMMddhhmm:[/^((\d{4})-(\d{2})-(\d{2}) (\d{2}):(\d{2}))*$/,"请输入正确的年月日时分,例:2015-01-01 10：10"],
            yyyyMMddhhmmss:[/^((\d{4})-(\d{2})-(\d{2}) (\d{2}):(\d{2}):(\d{2}))*$/,"请输入正确的年月日时分秒,例:2015-01-01 10：10：01"],
            ID_card: [/^([1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[A-Z]))*$/, "请输入正确的身份证号码"],
            url: [/^((https?|ftp):\/\/[^\s]+)*$/i, "网址格式不正确"],
            postcode: [/^([0-9]\d{5})*$/, "邮政编码格式不正确"],
            chinese: [/^([\u0391-\uFFE5]+)*$/, "请输入中文"],
            username: [/^(\w{3,12})*$/, "请输入3-12位数字、字母、下划线"], // 用户名
            password: [/^([0-9a-zA-Z]{6,16})*$/, "密码由6-16位数字、字母组成"], // 密码
            // password: [/^[\@A-Za-z0-9\!\#\$\%\^\*\.\~]{6,16}$/,
            // "密码由6-16位数字、字母或~!@#$%^*.组成"], //密码
            passport: [/^(P\d{7}|G\d{8}|S\d{7,8}|D\d+|1[4,5]\d{7})*$/,"请输入正确的护照号码"],
            //百分比数据校验,0~100间数字，可以输入0和100，小数最多输入2位
            Percentage : [/^([0-9]\d?(\.\d{1,2})?|0.\d{1,2}|100)*$/,"请输入百分比范围内的数据"],
            //正整数
            numeric : [/^[0-9]*$/,"请输正整数"],
            // 可接受的后缀名
            accept: function(element, params) {
                if (!params) return true;
                var ext = params[0];
                return (ext === '*') ||
                    (new RegExp(".(?:" + (ext || "png|jpg|jpeg|gif") + ")$", "i")).test(element.value) ||
                    this.renderMsg("只接受{1}后缀", ext.replace('|', ','));
            }
        };
        var returnObj={};
        returnObj.login_user = comm.js.username;
        returnObj.login_name = comm.js.nickname;
        var error= new Array();
        this.each(function () {
            var form = $(this);
            // 遍历form每个元素
            form.find('input,select,textarea').each(function () {
                var name = $(this).attr("name");
                var value = $(this).val();
                if($(this).is(":radio") && !$(this).is(":checked")){
                    return true;
                }
                if($(this).is(':visible') == true) {
                    var validate = $(this).attr("validate");
                    // 如果有validate属性,获取值并按照validate校验
                    if (validate != undefined && validate != "") {
                        var valArray = validate.split(",");
                        for (var v = 0; v < valArray.length; v++) {
                            var rule = rules[valArray[v]];
                            // 如果不满足条件,搜集错误信息
                            if (rule != undefined) {// 存在此规则
                                if (!rule[0].test(value)) {
                                    error.push({obj: $(this), info: rule[1]});
                                    return true;
                                }
                            } else {
                                if (valArray[v].indexOf("length") == 0) {
                                    var length = valArray[v].replace("length[", "").replace("]", "").split("-");
                                    if (length[0] > value.length) {
                                        error.push({obj: $(this), info: "不能小于" + length[0] + "个字!"});
                                    } else if (length[1] < value.length) {
                                        error.push({obj: $(this), info: "不能大于" + length[1] + "个字!"});
                                    }
                                }
                            }

                        }
                    }
                }

                // 封装数据
                if(name != undefined && name != ""){
                    returnObj[name] = value;
                }
            });
            // 清除原错误提示
            form.find("div,input,select").removeClass("has-error");
            form.find("input").tooltip("destroy");
        });
        // 判断是否有错误信息
        if(error.length>0){// 有错误信息 将错误信息的组件标红 返回false
            // 标记错误信息
            for(var e=0;e<error.length;e++){
                if(e==0)
                    $(error[e].obj[0]).focus();
                $(error[e].obj[0]).attr("data-toggle","tooltip").attr("data-placement","top").attr("title",error[e].info).tooltip();
                $(error[e].obj[0]).parent().addClass("has-error");
            }
            return false;
        }else {// 没有错误信息 返回封装的数据
            return returnObj;
        }
    }
});

