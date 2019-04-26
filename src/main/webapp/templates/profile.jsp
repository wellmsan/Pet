<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="req" value="${pageContext.request}" />
<c:set var="baseURL" value="${req.scheme}://${req.serverName}:${req.serverPort}${req.contextPath}" />
	
<li class="nav-header">
	<div class="dropdown profile-element">
		<img alt="image" class="rounded-circle" src="${baseURL}/assets/inspinia/img/profile_small.jpg" />
		<a data-toggle="dropdown" class="dropdown-toggle" href="#"> 
		<span class="block m-t-xs font-bold">${ sessionScope.usuarioLogado.nome }</span> 
		<span class="text-muted text-xs block">${ sessionScope.usuarioLogado.email }</span>
		</a>
	</div>
	<div class="logo-element">Pet+</div>
</li>