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
        <title>Paises</title>
        <link type="text/css" href="css/comun.css" rel="stylesheet" />
        <script src="Js/jquery-1.7.2.min.js" type="text/javascript"></script>
        <script type="text/javascript" src="Js/jquery.validate.js"></script>
        <script src="Js/i18n/messages_es.js" type="text/javascript"></script>
        <script type="text/javascript" src="Js/jquery.collapsible.min.js"></script>
        <script type="text/javascript">
            $(document).ready(function() {
                //collapsible management
                $('.collapsible').collapsible({
                    defaultOpen: 'section2,section3'
                });
            });
        </script>
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
                document.forms[0].idPais.readOnly=false;
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
                //var emer = window.open('../Auditoria?accion=Q&subAccion=D&hnumeroDo='+forma.hnumeroDo,'liquidarActualizar','width=500,height=500,top=100%,left=100%,scrollbars=yes,resizable=yes');
                var emer = window.open('../Estados/Jsp/Auditoria/Auditoria.jsp','liquidarActualizar','width=500,height=500,top=100%,left=100%,scrollbars=yes,resizable=yes');
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
    </head>
    <body>
        <div id="stylized" class="myform">
            <html:form styleClass="forma" styleId="forma" method="post" action="/Pais">
                <input type="hidden" name="op" value=""> 
                <input type="hidden" name="idUsu" value=""> 
                <h1>Paises</h1>
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
                    <a class="boton" href="javascript:nuevo();">Nuevo</a> <a class="boton" id="submit" href="javascript:guardar();">Guardar</a> <% if (request.getAttribute("getIdPais") != "") {%> <a class="boton" href="javascript:eliminar();">Eliminar</a> <% }%> <a class="boton" href="javascript:atras();">Volver</a>
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
