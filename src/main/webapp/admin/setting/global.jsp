<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="admin" uri="http://www.infop.cn/tag/admin"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<admin:meta title="通用设置" />
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
							<h1 class="m-0">通用设置</h1>
						</div>
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="console/home.jsp">主页</a></li>
								<li class="breadcrumb-item"><a>系统设置</a></li>
								<li class="breadcrumb-item active">通用设置</li>
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
						<form class="form-horizontal" method="POST" action="console/set-global-update.jsp" 
							enctype="multipart/form-data" id="global_form">
							<div class="card-body">
								<div class="form-group row">
									<label for="console_title" class="col-sm-2 col-form-label">后台标题</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="console_title" name="console_title" value="${console_title}">
									</div>
								</div>
								<div class="form-group row">
									<label for="system_name" class="col-sm-2 col-form-label">系统名称</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="system_name" name="system_name" value="${system_name}">
									</div>
								</div>
								<div class="form-group row">
									<label for="system_logo" class="col-sm-2 col-form-label">系统Logo</label>
									<div class="col-sm-10">
										<div class="input-group">
											<div class="custom-file">
												<input type="file" class="custom-file-input" name="system_logo" id="system_logo"
												accept="image/gif,image/jpeg,image/png"> 
												<label class="custom-file-label" for="system_logo"><img src="${system_logo}" style="max-height: 24px;width: auto;"></label>
											</div>
										</div>
										
									</div>
								</div>
								<div class="form-group row">
									<label for="favicon" class="col-sm-2 col-form-label">网站图标</label>
									<div class="col-sm-10">
										<div class="input-group">
											<div class="custom-file">
												<input type="file" class="custom-file-input" name="favicon" id="favicon"
												accept="image/x-icon,image/vnd.microsoft.icon"> 
												<label class="custom-file-label" for="favicon"><img src="${favicon}" style="max-height: 24px;width: auto;"></label>	
											</div>
										</div>										
									</div>
								</div>
								<div class="form-group row">
									<label for="copyright" class="col-sm-2 col-form-label">版权信息</label>
									<div class="col-sm-10">
										<textarea rows="2" cols="" class="form-control" id="copyright" name="copyright">${copyright}</textarea>
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
