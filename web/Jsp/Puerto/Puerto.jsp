<%-- 
    Document   : Puertos
    Created on : 18-septiembre-2012, 09:47:01
    Author     : ccastillor
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
        <title>Puertos</title>
        <link type="text/css" href="css/ui.all.css" rel="stylesheet" />
        <link type="text/css" href="css/comun.css" rel="stylesheet" />
        <script type="text/javascript" src="Js/ui/ui.core.js"></script>
        <script type="text/javascript" src="Js/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" src="Js/jquery.validate.js"></script>
        <script type="text/javascript" src="Js/i18n/messages_es.js"></script>
        <link rel="stylesheet" type="text/css" media="all" href="niceforms_files/niceforms-default.css">
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
                $("#idPais").change(function(){
                    $.post("Jsp/Comun/getDepartamento.jsp",{ id:$(this).val() },function(data){$("#idDepartamento").html(data);$.post("Jsp/Comun/getMunicipio.jsp",{ id:document.forms[0].idDepartamento.value, idPais:document.forms[0].idPais.value },function(data){$("#idMunicipio").html(data);})})
                });
                $("#idDepartamento").change(function(){
                    $.post("Jsp/Comun/getMunicipio.jsp",{ id:$(this).val(), idPais:document.forms[0].idPais.value },function(data){$("#idMunicipio").html(data);})
                });
                //guardar
                $('#submit').click(function(e) {
                    e.preventDefault();
                    if(document.forms[0].idPuerto.value==""){
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
                        nombre : {
                            required : true,
                            minlength : 3,
                            maxlength : 45
                        },
                        idPais : {
                            required : true
                        },
                        idDepartamento : {
                            required : true
                        },
                        idMunicipio : {
                            required : true
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
                document.forms[0].idPuerto.value="";
                document.forms[0].nombre.value="";
                document.forms[0].idPais.value="";
                document.forms[0].idDepartamento.value="";
                document.forms[0].idMunicipio.value="";
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
        </style>
    </head>
    <body>
        <div >
            <html:form action="/Puerto" method="post" styleId="forma">

                <input type="hidden" name="op" value=""> 
                <input type="hidden" name="idPuerto" value='<%= String.valueOf(request.getAttribute("getIdPuerto"))%>'> 

                <fieldset>
                    <legend>Puertos</legend>

                    <table>
                        <tr>
                            <td class="text">Nombre del Puerto</td>
                            <td><html:text property="nombre" value='<%= String.valueOf(request.getAttribute("getNombre"))%>'></html:text></td>
                        </tr>
                        <tr>
                            <td class="text">Pais</td>
                            <td><html:select property="idPais" styleId="idPais" size="1" style="width:240px;" disabled='false' value='<%= String.valueOf(request.getAttribute("getIdPais"))%>'>
                                    <% if (request.getAttribute("getIdPuerto") == "") {%>
                                    <html:option value=""><c:out value='[Seleccione]'/></html:option>
                                    <% }%>
                                    <c:forEach items="${CMB_PAIS}" var="cat">
                                        <html:option value="${cat.idPais}"><c:out value='${cat.nombre}'/></html:option>
                                    </c:forEach>
                                </html:select></td>

                        </tr>
                        <tr>
                            <td class="text">Departamento</td>

                            <td><html:select property="idDepartamento" styleId="idDepartamento" size="1" style="width:240px;" disabled='false' value='<%= String.valueOf(request.getAttribute("getIdDepartamento"))%>'>
                                    <c:forEach items="${CMB_DEPARTAMENTO}" var="cat">
                                        <html:option value="${cat.idDepartamento}"><c:out value='${cat.nombre}'/></html:option>
                                    </c:forEach>
                                </html:select></td>
                        </tr>
                        <tr>
                            <td class="text">Municipio</td>
                            <td><html:select property="idMunicipio" styleId="idMunicipio" size="1" style="width:240px;" disabled='false' value='<%= String.valueOf(request.getAttribute("getIdMunicipio"))%>'>
                                    <c:forEach items="${CMB_MUNICIPIO}" var="cat">
                                        <html:option value="${cat.idMunicipio}"><c:out value='${cat.nombre}'/></html:option>
                                    </c:forEach>
                                </html:select></td>
                        </tr>
                        <tr>
                            <td colspan="3"><a class="boton" href="javascript:nuevo();">Nuevo</a> <a class="boton" id="submit" href="javascript:guardar();">Guardar</a> <% if (request.getAttribute("getIdPuerto") != "") {%> <a class="boton" href="javascript:eliminar();">Eliminar</a> <% }%> <a class="boton" href="javascript:atras();">Volver</a></td>
                        </tr>
                        <%
                            if (request.getAttribute("respuesta") != "") {
                        %>
                        <tr>
                            <td class="text" colspan="3"><%= String.valueOf(request.getAttribute("respuesta"))%></td>
                        </tr>
                        <%  }
                        %>
                    </table>
                </fieldset>
            </html:form>
        </div>
    </body>
</html:html>
