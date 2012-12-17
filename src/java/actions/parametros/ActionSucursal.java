/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actions.parametros;

import forms.parametros.SucursalForm;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.log.GestionAuditoria;
import modelo.parametros.GestionSucursal;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Mario
 */
public class ActionSucursal extends Action {

    public ActionSucursal() {

        super();

    }

    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception {

        SucursalForm fo = (SucursalForm) form;
        GestionSucursal gr = new GestionSucursal();
        GestionAuditoria gA = new GestionAuditoria();
        HttpSession session = request.getSession();
        if (fo.getOp().equals("nuevo")) {

            request.setAttribute("getIdSucursal", fo.getIdSucursal());
            request.setAttribute("getNombre", fo.getNombreCorto());
            request.setAttribute("getDescripcion", fo.getDescripcion());

            ArrayList<Object> resultado = new ArrayList<Object>();
            Connection cn = null;
            resultado = gr.ObtenerConexion();
            if ((Boolean) resultado.get(0) == false) {

                cn = (Connection) resultado.get(1);
                ArrayList<Object> resultado1 = new ArrayList<Object>();
                resultado1 = gr.autoCommint(false, cn);
                if ((Boolean) resultado1.get(0) == false) {

                    ArrayList<Object> resultado2 = new ArrayList<Object>();
                    resultado2 = gr.IngresaSucursal(fo, true, cn);
                    if ((Boolean) resultado2.get(0) == false) {

                        fo.setIdSucursal(Integer.valueOf(resultado2.get(1).toString()));

                        ArrayList<Object> resultado3 = new ArrayList<Object>();
                        resultado3 = gA.BuscarFormulario("sucursal", true, cn);
                        if ((Boolean) resultado3.get(0) == false) {

                            ArrayList<Object> resultado4 = new ArrayList<Object>();
                            String valor_nuevo = "id=" + fo.getIdSucursal() + "&nombrCorto=" + fo.getNombreCorto() + "&descripcion=" + fo.getDescripcion();
                            resultado4 = gA.IngresaAuditoria("Nuevo", "", valor_nuevo, fo.getIdUsu(), Integer.valueOf(gA.getIdFormulario().toString()), String.valueOf(fo.getIdSucursal()), true, cn);
                            if ((Boolean) resultado4.get(0) == false) {

                                ArrayList<Object> resultado5 = new ArrayList<Object>();
                                resultado5 = gr.commint(cn);
                                if ((Boolean) resultado5.get(0) == false) {

                                    ArrayList<Object> resultado6 = new ArrayList<Object>();
                                    resultado6 = gr.autoCommint(true, cn);
                                    if ((Boolean) resultado6.get(0) == false) {

                                        ArrayList<Object> resultado7 = new ArrayList<Object>();
                                        resultado7 = gr.MostrarSucursalFormulario(fo.getIdSucursal(), false, null);
                                        if ((Boolean) resultado7.get(0) == false) {

                                            request.setAttribute("getIdSucursal", gr.getIdSucursal());
                                            request.setAttribute("getNombreCorto", gr.getNombreCorto());
                                            request.setAttribute("getDescripcion", gr.getDescripcion());
                                            request.setAttribute("getFechaModificacion", gr.getFechaModificacion());
                                            request.setAttribute("getNombreUsu", gr.getNombreUsu());
                                            //para validar si se modifico un campo
                                            session.setAttribute("getSucursalIdSucursal", gr.getIdSucursal());
                                            session.setAttribute("getSucursalNombreCorto", gr.getNombreCorto());
                                            session.setAttribute("getSucursalDescripcion", gr.getDescripcion());

                                            request.setAttribute("respuesta", "Registro ingresado correctamente.");
                                            System.out.println("Action Ingreso Sucursal");
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

            request.setAttribute("getIdSucursal", fo.getIdSucursal());
            request.setAttribute("getNombreCorto", fo.getNombreCorto());
            request.setAttribute("getDescripcion", fo.getDescripcion());

            ArrayList<Object> resultado = new ArrayList<Object>();
            Connection cn = null;
            resultado = gr.ObtenerConexion();
            if ((Boolean) resultado.get(0) == false) {

                cn = (Connection) resultado.get(1);
                ArrayList<Object> resultado1 = new ArrayList<Object>();
                resultado1 = gr.autoCommint(false, cn);
                if ((Boolean) resultado1.get(0) == false) {

                    ArrayList<Object> resultado2 = new ArrayList<Object>();
                    resultado2 = gr.ModificaSucursal(fo, true, cn);
                    if ((Boolean) resultado2.get(0) == false) {

                        ArrayList<Object> resultado3 = new ArrayList<Object>();
                        resultado3 = gA.BuscarFormulario("sucursal", true, cn);
                        if ((Boolean) resultado3.get(0) == false) {

                            ArrayList<Object> resultado4 = new ArrayList<Object>();

                            //valida si hubo un cambio en algun campo
                            String NIdSucursal = String.valueOf(fo.getIdSucursal());
                            String NNombreCorto = fo.getNombreCorto();
                            String NDescripcion = fo.getDescripcion();
                            String AIdSucursal = session.getAttribute("getSucursalIdSucursal").toString();
                            String ANombreCorto = session.getAttribute("getSucursalNombreCorto").toString();
                            String ADescripcion = session.getAttribute("getSucursalDescripcion").toString();
                            String valor_anterior = "";
                            String valor_nuevo = "";
                            if (NIdSucursal.equals(AIdSucursal) == false) {
                                valor_nuevo = "id='" + NIdSucursal + "'";
                                valor_anterior = "id='" + AIdSucursal + "'";
                            }
                            if (NNombreCorto.equals(ANombreCorto) == false) {
                                if (!valor_nuevo.equals("")) {
                                    valor_nuevo = valor_nuevo + "&";
                                    valor_anterior = valor_anterior + "&";
                                }
                                valor_nuevo = valor_nuevo + "nombreCorto='" + NNombreCorto + "'";
                                valor_anterior = valor_anterior + "nombreCorto='" + ANombreCorto + "'";
                            }
                            if (NDescripcion.equals(ADescripcion) == false) {
                                if (!valor_nuevo.equals("")) {
                                    valor_nuevo = valor_nuevo + "&";
                                    valor_anterior = valor_anterior + "&";
                                }
                                valor_nuevo = valor_nuevo + "descripcion='" + NDescripcion + "'";
                                valor_anterior = valor_anterior + "descripcion='" + ADescripcion + "'";
                            }

                            resultado4 = gA.IngresaAuditoria("Modificar", valor_anterior, valor_nuevo, fo.getIdUsu(), Integer.valueOf(gA.getIdFormulario().toString()), String.valueOf(fo.getIdSucursal()), true, cn);
                            if ((Boolean) resultado4.get(0) == false) {

                                ArrayList<Object> resultado5 = new ArrayList<Object>();
                                resultado5 = gr.commint(cn);
                                if ((Boolean) resultado5.get(0) == false) {

                                    ArrayList<Object> resultado6 = new ArrayList<Object>();
                                    resultado6 = gr.autoCommint(true, cn);
                                    if ((Boolean) resultado6.get(0) == false) {

                                        ArrayList<Object> resultado7 = new ArrayList<Object>();
                                        resultado7 = gr.MostrarSucursalFormulario(fo.getIdSucursal(), false, null);
                                        if ((Boolean) resultado7.get(0) == false) {

                                            request.setAttribute("getIdSucursal", fo.getIdSucursal());
                                            request.setAttribute("getNombreCorto", fo.getNombreCorto());
                                            request.setAttribute("getDescripcion", fo.getDescripcion());
                                            request.setAttribute("getFechaModificacion", gr.getFechaModificacion());
                                            request.setAttribute("getNombreUsu", gr.getNombreUsu());
                                            //para validar si se modifico un campo
                                            session.setAttribute("getSucursalIdSucursal", gr.getIdSucursal());
                                            session.setAttribute("getSucursalNombreCorto", gr.getNombreCorto());
                                            session.setAttribute("getSucursalDescripcion", gr.getDescripcion());

                                            request.setAttribute("respuesta", "Registro modificado correctamente.");
                                            System.out.println("Action Modicar Sucursal");
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

            request.setAttribute("getIdSucursal", "");
            request.setAttribute("getNombreCorto", "");
            request.setAttribute("getDescripcion", "");
            request.setAttribute("getFechaModificacion", "");
            request.setAttribute("getNombreUsu", "");

            ArrayList<Object> resultado = new ArrayList<Object>();
            Connection cn = null;
            resultado = gr.ObtenerConexion();
            if ((Boolean) resultado.get(0) == false) {

                cn = (Connection) resultado.get(1);
                ArrayList<Object> resultado1 = new ArrayList<Object>();
                resultado1 = gr.autoCommint(false, cn);
                if ((Boolean) resultado1.get(0) == false) {

                    ArrayList<Object> resultado2 = new ArrayList<Object>();
                    resultado2 = gr.EliminaSucursal(fo, true, cn);
                    if ((Boolean) resultado2.get(0) == false) {

                        ArrayList<Object> resultado3 = new ArrayList<Object>();
                        resultado3 = gA.BuscarFormulario("sucursal", true, cn);
                        if ((Boolean) resultado3.get(0) == false) {

                            ArrayList<Object> resultado4 = new ArrayList<Object>();
                            String valor_anterior = "id=" + fo.getIdSucursal() + "&nombreCorto=" + fo.getNombreCorto() + "&descripcion=" + fo.getDescripcion();
                            resultado4 = gA.IngresaAuditoria("Eliminar", valor_anterior, "", fo.getIdUsu(), Integer.valueOf(gA.getIdFormulario().toString()), String.valueOf(fo.getIdSucursal()), true, cn);
                            if ((Boolean) resultado4.get(0) == false) {

                                ArrayList<Object> resultado5 = new ArrayList<Object>();
                                resultado5 = gr.commint(cn);
                                if ((Boolean) resultado5.get(0) == false) {

                                    ArrayList<Object> resultado6 = new ArrayList<Object>();
                                    resultado6 = gr.autoCommint(true, cn);
                                    if ((Boolean) resultado6.get(0) == false) {

                                        request.setAttribute("respuesta", "Registro eliminado correctamente.");
                                        System.out.println("Action Eliminar Sucursal");
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

            request.setAttribute("getOp", "buscar");
            return mapping.findForward("atras");

        }

    }
}
