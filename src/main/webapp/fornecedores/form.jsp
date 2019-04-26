<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="form-group  row">
	<label class="col-sm-2 col-form-label">Razão Social</label>
    <div class="col-sm-10">
    	<input type="text" class="form-control" name="razaoSocial" value="${ fornecedor.razaoSocial }" required="required">
    </div>
</div>
<div class="hr-line-dashed"></div>
<div class="form-group  row">
	<label class="col-sm-2 col-form-label">Nome Fantasia</label>
    <div class="col-sm-10">
    	<input type="text" class="form-control" name="nomeFantasia" value="${ fornecedor.nomeFantasia }" >
    </div>
</div>
<div class="hr-line-dashed"></div>
<div class="form-group row">
	<label class="col-sm-2 col-form-label">CNPJ</label>
    <div class="col-sm-10">
    	<input type="text" class="form-control" name="cnpj" value="${ fornecedor.cnpj }" required="required">
    </div>
</div>
<div class="hr-line-dashed"></div>
<div class="form-group row">
	<label class="col-sm-2 col-form-label">Inscrição Estadual</label>
    <div class="col-sm-10">
    	<input type="text" class="form-control" name="inscricaoEstadual" value="${ fornecedor.inscricaoEstadual }" maxlength="20" >
    </div>
</div>
<div class="hr-line-dashed"></div>
<div class="form-group row">
	<label class="col-sm-2 col-form-label">Inscrição Municipal</label>
    <div class="col-sm-10">
    	<input type="text" class="form-control" name="inscricaoMunicipal" value="${ fornecedor.inscricaoMunicipal }" maxlength="20" >
    </div>
</div>
<div class="hr-line-dashed"></div>
<div class="form-group row">
	<label class="col-sm-2 col-form-label">Telefone</label>
    <div class="col-sm-10">
    	<input type="text" class="form-control" name="telefone" value="${ fornecedor.telefone }" required="required">
    </div>
</div>
<div class="hr-line-dashed"></div>
<div class="form-group row">
	<label class="col-sm-2 col-form-label">E-mail</label>
    <div class="col-sm-10">
    	<input type="email" class="form-control" name="email" value="${ fornecedor.email }" required="required">
    </div>
</div>
<div class="hr-line-dashed"></div>
<div class="form-group row">
	<label class="col-sm-2 col-form-label">Endereço</label>
    <div class="col-sm-10">
    	<input type="text" class="form-control" name="endereco" value="${ fornecedor.endereco }" required="required">
    </div>
</div>
<div class="hr-line-dashed"></div>
<div class="form-group row">
	<label class="col-sm-2 col-form-label">Complemento</label>
    <div class="col-sm-10">
    	<input type="text" class="form-control" name="complementoEndereco" value="${ fornecedor.complementoEndereco }" >
    </div>
</div>
<div class="hr-line-dashed"></div>
<div class="form-group row">
	<label class="col-sm-2 col-form-label">Número</label>
    <div class="col-sm-10">
    	<input type="text" class="form-control" name="numeroEndereco" value="${ fornecedor.numeroEndereco }" required="required">
    </div>
</div>
<div class="hr-line-dashed"></div>
<div class="form-group row">
	<label class="col-sm-2 col-form-label">Cidade</label>
    <div class="col-sm-10">
    	<input type="text" class="form-control" name="cidade" value="${ fornecedor.cidade }" required="required">
    </div>
</div>
<div class="hr-line-dashed"></div>
<div class="form-group row">
	<label class="col-sm-2 col-form-label">Estado</label>
    <div class="col-sm-10">
    	<select class="form-control m-b" name="estado" required="required">
    		<option ${ fornecedor.estado == null ? "selected='true'" : ""}>:: Selecione ::</option>	
    		<c:forEach var="estado" items="${ estados }">
    			<option value="${ estado.id }" ${ fornecedor.estado == estado ? "selected='true'" : ""} >${ estado.nome }</option>
    		</c:forEach>
         </select>
    </div>
</div>
<div class="hr-line-dashed"></div>

