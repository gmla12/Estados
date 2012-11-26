<%-- 
    Document   : OpPuerto
    Created on : 18-septiembre-2012, 09:44:34
    Author     : ccastillor
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri= "/WEB-INF/c.tld" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" href="css/ui.all.css" rel="stylesheet" />
        <link type="text/css" href="css/comun.css" rel="stylesheet" />
        <link rel="stylesheet" type="text/css" media="all" href="niceforms_files/niceforms-default.css">
        <link rel="stylesheet" type="text/css" media="screen" href="css/ui.jqgrid.css" />
        <script src="Js/jquery-1.7.2.min.js" type="text/javascript"></script>
        <script src="Js/i18n/grid.locale-es.js" type="text/javascript"></script>
        <script src="Js/jquery.jqGrid.min.js" type="text/javascript"></script>
        <title>Opciones de Puertos</title>
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
        <%
            if (request.getAttribute("getOp") == "buscar") {
        %>
        <jsp:forward page="/OpPuerto.do">
            <jsp:param name="getOp" value="buscar"/>
        </jsp:forward>
        <%            }
        %>
        <script type="text/javascript">
            
            $(function(){ 
                $("#idPais").change(function(){
                    $.post("Jsp/Comun/getDepartamento.jsp",{ id:$(this).val() },function(data){$("#idDepartamento").html(data);$.post("Jsp/Comun/getMunicipio.jsp",{ id:document.forms[0].idDepartamento.value, idPais:document.forms[0].idPais.value },function(data){$("#idMunicipio").html(data);})})
                });
                $("#idDepartamento").change(function(){
                    $.post("Jsp/Comun/getMunicipio.jsp",{ id:$(this).val(), idPais:document.forms[0].idPais.value },function(data){$("#idMunicipio").html(data);})
                });
                jQuery("#list4").jqGrid({
                    url:'Jsp/Puerto/getGriddahico.jsp?op=bus',
                    datatype: "json",
                    colNames:['ID', 'Nombre','Pais','Departamento','Municipio', 'Editar'],
                    colModel:[
                        {name:'idPuerto',index:'idPuerto', width:50, sortable:false},
                        {name:'nombre',index:'nombre', width:160, sortable:false},
                        {name:'nombrePais',index:'nombrePais', width:160, sortable:false},
                        {name:'nombreDepartamento',index:'nombreDepartamento', width:160, sortable:false},
                        {name:'nombreMunicipio',index:'nombreMunicipio', width:160, sortable:false},
                        {name:'editar',index:'editar', width:110, formatter:'showlink', sortable:false}
                    ],
                    pager: '#prowed1',
                    width: 550,
                    height: "100%",
                    rowNum:10,
                    viewrecords: true,
                    caption: "Lista de Puertos"
                }); 
                jQuery("#list4").jqGrid('navGrid',"#prowed1",{edit:false,add:false,del:false,search:false});
            }); 

            function buscar(){
                document.forms[0].op.value="buscar";
                document.forms[0].id.value="";
                document.forms[0].submit();
            }
            
            function modifica(id){
                document.forms[0].op.value="modificar";
                document.forms[0].id.value=id;
                document.forms[0].submit();
            }

            function nuevo(){
                document.forms[0].op.value="nuevo";
                document.forms[0].id.value="";
                document.forms[0].submit();
            }
        </script>

    </head>
    <body  bgcolor="#EFFBFB">
        <html:form action="/OpPuerto.do" method="post">
            <input type="hidden" name="op" value=""> 
            <input type="hidden" name="id" value=""> 
            <fieldset>
                <legend>Consulta de Puertos</legend>
                <table>
                    <tr>
                        <td>Nombre<input type="text" name="bNombre" style="width:240px;" value="<%= session.getAttribute("getbNombre")%>"/> </td>
                        <td class="text">Pais<html:select property="idPais" styleId="idPais" size="1" style="width:240px;" disabled='false' value='<%= String.valueOf(request.getAttribute("getIdPais"))%>'>
                                <html:option value=""><c:out value='[Todos]'/></html:option>
                                <c:forEach items="${CMB_PAIS}" var="cat">
                                    <html:option value="${cat.idPais}"><c:out value='${cat.nombre}'/></html:option>
                                </c:forEach>
                            </html:select>
                        </td>
                        <td class="text">Departamento<html:select property="idDepartamento" styleId="idDepartamento" size="1" style="width:240px;" disabled='false' value='<%= String.valueOf(request.getAttribute("getIdDepartamento"))%>'>
                                <html:option value=""><c:out value='[Todos]'/></html:option>
                                <c:forEach items="${CMB_DEPARTAMENTO}" var="cat">
                                    <html:option value="${cat.idDepartamento}"><c:out value='${cat.nombre}'/></html:option>
                                </c:forEach>
                            </html:select>
                        </td>
                        <td class="text">Municipio<html:select property="idMunicipio" styleId="idMunicipio" size="1" style="width:240px;" disabled='false' value='<%= String.valueOf(request.getAttribute("getIdMunicipio"))%>'>
                                <html:option value=""><c:out value='[Todos]'/></html:option>
                                <c:forEach items="${CMB_MUNICIPIO}" var="cat">
                                    <html:option value="${cat.idMunicipio}"><c:out value='${cat.nombre}'/></html:option>
                                </c:forEach>
                            </html:select>
                        </td>
                        <td><a class="boton" href="javascript:buscar()">Buscar</a></td>
                        <td><a class="boton" href="javascript:nuevo()">Nuevo</a></td>
                    </tr>
                </table>
            </fieldset>
            <fieldset>
                <legend>Listado de Puertos</legend>
                <table>
                    <tr>
                        <td><table id="list4"></table></td>
                        <td><div id="prowed1"></div></td>
                    </tr>
                </table>
            </fieldset>
        </html:form>
    </body>
</html:html>
