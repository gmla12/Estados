<%-- 
    Document   : FMM
    Created on : 24-diciembre-2012, 7:52:01
    Author     : Gilberth
--%>

<%@page import="forms.bean.BeanTipoDocumentoAut"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri= "/WEB-INF/c.tld" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FMMs</title>
        <link type="text/css" href="css/ui.all.css" rel="stylesheet" />
        <link type="text/css" href="css/comun.css" rel="stylesheet" />
        <script src="Js/jquery-1.7.2.min.js" type="text/javascript"></script>
        <script type="text/javascript" src="Js/jquery.validate.js"></script>
        <script type="text/javascript" src="Js/ui/ui.datepicker.js"></script>
        <script type="text/javascript" src="Js/ui/ui.core.js"></script>
        <script src="Js/i18n/messages_es.js" type="text/javascript"></script>
        <%
            String usuario = "";
            HttpSession sesionOk = request.getSession();
            if (sesionOk.getAttribute("usuario") == null) {
        %>
        <jsp:forward page="/index.jsp">
            <jsp:param name="mensaje" value="Es obligatorio identificarse"/>
        </jsp:forward>
        <%    } else {
                usuario = String.valueOf(sesionOk.getAttribute("usuario"));
            }
        %>
        <script type="text/javascript">
            $(function() {
            <%--
                matrixx = Array();
                <%

                ArrayList<Object> resultado = (ArrayList) session.getAttribute("CMB_TIPODOCUMENTOAUT");
                for (int i = 0; i < resultado.size(); i++) {
                    out.print("matrixx[" + i + "] = Array('");
                    BeanTipoDocumentoAut buTipoDocumentoAut2 = new BeanTipoDocumentoAut();
                    buTipoDocumentoAut2 = (BeanTipoDocumentoAut) resultado.get(i);
                    out.print(buTipoDocumentoAut2.getIdTipoDocumento() + "', '");
                    out.print(buTipoDocumentoAut2.getCampo() + "', '");
                    out.print(buTipoDocumentoAut2.getHabilitar() + "');");
                }

            %>
                    $("#idPais").change(function(){
                        $.post("Jsp/Comun/getDepartamento.jsp",{ id:$(this).val() },function(data){$("#idDepartamento").html(data);$.post("Jsp/Comun/getMunicipio.jsp",{ id:document.forms[0].idDepartamento.value, idPais:document.forms[0].idPais.value },function(data){$("#idMunicipio").html(data);})})
                    });
                    $("#idDepartamento").change(function(){
                        $.post("Jsp/Comun/getMunicipio.jsp",{ id:$(this).val(), idPais:document.forms[0].idPais.value },function(data){$("#idMunicipio").html(data);})
                    });
                    $("#idTipoDocumento").change(function(){
                        for (i=0;i<matrixx.length;i++){
                            if(matrixx[i][0] == $(this).val()){
                                switch (matrixx[i][1]){
                                    case "primerNombre" :
                                        if(matrixx[i][2] == "false"){
                                            $("#primerNombre").attr('disabled', true);
                                            $("#primerNombre").attr('value', ' ');
                                        }else{
                                            $("#primerNombre").attr('disabled', false);
                                        }
                                        break;
                                    case "segundoNombre" :
                                        if(matrixx[i][2] == "false"){
                                            $("#segundoNombre").attr('disabled', true);
                                            $("#segundoNombre").attr('value', ' ');
                                        }else{
                                            $("#segundoNombre").attr('disabled', false);
                                        }
                                        break;
                                    case "primerApellido" :
                                        if(matrixx[i][2] == "false"){
                                            $("#primerApellido").attr('disabled', true);
                                            $("#primerApellido").attr('value', ' ');
                                        }else{
                                            $("#primerApellido").attr('disabled', false);
                                        }
                                        break;
                                    case "segundoApellido" :
                                        if(matrixx[i][2] == "false"){
                                            $("#segundoApellido").attr('disabled', true);
                                            $("#segundoApellido").attr('value', ' ');
                                        }else{
                                            $("#segundoApellido").attr('disabled', false);
                                        }
                                        break;
                                    case "razonSocial" :
                                        if(matrixx[i][2] == "false"){
                                            $("#razonSocial").attr('disabled', true);
                                            $("#razonSocial").attr('value', ' ');
                                        }else{
                                            $("#razonSocial").attr('disabled', false);
                                        }
                                        break;
                                    case "idPais" :
                                        if(matrixx[i][2] == "false"){
                                            $("#idPais").attr('disabled', true);
                                            $("#idPais").attr('value', ' ');
                                        }else{
                                            $("#idPais").attr('disabled', false);
                                        }
                                        break;
                                    case "idDepartamento" :
                                        if(matrixx[i][2] == "false"){
                                            $("#idDepartamento").attr('disabled', true);
                                            $("#idDepartamento").attr('value', ' ');
                                        }else{
                                            $("#idDepartamento").attr('disabled', false);
                                        }
                                        break;
                                    case "idMunicipio" :
                                        if(matrixx[i][2] == "false"){
                                            $("#idMunicipio").attr('disabled', true);
                                            $("#idMunicipio").attr('value', ' ');
                                        }else{
                                            $("#idMunicipio").attr('disabled', false);
                                        }
                                        break;
                                    case "direccion" :
                                        if(matrixx[i][2] == "false"){
                                            $("#direccion").attr('disabled', true);
                                            $("#direccion").attr('value', ' ');
                                        }else{
                                            $("#direccion").attr('disabled', false);
                                        }
                                        break;
                                    case "telefono" :
                                        if(matrixx[i][2] == "false"){
                                            $("#telefono").attr('disabled', true);
                                            $("#telefono").attr('value', ' ');
                                        }else{
                                            $("#telefono").attr('disabled', false);
                                        }
                                        break;
                                    case "email" :
                                        if(matrixx[i][2] == "false"){
                                            $("#email").attr('disabled', true);
                                            $("#email").attr('value', ' ');
                                        }else{
                                            $("#email").attr('disabled', false);
                                        }
                                        break;
                                    case "idTipoEntidad" :
                                        if(matrixx[i][2] == "false"){
                                            $("#idTipoEntidad").attr('disabled', true);
                                            $("#idTipoEntidad").attr('value', ' ');
                                        }else{
                                            $("#idTipoEntidad").attr('disabled', false);
                                        }
                                        break;
                                }
                            }
                        }
                    });
            --%>

                    $("#forma").validate({
                        event: "blur",
                        rules : {
                            DO : {
                                required : true
                            }
                        },
                        debug: false,
                        errorElement: "label",
                        submitHandler: function(form){
                            form.submit();
                        }
                    });

                    //guardar
                    $('#submit').click(function(e) {
                        e.preventDefault();
                        if(document.forms[0].op2.value=="nuevo"){
                            document.forms[0].op.value="nuevo";
                        }
                        else {
                            document.forms[0].op.value="modificar";
                        }
                        $("#forma").submit(); 
                    }); 

                    $('input').filter('.datepicker').datepicker({
                        changeMonth: true,
                        changeYear: true,
                        showOn: "button",
                        buttonImage: "img/calendar.gif",
                        buttonImageOnly: true,
                        dateFormat: "dd/mm/yy"
                    });
                });
                
                function nuevo(){
                    document.forms[0].op.value="";
                    document.forms[0].idEntidad.value="";
                    document.forms[0].primerNombre.value="";
                    document.forms[0].segundoNombre.value="";
                    document.forms[0].primerApellido.value="";
                    document.forms[0].segundoApellido.value="";
                    document.forms[0].idTipoDocumento.value="1";
                    document.forms[0].identificacion.value="";
                    document.forms[0].razonSocial.value="";
                    document.forms[0].idPais.value="";
                    $.post("Jsp/Comun/getDepartamento.jsp",{ id:document.forms[0].idPais.value },function(data){$("#idDepartamento").html(data);$.post("Jsp/Comun/getMunicipio.jsp",{ id:document.forms[0].idDepartamento.value, idPais:document.forms[0].idPais.value },function(data){$("#idMunicipio").html(data);})})
                    document.forms[0].idDepartamento.value="";
                    document.forms[0].idMunicipio.value="";
                    document.forms[0].direccion.value="";
                    document.forms[0].telefono.value="";
                    document.forms[0].email.value="";
                    document.forms[0].idTipoEntidad.value="";
                }

                function eliminar(){
                    document.forms[0].op.value="eliminar";
                    document.forms[0].submit();
                }
            
                function atras(){
                    document.forms[0].op.value="atras";
                    document.forms[0].submit();
                }
        </script>

        <style type="text/css">
            .error-message, label.error {
                color: #ff0000;
                margin:0;
                display: inline;
                font-size: 1em !important;
                font-weight:lighter;
            }
            #el01 {
                padding:0
            }
        </style>
    </head>
    <body>
        <div id="stylized" class="myform">
            <html:form styleClass="forma" styleId="forma" action="/FMM" method="post">

                <input type="hidden" name="op" value=""> 
                <input type="hidden" name="op2" value="<%= String.valueOf(request.getAttribute("getOp2"))%>"> 
                <input type="hidden" name="idUsu" value=""> 
                <input type="hidden" name="idFMMs" value='<%= String.valueOf(request.getAttribute("getIdFMMs"))%>'> 
                
                <h1>FMMs</h1>
                <div>
                    <label for="txtFMM">FMM</label>
                    <% if (request.getAttribute("getIdFMMs") != "") {%> 
                    <html:text property="FMM" readonly="true" value='<%= String.valueOf(request.getAttribute("getFMM"))%>'></html:text>
                    <% } else {%> 
                    <html:text property="FMM" value='<%= String.valueOf(request.getAttribute("getFMM"))%>'></html:text>
                    <% }%> 
                </div>
                <div>
                    <label for="txtCliente">Cliente</label>
                    <html:text property="cliente" value='<%= String.valueOf(request.getAttribute("getCliente"))%>'></html:text>
                </div>
                <div>
                    <label for="txtPedido">Pedido</label>
                    <html:text property="pedido" value='<%= String.valueOf(request.getAttribute("getPedido"))%>'></html:text>
                </div>
                <div>
                    <label for="txtLote">Lote</label>
                    <html:text property="lote" value='<%= String.valueOf(request.getAttribute("getLote"))%>'></html:text>
                </div>

                <fieldset id="el01">
                    <legend>DOs</legend>

                    <table>
                        <tr>
                            <% if (request.getAttribute("getDO") != "") {%> 
                            <td class="text">DO</td><td><html:text property="DO" styleId="DO" readonly="true" value='<%= String.valueOf(request.getAttribute("getDO"))%>'></html:text></td>
                            <% } else {%>
                            <td class="text">DO</td><td><html:text property="DO" styleId="DO" value='<%= String.valueOf(request.getAttribute("getDO"))%>'></html:text></td>
                            <% }%> 
                            <td class="text">Sucursal</td>
                            <% if (request.getAttribute("getOp2") != "nuevo") {%> 
                            <td><html:select property="idSucursal" styleId="idSucursal" size="1" style="width:240px;" disabled="true" value='<%= String.valueOf(request.getAttribute("getIdSucursal"))%>'>
                                    <c:forEach items="${CMB_SUCURSAL}" var="cat">
                                        <html:option value="${cat.idSucursal}"><c:out value='${cat.nombre}'/></html:option>
                                    </c:forEach>
                                </html:select></td>
                                <% } else {%> 
                            <td><html:select property="idSucursal" styleId="idSucursal" size="1" style="width:240px;" value='<%= String.valueOf(request.getAttribute("getIdSucursal"))%>'>
                                    <html:option value=""><c:out value='[Seleccione]'/></html:option>
                                    <c:forEach items="${CMB_SUCURSAL}" var="cat">
                                        <html:option value="${cat.idSucursal}"><c:out value='${cat.nombre}'/></html:option>
                                    </c:forEach>
                                </html:select></td>
                                <% }%>
                        </tr>
                        <tr>
                            <% if (request.getAttribute("getOp2") != "nuevo") {%> 
                            <td class="text">Cliente</td><td><html:text property="idCliente" styleId="idCliente" readonly="true" value='<%= String.valueOf(request.getAttribute("getIdCliente"))%>'></html:text></td>
                            <% } else {%>
                            <td class="text">Cliente</td><td><html:text property="idCliente" styleId="idCliente" value='<%= String.valueOf(request.getAttribute("getIdCliente"))%>'></html:text></td>
                            <% }%> 
                            <td colspan="2"><html:text property="cliente" styleId="cliente" readonly="true" value='<%= String.valueOf(request.getAttribute("getCliente"))%>'></html:text></td>
                            </tr>
                            <tr>
                            <% if (request.getAttribute("getOp2") != "nuevo") {%> 
                            <td class="text">Lote</td><td><html:text property="lote" styleId="lote" readonly="true" value='<%= String.valueOf(request.getAttribute("getLote"))%>'></html:text></td>
                            <% } else {%>
                            <td class="text">Lote</td><td><html:text property="lote" styleId="lote" value='<%= String.valueOf(request.getAttribute("getLote"))%>'></html:text></td>
                            <% }%> 
                            <% if (request.getAttribute("getDO") != "") {%> 
                            <td class="text">BL</td><td><html:text property="BL" styleId="BL" readonly="true" value='<%= String.valueOf(request.getAttribute("getBL"))%>'></html:text></td>
                            <% } else {%>
                            <td class="text">BL</td><td><html:text property="BL" styleId="BL" value='<%= String.valueOf(request.getAttribute("getBL"))%>'></html:text></td>
                            <% }%>
                            <td class="text">Puerto</td>
                            <% if (request.getAttribute("getOp2") != "nuevo") {%> 
                            <td><html:select property="idPuerto" styleId="idPuerto" size="1" style="width:240px;" disabled="true" value='<%= String.valueOf(request.getAttribute("getIdPuerto"))%>'>
                                    <c:forEach items="${CMB_PUERTO}" var="cat">
                                        <html:option value="${cat.idPuerto}"><c:out value='${cat.nombre}'/></html:option>
                                    </c:forEach>
                                </html:select></td>
                                <% } else {%> 
                            <td><html:select property="idPuerto" styleId="idPuerto" size="1" style="width:240px;" value='<%= String.valueOf(request.getAttribute("getIdPuerto"))%>'>
                                    <html:option value=""><c:out value='[Seleccione]'/></html:option>
                                    <c:forEach items="${CMB_PUERTO}" var="cat">
                                        <html:option value="${cat.idPuerto}"><c:out value='${cat.nombre}'/></html:option>
                                    </c:forEach>
                                </html:select></td>
                                <% }%>
                        </tr>
                        <tr>
                            <td colspan="6">                
                                <fieldset id="el01">
                                    <legend>Items</legend>
                                    <table>
                                        <tr>
                                            <td colspan="2">
                                                <input type="checkbox" name="todos" value="true"> Seleccionar Todos
                                            </td>
                                            <td rowspan="2">
                                                <fieldset id="el01">
                                                    <table>
                                                        <tr>
                                                            <td>
                                                                <input type="checkbox" name="todos" value="true"> Seleccionar Todos
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <fieldset id="el01">
                                                                    <table>
                                                                        <tr>
                                                                            <td>
                                                                                <input type="checkbox" name="checkReferencia" value="true"> Referencia
                                                                            </td>
                                                                            <% if (request.getAttribute("getOp2") != "nuevo") {%> 
                                                                            <td colspan="2"><html:text property="referencia" styleId="referencia" readonly="true" value='<%= String.valueOf(request.getAttribute("getReferencia"))%>'></html:text></td>
                                                                            <% } else {%> 
                                                                            <td colspan="2"><html:text property="referencia" styleId="referencia" value='<%= String.valueOf(request.getAttribute("getReferencia"))%>'></html:text></td>
                                                                            <% }%> 
                                                                            <td colspan="2">
                                                                                <input type="checkbox" name="checkPedido" value="true"> Pedido
                                                                            </td>
                                                                            <% if (request.getAttribute("getOp2") != "nuevo") {%> 
                                                                            <td colspan="2"><html:text property="pedido" styleId="pedido" readonly="true" value='<%= String.valueOf(request.getAttribute("getPedido"))%>'></html:text></td>
                                                                            <% } else {%> 
                                                                            <td colspan="2"><html:text property="pedido" styleId="pedido" value='<%= String.valueOf(request.getAttribute("getPedido"))%>'></html:text></td>
                                                                            <% }%> 
                                                                        </tr>
                                                                        <tr>
                                                                            <td>
                                                                                <input type="checkbox" name="checkDescripcion" value="true"> Descripcion
                                                                            </td>
                                                                            <% if (request.getAttribute("getOp2") != "nuevo") {%> 
                                                                            <td colspan="2"><html:text property="descripcion" styleId="descripcion" readonly="true" value='<%= String.valueOf(request.getAttribute("getDescripcion"))%>'></html:text></td>
                                                                            <% } else {%> 
                                                                            <td colspan="2"><html:text property="referencia" styleId="referencia" value='<%= String.valueOf(request.getAttribute("getReferencia"))%>'></html:text></td>
                                                                            <% }%> 
                                                                        </tr>
                                                                        <tr>
                                                                            <td>
                                                                                <input type="checkbox" name="checkClienteFinal" value="true"> Cliente Final
                                                                            </td>
                                                                            <% if (request.getAttribute("getOp2") != "nuevo") {%> 
                                                                            <td colspan="2"><html:text property="clienteFinal" styleId="clienteFinal" readonly="true" value='<%= String.valueOf(request.getAttribute("getClienteFinal"))%>'></html:text></td>
                                                                            <% } else {%> 
                                                                            <td colspan="2"><html:text property="clienteFinal" styleId="clienteFinal" value='<%= String.valueOf(request.getAttribute("getClienteFinal"))%>'></html:text></td>
                                                                            <% }%> 
                                                                        </tr>
                                                                        <tr>
                                                                            <td>
                                                                                <input type="checkbox" name="checkProveedor" value="true"> Proveedor
                                                                            </td>
                                                                            <% if (request.getAttribute("getOp2") != "nuevo") {%> 
                                                                            <td colspan="2"><html:select property="idProveedor" styleId="idProveedor" size="1" style="width:240px;" disabled="true" value='<%= String.valueOf(request.getAttribute("getIdProveedor"))%>'>
                                                                                    <c:forEach items="${CMB_PROVEEDOR}" var="cat">
                                                                                        <html:option value="${cat.idProveedor}"><c:out value='${cat.nombre}'/></html:option>
                                                                                    </c:forEach>
                                                                                </html:select></td>
                                                                                <% } else {%> 
                                                                            <td colspan="2"><html:select property="idProveedor" styleId="idProveedor" size="1" style="width:240px;" value='<%= String.valueOf(request.getAttribute("getIdProveedor"))%>'>
                                                                                    <html:option value=""><c:out value='[Seleccione]'/></html:option>
                                                                                    <c:forEach items="${CMB_PROVEEDOR}" var="cat">
                                                                                        <html:option value="${cat.idProveedor}"><c:out value='${cat.nombre}'/></html:option>
                                                                                    </c:forEach>
                                                                                </html:select></td>
                                                                                <% }%>
                                                                            <td colspan="2">
                                                                                <input type="checkbox" name="checFechaEstimadaArribo" value="true"> Fecha Estimada de Arribo
                                                                            </td>
                                                                            <% if (request.getAttribute("getOp2") != "nuevo") {%> 
                                                                            <td colspan="2"><html:text property="fechaEstimadaArribo"  styleClass="datepicker" styleId="fechaEstimadaArribo" readonly="true" value='<%= String.valueOf(request.getAttribute("getFechaEstimadaArribo"))%>'></html:text></td>
                                                                            <% } else {%> 
                                                                            <td colspan="2"><html:text property="fechaEstimadaArribo" styleClass="datepicker" styleId="fechaEstimadaArribo" value='<%= String.valueOf(request.getAttribute("getFechaEstimadaArribo"))%>'></html:text></td>
                                                                            <% }%>
                                                                        </tr>
                                                                        <tr>
                                                                            <td>
                                                                                <input type="checkbox" name="checkFechaLlegada" value="true"> Fecha de Llegada
                                                                            </td>
                                                                            <% if (request.getAttribute("getOp2") != "nuevo") {%> 
                                                                            <td colspan="2"><html:text property="fechaLlegada" styleClass="datepicker" styleId="fechaLlegada" readonly="true" value='<%= String.valueOf(request.getAttribute("getFechaLlegada"))%>'></html:text></td>
                                                                            <% } else {%> 
                                                                            <td colspan="2"><html:text property="fechaLlegada" styleClass="datepicker" styleId="fechaLlegada" value='<%= String.valueOf(request.getAttribute("getFechaLlegada"))%>'></html:text></td>
                                                                            <% }%>
                                                                            <td colspan="2">
                                                                                <input type="checkbox" name="checFechaDocumentos" value="true"> Fecha Documentos
                                                                            </td>
                                                                            <% if (request.getAttribute("getOp2") != "nuevo") {%> 
                                                                            <td colspan="2"><html:text property="fechaDocumentos" styleClass="datepicker" styleId="fechaDocumentos" readonly="true" value='<%= String.valueOf(request.getAttribute("getFechaDocumentos"))%>'></html:text></td>
                                                                            <% } else {%> 
                                                                            <td colspan="2"><html:text property="fechaDocumentos" styleClass="datepicker" styleId="fechaDocumentos" value='<%= String.valueOf(request.getAttribute("getFechaDocumentos"))%>'></html:text></td>
                                                                            <% }%>
                                                                        </tr>
                                                                        <tr>
                                                                            <td>
                                                                                <input type="checkbox" name="checkFechaDocumentosOK" value="true"> Fecha de Documentos OK
                                                                            </td>
                                                                            <% if (request.getAttribute("getOp2") != "nuevo") {%> 
                                                                            <td colspan="2"><html:text property="fechaDocumentosOK" styleClass="datepicker" styleId="fechaDocumentosOK" readonly="true" value='<%= String.valueOf(request.getAttribute("getFechaDocuemtnosOK"))%>'></html:text></td>
                                                                            <% } else {%> 
                                                                            <td colspan="2"><html:text property="fechaDocumentosOK" styleClass="datepicker" styleId="fechaDocumentosOK" value='<%= String.valueOf(request.getAttribute("getFechaDocuemtnosOK"))%>'></html:text></td>
                                                                            <% }%>
                                                                            <td colspan="2">
                                                                                <input type="checkbox" name="checFechaAceptacion" value="true"> Fecha de Aceptacion
                                                                            </td>
                                                                            <% if (request.getAttribute("getOp2") != "nuevo") {%> 
                                                                            <td colspan="2"><html:text property="fechaAceptacion" styleClass="datepicker" styleId="fechaAceptacion" readonly="true" value='<%= String.valueOf(request.getAttribute("getFechaAceptacion"))%>'></html:text></td>
                                                                            <% } else {%> 
                                                                            <td colspan="2"><html:text property="fechaAceptacion" styleClass="datepicker" styleId="fechaAceptacion" value='<%= String.valueOf(request.getAttribute("getFechaAceptacion"))%>'></html:text></td>
                                                                            <% }%>
                                                                        </tr>
                                                                        <tr>
                                                                            <td>
                                                                                <input type="checkbox" name="checkFechaSolicitudAnticipo" value="true"> Fecha de Solicitud de Anticipo
                                                                            </td>
                                                                            <% if (request.getAttribute("getOp2") != "nuevo") {%> 
                                                                            <td colspan="2"><html:text property="fechaSolicitudAnticipo" styleClass="datepicker" styleId="fechaSolicitudAnticipo" readonly="true" value='<%= String.valueOf(request.getAttribute("getFechaSolicitudAnticipo"))%>'></html:text></td>
                                                                            <% } else {%> 
                                                                            <td colspan="2"><html:text property="fechaSolicitudAnticipo" styleClass="datepicker" styleId="fechaSolicitudAnticipo" value='<%= String.valueOf(request.getAttribute("getFechaSolicitudAnticipo"))%>'></html:text></td>
                                                                            <% }%>
                                                                            <td colspan="2">
                                                                                <input type="checkbox" name="checFechaPagoTributo" value="true"> Fecha de Pago de Tributos
                                                                            </td>
                                                                            <% if (request.getAttribute("getOp2") != "nuevo") {%> 
                                                                            <td colspan="2"><html:text property="fechaPagoTributo" styleClass="datepicker" styleId="fechaPagoTributo" readonly="true" value='<%= String.valueOf(request.getAttribute("getFechaPagoTributo"))%>'></html:text></td>
                                                                            <% } else {%> 
                                                                            <td colspan="2"><html:text property="fechaPagoTributo" styleClass="datepicker" styleId="fechaPagoTributo" value='<%= String.valueOf(request.getAttribute("getFechaPagoTributo"))%>'></html:text></td>
                                                                            <% }%>
                                                                        </tr>
                                                                        <tr>
                                                                            <td>
                                                                                <input type="checkbox" name="checkFechaLevante" value="true"> Fecha de Levante
                                                                            </td>
                                                                            <% if (request.getAttribute("getOp2") != "nuevo") {%> 
                                                                            <td colspan="2"><html:text property="fechaLevante" styleClass="datepicker" styleId="fechaLevante" readonly="true" value='<%= String.valueOf(request.getAttribute("getFechaLevante"))%>'></html:text></td>
                                                                            <% } else {%> 
                                                                            <td colspan="2"><html:text property="fechaLevante" styleClass="datepicker" styleId="fechaLevante" value='<%= String.valueOf(request.getAttribute("getFechaLevante"))%>'></html:text></td>
                                                                            <% }%>
                                                                            <td colspan="2">
                                                                                <input type="checkbox" name="checFechaPoder" value="true"> Fecha del Poder
                                                                            </td>
                                                                            <% if (request.getAttribute("getOp2") != "nuevo") {%> 
                                                                            <td colspan="2"><html:text property="fechaPoder" styleClass="datepicker" styleId="fechaPoder" readonly="true" value='<%= String.valueOf(request.getAttribute("getFechaPoder"))%>'></html:text></td>
                                                                            <% } else {%> 
                                                                            <td colspan="2"><html:text property="fechaPoder" styleClass="datepicker" styleId="fechaPoder" value='<%= String.valueOf(request.getAttribute("getFechaPoder"))%>'></html:text></td>
                                                                            <% }%>
                                                                        </tr>
                                                                        <tr>
                                                                            <td>
                                                                                <input type="checkbox" name="checkFechaEnvioDocumentos" value="true"> Fecha de Envio Documentos
                                                                            </td>
                                                                            <% if (request.getAttribute("getOp2") != "nuevo") {%> 
                                                                            <td colspan="2"><html:text property="fechaEnvioDocumentos" styleClass="datepicker" styleId="fechaEnvioDocumentos" readonly="true" value='<%= String.valueOf(request.getAttribute("getFechaEnvioDocumentos"))%>'></html:text></td>
                                                                            <% } else {%> 
                                                                            <td colspan="2"><html:text property="fechaEnvioDocumentos" styleClass="datepicker" styleId="fechaEnvioDocumentos" value='<%= String.valueOf(request.getAttribute("getFechaEnvioDocumentos"))%>'></html:text></td>
                                                                            <% }%>
                                                                            <td colspan="2">
                                                                                <input type="checkbox" name="checFechaAutenticacion" value="true"> Fecha de Autenticacion
                                                                            </td>
                                                                            <% if (request.getAttribute("getOp2") != "nuevo") {%> 
                                                                            <td colspan="2"><html:text property="fechaAutenticacion" styleClass="datepicker" styleId="fechaAutenticacion" readonly="true" value='<%= String.valueOf(request.getAttribute("getFechaAutenticacion"))%>'></html:text></td>
                                                                            <% } else {%> 
                                                                            <td colspan="2"><html:text property="fechaAutenticacion" styleClass="datepicker" styleId="fechaAutenticacion" value='<%= String.valueOf(request.getAttribute("getFechaAutenticacion"))%>'></html:text></td>
                                                                            <% }%>
                                                                        </tr> 
                                                                        <tr>
                                                                            <td>
                                                                                <input type="checkbox" name="checkFechaLiberacionBL" value="true"> Fecha de Liberacion BL
                                                                            </td>
                                                                            <% if (request.getAttribute("getOp2") != "nuevo") {%> 
                                                                            <td colspan="2"><html:text property="fechaLiberacionBL" styleClass="datepicker" styleId="fechaLiberacionBL" readonly="true" value='<%= String.valueOf(request.getAttribute("getFechaLiberacionBL"))%>'></html:text></td>
                                                                            <% } else {%> 
                                                                            <td colspan="2"><html:text property="fechaLiberacionBL" styleClass="datepicker" styleId="fechaLiberacionBL" value='<%= String.valueOf(request.getAttribute("getFechaLiberacionBL"))%>'></html:text></td>
                                                                            <% }%>
                                                                            <td colspan="2">
                                                                                <input type="checkbox" name="checFechaPlanilla" value="true"> Fecha de Planilla
                                                                            </td>
                                                                            <% if (request.getAttribute("getOp2") != "nuevo") {%> 
                                                                            <td colspan="2"><html:text property="fechaPlanilla" styleClass="datepicker" styleId="fechaPlanilla" readonly="true" value='<%= String.valueOf(request.getAttribute("getFechaPlanilla"))%>'></html:text></td>
                                                                            <% } else {%> 
                                                                            <td colspan="2"><html:text property="fechaPlanilla" styleClass="datepicker" styleId="fechaPlanilla" value='<%= String.valueOf(request.getAttribute("getFechaPlanilla"))%>'></html:text></td>
                                                                            <% }%>
                                                                        </tr> 
                                                                        <tr>
                                                                            <td>
                                                                                <input type="checkbox" name="checkFechaSOAT" value="true"> Fecha del SOAT
                                                                            </td>
                                                                            <% if (request.getAttribute("getOp2") != "nuevo") {%> 
                                                                            <td colspan="2"><html:text property="fechaSOAT" styleClass="datepicker" styleId="fechaSOAT" readonly="true" value='<%= String.valueOf(request.getAttribute("getFechaSOAT"))%>'></html:text></td>
                                                                            <% } else {%> 
                                                                            <td colspan="2"><html:text property="fechaSOAT" styleClass="datepicker" styleId="fechaSOAT" value='<%= String.valueOf(request.getAttribute("getFechaSOAT"))%>'></html:text></td>
                                                                            <% }%>
                                                                            <td colspan="2">
                                                                                <input type="checkbox" name="checFechaTransitoLibre" value="true"> Fecha de Transito Libre
                                                                            </td>
                                                                            <% if (request.getAttribute("getOp2") != "nuevo") {%> 
                                                                            <td colspan="2"><html:text property="fechaTransitoLibre" styleClass="datepicker" styleId="fechaTransitoLibre" readonly="true" value='<%= String.valueOf(request.getAttribute("getFechaTransitoLibre"))%>'></html:text></td>
                                                                            <% } else {%> 
                                                                            <td colspan="2"><html:text property="fechaTransitoLibre" styleClass="datepicker" styleId="fechaTransitoLibre" value='<%= String.valueOf(request.getAttribute("getFechaTransitoLibre"))%>'></html:text></td>
                                                                            <% }%>
                                                                        </tr> 
                                                                        <tr>
                                                                            <td>
                                                                                <input type="checkbox" name="checkFechaDespacho" value="true"> Fecha de Despacho
                                                                            </td>
                                                                            <% if (request.getAttribute("getOp2") != "nuevo") {%> 
                                                                            <td colspan="2"><html:text property="fechaDespacho" styleClass="datepicker" styleId="fechaDespacho" readonly="true" value='<%= String.valueOf(request.getAttribute("getFechaDespacho"))%>'></html:text></td>
                                                                            <% } else {%> 
                                                                            <td colspan="2"><html:text property="fechaDespacho" styleClass="datepicker" styleId="fechaDespacho" value='<%= String.valueOf(request.getAttribute("getFechaDespacho"))%>'></html:text></td>
                                                                            <% }%>
                                                                            <td colspan="2">
                                                                                <input type="checkbox" name="checFechaEntregaFacturacion" value="true"> Fecha de Entrega a Facturacion
                                                                            </td>
                                                                            <% if (request.getAttribute("getOp2") != "nuevo") {%> 
                                                                            <td colspan="2"><html:text property="fechaEntregaFacturacion" styleClass="datepicker" styleId="fechaEntregaFacturacion" readonly="true" value='<%= String.valueOf(request.getAttribute("getFechaEntregaFacturacion"))%>'></html:text></td>
                                                                            <% } else {%> 
                                                                            <td colspan="2"><html:text property="fechaEntregaFacturacion" styleClass="datepicker" styleId="fechaEntregaFacturacion" value='<%= String.valueOf(request.getAttribute("getFechaEntregaFacturacion"))%>'></html:text></td>
                                                                            <% }%>
                                                                        </tr> 
                                                                        <tr>
                                                                            <td>
                                                                                <input type="checkbox" name="checkFechaFacturacion" value="true"> Fecha de Facturacion
                                                                            </td>
                                                                            <% if (request.getAttribute("getOp2") != "nuevo") {%> 
                                                                            <td colspan="2"><html:text property="fechaFacturacion" styleClass="datepicker" styleId="fechaFacturacion" readonly="true" value='<%= String.valueOf(request.getAttribute("getFechaFacturacion"))%>'></html:text></td>
                                                                            <% } else {%> 
                                                                            <td colspan="2"><html:text property="fechaFacturacion" styleClass="datepicker" styleId="fechaFacturacion" value='<%= String.valueOf(request.getAttribute("getFechaFacturacion"))%>'></html:text></td>
                                                                            <% }%>
                                                                            <td colspan="2">
                                                                                <input type="checkbox" name="checNumeroFactura" value="true"> Numero de Factura
                                                                            </td>
                                                                            <% if (request.getAttribute("getOp2") != "nuevo") {%> 
                                                                            <td colspan="2"><html:text property="numeroFactura" styleId="numeroFactura" readonly="true" value='<%= String.valueOf(request.getAttribute("getNumeroFactura"))%>'></html:text></td>
                                                                            <% } else {%> 
                                                                            <td colspan="2"><html:text property="numeroFactura" styleId="numeroFactura" value='<%= String.valueOf(request.getAttribute("getNumeroFactura"))%>'></html:text></td>
                                                                            <% }%>
                                                                        </tr> 
                                                                        <tr>
                                                                            <td class="text">Comentarios</td>
                                                                        </tr>
                                                                        <tr>
                                                                            <% if (request.getAttribute("getOp2") != "nuevo") {%> 
                                                                            <td colspan="2"><html:textarea property="comentarios" styleId="comentarios" readonly="true" value='<%= String.valueOf(request.getAttribute("getComentarios"))%>'></html:textarea></td>
                                                                            <% } else {%> 
                                                                            <td colspan="2"><html:textarea property="comentarios" styleId="comentarios" value='<%= String.valueOf(request.getAttribute("getComentarios"))%>'></html:textarea></td>
                                                                            <% }%> 
                                                                        </tr>
                                                                        <tr>
                                                                            <td colspan="3"><a class="boton" href="javascript:nuevoItem();">Nuevo</a> <a class="boton" id="submit2" href="javascript:guardarItem();">Guardar</a> <% if (request.getAttribute("getOp2") != "nuevo") {%> <a class="boton" href="javascript:eliminarItem();">Eliminar</a> <% }%></td>
                                                                        </tr>
                                                                    </table>
                                                                </fieldset>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </fieldset>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="2">
                                                <div style="overflow: auto; width: 300px; height: 150px; border: 1px solid #336699">
                                                    <input type="checkbox" name="languages" value="English"> English<br>
                                                    <input type="checkbox" name="languages" value="Hindi"> Hindi<br>
                                                    <input type="checkbox" name="languages" value="Italian"> Italian<br>
                                                    <input type="checkbox" name="languages" value="Chinese"> Chinese<br>
                                                    <input type="checkbox" name="languages" value="Japanese"> Japanese<br>
                                                    <input type="checkbox" name="languages" value="German"> German<br>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="text">Observaciones</td>
                                            <td colspan="2"><html:textarea property="observaciones" styleId="observaciones" value='<%= String.valueOf(request.getAttribute("getObservaciones"))%>'></html:textarea></td>
                                        </tr>
                                    </table>
                                </fieldset>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="3"><a class="boton" href="javascript:nuevo();">Nuevo</a> <a class="boton" id="submit" href="javascript:guardar();">Guardar</a> <% if (request.getAttribute("getOp2") != "nuevo") {%> <a class="boton" href="javascript:eliminar();">Eliminar</a> <% }%> <a class="boton" href="javascript:atras();">Volver</a></td>
                        </tr>
                        <%
                            if (request.getAttribute("respuesta") != "") {
                        %>
                        <tr>
                            <td colspan="3" class="text"><%= String.valueOf(request.getAttribute("respuesta"))%></td>
                        </tr>
                        <%  }
                        %>
                    </table>
                </fieldset>
            </html:form>
        </div>
    </body>
</html:html>
