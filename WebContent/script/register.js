$(function () {
	$("button[name='submit']").click(function (){
		//获取注册信息
		var username = $("input[name='username']").val().trim();
		var password = $("input[name='password'").val().trim();
		var gender = $("input[name='gender'").val();
		var age = $("input[name='age']").val().trim();
		var city = $("select[name='city']").val();
		var hobbies = new Array();
		$("input[name='hobby']:checked").each(function () {
			hobbies.push($(this).val());
		});
		var introduction = $("textarea[name='introduction']").val();
		//传递数组时传递参数的名字用 *[],后台获取时也用 *[] 的名字获取
		if (username != "") {
			if (password != "") {
				if (age != "") {
					var args = {
							username : username,
							password : password,
							gender : gender,
							age : age,
							city : city,
							"hobbies[]" : hobbies,
							introduction : introduction
					}
					$.post("register", args, function (data) {
						if (data == "1") {
							//条用 blockUI 插件，实现动态效果，信息持续5秒
							$.blockUI({ message: '<h4>注册成功，将在5秒后跳转到聊天室！</h3>' });
							setTimeout($.unblockUI, 5000);
							//5秒后跳转
							setTimeout("window.location.href='login'", 5000);
						}
					})
				} else {
					alert("年龄不得为空");
				}
			} else {
				alert("密码不得为空!");
			}
		} else {
			alert("用户名不得为空！");
		}
		return false;
	})
})