<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="admin" uri="http://www.infop.cn/tag/admin"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<admin:meta title="修改菜单" />
</head>
<body class="hold-transition sidebar-mini layout-fixed">
	<div class="wrapper">
		<admin:navbar />
		<admin:main-sidebar />
		<div class="content-wrapper">
			<div class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h1 class="m-0">添加菜单</h1>
						</div>
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="console/home.jsp">主页</a></li>
								<li class="breadcrumb-item"><a href="console/menu-list.jsp">菜单管理</a></li>
								<li class="breadcrumb-item active">修改菜单</li>
							</ol>
						</div>
					</div>
				</div>
			</div>
			<section class="content">
				<div class="container-fluid">
					<div class="card">
						<div class="card-header">
							<h3 class="card-title">表单</h3>
						</div>
						<form class="form-horizontal" method="POST" action="console/menu-update.do" id="emnu_form">
							<input type="hidden" name="id" value="${menu.id}">
							<div class="card-body">
								<div class="form-group row">
									<label for="name" class="col-sm-2 col-form-label">菜单名称</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="name" name="name" value="${menu.name}">
									</div>
								</div>
								<div class="form-group row">
									<label for="symbol" class="col-sm-2 col-form-label">符号</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="symbol" name="symbol" value="${menu.symbol}">
									</div>
								</div>
								<div class="form-group row">
									<label for="pid" class="col-sm-2 col-form-label">父级菜单</label>
									<div class="col-sm-10">
										<select class="form-control" name="pid" id="pid" >
											<option value="${pmenu.id}">${pmenu.name}</option>
										</select>
									</div>
								</div>

							</div>
							<div class="card-footer">
								<button type="submit" class="btn btn-outline-success btn-sm float-right">保存</button>
							</div>
						</form>
					</div>
				</div>
			</section>
		</div>
		<admin:footer />
		<admin:control-sidebar />
	</div>
	<admin:js>
		<script src="asset/infop/security.js"></script>
		<script type="text/javascript">
			$(document).ready(function() {
				loadMenuLevleName();
			})
		</script>
	</admin:js>
</body>
</html>
