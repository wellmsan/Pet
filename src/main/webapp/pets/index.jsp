<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="req" value="${pageContext.request}" />
<c:set var="baseURL" value="${req.scheme}://${req.serverName}:${req.serverPort}${req.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Pet | Pets</title>

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
		<h2>Pets</h2>
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="index.html">Home</a></li>
			<li class="breadcrumb-item"><a>Pets</a></li>
			<li class="breadcrumb-item active"><strong>Lista</strong></li>
		</ol>
	</div>
	<div class="col-sm-4">
		<div class="title-action">
            <a href="" class="btn btn-primary">Adicionar</a>
        </div>
	</div>
</div>
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row">
		<div class="col-lg-12">
			<div class="ibox ">
				<div class="ibox-title">
					<h5>Lista de Pets</h5>

					<div class="ibox-tools">						
					</div>
				</div>
				<div class="ibox-content">
					<input type="text" class="form-control form-control-sm m-b-xs"
						id="filter" placeholder="Localizar na tabela">

					<table class="footable table table-stripped" data-page-size="8"
						data-filter=#filter>
						<thead>
							<tr>
								<th>Rendering engine</th>
								<th>Browser</th>
								<th data-hide="phone,tablet">Platform(s)</th>
								<th data-hide="phone,tablet">Engine version</th>
								<th data-hide="phone,tablet">CSS grade</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="pet" items="${ pets }">
								<tr>
									<td>${ pet.id }</td>
									<td>Internet Explorer 4.0</td>
									<td>Win 95+</td>
									<td>4</td>
									<td class="right">
										<a href="#" class="btn btn-white btn-bitbucket"><i class="fa fa-pencil"></i></a>
					                    <a href="#" class="btn btn-white btn-bitbucket"><i class="fa fa-trash-o"></i></a>
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