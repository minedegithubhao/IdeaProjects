/**
 * 菜单类型格式化
 * @param value
 * @param row
 * @param index
 * @returns {string}
 */
function typeFormatter(value,row,index){
    let result;
    switch (value) {
        case "M":
            result = "目录";
            break;
        case "C":
            result = "菜单";
            break;
        case "F":
            result = "按钮";
            break;
        default:
            result = "未定义";
    }
    return result;
}

/**
 * 显示隐藏格式化
 * @param value
 * @returns {string}
 */
function visualFormatter(value){
    let result;
    switch (value) {
        case "0":
            result = "显示";
            break;
        case "1":
            result = "隐藏";
            break;
        default:
            result = "未定义";
    }
    return result;
}

/**
 * 状态格式化
 * @param value
 * @returns {string}
 */
function statusFormatter(value) {
    let result;
    $.ajax({
        type:'GET',
        url:'../../system/sysDict/dictDataGrid?noCache=' + new Date().getTime(),
        data:{dictType:'sys_normal_disable'},
        dataType:'json',
        contentType: 'application/json',
        async:false,
        success:function (data) {
            if (data.success){
                let rows = data.rows;
                for (let i = 0; i < rows.length; i++) {
                    if (value == rows[i].dictValue){
                        result = rows[i].dictLabel;
                        // console.log("result:",result);
                    }
                }
            }
        }
    })
    return result;
    // switch (value) {
    //     case "1":
    //         result = "禁用";
    //         break;
    //     case "0":
    //         result = "启用";
    //         break;
    //     default:
    //         result = "未定义";
    // }
    // return result;
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

