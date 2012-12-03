<%-- 
    Document   : Pais
    Created on : 15-junio-2012, 14:49:01
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
        <title>Pais</title>
        <link rel="stylesheet" type="text/css" media="all" href="niceforms_files/niceforms-default.css">
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
                    if(document.forms[0].idPais.readOnly==false){
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
                        idPais : {
                            required : true,
                            minlength : 3,
                            maxlength : 3,
                            remote: { 
                                url: "Jsp/Parametros/Pais/getPais.jsp", //valida si existe el idPais
                                type: "post", 
                                data: { 
                                    lectura: function() { return document.forms[0].idPais.readOnly } 
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
                        idPais: {
                            remote: "El ID ya existe"
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
                document.forms[0].idPais.value="";
                document.forms[0].nombre.value="";
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
            *{
                font-family: sans-serif;
                font-size: 12px;
                color: #000000;
            }
            body{
                margin: auto;
                background-color: #FFFFFF;
            }
            .forma{
                border: 1px solid #CED5D7;
                border-radius: 6px;
                padding: 0px 15px 15px;
                margin-top: 0px;
                background-color: white;
            }
            .forma label{
                display: block;
                font-weight: bold;
            }
            .forma div{
                margin-bottom: 0px;
            }
            .forma input[type='text'], .forma textarea{
                padding: 2px 2px;
                border: 1px solid #CED5D7;
                resize: none;
                margin: 5px 0;
            }
            .forma input[type='text']:focus, .forma textarea:focus{
                outline: none;
                box-shadow:0 0 0 3px #dde9ec;
            }
        </style>

    </head>
    <body>
        <div id="stylized" class="myform">
            <html:form styleClass="forma" styleId="forma" method="post" action="/Pais">
                <input type="hidden" name="op" value=""> 
                <h1>Pais</h1>
                <div>
                    <label for="txtIdPais">ID</label>
                    <% if (request.getAttribute("getIdPais") != "") {%> 
                    <html:text property="idPais" readonly="true" value='<%= String.valueOf(request.getAttribute("getIdPais"))%>'></html:text>
                    <% } else {%> 
                    <html:text property="idPais" value='<%= String.valueOf(request.getAttribute("getIdPais"))%>'></html:text>
                    <% }%> 
                </div>
                <div>
                    <label for="txtNombre">Nombre</label>
                    <html:text property="nombre" value='<%= String.valueOf(request.getAttribute("getNombre"))%>'></html:text>
                </div>
                <div><br>
                </div>
                <div>
                    <a class="boton" href="javascript:nuevo();">Nuevo</a> <a class="boton" id="submit" href="javascript:guardar();">Guardar</a> <% if (request.getAttribute("getIdPais") != "") {%> <a class="boton" href="javascript:eliminar();">Eliminar</a> <% }%> <a class="boton" href="javascript:atras();">Volver</a>
                </div>
                <%
                    if (request.getAttribute("respuesta") != "") {
                %>
                <div>
                    <%= String.valueOf(request.getAttribute("respuesta"))%>
                </div>
                <%  }
                %>
            </html:form>
        </div>
    </body>
</html:html>
