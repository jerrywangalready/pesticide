/**
 * Created by jerrywang on 2017/1/12.
 */
//截取参数方法，hash：截取的字符串，name：截取的参数名，nvl：该参数不存在时的返回值
function getParameter(hash,name,nvl) {
    hash = hash;
    // hash = decodeURIComponent(comm.BASE64.decode(hash));
    if(!nvl){
        nvl = "";
    }
    var svalue = hash.match(new RegExp("[\?\&]?" + name + "=([^\&\#]*)(\&?)", "i"));
    if(svalue == null){
        return nvl;
    }else{
        svalue = svalue ? svalue[1] : svalue;
        svalue = svalue.replace(/<script>/gi,"").replace(/<\/script>/gi,"").replace(/<html>/gi,"").replace(/<\/html>/gi,"").replace(/alert/gi,"").replace(/<span>/gi,"").replace(/<\/span>/gi,"").replace(/<div>/gi,"").replace(/<\/div>/gi,"");
        return svalue;
    }
}
function setHash(hash) {
    window.location.hash = hash;
    // window.location.hash = comm.BASE64.encode(encodeURIComponent(hash));
}

template.helper('dict',function (value,properties) {
    return $.dict(value,properties);
});

jQuery.namespace = function(){
    var a=arguments, o=null, i, j, d, rt;
    for (i=0; i<a.length; ++i) {
        d=a[i].split(".");
        rt = d[0];
        eval('if (typeof ' + rt + ' == "undefined"){' + rt + ' = {};} o = ' + rt + ';');
        for (j=1; j<d.length; ++j) {
            o[d[j]]=o[d[j]] || {};
            o=o[d[j]];
        }
    }
};

jQuery.namespace("comm");
comm.js = {};
comm.js.dictionary = {};
comm.js.username = "";
comm.js.nickname = "";

comm.js.hashAppand = function () {
    var hash = location.hash;
    var p = hash.substring(1,hash.length).split("&");
    var newHash = "#";

    var len = arguments.length;
    if(len == 2){
        var key = arguments[0];
        var value = arguments[1];

        for(var i=0;i<p.length;i++){
            var pi = p[i].split("=");
            if(pi[0] == key){
                continue;
            }
            newHash += p[i] + "&";
        }
        newHash += key + "=" + value;
    }else {
        var kvs = arguments[0];
        for(var j=0;j<p.length;j++){
            var pj = p[j].split("=");

            if (kvs.hasOwnProperty(pj[0])) {
                continue;
            }
            newHash += p[j] + "&";
        }
        for(var item in kvs){
            if(kvs[item] == "")
                continue;
            newHash += item + "=" + kvs[item] + "&";
        }
        newHash = newHash.substring(0,newHash.length-1);
    }
    setHash(newHash);


};