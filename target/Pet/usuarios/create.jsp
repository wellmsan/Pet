<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="req" value="${pageContext.request}" />
<c:set var="baseURL"
	value="${req.scheme}://${req.serverName}:${req.serverPort}${req.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Pet | Usuários</title>

<link href="${baseURL}/assets/inspinia/css/bootstrap.min.css" rel="stylesheet">
<link href="${baseURL}/assets/inspinia/font-awesome/css/font-awesome.css" rel="stylesheet">

<!-- FooTable -->
<link href="${baseURL}/assets/inspinia/css/plugins/footable/footable.core.css" rel="stylesheet">

<link href="${baseURL}/assets/inspinia/css/animate.css" rel="stylesheet">
<link href="${baseURL}/assets/inspinia/css/style.css" rel="stylesheet">
</head>

<body class="">

</body>
<jsp:include page="/templates/header.jsp" />
<div class="row wrapper border-bottom white-bg page-heading">
	<div class="col-sm-8">
		<h2>Usuários</h2>
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="index.html">Home</a></li>
			<li class="breadcrumb-item"><a>Usuários</a></li>
			<li class="breadcrumb-item active"><strong>Cadastrar</strong></li>
		</ol>
	</div>
	<div class="col-sm-4">
		<div class="title-action">
            <!-- a href="" class="btn btn-primary">Adicionar</a -->
        </div>
	</div>
</div>
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row">
	    <div class="col-lg-12">
	        <div class="ibox ">
	            <div class="ibox-title">
	                <h5>Cadastrar Usuário</h5>
	                <div class="ibox-tools">
	                </div>
	            </div>
	            <div class="ibox-content">
	            	<c:if test="${mensagem != '' && mensagem != null}">
						<div class="alert alert-${tipo} alert-dismissable">
					        <button aria-hidden="true" data-dismiss="alert" class="close" type="button">×</button>
					        ${mensagem}
					    </div>
					</c:if>
	                <form method="POST" action="./controller?command=UsuarioSave">
	                    <jsp:include page="form.jsp" />
	                    <div class="form-group row">
                            <div class="col-sm-4 col-sm-offset-2">
                                <button class="btn btn-white btn-sm" type="reset">Cancelar</button>
                                <button class="btn btn-primary btn-sm" type="submit">Salvar</button>
                            </div>
                        </div>
	                </form>
	            </div>
	        </div>
	    </div>
	</div>
</div>

<jsp:include page="/templates/footer.jsp" />

<!-- Mainly scripts -->
<script src="${baseURL}/assets/inspinia/js/jquery-3.1.1.min.js"></script>
<script src="${baseURL}/assets/inspinia/js/popper.min.js"></script>
<script src="${baseURL}/assets/inspinia/js/bootstrap.js"></script>
<script src="${baseURL}/assets/inspinia/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="${baseURL}/assets/inspinia/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

<!-- FooTable -->
<script src="${baseURL}/assets/inspinia/js/plugins/footable/footable.all.min.js"></script>

<!-- Custom and plugin javascript -->
<script src="${baseURL}/assets/inspinia/js/inspinia.js"></script>
<script src="${baseURL}/assets/inspinia/js/plugins/pace/pace.min.js"></script>

<!-- Page-Level Scripts -->
<script>
    $(document).ready(function() {

        $('.footable').footable();

    });
</script>

</html>