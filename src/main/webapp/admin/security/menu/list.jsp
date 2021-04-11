<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%><%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib prefix="admin" 	uri="http://www.infop.cn/tag/admin"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<admin:meta title="菜单管理" />
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
							<h1>菜单管理<a href="console/menu-add.jsp"
							class="btn btn-outline-success btn-sm menu-add">添加</a></h1>
						</div>
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="console/home.jsp">主页</a></li>
								<li class="breadcrumb-item"><a href="console/menu-list.jsp">菜单管理</a></li>
								<li class="breadcrumb-item active">菜单管理</li>
							</ol>
						</div>
					</div>
				</div>
			</div>
			<section class="content">
				<div class="container-fluid">
					<div class="row">
						<div class="col-12">
							<div class="card">
								
								<div class="card-body">
									<div 
										class="dataTables_wrapper dt-bootstrap4">
										<div class="row">
											<div class="col-sm-12">
												<table id="example"
													class="table table-bordered table-hover dataTable dtr-inline"
													role="grid">
													<thead>
														<tr role="row">
															<th>#</th>
															<th>菜单名称</th>
															<th>符号</th>
															<th>操作</th>
														</tr>
													</thead>
													<tbody>
													</tbody>
													<tfoot>
														<tr>
															<th>#</th>
															<th>菜单名称</th>
															<th>符号</th>
															<th>操作</th>
														</tr>
													</tfoot>
												</table>
											</div>
										</div>
										
									</div>
								</div>
							</div>
						</div>
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
				loadMenuData();
			})
		</script>
	</admin:js>
</body>
</html>
