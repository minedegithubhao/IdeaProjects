$(function () {
    $('#LeftMenu').sidemenu({
        data: [{
            text: '系统管理',
            iconCls: 'fa fa-cog',
            state: 'open',
            children: [{
                text: '用户管理',
                iconCls : 'fa-user',
                url: 'system/user/index'
            }, {
                text: 'Option2',
                iconCls : 'fa-user'
            }, {
                text: 'Option3',
                iconCls: 'fa-user',
                children: [{
                    text: 'Option31',
                    iconCls: 'fa-user'
                }, {
                    text: 'Option32',
                    iconCls: 'fa-user'
                }]
            }]
        }, {
            text: '日志管理',
            iconCls: 'icon-more',
            children: [{
                text: 'Option4',
                iconCls: 'fa-user'
            }, {
                text: 'Option5',
                iconCls: 'fa-user'
            }, {
                text: 'Option6',
                iconCls: 'fa-user'
            }]
        }],
        onSelect: onSideMenuSelect,
        border: false
    });
});

function onSideMenuSelect(item) {
    if (!$('#mainTab').tabs('exists', item.text)) {
        $('#mainTab').tabs('add', {
            title: item.text,
            content: '<iframe scrolling="auto" frameborder="0"  src="../' + item.url + '" width="100%" height="99%"></iframe>',
            closable: true,
            icon: item.iconCls,
            id: item.id
        });
    } else {
        $('#mainTab').tabs('select', item.text);
    }
    addTabMenu();
}

function addTabMenu() {
    /* 双击关闭TAB选项卡 */
    $(".tabs-inner").dblclick(function () {
        var subtitle = $(this).children(".tabs-closable").text();
        $('#mainTab').tabs('close', subtitle);
    });
    /* 为选项卡绑定右键 */
    // $(".tabs-inner").bind('contextmenu', function (e) {
    //     $('#tab_menu').menu('show', {
    //         left: e.pageX,
    //         top: e.pageY
    //     });
    //
    //     var subtitle = $(this).children(".tabs-closable").text();
    //
    //     $('#tab_menu').data("currtab", subtitle);
    //     $('#mainTab').tabs('select', subtitle);
    //     return false;
    // });
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