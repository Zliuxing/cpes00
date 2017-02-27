<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="UTF-8">
  <head>
    <%@include file="/WEB-INF/jsp/common/head.jsp" %>
    <link rel="stylesheet" href="${APP_PATH}/css/main.css">
    <link rel="stylesheet" href="${APP_PATH}/ztree/zTreeStyle.css">
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
				<h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 许可数据</h3>
			  </div>
			  <div class="panel-body">
                 <ul id="permissionTree" class="ztree"></ul>
			  </div>
			</div>
        </div>
      </div>
    </div>

    <%@include file="/WEB-INF/jsp/common/script.jsp" %>
    <script src="${APP_PATH}/ztree/jquery.ztree.all-3.5.min.js"></script>
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
			  /*
			// 准备树形结构的数据
			var setting = {	};
	
			var zNodes =[
				{ name:"123123父节点1 - 展开",
					children: [
						{ name:"父节点11 - 折叠",
							children: [
								{ name:"叶子节点111"},
								{ name:"叶子节点112"},
								{ name:"叶子节点113"},
								{ name:"叶子节点114"}
							]},
						{ name:"父节点12 - 折叠",
							children: [
								{ name:"叶子节点121"},
								{ name:"叶子节点122"},
								{ name:"叶子节点123"},
								{ name:"叶子节点124"}
							]},
						{ name:"父节点13 - 没有子节点", isParent:true}
					]},
				{ name:"父节点2 - 折叠",
					children: [
						{ name:"父节点21 - 展开", open:true,
							children: [
								{ name:"叶子节点211"},
								{ name:"叶子节点212"},
								{ name:"叶子节点213"},
								{ name:"叶子节点214"}
							]},
						{ name:"父节点22 - 折叠",
							children: [
								{ name:"叶子节点221"},
								{ name:"叶子节点222"},
								{ name:"叶子节点223"},
								{ name:"叶子节点224"}
							]},
						{ name:"父节点23 - 折叠",
							children: [
								{ name:"叶子节点231"},
								{ name:"叶子节点232"},
								{ name:"叶子节点233"},
								{ name:"叶子节点234"}
							]}
					]},
				{ name:"父节点3 - 没有子节点", isParent:true}
	
			];
			
			// 初始化树形结构
			$.fn.zTree.init($("#permissionTree"), setting, zNodes);
			*/
			/*
			$.ajax({
				url : "${APP_PATH}/permission/loadMenus.do",
				type : "POST",
				success : function(r) {
					if ( r.success ) {
						var menus = r.menus;
						var setting = {};
						$.fn.zTree.init($("#permissionTree"), setting, menus);
					}
				}
			});
			*/
			
			// 使用ztree的异步方式读取数据
		    var setting = {
			    view: {
			        selectedMulti: false,
					addDiyDom: function(treeId, treeNode){
						var icoObj = $("#" + treeNode.tId + "_ico");
						if ( treeNode.icon ) {
							icoObj.removeClass("button ico_docu ico_open").addClass("fa fa-fw " + treeNode.icon).css("background","");
						}
					},
					addHoverDom: function(treeId, treeNode){
						var aObj = $("#" + treeNode.tId + "_a");
						aObj.attr("href", "javascript:;");
						if (treeNode.editNameFlag || $("#btnGroup"+treeNode.tId).length>0) return;
						var s = '<span id="btnGroup'+treeNode.tId+'">';
						if ( treeNode.level == 0 ) {
							s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" href="#" onclick="window.location.href=\'${APP_PATH}/permission/add.htm?pid='+treeNode.id+'&level='+treeNode.level+'\'" >&nbsp;&nbsp;<i class="fa fa-fw fa-plus rbg "></i></a>';
						} else if ( treeNode.level == 1 ) {
							s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;"  href="#" onclick="window.location.href=\'${APP_PATH}/permission/edit.htm?id='+treeNode.id+'\'" title="修改权限信息">&nbsp;&nbsp;<i class="fa fa-fw fa-edit rbg "></i></a>';
							if (treeNode.children.length == 0) {
								s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" href="#" onclick="deletePermission('+treeNode.id+')" >&nbsp;&nbsp;<i class="fa fa-fw fa-times rbg "></i></a>';
							}
							s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" href="#" onclick="window.location.href=\'${APP_PATH}/permission/add.htm?pid='+treeNode.id+'&level='+treeNode.level+'\'" >&nbsp;&nbsp;<i class="fa fa-fw fa-plus rbg "></i></a>';
						} else if ( treeNode.level == 2 ) {
							s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;"  href="#" onclick="window.location.href=\'${APP_PATH}/permission/edit.htm?id='+treeNode.id+'\'" title="修改权限信息">&nbsp;&nbsp;<i class="fa fa-fw fa-edit rbg "></i></a>';
							s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" href="#" onclick="deletePermission('+treeNode.id+')">&nbsp;&nbsp;<i class="fa fa-fw fa-times rbg "></i></a>';
						}
		
						s += '</span>';
						aObj.after(s);
					},
					removeHoverDom: function(treeId, treeNode){
						$("#btnGroup"+treeNode.tId).remove();
					}
			    },
			    async: {
			        enable: true,
			        url:"${APP_PATH}/permission/loadPermissions.do",
			        autoParam:["id", "name=n", "level=lv"]
			    },
			    callback: {
			        onClick : function(event, treeId, json) {

			        }
			    }
			};
			$.fn.zTree.init($("#permissionTree"), setting);

        });
        
        function deletePermission(id) {
			layer.confirm("删除许可信息,是否继续？",  {icon: 3, title:'提示'}, function(cindex){
				
				// 删除许可信息
				$.ajax({
					url : "${APP_PATH}/permission/delete.do",
					type : "POST",
					data : {
						id : id
					},
					success : function(r) {
						if ( r.success ) {
							layer.msg("许可信息删除成功", {time:2000, icon:6}, function(){
								var treeObj = $.fn.zTree.getZTreeObj("permissionTree");
								treeObj.reAsyncChildNodes(null, "refresh");
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
