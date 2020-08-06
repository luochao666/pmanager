layui.use(['element','form'],function(){
	var element = layui.element;
	var form = layui.form;
	
	form.verify({
		loginVerify: [
			/[a-zA-Z0-9]/,'账号中只能出现数字和英文'
		]
		});
	
    //监听登陆提交
    form.on('submit(login)', function (data) {
		// // 开发时的js
  //       $.ajax({
  //           type: 'POST',
  //           url: "login/login",
  //           data: {"username": $("#username").val(), "password": $("#password").val()},
  //           success: function (msg) { // 这里返回一个json 数据
  //               var url = "";
  //               if (msg === 1) {
  //                   url = "userManager";
  //                   layer.msg('登陆成功，正在跳转......', {icon: 6});
  //                   setTimeout(function () {
  //                       window.location.href = url;
  //                   }, 2000);
  //               } else {
  //                   layer.msg('账号或者密码错误', {icon: 5});
  //               }
  //           },
  //       });

        // return false;
		var loginName = $("#loginName").val();
		var pwd = $("#pwd").val();
		debugger
		layer.msg('账号是：'+loginName+'    密码是：'+pwd,{icon: 6});
		setTimeout(function () {
		                window.location.href = 'userManager.html';
		             }, 2000);
		return false;
    });



});

