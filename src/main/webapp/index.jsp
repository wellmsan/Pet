<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="req" value="${pageContext.request}" />
<c:set var="baseURL" value="${req.scheme}://${req.serverName}:${req.serverPort}${req.contextPath}" />

<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Pet | Estácio</title>

    <link href="${baseURL}/assets/inspinia/css/bootstrap.min.css" rel="stylesheet">
    <link href="${baseURL}/assets/inspinia/font-awesome/css/font-awesome.css" rel="stylesheet">

    <link href="${baseURL}/assets/inspinia/css/animate.css" rel="stylesheet">
    <link href="${baseURL}/assets/inspinia/css/style.css" rel="stylesheet">

</head>

<body class="gray-bg">

    <div class="middle-box text-center loginscreen animated fadeInDown">
        <div>
            <div>

                <h1 class="logo-name">Pet+</h1>

            </div>
            <h3>Bem vindo ao Pet+</h3>
            <p></p>
            <p>Login in</p>
            <c:if test="${mensagem != '' && mensagem != null}">
				<div class="alert alert-${tipo} alert-dismissable">
			        <button aria-hidden="true" data-dismiss="alert" class="close" type="button">×</button>
			        ${mensagem}
			    </div>
			</c:if>
            <form class="m-t" role="form" method="POST" action="./loginController?command=LoginEntrar">
                <div class="form-group">
                    <input type="email" class="form-control" placeholder="Email" name="email" required="">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" placeholder="Senha" name="senha" required="">
                </div>
                <button type="submit" class="btn btn-primary block full-width m-b">Entrar</button>

                <!--  a href="#"><small>Forgot password?</small></a -->
                <p class="text-muted text-center"><small>Ainda não tem uma conta?</small></p>
                <a class="btn btn-sm btn-white btn-block" href="./loginController?command=CriarConta">Criar Conta</a>
            </form>
            <p class="m-t"> <small>Copyright &copy; 2019 <strong>Welber Santana</strong></small> </p>
            <p>MBA Engenharia de Software <strong>Estácio</strong>.</p>
        </div>
    </div>

    <!-- Mainly scripts -->
    <script src="${baseURL}/assets/inspinia/js/jquery-3.1.1.min.js"></script>
    <script src="${baseURL}/assets/inspinia/js/popper.min.js"></script>
    <script src="${baseURL}/assets/inspinia/js/bootstrap.js"></script>

</body>

</html>
