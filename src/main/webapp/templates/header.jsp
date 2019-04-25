<nav class="navbar-default navbar-static-side" role="navigation">
	<div class="sidebar-collapse">
		<ul class="nav metismenu" id="side-menu">
			<li class="nav-header">
				<div class="dropdown profile-element">
					<img alt="image" class="rounded-circle" src="${baseURL}/assets/inspinia/img/profile_small.jpg" />
					<a data-toggle="dropdown" class="dropdown-toggle" href="#"> <span
						class="block m-t-xs font-bold">${ sessionScope.usuarioLogado.nome }</span> <span
						class="text-muted text-xs block">${ sessionScope.usuarioLogado.email }<b
							class="caret"></b></span>
					</a>
				</div>
				<div class="logo-element">Pet+</div>
			</li>
			<li>
				<a href="index.html"><i class="fa fa-paw"></i> <span class="nav-label">Pets</span></a>
			</li>
			<li>
				<a href="./controller?command=FornecedorIndex"><i class="fa fa-truck"></i> <span class="nav-label">Fornecedores</span></a>
			</li>
			<li>
				<a href="./controller?command=UsuarioIndex"><i class="fa fa-users"></i> <span class="nav-label">Usuários</span></a> 
			</li>
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