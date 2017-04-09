$(function (){
	//登陆验证
	$("button[type='submit']").click(function (){
		var username = $("input[name='username']").val().trim();
		var password = $("input[name='password']").val().trim();
		if (username != "") {
			if (password != "") {
				var url = "user/loginCheck";
				$.post(url, {username : username, password : password}, function (data) {
					if (data == "0") {
						alert("用户名或者密码错误");
					}  else {
						window.location.href = "user/login";
					}
				})
			} else {
				alert("请输入密码");
			}
		} else {
			alert("请输入用户名");
		}
	})
	//跳转到用户注册页面
	$("#toRegister").click(function (){
		window.location.href = "user/toRegister";
		return false;
	})
})