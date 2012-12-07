<%-- 
    Document   : Auditoria
    Created on : 11-octubre-2012, 16:58:34
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
        <link type="text/css" href="../../../css/ui.all.css" rel="stylesheet" />
        <link type="text/css" href="../../../css/comun.css" rel="stylesheet" />
        <link rel="stylesheet" type="text/css" media="screen" href="../../../css/ui.jqgrid.css" />
        <script src="../../../Js/jquery-1.7.2.min.js" type="text/javascript"></script>
        <script src="../../../Js/i18n/grid.locale-es.js" type="text/javascript"></script>
        <script src="../../../Js/jquery.jqGrid.min.js" type="text/javascript"></script>
        <title>Auditoria</title>
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
            String oop = request.getParameter("getOp").toString();
            if (oop.equals("buscar") == true) {
        %>
        <jsp:forward page="/Auditoria.do">
            <jsp:param name="getOp" value='buscar2'/>
        </jsp:forward>
        <%            }
        %>
        <script type="text/javascript">
            $(function(){ 
                jQuery("#list4").jqGrid({
                    url:'getGriddahico.jsp?op=bus',
                    datatype: "json",
                    colNames:['Usuario', 'Fecha', 'Accion', 'Valor Anterior', 'Valor Nuevo'],
                    colModel:[
                        {name:'nombreUsu',index:'NombreUsu', width:160, sortable:false},
                        {name:'fecha',index:'fecha', width:160, sortable:false},
                        {name:'accion',index:'accion', width:50, sortable:false},
                        {name:'valorAnterior',index:'valorAnterior', width:200, sortable:false},
                        {name:'valorNuevo',index:'valorNuevo', width:200, sortable:false}
                    ],
                    pager: '#prowed1',
                    width: 900,
                    height: "100%",
                    rowNum:10,
                    viewrecords: true,
                    caption: "Lista de Auditoria"
                }); 
                jQuery("#list4").jqGrid('navGrid',"#prowed1",{edit:false,add:false,del:false,search:false});
            }); 
            
            function historico(id){
                var forma = document.forms[0];
                
                var emer = window.open('../Estados/Jsp/Log/Auditoria/Auditoria.jsp?getOp=buscar&accion=buscar&formulario='+formulario+'&referencia='+id,'Auditoria Paises','width=950,height=500,top=100%,left=100%,scrollbars=yes,resizable=yes');
                emer.focus();
            }
        </script>

    </head>
    <body  bgcolor="#EFFBFB">
        <fieldset>
            <legend>Listado de Auditoria</legend>
            <table>
                <tr>
                    <td><table id="list4"></table></td>
                    <td><div id="prowed1"></div></td>
                </tr>
            </table>
        </fieldset>
    </body>
</html:html>
