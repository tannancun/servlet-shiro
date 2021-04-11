		<%@tag description="head信息" pageEncoding="UTF-8" %><%@ taglib prefix="admin" uri="http://www.infop.cn/tag/admin"%>
		<div
			class="preloader flex-column justify-content-center align-items-center">
			<img class="animation__shake" src="<admin:logo/>"
				alt="<admin:systemName/>" height="60" width="60">
		</div>

		<!-- Navbar -->
		<nav
			class="main-header navbar navbar-expand navbar-white navbar-light">
			<!-- Left navbar links -->
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" data-widget="pushmenu"
					href="#" role="button"><i class="fas fa-bars"></i></a></li>
				<li class="nav-item d-none d-sm-inline-block"><a
					href="index3.html" class="nav-link">控制台</a></li>
			</ul>

			<!-- Right navbar links -->
			<ul class="navbar-nav ml-auto">			
				<li class="nav-item"><a class="nav-link"  title="用户信息"
					href="logout" role="button"> <i
						class="fas fa-user-alt"></i>
				</a></li>
				<li class="nav-item"><a class="nav-link" title="退出"
					 href="logout" role="button"> <i
						class="fas fa-sign-out-alt"></i>
				</a></li>	
				<li class="nav-item"><a class="nav-link"  title="全屏"
					data-widget="fullscreen" href="#" role="button"> <i
						class="fas fa-expand-arrows-alt"></i>
				</a></li>					
				<li class="nav-item"><a class="nav-link"
					data-widget="control-sidebar" data-slide="true" href="#"
					role="button"> <i class="fas fa-th-large"></i>
				</a></li>
			</ul>
		</nav>
		<!-- /.navbar -->