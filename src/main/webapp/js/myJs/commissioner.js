var renderTable;

layui.config({
    base: '../js/'
}).use(['treeTable','jquery','element','form'], function () {
    var treeTable = layui.treeTable;
	var $ = layui.jquery;
	var element = layui.element;
	var form = layui.form;
	
	form.on('submit(editSubmitBtn)', function(data) {
		editQd();
	});
	
	form.on('submit(updateProject)', function(data) {
		editSubmit();
	});
	
	
	//负责人下拉表框渲染  
		$(function () {
		    $.ajax({
		        type: "post",
		        url: '/pmanager/project/queryBydeptId',
		        dataType: "json",
		        success:function (data) {
					var item = data;
					var str ='';
					if(item!=''){
					 	for(var i = 0;i<item.length;i++)
						{
						str += "<option  value = '"+item[i].userName+"'>"+item[i].userName+"</option>";	
						}
					}
					$("#worker").append(str);
		            layui.form.render("select");
		        },
		     error:function () {
		            alert("拉取数据失败")
				}
			})
		});
	
	 renderTable = treeTable.render({
				elem: '#itemTable',
				url: '/pmanager/item/query',
	            tree: {
					iconIndex: 2,
					isPidData: true,
					idName: 'itemId',
					pidName: 'parentId',
					arrowType: 'arrow2',   // 自定义箭头风格
					getIcon: function(d) {  // 自定义图标
					            // d是当前行的数据
					            if (d.haveChild) {  // 判断是否有子集
					                return '<i class="ew-tree-icon ew-tree-icon-folder"></i>';
					            } else {
					                return '<i class="ew-tree-icon ew-tree-icon-file"></i>';
					            }
					        }
				},
	            cols: [ [
	                {type:'numbers',title: '序号',width: '60px'},
					{field: 'itemId' , hide : true, title: '项目id',align: 'center'},
	                {field: 'itemName', title: '项目/工程名称'},
	                {field: 'worker' , title: '项目负责人',align: 'center'},
	                {field: 'userName', title: '创建人',align: 'center'},
	                {field: 'idx', title: '操作',toolbar: '#itemToolBar'}
	            ] ]
	        });
			
			
			//监听table中的工具条
			treeTable.on('tool(itemTable)', function(obj) {
				var data = obj.data; //获取当行的数据
				var layEvent = obj.event; //获取当行的lay-event的值
				var tr = obj.tr;
				if (layEvent === 'addProject') { //如果按的是编辑，将数据传递过去
					addProject(data);
				} else if (layEvent === 'editItem') {
					showEditForm(data);   		//编辑项目
				} else if (layEvent === 'delItem') {
					delItem(data); 
				} else if (layEvent === 'editProject') {
					editProject(data);
				}
			});
});




var layerIndex; //用来保存弹窗索引

function showEditForm(data) {
	var title = data ? "编辑项目" : "新增项目";  //这里依据obj是否存在来判断是编辑还是新建

	layerIndex = layui.layer.open({
		type: 1,
		skin: "sys-pop-skin",
		title: title,
		area: ['400px', '300px'], //设定弹出框的大小
		offset: 'auto',
		content: $('#addForm'), //主体内容
		success: function(layro, index) {
			if (data) {
				layui.form.val("edit-form", data); //如果是编辑，就本行数据进行回充
			} else {
				layui.form.val("edit-form", {
					itemId: '',
					itemName: '',
					worker:''
				});
				
			}
		}
	});
}

var parentId;  //父项目id
var worker;    //项目负责人
function addProject(data) {
	var title = (data.worker == null) ? "编辑工程":"新增工程";
	//如果是新增，就需要获取到父项目id以及他的负责人
	if(data.parentId === '0'){
		parentId = data.itemId;
		worker = data.worker
	}
	layerIndex = layui.layer.open({
		type: 1,
		skin: "sys-pop-skin",
		title: title,
		area: ['400px', '300px'], //设定弹出框的大小
		offset: 'auto',
		content: $('#editForm'), //主体内容
		success: function(layro, index) {
			if (data.worker == null) {   
				layui.form.val("project-form", data); 
			} else {
				layui.form.val("project-form", {
					itemId: '',
					itemName: ''
				});
				
			}
		}
	});
}


// 修改提交更新数据,这里需要根据不同的操作来定位不同的后台地址
function editQd() {
	
	var data = layui.form.val('edit-form');
	var worker = data.worker;
	var url = "";
	if(data.itemId === ''){
		url = "/pmanager/item/create";
	}else{
		url = "/pmanager/item/edit"
	}

	var word = data.itemId === '' ? '新建项目' : '编辑项目';
	 	$.ajax({
	                 url: url,
	                 type: "POST",
	                 dataType: "json",
	 			  	 data:{"itemId": data.itemId,"itemName": data.itemName,"worker":data.worker},
	                 success: function (msg) {
	                     if (msg.code === 0) 
						 {
	                         layer.msg(word+"成功");
	                         renderTable.reload({
	                         	    elem:'#itemTable'
	                         	});
	                     } 
						 if(msg.code === 2)
						 {
							 layer.msg(msg.msg, {icon: 5});
						 }
						 if(msg.code === 1)
						 {
	                         layer.msg(word+'失败', {icon: 5});
	                     }
	                 },
	                 error: function (error) {
	                     layer.msg('系统异常！请联系管理员', {icon: 5});
	                 }
	             });
	layui.layer.close(layerIndex);
}


function editSubmit() {
	
	var data = layui.form.val('project-form');
	var url = '';
	if(data.itemId === ''){
		url = "/pmanager/project/add";
	}else{
		url = "/pmanager/project/edit"
	}
	var word = data.itemId === '' ? '新建工程' : '编辑工程';
	var json = '';
	//如果是新建，需要传递parentId,worker,itemName
	if(data.itemId === ''){
		json = {"parentId":parentId,"worker": worker,"itemName": data.itemName};
	}else{
		json = {"itemId": data.itemId,"itemName": data.itemName};
	}
	 	$.ajax({
	                 url: url,
	                 type: "POST",
	                 dataType: "json",
	 			  	 data: json,
	                 success: function (msg) {
	                     if (msg.code === 0) {
	                         layer.msg(word+'成功');
	                         renderTable.reload({
	                         	    elem:'#itemTable'
	                         	});
	                     } 
						 if(msg.code === 2)
						 {
						 	layer.msg(msg.msg, {icon: 5});
						 }
						 if(msg.code === 1)
						 {
						    layer.msg(word+'失败', {icon: 5});
						 }
	                },
	                 error: function (error) {
	                     layer.msg('系统异常！请联系管理员', {icon: 5});
	                 }
	            });
	layui.layer.close(layerIndex);
}

//取消修改
function editQx() {
	layui.layer.close(layerIndex);
}

// 删除
function delItem(data) {
	var itemId = data.itemId;
	var url = '';
	var word = '';
	if(data.parentId === '0'){
		url = '/pmanager/item/delete';
		word = '是否删除该项目及其子工程？'
	}else{
		url = '/pmanager/project/delete';
		word = "是否删除该工程？"
	}
	debugger
	layui.layer.confirm(word, {
		icon: 3,
		title: '提示信息'
	}, function(index) {
		 	$.ajax({
		                 url: url,
		                 type: "POST",
		                 dataType: "json",
		 				 data: {"itemId": itemId},
		                 success: function (msg) {
		                     if (msg.code === 0) {
		                         layer.msg('删除成功');
	                         renderTable.reload({
	                         	    elem:'#itemTable'
	                         	});
		                     } else {
		                         layer.msg('删除失败', {icon: 5});
		                     }
		                 },
		                 error: function (error) {
		                    layer.msg('系统异常！请联系管理员', {icon: 5});
						}
				});
		});
}





//点击查询 --done
function doSearch() {
	var data = layui.form.val('itemForm');
	layui.treeTable.reload('itemTable', {
	                 where: {
						   name: data.name
	                 }
	             });
}