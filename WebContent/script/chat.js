$(function () {
	var username;
	//默认为刷新消息内容操作
	var url = "update";
	//登陆信息
	$.get("getUserInfo", {"time" : new Date()}, function (data) {
		username = data.username;
		var welcome = "欢迎登陆," + username + "&nbsp;&nbsp;<a href='#' id='quit'><strong>退出</strong></a>";
		$("#welcome").html(welcome);
		//将退出操作绑定到标签上
		$("body").on("click", "#quit", function () {
			window.location.href="quit";
			return false;
		})
	}, "json")
	
	//向后台传输数据
	function sendInfo (message) {
		$.get(url, message, function (data) {
			$("#messageContents").find("div").remove();
			for (var i = $("#messageContents").find("div").length; i < data.length; i++) {
				//通过时间戳获取消息记录的时间
				var date = new Date(data[i].date);
				var dateStr = date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate() + " " + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
				if (data[i].username == username) {
					//若是本用户的消息记录则放在左侧
					var node = "<div class='alert alert-info' role='alert'><p class='text-left'><strong>" + username + "</strong><br><small>" + dateStr + "</small><br>" + data[i].content + "</p></div>";
					$("#messageContents").append(node);
				} else {
					//若是其他用户的消息记录则放在右侧
					var node = "<div class='alert alert-info' role='alert'><p class='text-right'><strong>" + data[i].username + "</strong><br><small>" + dateStr + "</small><br>" + data[i].content + "</p></div>";
					$("#messageContents").append(node);
				}
			}
		})
	}
	
	//更新聊天内容
	function getMessageContents () {
		//获取要发送的内容
		var content = $("#content").html();
		var message;
		//url 为 chat 则新建 JSON 型的 message 数据；为 update 则只传个时间戳
		if (url == "chat") {
			if (content != "") {
				message = {username : username, content : content, date : new Date()};
				sendInfo(message);
			} else {
				alert("发送内容不得为空");
			}
		} else {
			message = {date : new Date()};
			sendInfo(message);
		}
	}
	
	//输入聊天内容后局部刷新聊天内容
	$("#send").click(function () {
		url = "chat";
		getMessageContents();
		url = "update";
	})
	//定时刷新消息内容
	window.setInterval(getMessageContents, 1000);
	
	//定时刷新在线人员信息
	window.setInterval(function() {
		$.get("updateOnlineUserInfo", function (data) {
			$("#onlineUserInfo").find("div").remove();
			for (var i = 0; i < data.length; i++) {
				$("#onlineUserInfo").append("<div class='text-success'>" + data[i] + "</div>");
			}
		})
	}, 1000)
})