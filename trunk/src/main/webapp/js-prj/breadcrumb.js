$(function(){
    //console.log("helloWorld!");
    //顶部菜单点击事件监听器
    $("#mynav a").click(function(){
        var $tar = $(this);
        var loc = $tar.text();
        var href = $tar.attr("href");
        //console.log("点击了:" + loc);
        if(href != "#"
            && href.indexOf("/") >= 0
            && loc != "首页"
            && href.indexOf("/logout") == -1){
            //console.log("将"+loc+"存入sessionStorage");
            sessionStorage.breadcrumb1 = $tar.parents(".dropdown").find("a:first").text();
            sessionStorage.breadcrumb2 = loc;
        }else{
            sessionStorage.breadcrumb1 = "";
            sessionStorage.breadcrumb2 = "";
        }
    });
    //更新导航栏
    $("#breadcrumb").html("首页");
    if(sessionStorage.breadcrumb2){
        var array = '&nbsp;<span class="glyphicon glyphicon-arrow-right"></span>&nbsp;';
        $("#breadcrumb").append(array);
        $("#breadcrumb").append(sessionStorage.breadcrumb1);
        $("#breadcrumb").append(array);
        $("#breadcrumb").append(sessionStorage.breadcrumb2);
    }
});