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

            ArrayList<Object> resultado = new ArrayList<Object>();
            java.sql.Connection cn = null;
            resultado = gr.ObtenerConexion();
            if ((Boolean) resultado.get(0) == false) {

                cn = (java.sql.Connection) resultado.get(1);
                ArrayList<Object> resultado1 = new ArrayList<Object>();
                resultado1 = gr.autoCommint(false, cn);
                if ((Boolean) resultado1.get(0) == false) {

                    ArrayList<Object> resultado2 = new ArrayList<Object>();
                    resultado2 = gr.ModificaDO(fo, true, cn);
                    if ((Boolean) resultado2.get(0) == false) {

                        ArrayList<Object> resultado3 = new ArrayList<Object>();
                        resultado3 = gA.BuscarFormulario("do", true, cn);
                        if ((Boolean) resultado3.get(0) == false) {

                            ArrayList<Object> resultado4 = new ArrayList<Object>();

                            //valida si hubo un cambio en algun campo
                            Integer NIdDOs = fo.getIdDOs();
                            String NDO = fo.getDO();
                            String NIdCliente = String.valueOf(fo.getIdCliente());
                            String NIdSucursal = String.valueOf(fo.getIdSucursal());
                            String NIdPuerto = String.valueOf(fo.getIdPuerto());
                            String NLote = fo.getLote();
                            String NBL = fo.getBL();
                            String NIdTipoMercancia = String.valueOf(fo.getIdTipoMercancia());
                            String NObservaciones = fo.getObservaciones();
                            String AIdDOs = session.getAttribute("getDOIdDOs").toString();
                            String ADO = session.getAttribute("getDODO").toString();
                            String AIdCliente = session.getAttribute("getDOIdCliente").toString();
                            String AIdSucursal = session.getAttribute("getDOIdSucursal").toString();
                            String AIdPuerto = session.getAttribute("getDOIdPuerto").toString();
                            String ALote = session.getAttribute("getDOLote").toString();
                            String ABL = session.getAttribute("getDOBL").toString();
                            String AIdTipoMercancia = session.getAttribute("getDOIdTipoMercancia").toString();
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
                                valor_nuevo = "do='" + NDO + "'";
                                valor_anterior = "do='" + ADO + "'";
                            }
                            if (NIdCliente.equals(AIdCliente) == false) {
                                if (!valor_nuevo.equals("")) {
                                    valor_nuevo = valor_nuevo + "&";
                                    valor_anterior = valor_anterior + "&";
                                }
                                valor_nuevo = "idCliente='" + NIdCliente + "'";
                                valor_anterior = "idCliente='" + AIdCliente + "'";
                            }
                            if (NIdSucursal.equals(AIdSucursal) == false) {
                                if (!valor_nuevo.equals("")) {
                                    valor_nuevo = valor_nuevo + "&";
                                    valor_anterior = valor_anterior + "&";
                                }
                                valor_nuevo = "idSucursal='" + NIdSucursal + "'";
                                valor_anterior = "idSucursal='" + AIdSucursal + "'";
                            }
                            if (NIdPuerto.equals(AIdPuerto) == false) {
                                if (!valor_nuevo.equals("")) {
                                    valor_nuevo = valor_nuevo + "&";
                                    valor_anterior = valor_anterior + "&";
                                }
                                valor_nuevo = "idPuerto='" + NIdPuerto + "'";
                                valor_anterior = "idPuerto='" + AIdPuerto + "'";
                            }
                            if (NLote.equals(ALote) == false) {
                                if (!valor_nuevo.equals("")) {
                                    valor_nuevo = valor_nuevo + "&";
                                    valor_anterior = valor_anterior + "&";
                                }
                                valor_nuevo = "lote='" + NLote + "'";
                                valor_anterior = "lote='" + ALote + "'";
                            }
                            if (NBL.equals(ABL) == false) {
                                if (!valor_nuevo.equals("")) {
                                    valor_nuevo = valor_nuevo + "&";
                                    valor_anterior = valor_anterior + "&";
                                }
                                valor_nuevo = "bl='" + NBL + "'";
                                valor_anterior = "bl='" + ABL + "'";
                            }
                            if (NIdTipoMercancia.equals(AIdTipoMercancia) == false) {
                                if (!valor_nuevo.equals("")) {
                                    valor_nuevo = valor_nuevo + "&";
                                    valor_anterior = valor_anterior + "&";
                                }
                                valor_nuevo = "idTipoMercancia='" + NIdTipoMercancia + "'";
                                valor_anterior = "idTipoMercancia='" + AIdTipoMercancia + "'";
                            }
                            if (NObservaciones.equals(AObservaciones) == false) {
                                if (!valor_nuevo.equals("")) {
                                    valor_nuevo = valor_nuevo + "&";
                                    valor_anterior = valor_anterior + "&";
                                }
                                valor_nuevo = "observaciones='" + NObservaciones + "'";
                                valor_anterior = "observaciones='" + AObservaciones + "'";
                            }

                            resultado4 = gA.IngresaAuditoria("Modificar", valor_anterior, valor_nuevo, fo.getIdUsu(), Integer.valueOf(gA.getIdFormulario().toString()), String.valueOf(fo.getIdDOs()), true, cn);
                            if ((Boolean) resultado4.get(0) == false) {

                                ArrayList<Object> resultado5 = new ArrayList<Object>();
                                resultado5 = gr.commint(cn);
                                if ((Boolean) resultado5.get(0) == false) {

                                    ArrayList<Object> resultado6 = new ArrayList<Object>();
                                    resultado6 = gr.autoCommint(true, cn);
                                    if ((Boolean) resultado6.get(0) == false) {

                                        ArrayList<Object> resultado7 = new ArrayList<Object>();
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
            System.out.println("Eliminar ActionDO");

//            ArrayList<Object> resultado;
//            resultado = (ArrayList) grCaract.ObtenerConexion();
//            if ((Boolean) resultado.get(0) == false) {
//
//                Connection cn = (Connection) resultado.get(1);
//
//                ArrayList<Object> resultado2;
//                resultado2 = (ArrayList) grCaract.autoCommint(false, cn);
//                if ((Boolean) resultado2.get(0) == false) {

            ArrayList<Object> resultado3;
            resultado3 = (ArrayList) gr.EliminaDO(fo, false, null);
//                    resultado3 = (ArrayList) gr.EliminaPlantillaDispositivo(fo, true, cn);

            if ((Boolean) resultado3.get(0) == false) {

//                        ArrayList<Object> resultado4;
//                        resultado4 = (ArrayList) grCaract.EliminaCaracteristicaPlantilla(Integer.valueOf(fo.getIdPlantillaDispositivo()), true, cn);
//
//                        if ((Boolean) resultado4.get(0) == false) {
//
//                            ArrayList<Object> resultado5;
//                            resultado5 = (ArrayList) grHija.EliminaPlantillaDispositivoHija(Integer.valueOf(fo.getIdPlantillaDispositivo()), true, cn);
//
//                            if ((Boolean) resultado5.get(0) == false) {
//
//                                ArrayList<Object> resultado6;
//                                resultado6 = (ArrayList) grCaract.commint(cn);
//
//                                if ((Boolean) resultado6.get(0) == false) {
//
//                                    ArrayList<Object> resultado7;
//                                    resultado7 = (ArrayList) grCaract.autoCommint(true, cn);
//
//                                    if ((Boolean) resultado7.get(0) == false) {

                request.setAttribute("getIdFactura", "");
                request.setAttribute("getIdEntidad", "");
                request.setAttribute("getNumFactura", "");
                request.setAttribute("getFecha", "");
                request.setAttribute("getTotal", "");

                request.setAttribute("respuesta", "Registro eliminado correctamente.");
                System.out.println("Action Eliminar Factura");

                return mapping.findForward("ok");

//                                    } else {
//
//                                        request.setAttribute("error", resultado7.get(1));
//                                        return mapping.findForward("error");
//
//                                    }
//
//                                } else {
//
//                                    request.setAttribute("error", resultado6.get(1));
//                                    return mapping.findForward("error");
//
//                                }
//
//                            } else {
//
//                                request.setAttribute("error", resultado5.get(1));
//                                return mapping.findForward("error");
//
//                            }
//
//                        } else {
//
//                            request.setAttribute("error", resultado4.get(1));
//                            return mapping.findForward("error");
//
//                        }

            } else {

                request.setAttribute("error", resultado3.get(1));
                return mapping.findForward("error");

            }

//                } else {
//
//                    request.setAttribute("error", resultado2.get(1));
//                    return mapping.findForward("error");
//
//                }
//
//            } else {
//
//                request.setAttribute("error", resultado.get(1));
//                return mapping.findForward("error");
//
//            }

        } else {
            System.out.println("Volver ActionDO");

            request.setAttribute("getOp", "buscar");
            return mapping.findForward("atras");

        }

    }
}
