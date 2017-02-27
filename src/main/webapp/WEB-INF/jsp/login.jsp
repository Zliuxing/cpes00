<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="UTF-8">
  <head>
    <%@include file="/WEB-INF/jsp/common/head.jsp" %>
	<link rel="stylesheet" href="${APP_PATH}/css/login.css">
	<style>

	</style>
  </head>
  <body>
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <div><img src="${APP_PATH}/img/logo.png" width="100" style="float:left;margin-top:5px;"><a class="navbar-brand" style="font-size:32px;" href="#">认证流程审批系统</a></div>
        </div>
      </div>
    </nav>

    <div class="container">

      <form class="form-signin" role="form">
        <h2 class="form-signin-heading"><i class="glyphicon glyphicon-user"></i> 用户登录</h2>
		  <div class="form-group has-success has-feedback">
			<input type="text" class="form-control" id="floginacct" name="loginacct" value="zhangsan" placeholder="请输入登录账号" autofocus autocomplete="off">
			<span class="glyphicon glyphicon-ok form-control-feedback"></span>
		  </div>
		  <div class="form-group has-success has-feedback">
			<input type="password" class="form-control" id="fuserpswd" name="userpswd" value="zhangsan" placeholder="请输入登录密码" style="margin-top:10px;">
			<span class="glyphicon glyphicon-lock form-control-feedback"></span>
		  </div>
		  <div class="form-group has-success has-feedback">
			<select id="flogintype" class="form-control" >
			    <option value="0">会员登陆</option>
			    <option value="1">管理登陆</option>
			</select>
		  </div>
        <div class="checkbox">
          <label>
            <input type="checkbox" id="frememberme" name="rememberme" value="1"> 2周内登陆有效
          </label>
        </div>
        <a id="loginBtn" class="btn btn-lg btn-success btn-block" ><i class="glyphicon glyphicon-log-in"></i> 登录</a>
      </form>
    </div>
    <%@include file="/WEB-INF/jsp/common/script.jsp" %>
    <script type="text/javascript">
    $("#loginBtn").click(function(){

    	var floginacct = $("#floginacct");
    	if ( floginacct.val() == "" ) {
    		alert("登陆账号不能为空，请输入");
    		floginacct.focus();
    		return;
    	}
    	
    	var fuserpswd = $("#fuserpswd");
    	if ( fuserpswd.val() == "" ) {
    		alert("登陆密码不能为空，请输入");
    		fuserpswd.focus();
    		return;
    	}
    	
    	var flogintype = $("#flogintype").val();
    	
    	var flg = $("#frememberme")[0].checked;
    	var url = "";
    	if ( flogintype == '0' ) {
    		url = "${APP_PATH}/member/dologin.do";
    	} else {
    		url = "${APP_PATH}/user/dologin.do";
    	}
    	
    	$.ajax({
    		url : url,
    		type : "POST",
    		data : {
    			"loginacct" : floginacct.val(),
    			"userpswd" : fuserpswd.val(),
    			"rememberme" : flg?"1":"0"
    		},
    		success : function(r) {
    			if ( r.success ) {
    				if ( flogintype == "0" ) {
    					window.location.href = "${APP_PATH}/member.htm";
    				} else {
    					window.location.href = "${APP_PATH}/main.htm";
    				}
    			} else {
    				alert("用户信息不存在，请重新输入");
    				floginacct.focus();
    			}
    		}
    	});
    });
    </script>
  </body>
</html>