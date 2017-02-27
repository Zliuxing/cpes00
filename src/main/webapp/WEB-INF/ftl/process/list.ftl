<!DOCTYPE html>
<html lang="UTF-8">
  <head>
    <#include "common/head.ftl">
    <link rel="stylesheet" href="${APP_PATH}/css/main.css">
	<link rel="stylesheet" href="${APP_PATH}/css/pagination.css">
	<style>
	.tree li {
        list-style-type: none;
		cursor:pointer;
	}
	table tbody tr:nth-child(odd){background:#F4F4F4;}
	table tbody td:nth-child(even){color:#C00;}
	</style>
  </head>

  <body>

    <nav class="navbar navbar-inverse navbar-fixed-top" cert="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
          <div><img src="${APP_PATH}/img/logo.png" width="100" style="float:left;margin-top:5px;"><a class="navbar-brand" style="font-size:32px;" href="#">认证流程审批系统</a></div>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li style="padding-top:8px;">
				<div class="btn-group">
				  <button type="button" class="btn btn-default btn-success dropdown-toggle" data-toggle="dropdown">
					<i class="glyphicon glyphicon-user"></i> ${suser.username} <span class="caret"></span>
				  </button>
					  <ul class="dropdown-menu" cert="menu">
						<li><a href="#"><i class="glyphicon glyphicon-cog"></i> 个人设置</a></li>
						<li><a href="#"><i class="glyphicon glyphicon-comment"></i> 消息</a></li>
						<li class="divider"></li>
						<li><a href="login.html"><i class="glyphicon glyphicon-off"></i> 退出系统</a></li>
					  </ul>
			    </div>
			</li>
            <li style="margin-left:10px;padding-top:8px;">
				<button type="button" class="btn btn-default btn-danger">
				  <span class="glyphicon glyphicon-question-sign"></span> 帮助
				</button>
			</li>
          </ul>
          <form class="navbar-form navbar-right">
            <input type="text" class="form-control" placeholder="Search...">
          </form>
        </div>
      </div>
    </nav>

    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
			<div class="tree">
				<ul style="padding-left:0px;" class="list-group">
				    <#list smenus as menu>
					<li class="list-group-item tree-closed" >
					    <#if menu.children?size == 0>
					        <a href="main.html"><i class="fa ${menu.icon} fa-fw"></i> ${menu.name}</a> 
					    <#else>
							<span><i class="fa ${menu.icon} fa-fw"></i> ${menu.name} <span class="badge" style="float:right">${menu.children?size}</span></span> 
							<ul style="margin-top:10px;display:none;">
							    <#list menu.children as childmenu>
									<li style="height:30px;">
										<a href="${APP_PATH}/${childmenu.url}"><i class="fa ${childmenu.icon} fa-fw"></i> ${childmenu.name}</a> 
									</li>
							    </#list>
							</ul>
					    </#if>
					</li>
				    </#list>
				</ul>
			</div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<div class="panel panel-default">
			  <div class="panel-heading">
				<h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
			  </div>
			  <div class="panel-body">

<button type="button" id="batDeleteBtn" class="btn btn-danger" style="float:right;margin-left:10px;"><i class=" glyphicon glyphicon-remove"></i> 删除</button>
<button type="button" id="uploadBtn" class="btn btn-primary" style="float:right;" ><i class="glyphicon glyphicon-open"></i> 加载流程文件</button>
<br>
 <hr style="clear:both;">
          <div class="table-responsive">
            <table class="table  table-bordered">
              <thead>
                <tr >
                  <th width="30">#</th>
				  <th width="30"><input type="checkbox" ></th>
                  <th>Key</th>
                  <th>名称</th>
                  <th>版本号</th>
                  <th width="100">操作</th>
                </tr>
              </thead>
              <tbody>
              </tbody>
			  <tfoot>
			     <tr >
				     <td id="pageTd" colspan="6" align="center">
				         <div id="Pagination" class="pagination" />
					 </td>
					 
				 </tr>
			  </tfoot>
            </table>
          </div>
			  </div>
			</div>
        </div>
      </div>
    </div>
    <form method="post" enctype="multipart/form-data" id="fileForm" style="display:none;" action="${APP_PATH}/process/uploadProcDefFile.do">
        <input type="file" name="procDefFile">
    </form>
    <#include "common/script.ftl" >
    <script src="${APP_PATH}/script/docs.min.js"></script>
	<script src="${APP_PATH}/jquery/jquery.pagination.js"></script>
	<script src="${APP_PATH}/js/layer/layer.js"></script>
    <script type="text/javascript">
        $(function () {
		  $(".list-group-item").click(function(){
			    if ( $(this).find("ul") ) {
					$(this).toggleClass("tree-closed");
					if ( $(this).hasClass("tree-closed") ) {
						$("ul", this).hide("fast");
					} else {
						$("ul", this).show("fast");
					}
				}
		   });
		   
		   // 点击上传按钮等同于点击文件域表单元素
		   $("#uploadBtn").click(function(){
		       $("#fileForm :file").click();
		   });
		   
		   $("#fileForm :file").change(function(event){
		   
		       var files = event.target.files, file;
		       if (files && files.length > 0) {
		           file = files[0];
		       }
		   
		       // 判断文件大小
		       var size = file.size;
		       // 2M = 2 * 1024kb = 2 * 1024 * 1024b
		       // 20M / 8 = 2.5;
		       if ( size > 2 * 1024 * 1024  ) {
		           layer.msg("上传的文件不能大于2M", {time:2000, icon:5, shift:6});
		           return;
		       }
		   
		       // 提交文件上传表单
		       var loadingIndex = -1;
		       
		    	$("#fileForm").ajaxSubmit({
		    		beforeSubmit : function(){
		    		    loadingIndex = layer.load(2, {time: 10*1000});
		    			return true;
		    		},
		    		success : function(r) {
		    		    layer.close(loadingIndex);
		    			if ( r.success ) {
			    			layer.msg("流程定义部署成功", {time:2000, icon:6}, function(){
			    			     queryProcess(0);
			    			     $("#fileForm")[0].reset();
			    			});
		    			} else {
				    		layer.msg("流程定义信息保存失败", {time:2000, icon:5, shift:6});
		    			}
		    		}
		    	});
		   });
			  
			//alert("2" !== 2);
			  
			queryProcess(0);
			
			// 给全选框增加点击事件
			$(".table thead :checkbox").click(function(){
				//var flg = this.checked;
				var that = this;
				$(".table tbody :checkbox").each(function(i, cb){
					cb.checked = that.checked;
				});
			});
			
			$("#batDeleteBtn").click(function(){
				
				// 获取选中的流程定义复选框的数量
				var certs = $(".table tbody input:checked");
				if ( certs.length > 0 ) {
					layer.confirm("删除选择的流程定义信息,是否继续？",  {icon: 3, title:'提示'}, function(cindex){
						// 多条数据的传递，可以采用对象加索引的方式
						
						var dataObj = {};
						$.each(certs, function(i, n){
							dataObj["certs["+i+"].id"] = n.value;
						});
						
						// 删除流程定义信息
						$.ajax({
							url : "${APP_PATH}/cert/deletes.do",
							type : "POST",
							data : dataObj,
							success : function(r) {
								if ( r.success ) {
									layer.msg("选择的流程定义信息删除成功", {time:2000, icon:6}, function(){
										queryProcess(0);
									});
								} else {
									layer.msg("选择的流程定义信息删除失败", {time:2000, icon:5});
								}
							}
						});
						
						layer.close(cindex);
					}, function(cindex){
					    layer.close(cindex);
					});
				} else {
					layer.msg("请选择需要删除的流程定义信息", {time:2000, icon:5, shift:6});
				}
			});
        });
        var pagesize = 10;
        // 分页查询流程定义信息
        function queryProcess(pageindex) {
        	var paramObj = {
       			"pageno" : pageindex+1,
       			"pagesize" : pagesize
       		};
        	
        	// 使用AJAX异步查询流程定义信息
        	$.ajax({
        		url : "${APP_PATH}/process/queryPageProcesss.do",
        		type : "POST",
        		data : paramObj,
        		beforeSend : function() {
        			$(".table tbody").html("<tr><td colspan='7'>数据查询中，请稍候。。。</td></tr>");
        		},
        		success : function(r) {
        			// 渲染页面
        			if ( r.success ) {
        				// 获取分页对象
        				var page = r.datapage;
        				var datas = page.datas;
        				
        				var content = '';
        				// jquery中循环
        				//$("tr").each(function（i， n）{});
        				$.each(datas, function(index, pd){
        					// i ==> index
        					// n ==> object ==> user
        	                content += '<tr>';
	      	                content += '  <td>'+(index+1)+'</td>';
	      					content += '  <td><input type="checkbox" value="'+pd.id+'"></td>';
	      	                content += '  <td>'+pd.key+'</td>';
	      	                content += '  <td>'+pd.name+'</td>';
	      	                content += '  <td>'+pd.version+'</td>';
	      	                content += '  <td>';
	      					content += '      <button type="button" class="btn btn-success btn-xs" onclick="window.location.href=\'${APP_PATH}/process/showimg.htm?id='+pd.id+'\'"><i class="glyphicon glyphicon-check"></i></button>';
	      					content += '	  <button type="button" onclick="deleteProcess('+pd.deployid+', \''+pd.name+'\')" class="btn btn-danger btn-xs"><i class=" glyphicon glyphicon-remove"></i></button>';
	      					content += '  </td>';
	      	                content += '</tr>';
        				});
        				
        				$(".table tbody").html(content);
        				
        				$("#Pagination").pagination(page.totalsize, {
        					num_edge_entries: 1, //边缘页数
        					num_display_entries: 4, //主体页数
        					current_page:pageindex,
        					prev_text:"上一页",
        					next_text:"下一页",
        					ellipse_text:"...",
        					callback: queryProcess, // 当点击页码链接时，会执行回调方法，执行查询操作
        					items_per_page:pagesize //每页显示1项
        				});
        				
        			} else {
        				$(".table tbody").html("<tr><td colspan='6' style='color:red'>数据查询失败</td></tr>");
        			}
        		},
        		error : function(){
        			
        		}
        		
        	});
        }
        function deleteProcess(id, name) {
			layer.confirm("删除流程定义["+name+"]信息,是否继续？",  {icon: 3, title:'提示'}, function(cindex){
				
				// 删除流程定义信息
				$.ajax({
					url : "${APP_PATH}/process/delete.do",
					type : "POST",
					data : {
						deploymentId : id
					},
					success : function(r) {
						if ( r.success ) {
							layer.msg("流程定义["+name+"]信息删除成功", {time:2000, icon:6}, function(){
								queryProcess(0);
							});
						}
					}
				});
				
				layer.close(cindex);
			}, function(cindex){
			    layer.close(cindex);
			});
        }
    </script>
  </body>
</html>
