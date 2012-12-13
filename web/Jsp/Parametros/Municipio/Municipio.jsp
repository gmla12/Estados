<%-- 
    Document   : Municipio
    Created on : 18-junio-2012, 17:08:01
    Author     : Gilberth
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri= "/WEB-INF/c.tld" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Municipio</title>
        <link type="text/css" href="css/ui.all.css" rel="stylesheet" />
        <link type="text/css" href="css/comun.css" rel="stylesheet" />
        <script src="Js/jquery-1.7.2.min.js" type="text/javascript"></script>
        <script type="text/javascript" src="Js/jquery.validate.js"></script>
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
            $(function () { 
                //guardar
                $('#submit').click(function(e) {
                    e.preventDefault();
                    document.forms[0].idUsu.value=<%= session.getAttribute("idusu")%>
                    if(document.forms[0].idMunicipio.readOnly==false){
                        document.forms[0].op.value="nuevo";
                    }
                    else {
                        document.forms[0].op.value="modificar";
                    }
                    $("#forma").submit(); 
                }); 
                $("#forma").validate({
                    event: "blur",
                    rules : {
                        idMunicipio : {
                            required : true,
                            minlength : 3,
                            maxlength : 3,
                            remote: { 
                                url: "Jsp/Parametros/Municipio/getMunicipio.jsp", //valida si existe el idMunicipio
                                type: "post", 
                                data: { 
                                    lectura: function() { return document.forms[0].idMunicipio.readOnly },
                                    idPais: function() { return $("#idPais").val() },
                                    idDepartamento: function() { return $("#idDepartamento").val() } 
                                } 
                            }
                        },
                        idDepartamento : {
                            required : true,
                            remote: { 
                                url: "Jsp/Parametros/Municipio/getMunicipio.jsp", //valida si existe la identificacion
                                type: "post", 
                                data: { 
                                    lectura: function() { return document.forms[0].idMunicipio.readOnly },
                                    idMunicipio: function() { return $("#idMunicipio").val() },
                                    idPais: function() { return $("#idPais").val() }
                                } 
                            }
                        },
                        idPais : {
                            required : true,
                            remote: { 
                                url: "Jsp/Parametros/Municipio/getMunicipio.jsp", //valida si existe la identificacion
                                type: "post", 
                                data: { 
                                    lectura: function() { return document.forms[0].idMunicipio.readOnly },
                                    idMunicipio: function() { return $("#idMunicipio").val() },
                                    idDepartamento: function() { return $("#idDepartamento").val() } 
                                } 
                            }
                        },
                        nombre : {
                            required : true,
                            minlength : 3,
                            maxlength : 45
                        }
                    },
                    messages: {
                        idMunicipio: {
                            remote: "El ID ya existe con el Departamento y Pais escogido"
                        },
                        idDepartamento: {
                            remote: "El ID ya existe con el Departamento y Pais escogido"
                        },
                        idPais: {
                            remote: "El ID ya existe con el Departamento y Pais escogido"
                        }
                    },
                    debug: false,
                    errorElement: "label",
                    submitHandler: function(form){
                        form.submit();
                        //alert('El formulario ha sido validado correctamente!');
                    }
                });
                $("#idPais").change(function(){
                    $.post("Jsp/Comun/getDepartamento.jsp",{ id:$(this).val() },function(data){$("#idDepartamento").html(data);})
                });
            });

            function nuevo(){
                document.forms[0].op.value="";
                document.forms[0].idMunicipio.value="";
                document.forms[0].idMunicipio.readOnly=false;
                document.forms[0].idDepartamento.value="";
                document.forms[0].idDepartamento.disabled=false;
                document.forms[0].idPais.value="";
                document.forms[0].idPais.disabled=false;
                document.forms[0].nombre.value="";
                document.getElementById('nombreUsu').innerHTML = "";
                document.getElementById('fechaModificacion').innerHTML = "";
            }
            
            function eliminar(){
                document.forms[0].op.value="eliminar";
                document.forms[0].idUsu.value=<%= session.getAttribute("idusu")%>
                document.forms[0].submit();
            }
            
            function atras(){
                document.forms[0].op.value="atras";
                document.forms[0].submit();
            }
            
            function historico(){
                var forma = document.forms[0];
                var emer = window.open('../Estados/Jsp/Log/Auditoria/Auditoria.jsp?getOp=buscar&accion=referencia&formulario=municipio&referencia='+'<%=request.getAttribute("getIdPais")%>'+'<%=request.getAttribute("getIdDepartamento")%>'+'<%=request.getAttribute("getIdMunicipio")%>','Auditoria Municipio','width=950,height=500,top=100%,left=100%,scrollbars=yes,resizable=yes');
                emer.focus();
            }
            
            function toggleLayer(whichLayer) {
                if (document.getElementById) {
                    // this is the way the standards work
                    var style2 = document.getElementById(whichLayer).style;
                    if(arguments.length == 2){
                        style2.display = arguments[1]==true?"inline":"none";
                        return;
                    }
                    if(style2.display.length == 0 || style2.display == "inline"){
                        style2.display = "none";
                    }
                    else{
                        style2.display = "inline";
                    }
                    //		style2.display = "none";
                } else if (document.all) {
                    // this is the way old msie versions work
                    var style2 = document.all[whichLayer].style;
                    if(arguments.length == 2){
                        style2.display = arguments[1]==true?"block":"";
                        return;
                    }
                    style2.display = style2.display? "":"block";
                } else if (document.layers) {
                    // this is the way nn4 works
                    var style2 = document.layers[whichLayer].style;
                    if(arguments.length == 2){
                        style2.display = arguments[1]==true?"block":"";
                        return;
                    }
                    style2.display = style2.display? "":"block";
                }
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
        </style>
    </head>
    <body>
        <div id="stylized" class="myform">
            <html:form styleClass="forma" styleId="forma" action="/Municipio" method="post">

                <input type="hidden" name="op" value=""> 
                <input type="hidden" name="idUsu" value=""> 
                <% if (request.getAttribute("getIdMunicipio") != "") {%> 
                <input type="hidden" name="idPais" value='<%= String.valueOf(request.getAttribute("getIdPais"))%>'> 
                <input type="hidden" name="idDepartamento" value='<%= String.valueOf(request.getAttribute("getIdDepartamento"))%>'> 
                <% }%> 
                
                <h1>Municipios</h1>
                <div>
                    <label for="txtIdMunicipio">ID</label>
                    <% if (request.getAttribute("getIdMunicipio") != "") {%> 
                    <html:text property="idMunicipio" readonly="true" value='<%= String.valueOf(request.getAttribute("getIdMunicipio"))%>'></html:text>
                    <% } else {%> 
                    <html:text property="idMunicipio" value='<%= String.valueOf(request.getAttribute("getIdMunicipio"))%>'></html:text>
                    <% }%> 
                </div>
                <div>
                    <label for="txtIdPais">Pais</label>
                    <% if (request.getAttribute("getIdMunicipio") != "") {%> 
                    <html:select property="idPais" styleId="idPais" size="1" style="width:240px;" disabled="true" value='<%= String.valueOf(request.getAttribute("getIdPais"))%>'>
                        <c:forEach items="${CMB_PAIS}" var="cat">
                            <html:option value="${cat.idPais}"><c:out value='${cat.nombre}'/></html:option>
                        </c:forEach>
                    </html:select>
                    <% } else {%> 
                    <html:select property="idPais" styleId="idPais" size="1" style="width:240px;" value='<%= String.valueOf(request.getAttribute("getIdPais"))%>'>
                        <c:forEach items="${CMB_PAIS}" var="cat">
                            <html:option value="${cat.idPais}"><c:out value='${cat.nombre}'/></html:option>
                        </c:forEach>
                    </html:select>
                    <% }%> 
                </div>
                <div>
                    <label for="txtIdDepartamento">Departamento</label>
                    <% if (request.getAttribute("getIdMunicipio") != "") {%> 
                    <html:select property="idDepartamento" styleId="idDepartamento" size="1" style="width:240px;" disabled="true" value='<%= String.valueOf(request.getAttribute("getIdPais"))%>'>
                        <c:forEach items="${CMB_DEPARTAMENTO}" var="cat">
                            <html:option value="${cat.idDepartamento}"><c:out value='${cat.nombre}'/></html:option>
                        </c:forEach>
                    </html:select>
                    <% } else {%> 
                    <html:select property="idDepartamento" styleId="idDepartamento" size="1" style="width:240px;" value='<%= String.valueOf(request.getAttribute("getIdPais"))%>'>
                        <c:forEach items="${CMB_DEPARTAMENTO}" var="cat">
                            <html:option value="${cat.idDepartamento}"><c:out value='${cat.nombre}'/></html:option>
                        </c:forEach>
                    </html:select>
                    <% }%> 
                </div>
                <div>
                    <label for="txtNombre">Nombre del Municipio</label>
                    <html:text property="nombre" value='<%= String.valueOf(request.getAttribute("getNombre"))%>'></html:text>
                    </div>

                    <fieldset>
                        <legend>
                            [<a class="linkin" href="javascript:toggleLayer('auditoria')">
                                Auditoría
                            </a>]
                        </legend>
                        <div id="auditoria" style="display: none;">
                            <label for="txtUsu">Usuario: </label><strong><div id="nombreUsu"><%= String.valueOf(request.getAttribute("getNombreUsu"))%></div></strong>
                        <label for="txtFechaModificacion">Fecha de Modificación: </label><strong><div id="fechaModificacion"><%= String.valueOf(request.getAttribute("getFechaModificacion"))%></div></strong>
                        <div><br>
                        </div>
                        <div><a class="boton" href="javascript:historico();">Historico</a>
                        </div>
                    </div>
                </fieldset>
                <div><br>
                </div>
                <div>
                    <a class="boton" href="javascript:nuevo();">Nuevo</a> <a class="boton" id="submit" href="javascript:guardar();">Guardar</a> <% if (request.getAttribute("getIdMunicipio") != "") {%> <a class="boton" href="javascript:eliminar();">Eliminar</a> <% }%> <a class="boton" href="javascript:atras();">Volver</a>
                </div>
                <%
                    if (request.getAttribute("respuesta") != "") {
                %>
                <div><br>
                </div>
                <div>
                    <%= String.valueOf(request.getAttribute("respuesta"))%>
                </div>
                <%  }
                %>
            </html:form>
        </div>
    </body>
</html:html>
