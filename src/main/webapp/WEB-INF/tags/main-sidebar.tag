<%@tag description="head信息" pageEncoding="UTF-8"%><%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %><%@ taglib prefix="admin" uri="http://www.infop.cn/tag/admin"%>
<!-- Main Sidebar Container -->
<aside class="main-sidebar sidebar-dark-primary elevation-4">
	<!-- Brand Logo -->
	<a href="console/home.jsp" class="brand-link"> <img
		src="<admin:logo/>" alt="<admin:systemName/>"
		class="brand-image img-circle elevation-3" >
		<span class="brand-text font-weight-light"><admin:consoleTitle/> </span>
	</a>
	<!-- Sidebar -->
	<div class="sidebar">
		<!-- Sidebar user panel (optional) -->
		<div class="user-panel mt-3 pb-3 mb-3 d-flex">
			<div class="image">
				<img src="asset/AdminLTE/3.1.0/img/user1-128x128.jpg"
					class="img-circle elevation-2" alt="User Image">
			</div>
			<div class="info">
				<a href="#" class="d-block"><shiro:principal/></a>
			</div>
		</div>

		<!-- Sidebar Menu -->
		<nav class="mt-2">
			<ul class="nav nav-pills nav-sidebar flex-column"
				data-widget="treeview" role="menu" data-accordion="false">
				<!-- Add icons to the links using the .nav-icon class
               with font-awesome or any other icon font library -->


				<li class="nav-item"><a href="pages/widgets.html"
					class="nav-link"> <i class="nav-icon fas fa-th"></i>
						<p>
							Widgets <span class="right badge badge-danger">New</span>
						</p>
				</a></li>
				
				<li class="nav-item"><a href="#" class="nav-link"> <i
						class="nav-icon fas fa-table"></i>
						<p>
							Tables <i class="fas fa-angle-left right"></i>
						</p>
				</a>
					<ul class="nav nav-treeview">
						<li class="nav-item"><a href="pages/tables/simple.html"
							class="nav-link"> <i class="far fa-circle nav-icon"></i>
								<p>Simple Tables</p>
						</a></li>
						<li class="nav-item"><a href="pages/tables/data.html"
							class="nav-link"> <i class="far fa-circle nav-icon"></i>
								<p>DataTables</p>
						</a></li>
						<li class="nav-item"><a href="pages/tables/jsgrid.html"
							class="nav-link"> <i class="far fa-circle nav-icon"></i>
								<p>jsGrid</p>
						</a></li>
					</ul></li>

				<li class="nav-item"><a href="#" class="nav-link"> <i
						class="nav-icon fas fa-shield-alt"></i>
						<p>
							权限管理 <i class="fas fa-angle-left right"></i>
						</p>
				</a>
					<ul class="nav nav-treeview">
						<li class="nav-item"><a href="pages/tables/simple.html"
							class="nav-link"> <i class="far fa-circle nav-icon"></i>
								<p>用户管理</p>
						</a></li>
						<li class="nav-item"><a href="pages/tables/data.html"
							class="nav-link"> <i class="far fa-circle nav-icon"></i>
								<p>角色管理</p>
						</a></li>
						<shiro:hasRole name="ROOT">	
						<li class="nav-item"><a href="console/menu-list.jsp"
							class="nav-link"> <i class="far fa-circle nav-icon"></i>
								<p>菜单管理</p>
						</a></li>
						</shiro:hasRole>
					</ul></li>
					
				
				<li class="nav-item"><a href="#" class="nav-link"> <i
						class="nav-icon fas fa-tools"></i>
						<p>
							系统设置 <i class="fas fa-angle-left right"></i>
						</p>
				</a>
					<ul class="nav nav-treeview">
						<li class="nav-item"><a href="console/set-global.jsp"
							class="nav-link"> <i class="far fa-circle nav-icon"></i>
								<p>通用设置</p>
						</a></li>						
					</ul></li>
				<shiro:hasRole name="ROOT">	
				</shiro:hasRole>	
			</ul>
		</nav>
		<!-- /.sidebar-menu -->
	</div>
	<!-- /.sidebar -->
</aside>