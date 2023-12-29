$(function() {
    //添加新的Tab页
    $("#navMenu").on("click", "a[data-url]", function(e) {
        e.preventDefault();
        var tabTitle = $(this).text();
        var tabUrl = $(this).data("url");

        if($("#tt").tabs("exists", tabTitle)) { //判断该Tab页是否已经存在
            $("#tt").tabs("select", tabTitle);
        }else {
            var content = '<iframe scrolling="no" frameborder="0" src="../'+tabUrl+'" width="99%" height="99%"></iframe>';
            $("#tt").tabs("add", {
                title: tabTitle,
                content: content,
                closable: true
            });
        }
        $("#navMenu .active").removeClass("active");
        $(this).parent().addClass("active");
    });

    //解决闪屏的问题
    window.setTimeout(function() {
        $("#layout").css("visibility", "visible");
    }, 800);
});

/**
 *  根据百分比展示宽度
 */
function fixWidth(percent){
    return (document.body.clientWidth) * percent;
}

function statusFormatter(value,row,index){
    let result;
    switch (value) {
        case "1":
            result = "禁用";
            break;
        case "0":
            result = "启用";
            break;
        default:
            result = "未定义";
    }
    return result;
}

/**
 * LocalDate格式化
 * @param value
 * @returns {String} yyyy-MM-dd
 */
function dateFormatter(value){
    let y = value.year;
    let m = value.monthValue;
    let d = value.dayOfMonth;
    return y + "-" + (m<10 ? ('0'+m) : m) + "-" + (d<10 ? ('0'+d) : d);
}

/**
 * LocalDateTime格式化
 * @param value
 * @returns {string} yyyy-MM-dd hh:mm:ss
 */
function dateTimeFormatter(value){
    let date = dateFormatter(value);
    let h = value.hour;
    let mi = value.minute;
    let s = value.second;
    return date + ' ' + (h<10 ? ('0'+h) : h) + ":" + (mi<10 ? ('0'+mi) : mi) + ':' + (s<10 ? ('0'+s) : s);
}

function checkSpace(val){
    let regExpression = "^[ ]+$";
    return new RegExp(regExpression).test(val);
}

