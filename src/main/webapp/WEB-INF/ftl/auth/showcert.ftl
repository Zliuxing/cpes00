<!DOCTYPE html>
<html lang="UTF-8">
  <head>
    <#include "common/head.ftl">
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
				<ol class="breadcrumb">
				  <li><a href="#">首页</a></li>
				  <li><a href="#">数据列表</a></li>
				  <li class="active">新增</li>
				</ol>
			<div class="panel panel-default">
			  <div class="panel-body">
			      <#list mcs as mc>
			          <div>${mc.certname}</div>
			          <div><img src="${APP_PATH}/imgs/${mc.path}"></div>
			      </#list>				  
			  </div>
				<button id="authBtn" type="button" class="btn btn-default btn-danger">
				  <span class="glyphicon glyphicon-check"></span> 审核通过
				</button>
			</div>
        </div>
      </div>
    </div>
	<div class="modal fade" id="myModal" tabindex="-1" cert="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
    <#include "common/script.ftl" >
	<script src="${APP_PATH}/script/docs.min.js"></script>
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
				
				$("#authBtn").click(function(){
				   $.ajax({
				       type : "POST",
				       url  : "${APP_PATH}/auth/ok.do",
				       data : {
				           "taskid" : "${id}",
				           "memberid" : "${memberid}"
				       },
				       success : function(r) {
				           if ( r.success ) {
				               layer.msg("实名认证流程通过", {time:2000, icon:6}, function(){
				                   window.location.href = "${APP_PATH}/auth/list.htm";
				               });
				           }
				       }
				   });
				});
            });
            
            
        </script>
  </body>
</html>

