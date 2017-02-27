<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="UTF-8">
  <head>
    <%--<jsp:include page="/WEB-INF/jsp/common/head.jsp"></jsp:include>--%>
    <%@include file="/WEB-INF/jsp/common/head.jsp" %>
	<link rel="stylesheet" href="${APP_PATH}/css/main.css">
	<style>
	.tree li {
        list-style-type: none;
		cursor:pointer;
	}
	.tree-closed {
	    height : 40px;
	}
	.tree-expanded {
	    height : auto;
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
            <input type="text" class="form-control" placeholder="查询">
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
					        <c:if test="${empty menu.url}">
								<a href="#"><i class="fa ${menu.icon} fa-fw"></i> ${menu.name}</a> 	        
					        </c:if>
					    	<c:if test="${not empty menu.url}">
					    	    <a href="${menu.url}"><i class="fa ${menu.icon} fa-fw"></i> ${menu.name}</a> 	        
					    	</c:if>
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
				    <%-- 
					<li class="list-group-item tree-closed" >
						<a href="main.html"><i class="glyphicon glyphicon-home"></i> 首页</a> 
					</li>
					<li class="list-group-item tree-closed">
						<span><i class="glyphicon glyphicon glyphicon-tasks"></i> 权限管理 <span class="badge" style="float:right">3</span></span> 
						<ul style="margin-top:10px;display:none;">
							<li style="height:30px;">
								<a href="table.html"><i class="glyphicon glyphicon-minus-sign"></i> 用户维护</a> 
							</li>
							<li style="height:30px;">
								<a><i class="glyphicon glyphicon-minus-sign"></i> 角色维护</a> 
							</li>
							<li style="height:30px;">
								<a><i class="glyphicon glyphicon-minus-sign"></i> 许可维护</a> 
							</li>
						</ul>
					</li>
					<li class="list-group-item tree-closed">
						<span><i class="glyphicon glyphicon-leaf"></i> 资质管理 <span class="badge" style="float:right">2</span></span> 
						<ul style="margin-top:10px;display:none;">
							<li style="height:30px;">
								<a><i class="glyphicon glyphicon-minus-sign"></i> 资质维护</a> 
							</li>
							<li style="height:30px;">
								<a><i class="glyphicon glyphicon-minus-sign"></i> 分类管理</a> 
							</li>
						</ul>
					</li>
					 --%>
				</ul>
				
			</div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h1 class="page-header">控制面板</h1>

          <div class="row placeholders">
            <div class="col-xs-6 col-sm-3 placeholder">
              <img data-src="holder.js/200x200/auto/sky" class="img-responsive" alt="Generic placeholder thumbnail">
              <h4>Label</h4>
              <span class="text-muted">Something else</span>
            </div>
            <div class="col-xs-6 col-sm-3 placeholder">
              <img data-src="holder.js/200x200/auto/vine" class="img-responsive" alt="Generic placeholder thumbnail">
              <h4>Label</h4>
              <span class="text-muted">Something else</span>
            </div>
            <div class="col-xs-6 col-sm-3 placeholder">
              <img data-src="holder.js/200x200/auto/sky" class="img-responsive" alt="Generic placeholder thumbnail">
              <h4>Label</h4>
              <span class="text-muted">Something else</span>
            </div>
            <div class="col-xs-6 col-sm-3 placeholder">
              <img data-src="holder.js/200x200/auto/vine" class="img-responsive" alt="Generic placeholder thumbnail">
              <h4>Label</h4>
              <span class="text-muted">Something else</span>
            </div>
          </div>
        </div>
      </div>
    </div>
    <%@include file="/WEB-INF/jsp/common/script.jsp" %>
	<script src="${APP_PATH}/script/docs.min.js"></script>
    <script type="text/javascript">
        $(function () {
		    $(".list-group-item").click(function(){
		    	// this ==> DOM对象
		    	// JQuery对象
		    	// 如果DOM对象需要调用JQuery框架的方法，那么必须将DOM对象转换为Jquery对象
		    	// $(DOM对象)==> JQuery对象
		    	// JQuery对象[0] ==> DOM对象, JQuery方法的回调函数中的this关键字
			    if ( $(this).find("ul") ) {
					$(this).toggleClass("tree-closed");
					if ( $(this).hasClass("tree-closed") ) {
						$("ul", this).hide("fast");
					} else {
						$("ul", this).show("fast");
					}
				}
			});
        });
    </script>
  </body>
</html>
