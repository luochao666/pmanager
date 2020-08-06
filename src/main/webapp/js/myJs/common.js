layui.use(['table', 'form', 'jquery', 'layer'], function() {
	var table = layui.table;
	var form = layui.form;
	var $ = layui.jquery;
	var layer = layui.layer;

	$('#logout').click(doLogout);
});


function doLogout() {
	
	$.ajax({
	            url: '/pmanager/project/logout',
	            type: "POST",
	            success: function (msg) {
					var url = "";
					if (msg.code === 0) {
                    url = "/pmanager/index.html";
                    layer.msg('注销成功，正在跳转......', {icon: 6});
                    setTimeout(function () {
                        window.location.href = url;
                    }, 2000);
	                 } else {
	                     layer.msg('注销失败', {icon: 5});
	                 }
	            },
	             error: function (error) {
	                 layer.msg('系统异常！请联系管理员', {icon: 5});
	             }
	        });
}
