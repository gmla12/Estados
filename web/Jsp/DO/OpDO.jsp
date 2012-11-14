<%-- 
    Document   : OpDO
    Created on : 19-septiembre-2012, 10:24:34
    Author     : Gilberth
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
        <script type="text/javascript" src="Js/jquery-1.3.2.js"></script>
        <script type="text/javascript" src="Js/ui/ui.core.js"></script>
        <script type="text/javascript" src="Js/ui/ui.datepicker.js"></script>
        <link rel="stylesheet" type="text/css" media="screen" href="css/ui.jqgrid.css" />
        <script src="Js/jquery-1.7.2.min.js" type="text/javascript"></script>
        <script src="Js/i18n/grid.locale-es.js" type="text/javascript"></script>
        <script src="Js/jquery.jqGrid.min.js" type="text/javascript"></script>
        <title>Opciones DOs</title>
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
        <jsp:forward page="/OpDO.do">
            <jsp:param name="getOp" value="buscar"/>
        </jsp:forward>
        <%            }
        %>

        <script type="text/javascript">
            $(function(){ 
                jQuery("#list4").jqGrid({
                    url:'Jsp/DO/getGriddahico.jsp?op=bus',
                    datatype: "json",
                    colNames:['ID', 'Nombre', 'Tipo de Documento', 'Identificacion', 'Editar'],
                    colModel:[
                        {name:'idPais',index:'idPais', width:15, sortable:false},
                        {name:'nombre',index:'nombre', width:90, sortable:false},
                        {name:'nombreTipoDoc',index:'nombreTipoDoc', width:70, sortable:false},
                        {name:'identificacion',index:'identificacion', width:40, sortable:false},
                        {name:'editar',index:'editar', width:40, formatter:'showlink', sortable:false}
                    ],
                    pager: '#prowed1',
                    width: 590,
                    height: "100%",
                    rowNum:10,
                    viewrecords: true,
                    caption: "Lista de DOs"
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
        <html:form action="/OpDO.do" method="post">
            <input type="hidden" name="op" value=""> 
            <input type="hidden" name="id" value=""> 
            <fieldset>
                <legend>Consulta de DO</legend>
                <table>
                    <tr>
                        <td>DO<input type="text" name="bDO" value="<%= session.getAttribute("getbDO")%>"/> </td>
                        <td>Referencia<input type="text" name="bReferencia" value="<%= session.getAttribute("getbReferencia")%>"/> </td>
                        <td>
                        </td>
                        <td><a class="boton" href="javascript:buscar()">Buscar</a></td>
                        <td><a class="boton" href="javascript:nuevo()">Nuevo</a></td>
                    </tr>
                    <tr>
                        <td>Sucursal<html:select property="bSucursal"  size="1" style="width:150px;" value='<%= String.valueOf(session.getAttribute("getbSucursal"))%>'>
                                <html:option value=""><c:out value='[Todos]'/></html:option>    
                                <c:forEach items="${CMB_SUCURSAL}" var="cat">
                                    <html:option value="${cat.idSucursal}"><c:out value='${cat.nombre}'/></html:option>
                                </c:forEach>
                            </html:select>
                        </td>
                        <td>Puerto<html:select property="bPuerto"  size="1" style="width:150px;" value='<%= String.valueOf(session.getAttribute("getbPuerto"))%>'>
                                <html:option value=""><c:out value='[Todos]'/></html:option>    
                                <c:forEach items="${CMB_PUERTO}" var="cat">
                                    <html:option value="${cat.idPuerto}"><c:out value='${cat.nombre}'/></html:option>
                                </c:forEach>
                            </html:select>
                        </td>
                        <td>
                        </td>
                        <td>
                        </td>
                        <td>
                        </td>
                    </tr>
                    <tr>
                        <td>Cliente<input type="text" name="bIdCliente" size="15" value="<%= session.getAttribute("getbIdCliente")%>"/> </td>
                        <td><input type="text" name="bCliente" size="15" value="<%= session.getAttribute("getbCliente")%>"/> </td>
                        <td>BL<input type="text" name="bBL" size="15" value="<%= session.getAttribute("getbBL")%>"/> </td>
                        <td>
                        </td>
                        <td>
                        </td>
                    </tr>
                    <tr>
                        <td>Pedido<input type="text" name="bPedido" size="15" value="<%= session.getAttribute("getbPedido")%>"/> </td>
                        <td>Lote<input type="text" name="bLote" size="15" value="<%= session.getAttribute("getbLote")%>"/> </td>
                        <td>
                        </td>
                        <td>
                        </td>
                        <td>
                        </td>
                    </tr>
                    <tr>
                        <td>Descripcion<input type="text" name="bDescripcion" size="30" value="<%= session.getAttribute("getbDescripcion")%>"/> </td>
                        <td>
                        </td>
                        <td>
                        </td>
                        <td>
                        </td>
                        <td>
                        </td>
                    </tr>
                </table>
            </fieldset>
            <fieldset>
                <legend>Listado de DOs</legend>
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
