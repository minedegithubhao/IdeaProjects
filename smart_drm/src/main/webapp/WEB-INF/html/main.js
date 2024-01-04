$(function () {
    $('#LeftMenu').sidemenu({
        data: getMenu(),
        onSelect: onSideMenuSelect,
        border: false
    });
});

function getMenu(){
    let result;
    $.ajax({
        type: 'POST',
        url:'../system/menu/getMenu?noCache=' + new Date().getTime(),
        contentType: 'application/json;charset=UTF-8',
        async:false,
        success: function(data){
            debugger
            if (data.success){
                result = data.rows;
            }
        }
    })
    return result;
}

function onSideMenuSelect(item) {
    debugger
    if (!$('#mainTab').tabs('exists', item.text)) {
        $('#mainTab').tabs('add', {
            title: item.text,
            content: '<iframe scrolling="auto" frameborder="0"  src="..' + item.url + '" width="100%" height="99%"></iframe>',
            closable: true,
            icon: item.iconCls,
            id: item.id
        });
    } else {
        $('#mainTab').tabs('select', item.text);
    }
}

/**
 *  根据百分比展示宽度
 */
function fixWidth(percent) {
    return (document.body.clientWidth) * percent;
}

function statusFormatter(value, row, index) {
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
function dateFormatter(value) {
    let y = value.year;
    let m = value.monthValue;
    let d = value.dayOfMonth;
    return y + "-" + (m < 10 ? ('0' + m) : m) + "-" + (d < 10 ? ('0' + d) : d);
}

/**
 * LocalDateTime格式化
 * @param value
 * @returns {string} yyyy-MM-dd hh:mm:ss
 */
function dateTimeFormatter(value) {
    let date = dateFormatter(value);
    let h = value.hour;
    let mi = value.minute;
    let s = value.second;
    return date + ' ' + (h < 10 ? ('0' + h) : h) + ":" + (mi < 10 ? ('0' + mi) : mi) + ':' + (s < 10 ? ('0' + s) : s);
}

function checkSpace(val) {
    let regExpression = "^[ ]+$";
    return new RegExp(regExpression).test(val);
}