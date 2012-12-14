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
        <jsp:forward page="/Auditoria2.do">
            <jsp:param name="getOp" value='buscar2'/>
        </jsp:forward>
        <%            }
        %>
        <script type="text/javascript">
            $(function(){ 
                jQuery("#list4").jqGrid({
                    url:'getGriddahico2.jsp?op=bus',
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
                jQuery("#list5").jqGrid({
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
                    pager: '#prowed2',
                    width: 900,
                    height: "100%",
                    rowNum:10,
                    viewrecords: true,
                    caption: "Lista de Auditoria"
                }); 
                jQuery("#list5").jqGrid('navGrid',"#prowed2",{edit:false,add:false,del:false,search:false});
            }); 
            
            function Busca(texto,letra)
            {
                for(i=0;i<texto.length;i++)
                {
                    if(texto.charAt(i)==letra) return i;
                }
                return false
            }
            
            function historico(id){
                var forma = document.forms[0];
                var x1 = Busca(id,"&");
                if (x1 != false)
                {
                    var id1 = id.substring(0,x1);
                    //alert(id1);
                    switch('<%= request.getParameter("num")%>'){
                        case '1':
                            var x2 = Busca(id1,"=");
                            if (x2 != false)
                            {
                                var id2 = id1.substring(x2+1,id1.Length);
                                window.location.href= '../Auditoria/Auditoria2.jsp?getOp=buscar&accion=referencia&formulario='+'<%= request.getParameter("formulario")%>'+'&referencia='+id2+'&num=1','Auditoria Eliminados','width=950,height=500,top=100%,left=100%,scrollbars=yes,resizable=yes';
                                emer.focus();
                            }
                        case '2':
                            var id2 = id.substring(x1+1,id.Length);
                            //alert(id2);
                            var x2 = Busca(id2,"&");
                            if (x2 != false)
                            {
                                var id3 = id2.substring(0,x2);
                                //alert(id3);
                                var x3 = Busca(id1,"=");
                                var x4 = Busca(id3,"=");
                                if ((x3 != false) || (x4 != false))
                                {
                                    var id4 = id1.substring(x3+1,id1.Length);
                                    var id5 = id3.substring(x4+1,id3.Length);
                                    //alert(id5+id4);
                                    window.location.href = '../Auditoria/Auditoria2.jsp?getOp=buscar&accion=referencia&formulario='+'<%= request.getParameter("formulario")%>'+'&referencia='+id5+id4+'&num=2','Auditoria Eliminados','width=950,height=500,top=100%,left=100%,scrollbars=yes,resizable=yes';
                                    //emer.focus();
                                }
                            }
                        case '3':
                            var id2 = id.substring(x1+1,id.Length);
                            //alert(id2);
                            var x2 = Busca(id2,"&");
                            if (x2 != false)
                            {
                                var id3 = id2.substring(0,x2);
                                //alert(id3);
                                var id4 = id2.substring(x2+1,id2.Length);
                                //alert(id4);
                                var x3 = Busca(id4,"&");
                                if (x3 != false)
                                {
                                    var id5 = id4.substring(0,x3);
                                    //alert(id5);
                                    var x4 = Busca(id1,"=");
                                    var x5 = Busca(id3,"=");
                                    var x6 = Busca(id5,"=");
                                    if ((x4 != false) || (x5 != false) || (x6 != false))
                                    {
                                        var id6 = id1.substring(x4+1,id1.Length);
                                        var id7 = id3.substring(x5+1,id3.Length);
                                        var id8 = id5.substring(x6+1,id5.Length);
                                        //alert(id8+id7+id6);
                                        window.location.href = '../Auditoria/Auditoria2.jsp?getOp=buscar&accion=referencia&formulario='+'<%= request.getParameter("formulario")%>'+'&referencia='+id8+id7+id6+'&num=3','Auditoria Eliminados','width=950,height=500,top=100%,left=100%,scrollbars=yes,resizable=yes';
                                        //emer.focus();
                                    }
                                }
                            }
                        }
                    }
                }
        </script>
        <style>
            .ui-jqgrid tr.jqgrow td {
                white-space: pre-line !important;
                word-wrap: break-word;
            }
        </style>
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
            <table>
                <tr>
                    <td><table id="list5"></table></td>
                    <td><div id="prowed2"></div></td>
                </tr>
            </table>
        </fieldset>
    </body>
</html:html>
