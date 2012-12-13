<%-- 
    Document   : Departamento
    Created on : 24-junio-2012, 20:34:01
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
        <title>Departamento</title>
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
                    //alert(document.forms[0].idPais.value)
                    if(document.forms[0].idDepartamento.readOnly==false){
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
                        idDepartamento : {
                            required : true,
                            minlength : 2,
                            maxlength : 2,
                            remote: { 
                                url: "Jsp/Parametros/Departamento/getDepartamento.jsp", //valida si existe el idDepartamento
                                type: "post", 
                                data: { 
                                    lectura: function() { return document.forms[0].idDepartamento.readOnly },
                                    idPais: function() { return $("#idPais").val() }
                                } 
                            }
                        },
                        idPais : {
                            required : true,
                            remote: { 
                                url: "Jsp/Parametros/Departamento/getDepartamento.jsp", //valida si existe la identificacion
                                type: "post", 
                                data: { 
                                    lectura: function() { return document.forms[0].idDepartamento.readOnly },
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
                        idDepartamento: {
                            remote: "El ID ya existe con el Pais escogido"
                        },
                        idPais: {
                            remote: "El ID ya existe con el Pais escogido"
                        }
                    },
                    debug: false,
                    errorElement: "label",
                    submitHandler: function(form){
                        form.submit();
                        //alert('El formulario ha sido validado correctamente!');
                    }
                });
            });

            function nuevo(){
                document.forms[0].op.value="";
                document.forms[0].idDepartamento.value="";
                document.forms[0].idDepartamento.readOnly=false;
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
                var emer = window.open('../Estados/Jsp/Log/Auditoria/Auditoria.jsp?getOp=buscar&accion=referencia&formulario=departamento&referencia='+'<%=request.getAttribute("getIdPais")%>'+'<%=request.getAttribute("getIdDepartamento")%>','Auditoria Departamento','width=950,height=500,top=100%,left=100%,scrollbars=yes,resizable=yes');
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
            <html:form styleClass="forma" styleId="forma" action="/Departamento" method="post">

                <input type="hidden" name="op" value=""> 
                <input type="hidden" name="idUsu" value=""> 
                <% if (request.getAttribute("getIdDepartamento") != "") {%> 
                <input type="hidden" name="idPais" value='<%= String.valueOf(request.getAttribute("getIdPais"))%>'> 
                <% }%> 
                <h1>Departamentos</h1>
                <div>
                    <label for="txtIdDepartamento">ID</label>
                    <% if (request.getAttribute("getIdDepartamento") != "") {%> 
                    <html:text property="idDepartamento" readonly="true" value='<%= String.valueOf(request.getAttribute("getIdDepartamento"))%>'></html:text>
                    <% } else {%> 
                    <html:text property="idDepartamento" value='<%= String.valueOf(request.getAttribute("getIdDepartamento"))%>'></html:text>
                    <% }%> 
                </div>
                <div>
                    <label for="txtIdPais">Pais</label>
                    <% if (request.getAttribute("getIdDepartamento") != "") {%> 
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
                    <label for="txtNombre">Nombre del Departamento</label>
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
                    <a class="boton" href="javascript:nuevo();">Nuevo</a> <a class="boton" id="submit" href="javascript:guardar();">Guardar</a> <% if (request.getAttribute("getIdDepartamento") != "") {%> <a class="boton" href="javascript:eliminar();">Eliminar</a> <% }%> <a class="boton" href="javascript:atras();">Volver</a>
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
