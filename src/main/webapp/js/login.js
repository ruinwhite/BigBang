$("#loginName").focus(function(){
	if($("#loginModal form .form-group").hasClass("has-error")){
		$("#loginName").val("");
		$("#loginName").attr("placeholder","登录用户");
		$("#userPassword").attr("placeholder","登录密码");
		$("#loginModal form .form-group").removeClass("has-error");
		$("#loginModal form .glyphicon-remove").addClass("hide");
	}
});
$("#userPassword").focus(function(){
	if($("#loginModal form .form-group").hasClass("has-error")){
		$("#userPassword").val("");
		$("#loginName").attr("placeholder","登录用户");
		$("#userPassword").attr("placeholder","登录密码");
		$("#loginModal form .form-group").removeClass("has-error");
		$("#loginModal form .glyphicon-remove").addClass("hide");
	}
});

function login(){
	var loginName = $("#loginName").val();
	var userPassword = $("#userPassword").val();
	var json = {"loginName":loginName,"userPassword":userPassword};
	$.post("/husky/login",json,function(data){
		var josn = jQuery.parseJSON(data);
		if(josn.status==400){
			$("#loginModal form .form-group").addClass("has-error");
			$("#loginModal form .glyphicon-remove").removeClass("hide");
			$("#loginName").val("");
			$("#loginName").attr("placeholder",josn.errorInfo);
			$("#userPassword").val("");
			$("#userPassword").attr("placeholder",josn.errorInfo);
		}else if(josn.status==200){
			$("#loginModal").modal("hide");
			//TODO 增加用户信息展示区域
		}
	});
}