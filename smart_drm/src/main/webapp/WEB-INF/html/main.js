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
        type: 'GET',
        url:'../system/menu/getMenu?noCache=' + new Date().getTime(),
        contentType: 'application/json;charset=UTF-8',
        async:false,
        success: function(data){
            if (data.success){
                result = data.rows;
            }
        }
    })
    return result;
}

function onSideMenuSelect(item) {
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

function checkSpace(val) {
    let regExpression = "^[ ]+$";
    return new RegExp(regExpression).test(val);
}