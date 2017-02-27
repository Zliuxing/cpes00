<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="UTF-8">
  <head>
    <%@include file="/WEB-INF/jsp/common/head.jsp" %>
	<link rel="stylesheet" href="${APP_PATH}/css/main.css">
	<link rel="stylesheet" href="${APP_PATH}/css/doc.min.css">
	<style>
	.tree li {
        list-style-type: none;
		cursor:pointer;
	}
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
				<ol class="breadcrumb">
				  <li><a href="#">首页</a></li>
				  <li><a href="#">数据列表</a></li>
				  <li class="active">修改</li>
				</ol>
			<div class="panel panel-default">
              <div class="panel-heading">表单数据<div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i class="glyphicon glyphicon-question-sign"></i></div></div>
			  <div class="panel-body">
				<form id="userForm" role="form" action="${APP_PATH}/user/update.do" method="post">
				  <input type="hidden" name="id" value="${user.id}">
				  <div class="form-group">
					<label for="exampleInputPassword1">登陆账号</label>
					<input type="text" class="form-control" id="floginacct" value="${user.loginacct}" name="loginacct" placeholder="登陆账号">
				  </div>
				  <div class="form-group">
					<label for="exampleInputPassword1">用户名称</label>
					<input type="text" class="form-control" id="fusername" value="${user.username}" name="username" placeholder="用户名称">
				  </div>
				  <div class="form-group">
					<label for="exampleInputEmail1">邮箱地址</label>
					<input type="text" class="form-control" id="femail" name="email" value="${user.email}" placeholder="邮箱地址">
					<p class="help-block label label-warning">请输入合法的邮箱地址, 格式为： xxxx@xxxx.com</p>
				  </div>
				  <div class="form-group">
					<label for="exampleInputEmail1">性别</label>
					<select id="fsex" class="form-control" name="sex" >
					    <option value="0">女</option>
					    <option value="1">男</option>
					</select>
				  </div>
				  <button type="button" id="updateBtn" class="btn btn-success"><i class="glyphicon glyphicon-pencil"></i> 修改</button>
				  <button type="button" class="btn btn-danger"><i class="glyphicon glyphicon-refresh"></i> 重置</button>
				</form>
			  </div>
			</div>
        </div>
      </div>
    </div>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
		<div class="modal-content">
		  <div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
			<h4 class="modal-title" id="myModalLabel">帮助</h4>
		  </div>
		  <div class="modal-body">
			<div class="bs-callout bs-callout-info">
				<h4>没有默认类</h4>
				<p>警告框没有默认类，只有基类和修饰类。默认的灰色警告框并没有多少意义。所以您要使用一种有意义的警告类。目前提供了成功、消息、警告或危险。</p>
			  </div>
			<div class="bs-callout bs-callout-info">
				<h4>没有默认类</h4>
				<p>警告框没有默认类，只有基类和修饰类。默认的灰色警告框并没有多少意义。所以您要使用一种有意义的警告类。目前提供了成功、消息、警告或危险。</p>
			  </div>
		  </div>
		  <!--
		  <div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<button type="button" class="btn btn-primary">Save changes</button>
		  </div>
		  -->
		</div>
	  </div>
	</div>
    <%@include file="/WEB-INF/jsp/common/script.jsp" %>
	<script src="${APP_PATH}/script/docs.min.js"></script>
	<script src="${APP_PATH}/js/layer/layer.js"></script>
        <script type="text/javascript">
            $(function () {
            	
            	$("#fsex").val("${user.sex}");
            	
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
			    
			    $("#updateBtn").click(function(){
			    	
			    	var loginacct = $("#floginacct");
			    	if ( loginacct.val() == "" ) {
			    		//alert("登陆账号不能为空，请输入");// alert, confirm方法会暂停UI线程
			    		//return;
			    		layer.msg("登陆账号不能为空，请输入", {time:2000, icon:5, shift:6}, function(){
				    		loginacct.focus();
			    		});
			    		return;
			    	}
			    	
			    	/*
			    	$.ajax({
			    		url : "${APP_PATH}/user/insert.do",
			    		type : "POST",
			    		data : {
			    			"loginacct" : loginacct.val(),
			    			"username"  : $("#fusername").val(),
			    			"sex"       : $("#fsex").val(),
			    			"email"     : $("#femail").val()
			    		},
			    		success : function( r ) {
			    			if ( r.success ) {
			    				alert("用户信息修改成功");
			    			} else {
			    				alert("用户信息修改失败");
			    			}
			    		}
			    	});
			    	*/
			    	$("#userForm").ajaxSubmit({
			    		beforeSubmit : function(){
			    			return true;
			    		},
			    		success : function(r) {
			    			if ( r.success ) {
			    				layer.msg("用户信息修改成功", {time:2000, icon:6});
			    			} else {
					    		layer.msg("用户信息修改失败", {time:2000, icon:5, shift:6});
			    			}
			    		}
			    	});
			    	
			    });
            });
        </script>
  </body>
</html>

