$(function() {
    //添加新的Tab页
    $("#navmenu").on("click", "a[data-url]", function(e) {
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
        $("#navmenu .active").removeClass("active");
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