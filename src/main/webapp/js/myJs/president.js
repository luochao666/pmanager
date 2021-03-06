layui.config({
    base: '../js/'
}).use(['treeTable','jquery','element','form'], function () {
    var treeTable = layui.treeTable;
	var $ = layui.jquery;
	var element = layui.element;
	var form = layui.form;
	
	
	//负责人下拉表框渲染
		$(function () {
		    $.ajax({
		        type: "post",
		        url: '/pmanager/dept/getList',
		        dataType: "json",
		        success:function (data) {
					var item = data;
					var str ='';
					if(item!=''){
					 	for(var i = 0;i<item.length;i++)
						{
						str += "<option  value = '"+item[i].deptId+"'>"+item[i].deptName+"</option>";	
						}
					}
					$("#deptId").append(str);
		            layui.form.render("select");
		        },
		     error:function () {
		            alert("拉取数据失败")
				}
			})
		});
	
	
	var renderTable = treeTable.render({
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
	            ] ]
	        });
});


//点击查询 --done
function doSearch() {
	var data = layui.form.val('itemForm');
	layui.treeTable.reload('itemTable', {
	                 where: {
						   name: data.name,
						   deptId: data.deptId
	                 }
	             });
}