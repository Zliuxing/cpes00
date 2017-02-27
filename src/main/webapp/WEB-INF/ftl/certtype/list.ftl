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
	
	table tbody input {
	    width : 20px;
	    height: 20px;
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
			<div class="panel panel-default">
			  <div class="panel-heading">
				<h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
			  </div>
			  <div class="panel-body">
 <hr style="clear:both;">
          <div class="table-responsive">
            <table class="table  table-bordered">
              <thead>
                <tr >
                  <th >证件图片</th>
				  <th >商业公司</th>
                  <th>个体工商户 </th>
                  <th >个人经营 </th>
                  <th >政府及非营利组织 </th>
                </tr>
              </thead>
              <tbody>
	              <#list cs as c>
	                <tr >
	                  <th >${c.name}</th>
					  <th ><input type="checkbox" data-certid="${c.id}" data-acctype="0"></th>
	                  <th><input type="checkbox" data-certid="${c.id}" data-acctype="1"> </th>
	                  <th ><input type="checkbox" data-certid="${c.id}" data-acctype="2"> </th>
	                  <th ><input type="checkbox" data-certid="${c.id}" data-acctype="3"> </th>
	                </tr>
	              </#list>
              </tbody>
            </table>
          </div>
			  </div>
			</div>
        </div>
      </div>
    </div>
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
			
			// 对已经分类的数据进行复选框勾选
			<#list cts as ct>
			    var box = $(":checkbox[data-certid='${ct.certid}'][data-acctype='${ct.acctype}']");
			    box[0].checked = true;
			</#list>
			
			$(":checkbox").click(function(){
			    var flg = this.checked;
			    
			    var certid = $(this).attr("data-certid");
			    var acctype = $(this).attr("data-acctype");
			    
			    var url = "";
			    if ( flg ) {
			        url = "${APP_PATH}/certtype/insert.do";
			    } else {
			        url = "${APP_PATH}/certtype/delete.do";
			    }
			    $.ajax({
			       url : url,
			       type : "POST",
			       data : {
			           certid : certid,
			           acctype :acctype
			       }
			    });
			});
        });
    </script>
  </body>
</html>
