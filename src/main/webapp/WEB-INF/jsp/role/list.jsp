<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="UTF-8">
  <head>
    <%@include file="/WEB-INF/jsp/common/head.jsp" %>
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

    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
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
					  <ul class="dropdown-menu" role="menu">
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
				    <c:forEach items="${smenus}" var="menu" varStatus="status">
					<li class="list-group-item tree-closed" >
					    <c:if test="${empty menu.children}">
					    	<a href="main.html"><i class="fa ${menu.icon} fa-fw"></i> ${menu.name}</a> 
					    </c:if>
					    <c:if test="${not empty menu.children}">
							<span><i class="fa ${menu.icon} fa-fw"></i> ${menu.name} <span class="badge" style="float:right">${fn:length(menu.children)}</span></span> 
							<ul style="margin-top:10px;display:none;">
								<c:forEach items="${menu.children}" var="childmenu">
								<li style="height:30px;">
									<a href="${APP_PATH}/${childmenu.url}"><i class="fa ${childmenu.icon} fa-fw"></i> ${childmenu.name}</a> 
								</li>
								</c:forEach>
							</ul>
					    </c:if>
					</li>
				    </c:forEach>
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
<button type="button" class="btn btn-primary" style="float:right;" onclick="window.location.href='${APP_PATH}/role/add.htm'"><i class="glyphicon glyphicon-plus"></i> 新增</button>
<br>
 <hr style="clear:both;">
          <div class="table-responsive">
            <table class="table  table-bordered">
              <thead>
                <tr >
                  <th width="30">#</th>
				  <th width="30"><input type="checkbox" ></th>
                  <th>名称</th>
                  <th width="100">操作</th>
                </tr>
              </thead>
              <tbody>
              </tbody>
			  <tfoot>
			     <tr >
				     <td id="pageTd" colspan="4" align="center">
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

    <%@include file="/WEB-INF/jsp/common/script.jsp" %>
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
			  
			//alert("2" !== 2);
			  
			queryRole(0);
			
			// 给全选框增加点击事件
			$(".table thead :checkbox").click(function(){
				//var flg = this.checked;
				var that = this;
				$(".table tbody :checkbox").each(function(i, cb){
					cb.checked = that.checked;
				});
			});
			
			$("#batDeleteBtn").click(function(){
				
				// 获取选中的角色复选框的数量
				var roles = $(".table tbody input:checked");
				if ( roles.length > 0 ) {
					layer.confirm("删除选择的角色信息,是否继续？",  {icon: 3, title:'提示'}, function(cindex){
						// 多条数据的传递，可以采用对象加索引的方式
						
						var dataObj = {};
						$.each(roles, function(i, n){
							dataObj["roles["+i+"].id"] = n.value;
						});
						
						// 删除角色信息
						$.ajax({
							url : "${APP_PATH}/role/deletes.do",
							type : "POST",
							data : dataObj,
							success : function(r) {
								if ( r.success ) {
									layer.msg("选择的角色信息删除成功", {time:2000, icon:6}, function(){
										queryRole(0);
									});
								} else {
									layer.msg("选择的角色信息删除失败", {time:2000, icon:5});
								}
							}
						});
						
						layer.close(cindex);
					}, function(cindex){
					    layer.close(cindex);
					});
				} else {
					layer.msg("请选择需要删除的角色信息", {time:2000, icon:5, shift:6});
				}
			});
        });
        var pagesize = 10;
        // 分页查询角色信息
        function queryRole(pageindex) {
        	var paramObj = {
       			"pageno" : pageindex+1,
       			"pagesize" : pagesize
       		};
        	
        	// 使用AJAX异步查询角色信息
        	$.ajax({
        		url : "${APP_PATH}/role/queryPageRoles.do",
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
        				$.each(datas, function(index, role){
        					// i ==> index
        					// n ==> object ==> user
        	                content += '<tr>';
	      	                content += '  <td>'+(index+1)+'</td>';
	      					content += '  <td><input type="checkbox" value="'+role.id+'"></td>';
	      	                content += '  <td>'+role.name+'</td>';
	      	                content += '  <td>';
	      					content += '      <button type="button" class="btn btn-success btn-xs" onclick="window.location.href=\'${APP_PATH}/role/assign.htm?id='+role.id+'\'"><i class="glyphicon glyphicon-check"></i></button>';
	      					content += '      <button type="button" onclick="window.location.href=\'${APP_PATH}/role/edit/'+role.id+'.htm\'" class="btn btn-primary btn-xs"><i class=" glyphicon glyphicon-pencil"></i></button>';
	      					content += '	  <button type="button" onclick="deleteRole('+role.id+', \''+role.name+'\')" class="btn btn-danger btn-xs"><i class=" glyphicon glyphicon-remove"></i></button>';
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
        					callback: queryRole, // 当点击页码链接时，会执行回调方法，执行查询操作
        					items_per_page:pagesize //每页显示1项
        				});
        				
        			} else {
        				$(".table tbody").html("<tr><td colspan='4' style='color:red'>数据查询失败</td></tr>");
        			}
        		},
        		error : function(){
        			
        		}
        		
        	});
        }
        function deleteRole(id, name) {
			layer.confirm("删除角色["+name+"]信息,是否继续？",  {icon: 3, title:'提示'}, function(cindex){
				
				// 删除角色信息
				$.ajax({
					url : "${APP_PATH}/role/delete.do",
					type : "POST",
					data : {
						id : id
					},
					success : function(r) {
						if ( r.success ) {
							layer.msg("角色["+name+"]信息删除成功", {time:2000, icon:6}, function(){
								queryRole(0);
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
