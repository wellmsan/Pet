<nav class="navbar-default navbar-static-side" role="navigation">
	<div class="sidebar-collapse">
		<ul class="nav metismenu" id="side-menu">
			<jsp:include page="/templates/profile.jsp" />
			<jsp:include page="/templates/menu.jsp" />
		</ul>

	</div>
</nav>

<div id="page-wrapper" class="gray-bg">
	<div class="row border-bottom">
		<nav class="navbar navbar-static-top  " role="navigation"
			style="margin-bottom: 0">
			<div class="navbar-header">
				<a class="navbar-minimalize minimalize-styl-2 btn btn-primary "
					href="#"><i class="fa fa-bars"></i> </a>
				<form role="search" class="navbar-form-custom"
					action="search_results.html">
					<div class="form-group">
						<input type="text" placeholder="Search for something..." class="form-control" name="top-search" id="top-search">
					</div>
				</form>
			</div>
			<ul class="nav navbar-top-links navbar-right">
				<li><a href="./controller?command=Logout"> <i class="fa fa-sign-out"></i>
						Sair
				</a></li>
			</ul>

		</nav>
	</div>