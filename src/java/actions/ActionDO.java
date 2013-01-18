/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import com.mysql.jdbc.Connection;
import forms.DOForm;
import forms.bean.BeanDO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.GestionDO;
import modelo.GestionItem;
import modelo.log.GestionAuditoria;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Mario
 */
public class ActionDO extends Action {

    public ActionDO() {

        super();

    }

    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception {

        DOForm fo = (DOForm) form;
        GestionDO gr = new GestionDO();
        GestionItem grItem = new GestionItem();
        GestionAuditoria gA = new GestionAuditoria();
        HttpSession session = request.getSession();
        System.out.println("Action DO");

        if (fo.getOp().equals("nuevo")) {
            System.out.println("Nuevo ActionDO");

            request.setAttribute("getIdDOs", fo.getIdDOs());
            request.setAttribute("getDO", fo.getDO());
            request.setAttribute("getIdCliente", fo.getIdCliente());
            request.setAttribute("getIdSucursal", fo.getIdSucursal());
            request.setAttribute("getIdPuerto", fo.getIdPuerto());
            request.setAttribute("getLote", fo.getLote());
            request.setAttribute("getBL", fo.getBL());
            request.setAttribute("getIdTipoMercancia", fo.getIdTipoMercancia());
            request.setAttribute("getObservaciones", fo.getObservaciones());

            ArrayList<Object> resultado;
            Connection cn;
            resultado = gr.ObtenerConexion();
            if ((Boolean) resultado.get(0) == false) {

                cn = (Connection) resultado.get(1);
                ArrayList<Object> resultado1;
                resultado1 = gr.autoCommint(false, cn);
                if ((Boolean) resultado1.get(0) == false) {

                    ArrayList<Object> resultado2;
                    resultado2 = gr.IngresaDO(fo, false, null);
                    if ((Boolean) resultado2.get(0) == false) {

                        fo.setIdDOs(Integer.valueOf(resultado2.get(1).toString()));

                        ArrayList<Object> resultado3;
                        resultado3 = gA.BuscarFormulario("do", true, cn);
                        if ((Boolean) resultado3.get(0) == false) {

                            ArrayList<Object> resultado4;
                            String valor_nuevo = "id=" + fo.getIdDOs() + "&do=" + fo.getDO() + "&idCliente=" + fo.getIdCliente() + "&idSucursal=" + fo.getIdSucursal() + "&lote=" + fo.getLote() + "&bl=" + fo.getBL() + "&idPuerto=" + fo.getIdPuerto() + "&idTipoMercancia=" + fo.getIdTipoMercancia() + "&observaciones=" + fo.getObservaciones();
                            resultado4 = gA.IngresaAuditoria("Nuevo", "", valor_nuevo, fo.getIdUsu(), Integer.valueOf(gA.getIdFormulario().toString()), String.valueOf(fo.getIdDOs()), true, cn);
                            if ((Boolean) resultado4.get(0) == false) {

                                ArrayList<Object> resultado5;
                                resultado5 = gr.commint(cn);
                                if ((Boolean) resultado5.get(0) == false) {

                                    ArrayList<Object> resultado6;
                                    resultado6 = gr.autoCommint(true, cn);
                                    if ((Boolean) resultado6.get(0) == false) {

                                        ArrayList<Object> resultado7;
                                        resultado7 = gr.MostrarDOFormulario(String.valueOf(fo.getIdDOs()), false, null);
                                        if ((Boolean) resultado7.get(0) == false) {

                                            request.setAttribute("getIdDOs", gr.getIdDOs());
                                            request.setAttribute("getDO", gr.getDO());
                                            request.setAttribute("getIdCliente", gr.getIdCliente());
                                            request.setAttribute("getIdSucursal", gr.getIdSucursal());
                                            request.setAttribute("getIdPuerto", gr.getIdPuerto());
                                            request.setAttribute("getLote", gr.getLote());
                                            request.setAttribute("getBL", gr.getBL());
                                            request.setAttribute("getIdTipoMercancia", gr.getIdTipoMercancia());
                                            request.setAttribute("getObservaciones", gr.getObservaciones());
                                            request.setAttribute("getFechaModificacion", gr.getFechaModificacion());
                                            request.setAttribute("getNombreUsu", gr.getNombreUsu());
                                            //item
                                            request.setAttribute("getIdItems", "");
                                            request.setAttribute("getIdDOs", "");
                                            request.setAttribute("getReferencia", "");
                                            request.setAttribute("getPedido", "");
                                            request.setAttribute("getDescripcion", "");
                                            request.setAttribute("getClienteFinal", "");
                                            request.setAttribute("getIdProveedor", "");
                                            request.setAttribute("getFechaEstimadaArribo", "");
                                            request.setAttribute("getFechaLlegada", "");
                                            request.setAttribute("getFechaDocumentos", "");
                                            request.setAttribute("getFechaDocumentosOK", "");
                                            request.setAttribute("getFechaAceptacion", "");
                                            request.setAttribute("getFechaSolicitudAnticipo", "");
                                            request.setAttribute("getFechaPagoTributo", "");
                                            request.setAttribute("getFechaLevante", "");
                                            request.setAttribute("getFechaPoder", "");
                                            request.setAttribute("getFechaEnvioDocumentos", "");
                                            request.setAttribute("getFechaAutenticacion", "");
                                            request.setAttribute("getFechaLiberacionBL", "");
                                            request.setAttribute("getFechaPlanilla", "");
                                            request.setAttribute("getFechaSOAT", "");
                                            request.setAttribute("getFechaTransitoLibre", "");
                                            request.setAttribute("getFechaDespacho", "");
                                            request.setAttribute("getFechaEntregaFacturacion", "");
                                            request.setAttribute("getFechaFacturacion", "");
                                            request.setAttribute("getNumeroFactura", "");
                                            //para validar si se modifico un campo
                                            session.setAttribute("getDOIdDOs", gr.getIdDOs());
                                            session.setAttribute("getDODO", gr.getDO());
                                            session.setAttribute("getDOIdCliente", gr.getIdCliente());
                                            session.setAttribute("getDOIdSucursal", gr.getIdSucursal());
                                            session.setAttribute("getDOIdPuerto", gr.getIdPuerto());
                                            session.setAttribute("getDOLote", gr.getLote());
                                            session.setAttribute("getDOBL", gr.getBL());
                                            session.setAttribute("getDOIdTipoMercancia", gr.getIdTipoMercancia());
                                            session.setAttribute("getDOObservaciones", gr.getObservaciones());
                                            request.setAttribute("respuesta", "Registro ingresado correctamente.");
                                            System.out.println("Action Ingreso DO");
                                            return mapping.findForward("ok");

                                        } else {

                                            request.setAttribute("error", resultado7.get(1));
                                            return mapping.findForward("error");

                                        }

                                    } else {

                                        request.setAttribute("error", resultado6.get(1));
                                        return mapping.findForward("error");

                                    }

                                } else {

                                    request.setAttribute("error", resultado5.get(1));
                                    return mapping.findForward("error");

                                }

                            } else {

                                request.setAttribute("error", resultado4.get(1));
                                return mapping.findForward("error");

                            }

                        } else {

                            request.setAttribute("error", resultado3.get(1));
                            return mapping.findForward("error");

                        }

                    } else {

                        request.setAttribute("error", resultado2.get(1));
                        return mapping.findForward("error");

                    }

                } else {

                    request.setAttribute("error", resultado1.get(1));
                    return mapping.findForward("error");

                }

            } else {

                request.setAttribute("error", resultado.get(1));
                return mapping.findForward("error");

            }

        } else if (fo.getOp().equals("modificar")) {
            System.out.println("Modificar ActionDO");

            request.setAttribute("getIdDOs", fo.getIdDOs());
            request.setAttribute("getDO", fo.getDO());
            request.setAttribute("getIdCliente", fo.getIdCliente());
            request.setAttribute("getIdSucursal", fo.getIdSucursal());
            request.setAttribute("getIdPuerto", fo.getIdPuerto());
            request.setAttribute("getLote", fo.getLote());
            request.setAttribute("getBL", fo.getBL());
            request.setAttribute("getIdTipoMercancia", fo.getIdTipoMercancia());
            request.setAttribute("getObservaciones", fo.getObservaciones());
            //item
            request.setAttribute("getIdItems", fo.getIdItems());
            request.setAttribute("getReferencia", fo.getReferencia());
            request.setAttribute("getPedido", fo.getPedido());
            request.setAttribute("getDescripcion", fo.getDescripcion());
            request.setAttribute("getClienteFinal", fo.getClienteFinal());
            request.setAttribute("getIdProveedor", fo.getIdProveedor());
            request.setAttribute("getFechaEstimadaArribo", fo.getFechaEstimadaArribo());
            request.setAttribute("getFechaLlegada", fo.getFechaLlegada());
            request.setAttribute("getFechaDocumentos", fo.getFechaDocumentos());
            request.setAttribute("getFechaDocumentosOK", fo.getFechaDocumentosOK());
            request.setAttribute("getFechaAceptacion", fo.getFechaAceptacion());
            request.setAttribute("getFechaSolicitudAnticipo", fo.getFechaSolicitudAnticipo());
            request.setAttribute("getFechaPagoTributo", fo.getFechaPagoTributo());
            request.setAttribute("getFechaLevante", fo.getFechaLevante());
            request.setAttribute("getFechaPoder", fo.getFechaPoder());
            request.setAttribute("getFechaEnvioDocumentos", fo.getFechaEnvioDocumentos());
            request.setAttribute("getFechaAutenticacion", fo.getFechaAutenticacion());
            request.setAttribute("getFechaLiberacionBL", fo.getFechaLiberacionBL());
            request.setAttribute("getFechaPlanilla", fo.getFechaPlanilla());
            request.setAttribute("getFechaSOAT", fo.getFechaSOAT());
            request.setAttribute("getFechaTransitoLibre", fo.getFechaTransitoLibre());
            request.setAttribute("getFechaDespacho", fo.getFechaDespacho());
            request.setAttribute("getFechaEntregaFacturacion", fo.getFechaEntregaFacturacion());
            request.setAttribute("getFechaFacturacion", fo.getFechaFacturacion());
            request.setAttribute("getNumeroFactura", fo.getNumeroFactura());

            ArrayList<Object> resultado;
            java.sql.Connection cn;
            resultado = gr.ObtenerConexion();
            if ((Boolean) resultado.get(0) == false) {

                cn = (java.sql.Connection) resultado.get(1);
                ArrayList<Object> resultado1;
                resultado1 = gr.autoCommint(false, cn);
                if ((Boolean) resultado1.get(0) == false) {

                    ArrayList<Object> resultado2;
                    resultado2 = gr.ModificaDO(fo, true, cn);
                    if ((Boolean) resultado2.get(0) == false) {

                        ArrayList<Object> resultado3;
                        resultado3 = gA.BuscarFormulario("do", true, cn);
                        if ((Boolean) resultado3.get(0) == false) {

                            ArrayList<Object> resultado4;

                            //valida si hubo un cambio en algun campo
                            Integer NIdDOs = fo.getIdDOs();
                            String NDO = fo.getDO();
                            String NLote = fo.getLote();
                            String NBL = fo.getBL();
                            String NObservaciones = fo.getObservaciones();
                            String AIdDOs = session.getAttribute("getDOIdDOs").toString();
                            String ADO = session.getAttribute("getDODO").toString();
                            String ALote = session.getAttribute("getDOLote").toString();
                            String ABL = session.getAttribute("getDOBL").toString();
                            String AObservaciones = session.getAttribute("getDOObservaciones").toString();
                            String valor_anterior = "";
                            String valor_nuevo = "";
                            if (NIdDOs.toString().equals(AIdDOs) == false) {
                                valor_nuevo = "id='" + NIdDOs.toString() + "'";
                                valor_anterior = "id='" + AIdDOs + "'";
                            }
                            if (NDO.equals(ADO) == false) {
                                if (!valor_nuevo.equals("")) {
                                    valor_nuevo = valor_nuevo + "&";
                                    valor_anterior = valor_anterior + "&";
                                }
                                valor_nuevo += "do='" + NDO + "'";
                                valor_anterior += "do='" + ADO + "'";
                            }
                            if (session.getAttribute("getDOIdCliente") != null) {
                                String AIdCliente = session.getAttribute("getDOIdCliente").toString();
                                String NIdCliente = String.valueOf(fo.getIdCliente());
                                if (NIdCliente.equals(AIdCliente) == false) {
                                    if (!valor_nuevo.equals("")) {
                                        valor_nuevo = valor_nuevo + "&";
                                        valor_anterior = valor_anterior + "&";
                                    }
                                    valor_nuevo += "idCliente='" + NIdCliente + "'";
                                    valor_anterior += "idCliente='" + AIdCliente + "'";
                                }
                            }
                            if (session.getAttribute("getDOIdSucursal") != null) {
                                String AIdSucursal = session.getAttribute("getDOIdSucursal").toString();
                                String NIdSucursal = String.valueOf(fo.getIdSucursal());
                                if (NIdSucursal.equals(AIdSucursal) == false) {
                                    if (!valor_nuevo.equals("")) {
                                        valor_nuevo = valor_nuevo + "&";
                                        valor_anterior = valor_anterior + "&";
                                    }
                                    valor_nuevo += "idSucursal='" + NIdSucursal + "'";
                                    valor_anterior += "idSucursal='" + AIdSucursal + "'";
                                }
                            }
                            if (session.getAttribute("getDOIdPuerto") != null) {
                                String AIdPuerto = session.getAttribute("getDOIdPuerto").toString();
                                String NIdPuerto = String.valueOf(fo.getIdPuerto());
                                if (NIdPuerto.equals(AIdPuerto) == false) {
                                    if (!valor_nuevo.equals("")) {
                                        valor_nuevo = valor_nuevo + "&";
                                        valor_anterior = valor_anterior + "&";
                                    }
                                    valor_nuevo += "idPuerto='" + NIdPuerto + "'";
                                    valor_anterior += "idPuerto='" + AIdPuerto + "'";
                                }
                            }
                            if (NLote.equals(ALote) == false) {
                                if (!valor_nuevo.equals("")) {
                                    valor_nuevo = valor_nuevo + "&";
                                    valor_anterior = valor_anterior + "&";
                                }
                                valor_nuevo += "lote='" + NLote + "'";
                                valor_anterior += "lote='" + ALote + "'";
                            }
                            if (NBL.equals(ABL) == false) {
                                if (!valor_nuevo.equals("")) {
                                    valor_nuevo = valor_nuevo + "&";
                                    valor_anterior = valor_anterior + "&";
                                }
                                valor_nuevo += "bl='" + NBL + "'";
                                valor_anterior += "bl='" + ABL + "'";
                            }
                            if (session.getAttribute("getDOIdTipoMercancia") != null) {
                                String AIdTipoMercancia = session.getAttribute("getDOIdTipoMercancia").toString();
                                String NIdTipoMercancia = String.valueOf(fo.getIdTipoMercancia());
                                if (NIdTipoMercancia.equals(AIdTipoMercancia) == false) {
                                    if (!valor_nuevo.equals("")) {
                                        valor_nuevo = valor_nuevo + "&";
                                        valor_anterior = valor_anterior + "&";
                                    }
                                    valor_nuevo += "idTipoMercancia='" + NIdTipoMercancia + "'";
                                    valor_anterior += "idTipoMercancia='" + AIdTipoMercancia + "'";
                                }
                            }
                            if (NObservaciones.equals(AObservaciones) == false) {
                                if (!valor_nuevo.equals("")) {
                                    valor_nuevo = valor_nuevo + "&";
                                    valor_anterior = valor_anterior + "&";
                                }
                                valor_nuevo += "observaciones='" + NObservaciones + "'";
                                valor_anterior += "observaciones='" + AObservaciones + "'";
                            }

                            resultado4 = gA.IngresaAuditoria("Modificar", valor_anterior, valor_nuevo, fo.getIdUsu(), Integer.valueOf(gA.getIdFormulario().toString()), String.valueOf(fo.getIdDOs()), true, cn);
                            if ((Boolean) resultado4.get(0) == false) {

                                ArrayList<Object> resultado5;
                                resultado5 = gr.commint(cn);
                                if ((Boolean) resultado5.get(0) == false) {

                                    ArrayList<Object> resultado6;
                                    resultado6 = gr.autoCommint(true, cn);
                                    if ((Boolean) resultado6.get(0) == false) {

                                        ArrayList<Object> resultado7;
                                        resultado7 = gr.MostrarDOFormulario(String.valueOf(fo.getIdDOs()), false, null);
                                        if ((Boolean) resultado7.get(0) == false) {

                                            request.setAttribute("getIdDOs", gr.getIdDOs());
                                            request.setAttribute("getDO", gr.getDO());
                                            request.setAttribute("getIdCliente", gr.getIdCliente());
                                            request.setAttribute("getIdSucursal", gr.getIdSucursal());
                                            request.setAttribute("getIdPuerto", gr.getIdPuerto());
                                            request.setAttribute("getLote", gr.getLote());
                                            request.setAttribute("getBL", gr.getBL());
                                            request.setAttribute("getIdTipoMercancia", gr.getIdTipoMercancia());
                                            request.setAttribute("getObservaciones", gr.getObservaciones());
                                            request.setAttribute("getFechaModificacion", gr.getFechaModificacion());
                                            request.setAttribute("getNombreUsu", gr.getNombreUsu());
                                            //para validar si se modifico un campo
                                            session.setAttribute("getDOIdDOs", gr.getIdDOs());
                                            session.setAttribute("getDODO", gr.getDO());
                                            session.setAttribute("getDOIdCliente", gr.getIdCliente());
                                            session.setAttribute("getDOIdSucursal", gr.getIdSucursal());
                                            session.setAttribute("getDOIdPuerto", gr.getIdPuerto());
                                            session.setAttribute("getDOLote", gr.getLote());
                                            session.setAttribute("getDOBL", gr.getBL());
                                            session.setAttribute("getDOIdTipoMercancia", gr.getIdTipoMercancia());
                                            session.setAttribute("getDOObservaciones", gr.getObservaciones());
                                            request.setAttribute("respuesta", "Registro modificado correctamente.");
                                            System.out.println("Action Modificar DO");
                                            return mapping.findForward("ok");

                                        } else {

                                            request.setAttribute("error", resultado7.get(1));
                                            return mapping.findForward("error");

                                        }

                                    } else {

                                        request.setAttribute("error", resultado6.get(1));
                                        return mapping.findForward("error");

                                    }

                                } else {

                                    request.setAttribute("error", resultado5.get(1));
                                    return mapping.findForward("error");

                                }

                            } else {

                                request.setAttribute("error", resultado4.get(1));
                                return mapping.findForward("error");

                            }

                        } else {

                            request.setAttribute("error", resultado3.get(1));
                            return mapping.findForward("error");

                        }

                    } else {

                        request.setAttribute("error", resultado2.get(1));
                        return mapping.findForward("error");

                    }

                } else {

                    request.setAttribute("error", resultado1.get(1));
                    return mapping.findForward("error");

                }

            } else {

                request.setAttribute("error", resultado.get(1));
                return mapping.findForward("error");

            }

        } else if (fo.getOp().equals("eliminar")) {
            //verificar si ya se eliminaron todos los items
            System.out.println("Eliminar ActionDO");
            request.setAttribute("getIdDOs", "");
            request.setAttribute("getDO", "");
            request.setAttribute("getIdCliente", "");
            request.setAttribute("getIdSucursal", "");
            request.setAttribute("getIdPuerto", "");
            request.setAttribute("getLote", "");
            request.setAttribute("getBL", "");
            request.setAttribute("getIdTipoMercancia", "");
            request.setAttribute("getObservaciones", "");
            request.setAttribute("getFechaModificacion", "");
            request.setAttribute("getNombreUsu", "");

            ArrayList<Object> resultado;
            java.sql.Connection cn;
            resultado = gr.ObtenerConexion();
            if ((Boolean) resultado.get(0) == false) {

                cn = (java.sql.Connection) resultado.get(1);
                ArrayList<Object> resultado1;
                resultado1 = gr.autoCommint(false, cn);
                if ((Boolean) resultado1.get(0) == false) {

                    ArrayList<Object> resultado2;
                    resultado2 = (ArrayList) gr.EliminaDO(fo, false, null);
                    if ((Boolean) resultado2.get(0) == false) {

                        ArrayList<Object> resultado3;
                        resultado3 = gA.BuscarFormulario("do", true, cn);
                        if ((Boolean) resultado3.get(0) == false) {

                            ArrayList<Object> resultado4;
                            String valor_anterior = "id=" + fo.getIdDOs() + "&do=" + fo.getDO() + "&idCliente=" + fo.getIdCliente() + "&idSucursal=" + fo.getIdSucursal() + "&idPuerto=" + fo.getIdPuerto() + "&lote=" + fo.getLote() + "&bl=" + fo.getBL() + "&idTipoMercancia=" + fo.getIdTipoMercancia() + "&observaciones=" + fo.getObservaciones();
                            resultado4 = gA.IngresaAuditoria("Eliminar", valor_anterior, "", fo.getIdUsu(), Integer.valueOf(gA.getIdFormulario().toString()), String.valueOf(fo.getIdDOs()), true, cn);
                            if ((Boolean) resultado4.get(0) == false) {

                                ArrayList<Object> resultado5;
                                resultado5 = gr.commint(cn);
                                if ((Boolean) resultado5.get(0) == false) {

                                    ArrayList<Object> resultado6;
                                    resultado6 = gr.autoCommint(true, cn);
                                    if ((Boolean) resultado6.get(0) == false) {

                                        request.setAttribute("respuesta", "Registro eliminado correctamente.");
                                        System.out.println("Action Eliminar DO");

                                        return mapping.findForward("ok");

                                    } else {

                                        request.setAttribute("error", resultado6.get(1));
                                        return mapping.findForward("error");

                                    }

                                } else {

                                    request.setAttribute("error", resultado5.get(1));
                                    return mapping.findForward("error");

                                }

                            } else {

                                request.setAttribute("error", resultado4.get(1));
                                return mapping.findForward("error");

                            }

                        } else {

                            request.setAttribute("error", resultado3.get(1));
                            return mapping.findForward("error");

                        }

                    } else {

                        request.setAttribute("error", resultado2.get(1));
                        return mapping.findForward("error");

                    }

                } else {

                    request.setAttribute("error", resultado1.get(1));
                    return mapping.findForward("error");

                }

            } else {

                request.setAttribute("error", resultado.get(1));
                return mapping.findForward("error");

            }

        } else if (fo.getOp().equals("nuevoItem")) {
            System.out.println("Nuevo ActionDO - Item");

            request.setAttribute("getIdDOs", fo.getIdDOs());
            request.setAttribute("getDO", fo.getDO());
            request.setAttribute("getIdCliente", fo.getIdCliente());
            request.setAttribute("getIdSucursal", fo.getIdSucursal());
            request.setAttribute("getIdPuerto", fo.getIdPuerto());
            request.setAttribute("getLote", fo.getLote());
            request.setAttribute("getBL", fo.getBL());
            request.setAttribute("getIdTipoMercancia", fo.getIdTipoMercancia());
            request.setAttribute("getObservaciones", fo.getObservaciones());

            ArrayList<Object> resultado;
            Connection cn;
            resultado = grItem.ObtenerConexion();
            if ((Boolean) resultado.get(0) == false) {

                cn = (Connection) resultado.get(1);
                ArrayList<Object> resultado1;
                resultado1 = grItem.autoCommint(false, cn);
                if ((Boolean) resultado1.get(0) == false) {

                    ArrayList<Object> resultado2;
                    resultado2 = grItem.IngresaItem(fo, false, null);
                    if ((Boolean) resultado2.get(0) == false) {

                        fo.setIdItems(Integer.valueOf(resultado2.get(1).toString()));

                        ArrayList<Object> resultado3;
                        resultado3 = gA.BuscarFormulario("do", true, cn);
                        if ((Boolean) resultado3.get(0) == false) {

                            ArrayList<Object> resultado4;
                            String valor_nuevo = "id=" + fo.getIdDOs() + "&do=" + fo.getDO() + "&idItem=" + fo.getIdItems() + "&referencia=" + fo.getReferencia() + "&pedido=" + fo.getPedido() + "&descripcion=" + fo.getDescripcion() + "&clienteFinal=" + fo.getClienteFinal() + "&idProveedor=" + fo.getIdProveedor() + "&fechaEstimadaArribo=" + fo.getFechaEstimadaArribo();
                            valor_nuevo += "&fechaLlegada=" + fo.getFechaLlegada() + "&fechaDocumentos=" + fo.getFechaDocumentos() + "&fechaDocumentosOK=" + fo.getFechaDocumentosOK() + "&FechaAceptacion=" + fo.getFechaAceptacion() + "&fechaSolicitudAnticipo=" + fo.getFechaSolicitudAnticipo() + "&fechaPagoTributo=" + fo.getFechaPagoTributo() + "&fechaLevante=" + fo.getFechaLevante() + "&fechaPoder=" + fo.getFechaPoder() + "&fechaEnvioDocumentos=" + fo.getFechaEnvioDocumentos();
                            valor_nuevo += "&fechaAutenticacion=" + fo.getFechaAutenticacion() + "&fechaLiberacionBL=" + fo.getFechaLiberacionBL() + "&fechaPlanilla=" + fo.getFechaPlanilla() + "&FechaSOAT=" + fo.getFechaSOAT() + "&fechaTransitoLibre=" + fo.getFechaTransitoLibre() + "&fechaDespacho=" + fo.getFechaDespacho() + "&fechaEntregaFacturacion=" + fo.getFechaEntregaFacturacion() + "&fechaFacturacion=" + fo.getFechaFacturacion() + "&numeroFactura=" + fo.getNumeroFactura();
                            resultado4 = gA.IngresaAuditoria("Nuevo Item", "", valor_nuevo, fo.getIdUsu(), Integer.valueOf(gA.getIdFormulario().toString()), String.valueOf(fo.getIdDOs()), true, cn);
                            if ((Boolean) resultado4.get(0) == false) {

                                ArrayList<Object> resultado5;
                                resultado5 = grItem.commint(cn);
                                if ((Boolean) resultado5.get(0) == false) {

                                    ArrayList<Object> resultado6;
                                    resultado6 = grItem.autoCommint(true, cn);
                                    if ((Boolean) resultado6.get(0) == false) {

                                        ArrayList<Object> resultado7;
                                        resultado7 = grItem.MostrarItemFormulario(String.valueOf(fo.getIdItems()), String.valueOf(fo.getIdDOs()), false, null);
                                        if ((Boolean) resultado7.get(0) == false) {

                                            request.setAttribute("getIdItems", grItem.getIdItems());
                                            request.setAttribute("getIdDOs", grItem.getIdDOs());
                                            request.setAttribute("getReferencia", grItem.getReferencia());
                                            request.setAttribute("getPedido", grItem.getPedido());
                                            request.setAttribute("getDescripcion", grItem.getDescripcion());
                                            request.setAttribute("getClienteFinal", grItem.getClienteFinal());
                                            request.setAttribute("getIdProveedor", grItem.getIdProveedor());
                                            request.setAttribute("getFechaEstimadaArribo", grItem.getFechaEstimadaArribo());
                                            request.setAttribute("getFechaLlegada", grItem.getFechaLlegada());
                                            request.setAttribute("getFechaDocumentos", grItem.getFechaDocumentos());
                                            request.setAttribute("getFechaDocumentosOK", grItem.getFechaDocumentosOK());
                                            request.setAttribute("getFechaAceptacion", grItem.getFechaAceptacion());
                                            request.setAttribute("getFechaSolicitudAnticipo", grItem.getFechaSolicitudAnticipo());
                                            request.setAttribute("getFechaPagoTributo", grItem.getFechaPagoTributo());
                                            request.setAttribute("getFechaLevante", grItem.getFechaLevante());
                                            request.setAttribute("getFechaPoder", grItem.getFechaPoder());
                                            request.setAttribute("getFechaEnvioDocumentos", grItem.getFechaEnvioDocumentos());
                                            request.setAttribute("getFechaAutenticacion", grItem.getFechaAutenticacion());
                                            request.setAttribute("getFechaLiberacionBL", grItem.getFechaLiberacionBL());
                                            request.setAttribute("getFechaPlanilla", grItem.getFechaPlanilla());
                                            request.setAttribute("getFechaSOAT", grItem.getFechaSOAT());
                                            request.setAttribute("getFechaTransitoLibre", grItem.getFechaTransitoLibre());
                                            request.setAttribute("getFechaDespacho", grItem.getFechaDespacho());
                                            request.setAttribute("getFechaEntregaFacturacion", grItem.getFechaEntregaFacturacion());
                                            request.setAttribute("getFechaFacturacion", grItem.getFechaFacturacion());
                                            request.setAttribute("getNumeroFactura", grItem.getNumeroFactura());
                                            request.setAttribute("getFechaModificacion", grItem.getFechaModificacion());
                                            request.setAttribute("getNombreUsu", grItem.getNombreUsu());
                                            //para validar si se modifico un campo
                                            session.setAttribute("getDOIdItems", grItem.getIdItems());
                                            session.setAttribute("getDOIdDOs", grItem.getIdDOs());
                                            session.setAttribute("getDOReferencia", grItem.getReferencia());
                                            session.setAttribute("getDOPedido", grItem.getPedido());
                                            session.setAttribute("getDODescripcion", grItem.getDescripcion());
                                            session.setAttribute("getDOClienteFinal", grItem.getClienteFinal());
                                            session.setAttribute("getDOIdProveedor", grItem.getIdProveedor());
                                            session.setAttribute("getDOFechaEstimadaArribo", grItem.getFechaEstimadaArribo());
                                            session.setAttribute("getDOFechaLlegada", grItem.getFechaLlegada());
                                            session.setAttribute("getDOFechaDocumentos", grItem.getFechaDocumentos());
                                            session.setAttribute("getDOFechaDocumentosOK", grItem.getFechaDocumentosOK());
                                            session.setAttribute("getDOFechaAceptacion", grItem.getFechaAceptacion());
                                            session.setAttribute("getDOFechaSolicitudAnticipo", grItem.getFechaSolicitudAnticipo());
                                            session.setAttribute("getDOFechaPagoTributo", grItem.getFechaPagoTributo());
                                            session.setAttribute("getDOFechaLevante", grItem.getFechaLevante());
                                            session.setAttribute("getDOFechaPoder", grItem.getFechaPoder());
                                            session.setAttribute("getDOFechaEnvioDocumentos", grItem.getFechaEnvioDocumentos());
                                            session.setAttribute("getDOFechaAutenticacion", grItem.getFechaAutenticacion());
                                            session.setAttribute("getDOFechaLiberacionBL", grItem.getFechaLiberacionBL());
                                            session.setAttribute("getDOFechaPlanilla", grItem.getFechaPlanilla());
                                            session.setAttribute("getDOFechaSOAT", grItem.getFechaSOAT());
                                            session.setAttribute("getDOFechaTransitoLibre", grItem.getFechaTransitoLibre());
                                            session.setAttribute("getDOFechaDespacho", grItem.getFechaDespacho());
                                            session.setAttribute("getDOFechaEntregaFacturacion", grItem.getFechaEntregaFacturacion());
                                            session.setAttribute("getDOFechaFacturacion", grItem.getFechaFacturacion());
                                            session.setAttribute("getDONumeroFactura", grItem.getNumeroFactura());
                                            request.setAttribute("respuesta", "Registro ingresado correctamente.");
                                            System.out.println("Action Ingreso Item");
                                            return mapping.findForward("ok");

                                        } else {

                                            request.setAttribute("error", resultado7.get(1));
                                            return mapping.findForward("error");

                                        }

                                    } else {

                                        request.setAttribute("error", resultado6.get(1));
                                        return mapping.findForward("error");

                                    }

                                } else {

                                    request.setAttribute("error", resultado5.get(1));
                                    return mapping.findForward("error");

                                }

                            } else {

                                request.setAttribute("error", resultado4.get(1));
                                return mapping.findForward("error");

                            }

                        } else {

                            request.setAttribute("error", resultado3.get(1));
                            return mapping.findForward("error");

                        }

                    } else {

                        request.setAttribute("error", resultado2.get(1));
                        return mapping.findForward("error");

                    }

                } else {

                    request.setAttribute("error", resultado1.get(1));
                    return mapping.findForward("error");

                }

            } else {

                request.setAttribute("error", resultado.get(1));
                return mapping.findForward("error");

            }

        } else if (fo.getOp().equals("consultaItem")) {
            System.out.println("Nuevo ActionDO - Item");

            request.setAttribute("getIdDOs", fo.getIdDOs());
            request.setAttribute("getDO", fo.getDO());
            request.setAttribute("getIdCliente", fo.getIdCliente());
            request.setAttribute("getIdSucursal", fo.getIdSucursal());
            request.setAttribute("getIdPuerto", fo.getIdPuerto());
            request.setAttribute("getLote", fo.getLote());
            request.setAttribute("getBL", fo.getBL());
            request.setAttribute("getIdTipoMercancia", fo.getIdTipoMercancia());
            request.setAttribute("getObservaciones", fo.getObservaciones());

            ArrayList<Object> resultado;
            resultado = grItem.MostrarItemFormulario(String.valueOf(fo.getIdItems()), String.valueOf(fo.getIdDOs()), false, null);
            if ((Boolean) resultado.get(0) == false) {

                request.setAttribute("getIdItems", grItem.getIdItems());
                request.setAttribute("getIdDOs", grItem.getIdDOs());
                request.setAttribute("getReferencia", grItem.getReferencia());
                request.setAttribute("getPedido", grItem.getPedido());
                request.setAttribute("getDescripcion", grItem.getDescripcion());
                request.setAttribute("getClienteFinal", grItem.getClienteFinal());
                request.setAttribute("getIdProveedor", grItem.getIdProveedor());
                request.setAttribute("getFechaEstimadaArribo", grItem.getFechaEstimadaArribo());
                request.setAttribute("getFechaLlegada", grItem.getFechaLlegada());
                request.setAttribute("getFechaDocumentos", grItem.getFechaDocumentos());
                request.setAttribute("getFechaDocumentosOK", grItem.getFechaDocumentosOK());
                request.setAttribute("getFechaAceptacion", grItem.getFechaAceptacion());
                request.setAttribute("getFechaSolicitudAnticipo", grItem.getFechaSolicitudAnticipo());
                request.setAttribute("getFechaPagoTributo", grItem.getFechaPagoTributo());
                request.setAttribute("getFechaLevante", grItem.getFechaLevante());
                request.setAttribute("getFechaPoder", grItem.getFechaPoder());
                request.setAttribute("getFechaEnvioDocumentos", grItem.getFechaEnvioDocumentos());
                request.setAttribute("getFechaAutenticacion", grItem.getFechaAutenticacion());
                request.setAttribute("getFechaLiberacionBL", grItem.getFechaLiberacionBL());
                request.setAttribute("getFechaPlanilla", grItem.getFechaPlanilla());
                request.setAttribute("getFechaSOAT", grItem.getFechaSOAT());
                request.setAttribute("getFechaTransitoLibre", grItem.getFechaTransitoLibre());
                request.setAttribute("getFechaDespacho", grItem.getFechaDespacho());
                request.setAttribute("getFechaEntregaFacturacion", grItem.getFechaEntregaFacturacion());
                request.setAttribute("getFechaFacturacion", grItem.getFechaFacturacion());
                request.setAttribute("getNumeroFactura", grItem.getNumeroFactura());
                request.setAttribute("getFechaModificacion", grItem.getFechaModificacion());
                request.setAttribute("getNombreUsu", grItem.getNombreUsu());
                //para validar si se modifico un campo
                session.setAttribute("getDOIdItems", grItem.getIdItems());
                session.setAttribute("getDOIdDOs", grItem.getIdDOs());
                session.setAttribute("getDOReferencia", grItem.getReferencia());
                session.setAttribute("getDOPedido", grItem.getPedido());
                session.setAttribute("getDODescripcion", grItem.getDescripcion());
                session.setAttribute("getDOClienteFinal", grItem.getClienteFinal());
                session.setAttribute("getDOIdProveedor", grItem.getIdProveedor());
                session.setAttribute("getDOFechaEstimadaArribo", grItem.getFechaEstimadaArribo());
                session.setAttribute("getDOFechaLlegada", grItem.getFechaLlegada());
                session.setAttribute("getDOFechaDocumentos", grItem.getFechaDocumentos());
                session.setAttribute("getDOFechaDocumentosOK", grItem.getFechaDocumentosOK());
                session.setAttribute("getDOFechaAceptacion", grItem.getFechaAceptacion());
                session.setAttribute("getDOFechaSolicitudAnticipo", grItem.getFechaSolicitudAnticipo());
                session.setAttribute("getDOFechaPagoTributo", grItem.getFechaPagoTributo());
                session.setAttribute("getDOFechaLevante", grItem.getFechaLevante());
                session.setAttribute("getDOFechaPoder", grItem.getFechaPoder());
                session.setAttribute("getDOFechaEnvioDocumentos", grItem.getFechaEnvioDocumentos());
                session.setAttribute("getDOFechaAutenticacion", grItem.getFechaAutenticacion());
                session.setAttribute("getDOFechaLiberacionBL", grItem.getFechaLiberacionBL());
                session.setAttribute("getDOFechaPlanilla", grItem.getFechaPlanilla());
                session.setAttribute("getDOFechaSOAT", grItem.getFechaSOAT());
                session.setAttribute("getDOFechaTransitoLibre", grItem.getFechaTransitoLibre());
                session.setAttribute("getDOFechaDespacho", grItem.getFechaDespacho());
                session.setAttribute("getDOFechaEntregaFacturacion", grItem.getFechaEntregaFacturacion());
                session.setAttribute("getDOFechaFacturacion", grItem.getFechaFacturacion());
                session.setAttribute("getDONumeroFactura", grItem.getNumeroFactura());

                request.setAttribute("respuesta", "");
                return mapping.findForward("ok");

            } else {

                request.setAttribute("error", resultado.get(1));
                return mapping.findForward("error");

            }

        } else if (fo.getOp().equals("modificarItem")) {
            System.out.println("Modificar ActionDO - Item");

            request.setAttribute("getIdDOs", fo.getIdDOs());
            request.setAttribute("getDO", fo.getDO());
            request.setAttribute("getIdCliente", fo.getIdCliente());
            request.setAttribute("getIdSucursal", fo.getIdSucursal());
            request.setAttribute("getIdPuerto", fo.getIdPuerto());
            request.setAttribute("getLote", fo.getLote());
            request.setAttribute("getBL", fo.getBL());
            request.setAttribute("getIdTipoMercancia", fo.getIdTipoMercancia());
            request.setAttribute("getObservaciones", fo.getObservaciones());
            //item
            request.setAttribute("getIdItems", fo.getIdItems());
            request.setAttribute("getReferencia", fo.getReferencia());
            request.setAttribute("getPedido", fo.getPedido());
            request.setAttribute("getDescripcion", fo.getDescripcion());
            request.setAttribute("getClienteFinal", fo.getClienteFinal());
            request.setAttribute("getIdProveedor", fo.getIdProveedor());
            request.setAttribute("getFechaEstimadaArribo", fo.getFechaEstimadaArribo());
            request.setAttribute("getFechaLlegada", fo.getFechaLlegada());
            request.setAttribute("getFechaDocumentos", fo.getFechaDocumentos());
            request.setAttribute("getFechaDocumentosOK", fo.getFechaDocumentosOK());
            request.setAttribute("getFechaAceptacion", fo.getFechaAceptacion());
            request.setAttribute("getFechaSolicitudAnticipo", fo.getFechaSolicitudAnticipo());
            request.setAttribute("getFechaPagoTributo", fo.getFechaPagoTributo());
            request.setAttribute("getFechaLevante", fo.getFechaLevante());
            request.setAttribute("getFechaPoder", fo.getFechaPoder());
            request.setAttribute("getFechaEnvioDocumentos", fo.getFechaEnvioDocumentos());
            request.setAttribute("getFechaAutenticacion", fo.getFechaAutenticacion());
            request.setAttribute("getFechaLiberacionBL", fo.getFechaLiberacionBL());
            request.setAttribute("getFechaPlanilla", fo.getFechaPlanilla());
            request.setAttribute("getFechaSOAT", fo.getFechaSOAT());
            request.setAttribute("getFechaTransitoLibre", fo.getFechaTransitoLibre());
            request.setAttribute("getFechaDespacho", fo.getFechaDespacho());
            request.setAttribute("getFechaEntregaFacturacion", fo.getFechaEntregaFacturacion());
            request.setAttribute("getFechaFacturacion", fo.getFechaFacturacion());
            request.setAttribute("getNumeroFactura", fo.getNumeroFactura());

            ArrayList<Object> resultado;
            java.sql.Connection cn;
            resultado = grItem.ObtenerConexion();
            if ((Boolean) resultado.get(0) == false) {

                cn = (java.sql.Connection) resultado.get(1);
                ArrayList<Object> resultado1;
                resultado1 = grItem.autoCommint(false, cn);
                if ((Boolean) resultado1.get(0) == false) {

                    ArrayList<Object> resultado2;
                    resultado2 = grItem.ModificaDO(fo, true, cn);
                    if ((Boolean) resultado2.get(0) == false) {

                        ArrayList<Object> resultado3;
                        resultado3 = gA.BuscarFormulario("do", true, cn);
                        if ((Boolean) resultado3.get(0) == false) {

                            ArrayList<Object> resultado4;

                            //valida si hubo un cambio en algun campo
                            Integer NIdDOs = fo.getIdDOs();
                            String NDO = fo.getDO();
                            String NLote = fo.getLote();
                            String NBL = fo.getBL();
                            String NObservaciones = fo.getObservaciones();
                            String AIdDOs = session.getAttribute("getDOIdDOs").toString();
                            String ADO = session.getAttribute("getDODO").toString();
                            String ALote = session.getAttribute("getDOLote").toString();
                            String ABL = session.getAttribute("getDOBL").toString();
                            String AObservaciones = session.getAttribute("getDOObservaciones").toString();
                            String valor_anterior = "";
                            String valor_nuevo = "";
                            if (NIdDOs.toString().equals(AIdDOs) == false) {
                                valor_nuevo = "id='" + NIdDOs.toString() + "'";
                                valor_anterior = "id='" + AIdDOs + "'";
                            }
                            if (NDO.equals(ADO) == false) {
                                if (!valor_nuevo.equals("")) {
                                    valor_nuevo = valor_nuevo + "&";
                                    valor_anterior = valor_anterior + "&";
                                }
                                valor_nuevo += "do='" + NDO + "'";
                                valor_anterior += "do='" + ADO + "'";
                            }
                            if (session.getAttribute("getDOIdCliente") != null) {
                                String AIdCliente = session.getAttribute("getDOIdCliente").toString();
                                String NIdCliente = String.valueOf(fo.getIdCliente());
                                if (NIdCliente.equals(AIdCliente) == false) {
                                    if (!valor_nuevo.equals("")) {
                                        valor_nuevo = valor_nuevo + "&";
                                        valor_anterior = valor_anterior + "&";
                                    }
                                    valor_nuevo += "idCliente='" + NIdCliente + "'";
                                    valor_anterior += "idCliente='" + AIdCliente + "'";
                                }
                            }
                            if (session.getAttribute("getDOIdSucursal") != null) {
                                String AIdSucursal = session.getAttribute("getDOIdSucursal").toString();
                                String NIdSucursal = String.valueOf(fo.getIdSucursal());
                                if (NIdSucursal.equals(AIdSucursal) == false) {
                                    if (!valor_nuevo.equals("")) {
                                        valor_nuevo = valor_nuevo + "&";
                                        valor_anterior = valor_anterior + "&";
                                    }
                                    valor_nuevo += "idSucursal='" + NIdSucursal + "'";
                                    valor_anterior += "idSucursal='" + AIdSucursal + "'";
                                }
                            }
                            if (session.getAttribute("getDOIdPuerto") != null) {
                                String AIdPuerto = session.getAttribute("getDOIdPuerto").toString();
                                String NIdPuerto = String.valueOf(fo.getIdPuerto());
                                if (NIdPuerto.equals(AIdPuerto) == false) {
                                    if (!valor_nuevo.equals("")) {
                                        valor_nuevo = valor_nuevo + "&";
                                        valor_anterior = valor_anterior + "&";
                                    }
                                    valor_nuevo += "idPuerto='" + NIdPuerto + "'";
                                    valor_anterior += "idPuerto='" + AIdPuerto + "'";
                                }
                            }
                            if (NLote.equals(ALote) == false) {
                                if (!valor_nuevo.equals("")) {
                                    valor_nuevo = valor_nuevo + "&";
                                    valor_anterior = valor_anterior + "&";
                                }
                                valor_nuevo += "lote='" + NLote + "'";
                                valor_anterior += "lote='" + ALote + "'";
                            }
                            if (NBL.equals(ABL) == false) {
                                if (!valor_nuevo.equals("")) {
                                    valor_nuevo = valor_nuevo + "&";
                                    valor_anterior = valor_anterior + "&";
                                }
                                valor_nuevo += "bl='" + NBL + "'";
                                valor_anterior += "bl='" + ABL + "'";
                            }
                            if (session.getAttribute("getDOIdTipoMercancia") != null) {
                                String AIdTipoMercancia = session.getAttribute("getDOIdTipoMercancia").toString();
                                String NIdTipoMercancia = String.valueOf(fo.getIdTipoMercancia());
                                if (NIdTipoMercancia.equals(AIdTipoMercancia) == false) {
                                    if (!valor_nuevo.equals("")) {
                                        valor_nuevo = valor_nuevo + "&";
                                        valor_anterior = valor_anterior + "&";
                                    }
                                    valor_nuevo += "idTipoMercancia='" + NIdTipoMercancia + "'";
                                    valor_anterior += "idTipoMercancia='" + AIdTipoMercancia + "'";
                                }
                            }
                            if (NObservaciones.equals(AObservaciones) == false) {
                                if (!valor_nuevo.equals("")) {
                                    valor_nuevo = valor_nuevo + "&";
                                    valor_anterior = valor_anterior + "&";
                                }
                                valor_nuevo += "observaciones='" + NObservaciones + "'";
                                valor_anterior += "observaciones='" + AObservaciones + "'";
                            }

                            resultado4 = gA.IngresaAuditoria("Modificar", valor_anterior, valor_nuevo, fo.getIdUsu(), Integer.valueOf(gA.getIdFormulario().toString()), String.valueOf(fo.getIdDOs()), true, cn);
                            if ((Boolean) resultado4.get(0) == false) {

                                ArrayList<Object> resultado5;
                                resultado5 = grItem.commint(cn);
                                if ((Boolean) resultado5.get(0) == false) {

                                    ArrayList<Object> resultado6;
                                    resultado6 = grItem.autoCommint(true, cn);
                                    if ((Boolean) resultado6.get(0) == false) {

                                        ArrayList<Object> resultado7;
                                        resultado7 = grItem.MostrarDOFormulario(String.valueOf(fo.getIdDOs()), false, null);
                                        if ((Boolean) resultado7.get(0) == false) {

                                            request.setAttribute("getIdDOs", gr.getIdDOs());
                                            request.setAttribute("getDO", gr.getDO());
                                            request.setAttribute("getIdCliente", gr.getIdCliente());
                                            request.setAttribute("getIdSucursal", gr.getIdSucursal());
                                            request.setAttribute("getIdPuerto", gr.getIdPuerto());
                                            request.setAttribute("getLote", gr.getLote());
                                            request.setAttribute("getBL", gr.getBL());
                                            request.setAttribute("getIdTipoMercancia", gr.getIdTipoMercancia());
                                            request.setAttribute("getObservaciones", gr.getObservaciones());
                                            request.setAttribute("getFechaModificacion", gr.getFechaModificacion());
                                            request.setAttribute("getNombreUsu", gr.getNombreUsu());
                                            //para validar si se modifico un campo
                                            session.setAttribute("getDOIdDOs", gr.getIdDOs());
                                            session.setAttribute("getDODO", gr.getDO());
                                            session.setAttribute("getDOIdCliente", gr.getIdCliente());
                                            session.setAttribute("getDOIdSucursal", gr.getIdSucursal());
                                            session.setAttribute("getDOIdPuerto", gr.getIdPuerto());
                                            session.setAttribute("getDOLote", gr.getLote());
                                            session.setAttribute("getDOBL", gr.getBL());
                                            session.setAttribute("getDOIdTipoMercancia", gr.getIdTipoMercancia());
                                            session.setAttribute("getDOObservaciones", gr.getObservaciones());
                                            request.setAttribute("respuesta", "Registro modificado correctamente.");
                                            System.out.println("Action Modificar DO");
                                            return mapping.findForward("ok");

                                        } else {

                                            request.setAttribute("error", resultado7.get(1));
                                            return mapping.findForward("error");

                                        }

                                    } else {

                                        request.setAttribute("error", resultado6.get(1));
                                        return mapping.findForward("error");

                                    }

                                } else {

                                    request.setAttribute("error", resultado5.get(1));
                                    return mapping.findForward("error");

                                }

                            } else {

                                request.setAttribute("error", resultado4.get(1));
                                return mapping.findForward("error");

                            }

                        } else {

                            request.setAttribute("error", resultado3.get(1));
                            return mapping.findForward("error");

                        }

                    } else {

                        request.setAttribute("error", resultado2.get(1));
                        return mapping.findForward("error");

                    }

                } else {

                    request.setAttribute("error", resultado1.get(1));
                    return mapping.findForward("error");

                }

            } else {

                request.setAttribute("error", resultado.get(1));
                return mapping.findForward("error");

            }

        } else if (fo.getOp().equals("eliminar")) {
            //verificar si ya se eliminaron todos los items
            System.out.println("Eliminar ActionDO - Item");
            request.setAttribute("getIdDOs", "");
            request.setAttribute("getDO", "");
            request.setAttribute("getIdCliente", "");
            request.setAttribute("getIdSucursal", "");
            request.setAttribute("getIdPuerto", "");
            request.setAttribute("getLote", "");
            request.setAttribute("getBL", "");
            request.setAttribute("getIdTipoMercancia", "");
            request.setAttribute("getObservaciones", "");
            request.setAttribute("getFechaModificacion", "");
            request.setAttribute("getNombreUsu", "");

            ArrayList<Object> resultado;
            java.sql.Connection cn;
            resultado = gr.ObtenerConexion();
            if ((Boolean) resultado.get(0) == false) {

                cn = (java.sql.Connection) resultado.get(1);
                ArrayList<Object> resultado1;
                resultado1 = gr.autoCommint(false, cn);
                if ((Boolean) resultado1.get(0) == false) {

                    ArrayList<Object> resultado2;
                    resultado2 = (ArrayList) gr.EliminaDO(fo, false, null);
                    if ((Boolean) resultado2.get(0) == false) {

                        ArrayList<Object> resultado3;
                        resultado3 = gA.BuscarFormulario("do", true, cn);
                        if ((Boolean) resultado3.get(0) == false) {

                            ArrayList<Object> resultado4;
                            String valor_anterior = "id=" + fo.getIdDOs() + "&do=" + fo.getDO() + "&idCliente=" + fo.getIdCliente() + "&idSucursal=" + fo.getIdSucursal() + "&idPuerto=" + fo.getIdPuerto() + "&lote=" + fo.getLote() + "&bl=" + fo.getBL() + "&idTipoMercancia=" + fo.getIdTipoMercancia() + "&observaciones=" + fo.getObservaciones();
                            resultado4 = gA.IngresaAuditoria("Eliminar", valor_anterior, "", fo.getIdUsu(), Integer.valueOf(gA.getIdFormulario().toString()), String.valueOf(fo.getIdDOs()), true, cn);
                            if ((Boolean) resultado4.get(0) == false) {

                                ArrayList<Object> resultado5;
                                resultado5 = gr.commint(cn);
                                if ((Boolean) resultado5.get(0) == false) {

                                    ArrayList<Object> resultado6;
                                    resultado6 = gr.autoCommint(true, cn);
                                    if ((Boolean) resultado6.get(0) == false) {

                                        request.setAttribute("respuesta", "Registro eliminado correctamente.");
                                        System.out.println("Action Eliminar DO");

                                        return mapping.findForward("ok");

                                    } else {

                                        request.setAttribute("error", resultado6.get(1));
                                        return mapping.findForward("error");

                                    }

                                } else {

                                    request.setAttribute("error", resultado5.get(1));
                                    return mapping.findForward("error");

                                }

                            } else {

                                request.setAttribute("error", resultado4.get(1));
                                return mapping.findForward("error");

                            }

                        } else {

                            request.setAttribute("error", resultado3.get(1));
                            return mapping.findForward("error");

                        }

                    } else {

                        request.setAttribute("error", resultado2.get(1));
                        return mapping.findForward("error");

                    }

                } else {

                    request.setAttribute("error", resultado1.get(1));
                    return mapping.findForward("error");

                }

            } else {

                request.setAttribute("error", resultado.get(1));
                return mapping.findForward("error");

            }

        } else {
            System.out.println("Volver ActionDO");

            request.setAttribute("getOp", "buscar");
            return mapping.findForward("atras");

        }

    }
}
