<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="req" value="${pageContext.request}" />
<c:set var="baseURL"value="${req.scheme}://${req.serverName}:${req.serverPort}${req.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Pet | Fornecedores</title>

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
		<h2>Fornecedores</h2>
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="index.html">Home</a></li>
			<li class="breadcrumb-item"><a>Fornecedores</a></li>
			<li class="breadcrumb-item active"><strong>Lista</strong></li>
		</ol>
	</div>
	<div class="col-sm-4">
		<div class="title-action">
            <a href="./controller?command=FornecedorCreate" class="btn btn-primary">Cadastrar</a>
        </div>
	</div>
</div>
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row">
		<div class="col-lg-12">
			<div class="ibox ">
				<div class="ibox-title">
					<h5>Lista de Fornecedores</h5>

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
					<input type="text" class="form-control form-control-sm m-b-xs"
						id="filter" placeholder="Localizar...">

					<table class="footable table table-stripped" data-page-size="8"
						data-filter=#filter>
						<thead>
							<tr>
								<th>Razão Social</th>
								<th>Nome Fantasia</th>
								<th>CNPJ</th>
								<th>Cidade</th>
								<th>UF</th>
								<th width="10%;"></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="fornecedor" items="${ fornecedores }">
								<tr>
									<td>${ fornecedor.razaoSocial }</td>
									<td>${ fornecedor.nomeFantasia }</td>
									<td>${ fornecedor.cnpj }</td>
									<td>${ fornecedor.cidade }</td>
									<td>${ fornecedor.estado }</td>
									<td class="right">
										<a href="./controller?command=FornecedorEdit&id=${fornecedor.id}" class="btn btn-white btn-bitbucket"><i class="fa fa-pencil"></i></a>
					                    <a href="./controller?command=FornecedorRemove&id=${fornecedor.id}" class="btn btn-white btn-bitbucket"><i class="fa fa-trash-o"></i></a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="5">
									<ul class="pagination float-right"></ul>
								</td>
							</tr>
						</tfoot>
					</table>
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