function login(){
	var loginName = $("#loginName").value();
	var userPassword = $("#userPassword").value();
	var json = {"loginName":loginName,"userPassword":userPassword};
	$.post("/husky/login",json,function(data){
		if(data.status==0){
			//错误
		}else if(data.status==1){
			//成功
		}
	});
	$("#loginModal").modal("toggle");
}