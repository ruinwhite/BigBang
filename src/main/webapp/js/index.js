$("header div.navbar ul.nav li").hover(function(){
	$(this).addClass("bg-info");
},function(){
	$("header div.navbar ul.nav li").removeClass("bg-info");
});

$("header div.navbar ul.nav li:not(.dropdown)").click(function(){
	$("header div.navbar ul.nav li:not(.dropdown)").removeClass("active");
	$(this).addClass("active");
});

$("header div.navbar ul.nav li.dropdown").click(function(){
	$("header div.navbar ul.nav li").removeClass("active");
});

function getPageArticles(pageNum,pageSize){
	var json = {"pageNum":pageNum,"pageSize":pageSize};
	$.post("/husky/getPageArticles",json,function(data){
		var josn = jQuery.parseJSON(data);
		if(josn.status==400){
			$("#page-arts").val(josn.errorInfo);
//			$("#loginModal form .form-group").addClass("has-error");
//			$("#loginModal form .glyphicon-remove").removeClass("hide");
//			$("#loginName").val("");
//			$("#loginName").attr("placeholder",josn.errorInfo);
//			$("#userPassword").val("");
//			$("#userPassword").attr("placeholder",josn.errorInfo);
		}else if(josn.status==200){
			var list = josn.arts;
			return list;
		}
	});
}

$(function(){
	var list = getPageArticles(1,5);
})
