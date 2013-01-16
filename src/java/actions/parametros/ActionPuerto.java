/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actions.parametros;

import forms.parametros.PuertoForm;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.log.GestionAuditoria;
import modelo.parametros.GestionPuerto;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Mario
 */
public class ActionPuerto extends Action {

    public ActionPuerto() {

        super();

    }

    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception {

        PuertoForm fo = (PuertoForm) form;
        GestionPuerto gr = new GestionPuerto();
        GestionAuditoria gA = new GestionAuditoria();
        HttpSession session = request.getSession();
        if (fo.getOp().equals("nuevo")) {

            request.setAttribute("getIdPuerto", fo.getIdPuerto());
            request.setAttribute("getNombreCorto", fo.getNombreCorto());
            request.setAttribute("getDescripcion", fo.getDescripcion());
            request.setAttribute("getIdMunicipio", fo.getIdMunicipio());
            request.setAttribute("getIdDepartamento", fo.getIdDepartamento());
            request.setAttribute("getIdPais", fo.getIdPais());
            request.setAttribute("getIdSucursal", fo.getIdSucursal());

            ArrayList<Object> resultado = new ArrayList<Object>();
            Connection cn = null;
            resultado = gr.ObtenerConexion();
            if ((Boolean) resultado.get(0) == false) {

                cn = (Connection) resultado.get(1);
                ArrayList<Object> resultado1 = new ArrayList<Object>();
                resultado1 = gr.autoCommint(false, cn);
                if ((Boolean) resultado1.get(0) == false) {

                    ArrayList<Object> resultado2 = new ArrayList<Object>();
                    resultado2 = gr.IngresaPuerto(fo, true, cn);
                    if ((Boolean) resultado2.get(0) == false) {
                        
                        fo.setIdPuerto(Integer.valueOf(resultado2.get(1).toString()));

                        ArrayList<Object> resultado3 = new ArrayList<Object>();
                        resultado3 = gA.BuscarFormulario("puerto", true, cn);
                        if ((Boolean) resultado3.get(0) == false) {

                            ArrayList<Object> resultado4 = new ArrayList<Object>();
                            String valor_nuevo = "id=" + resultado.get(1) + "&nombre_corto=" + fo.getNombreCorto() + "&descripcion=" + fo.getDescripcion() + "&id_departamento=" + fo.getIdDepartamento() + "&id_pais=" + fo.getIdPais() + "id_Municipio=" + fo.getIdMunicipio() + "id_Sucursal=" + fo.getIdSucursal();
                            resultado4 = gA.IngresaAuditoria("Nuevo", "", valor_nuevo, fo.getIdUsu(), Integer.valueOf(gA.getIdFormulario().toString()), String.valueOf(fo.getIdPuerto()), true, cn);
                            if ((Boolean) resultado4.get(0) == false) {

                                ArrayList<Object> resultado5 = new ArrayList<Object>();
                                resultado5 = gr.commint(cn);
                                if ((Boolean) resultado5.get(0) == false) {

                                    ArrayList<Object> resultado6 = new ArrayList<Object>();
                                    resultado6 = gr.autoCommint(true, cn);
                                    if ((Boolean) resultado6.get(0) == false) {

                                        ArrayList<Object> resultado7 = new ArrayList<Object>();
                                        resultado7 = gr.MostrarPuertoFormulario(fo.getIdPuerto(), false, null);
                                        if ((Boolean) resultado7.get(0) == false) {

                                            request.setAttribute("getIdPuerto", fo.getIdPuerto());
                                            request.setAttribute("getNombreCorto", fo.getNombreCorto());
                                            request.setAttribute("getDescripcion", fo.getDescripcion());
                                            request.setAttribute("getIdMunicipio", fo.getIdMunicipio());
                                            request.setAttribute("getIdDepartamento", fo.getIdDepartamento());
                                            request.setAttribute("getIdPais", fo.getIdPais());
                                            request.setAttribute("getIdSucursal", fo.getIdSucursal());
                                            request.setAttribute("getFechaModificacion", gr.getFechaModificacion());
                                            request.setAttribute("getNombreUsu", gr.getNombreUsu());
                                            //para validar si se modifico un campo
                                            session.setAttribute("getPuertoIdPuerto", gr.getIdPuerto());
                                            session.setAttribute("getPuertoNombreCorto", gr.getNombreCorto());
                                            session.setAttribute("getPuertoDescripcion", gr.getDescripcion());
                                            session.setAttribute("getPuertoIdMunicipio", gr.getIdMunicipio());
                                            session.setAttribute("getPuertoIdDepartamento", gr.getIdDepartamento());
                                            session.setAttribute("getPuertoIdPais", gr.getIdPais());
                                            session.setAttribute("getPuertoIdSucursal", gr.getIdSucursal());

                                            request.setAttribute("respuesta", "Registro ingresado correctamente.");
                                            System.out.println("Action Ingreso Puerto");
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

            request.setAttribute("getIdPuerto", fo.getIdPuerto());
            request.setAttribute("getNombreCorto", fo.getNombreCorto());
            request.setAttribute("getDescripcion", fo.getDescripcion());
            request.setAttribute("getIdMunicipio", fo.getIdMunicipio());
            request.setAttribute("getIdDepartamento", fo.getIdDepartamento());
            request.setAttribute("getIdPais", fo.getIdPais());
            request.setAttribute("getIdSucursal", fo.getIdSucursal());

            ArrayList<Object> resultado = new ArrayList<Object>();
            Connection cn = null;
            resultado = gr.ObtenerConexion();
            if ((Boolean) resultado.get(0) == false) {

                cn = (Connection) resultado.get(1);
                ArrayList<Object> resultado1 = new ArrayList<Object>();
                resultado1 = gr.autoCommint(false, cn);
                if ((Boolean) resultado1.get(0) == false) {

                    ArrayList<Object> resultado2 = new ArrayList<Object>();
                    resultado2 = gr.ModificaPuerto(fo, true, cn);
                    if ((Boolean) resultado2.get(0) == false) {

                        ArrayList<Object> resultado3 = new ArrayList<Object>();
                        resultado3 = gA.BuscarFormulario("puerto", true, cn);
                        if ((Boolean) resultado3.get(0) == false) {

                            ArrayList<Object> resultado4 = new ArrayList<Object>();

                            //valida si hubo un cambio en algun campo
                            Integer NIdPuerto = fo.getIdPuerto();
                            String NNombreCorto = fo.getNombreCorto();
                            String NDescripcion = fo.getDescripcion();
                            String NIdMunicipio = fo.getIdMunicipio();
                            String NIdDepartamento = fo.getIdDepartamento();
                            String NIdPais = fo.getIdPais();
                            String NIdSucursal = fo.getIdSucursal().toString();
                            String AIdPuerto = session.getAttribute("getPuertoIdPuerto").toString();
                            String ANombreCorto = session.getAttribute("getPuertoNombreCorto").toString();
                            String ADescripcion = session.getAttribute("getPuertoDescripcion").toString();
                            String AIdMunicipio = session.getAttribute("getPuertoIdMunicipio").toString();
                            String AIdDepartamento = session.getAttribute("getPuertoIdDepartamento").toString();
                            String AIdPais = session.getAttribute("getPuertoIdPais").toString();
                            String AIdSucursal = session.getAttribute("getPuertoIdSucursal").toString();
                            String valor_anterior = "";
                            String valor_nuevo = "";
                            if (NIdPuerto.toString().equals(AIdPuerto) == false) {
                                valor_nuevo = "id='" + NIdPuerto.toString() + "'";
                                valor_anterior = "id='" + AIdPuerto + "'";
                            }
                            if (NNombreCorto.equals(ANombreCorto) == false) {
                                if (!valor_nuevo.equals("")) {
                                    valor_nuevo = valor_nuevo + "&";
                                    valor_anterior = valor_anterior + "&";
                                }
                                valor_nuevo = "nombre_corto='" + NNombreCorto + "'";
                                valor_anterior = "nombre_corto='" + ANombreCorto + "'";
                            }
                            if (NDescripcion.equals(ADescripcion) == false) {
                                if (!valor_nuevo.equals("")) {
                                    valor_nuevo = valor_nuevo + "&";
                                    valor_anterior = valor_anterior + "&";
                                }
                                valor_nuevo = "descripcion='" + NDescripcion + "'";
                                valor_anterior = "descripcion='" + ADescripcion + "'";
                            }
                            if (NIdMunicipio.equals(AIdMunicipio) == false) {
                                if (!valor_nuevo.equals("")) {
                                    valor_nuevo = valor_nuevo + "&";
                                    valor_anterior = valor_anterior + "&";
                                }
                                valor_nuevo = "id='" + NIdMunicipio + "'";
                                valor_anterior = "id='" + AIdMunicipio + "'";
                            }
                            if (NIdDepartamento.equals(AIdDepartamento) == false) {
                                if (!valor_nuevo.equals("")) {
                                    valor_nuevo = valor_nuevo + "&";
                                    valor_anterior = valor_anterior + "&";
                                }
                                valor_nuevo = "id_departamento='" + NIdDepartamento + "'";
                                valor_anterior = "id_departamento='" + AIdDepartamento + "'";
                            }
                            if (NIdPais.equals(AIdPais) == false) {
                                if (!valor_nuevo.equals("")) {
                                    valor_nuevo = valor_nuevo + "&";
                                    valor_anterior = valor_anterior + "&";
                                }
                                valor_nuevo = "id_pais='" + NIdPais + "'";
                                valor_anterior = "id_pais='" + AIdPais + "'";
                            }
                            if (NIdSucursal.equals(AIdSucursal) == false) {
                                if (!valor_nuevo.equals("")) {
                                    valor_nuevo = valor_nuevo + "&";
                                    valor_anterior = valor_anterior + "&";
                                }
                                valor_nuevo = "id_sucursal='" + NIdSucursal + "'";
                                valor_anterior = "id_sucursal='" + AIdSucursal + "'";
                            }

                            resultado4 = gA.IngresaAuditoria("Modificar", valor_anterior, valor_nuevo, fo.getIdUsu(), Integer.valueOf(gA.getIdFormulario().toString()), String.valueOf(fo.getIdPuerto()), true, cn);
                            if ((Boolean) resultado4.get(0) == false) {

                                ArrayList<Object> resultado5 = new ArrayList<Object>();
                                resultado5 = gr.commint(cn);
                                if ((Boolean) resultado5.get(0) == false) {

                                    ArrayList<Object> resultado6 = new ArrayList<Object>();
                                    resultado6 = gr.autoCommint(true, cn);
                                    if ((Boolean) resultado6.get(0) == false) {

                                        ArrayList<Object> resultado7 = new ArrayList<Object>();
                                        resultado7 = gr.MostrarPuertoFormulario(fo.getIdPuerto(), false, null);
                                        if ((Boolean) resultado7.get(0) == false) {

                                            request.setAttribute("getIdPuerto", fo.getIdPuerto());
                                            request.setAttribute("getNombreCorto", fo.getNombreCorto());
                                            request.setAttribute("getDescripcion", fo.getDescripcion());
                                            request.setAttribute("getIdMunicipio", fo.getIdMunicipio());
                                            request.setAttribute("getIdDepartamento", fo.getIdDepartamento());
                                            request.setAttribute("getIdPais", fo.getIdPais());
                                            request.setAttribute("getIdSucursal", fo.getIdSucursal());
                                            request.setAttribute("getFechaModificacion", gr.getFechaModificacion());
                                            request.setAttribute("getNombreUsu", gr.getNombreUsu());
                                            //para validar si se modifico un campo
                                            session.setAttribute("getPuertoIdPuerto", gr.getIdPuerto());
                                            session.setAttribute("getPuertoNombreCorto", gr.getNombreCorto());
                                            session.setAttribute("getPuertoDescripcion", gr.getDescripcion());
                                            session.setAttribute("getPuertoIdMunicipio", gr.getIdMunicipio());
                                            session.setAttribute("getPuertoIdDepartamento", gr.getIdDepartamento());
                                            session.setAttribute("getPuertoIdSucursal", gr.getIdSucursal());
                                            session.setAttribute("getPuertoIdPais", gr.getIdPais());

                                            request.setAttribute("respuesta", "Registro modificado correctamente.");
                                            System.out.println("Action Modificar Puerto");
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

            request.setAttribute("getIdPuerto", "");
            request.setAttribute("getNombreCorto", "");
            request.setAttribute("getDescripcion", "");
            request.setAttribute("getIdMunicipio", "");
            request.setAttribute("getIdDepartamento", "");
            request.setAttribute("getIdPais", "");
            request.setAttribute("getIdSucursal", "");
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
                    resultado2 = gr.EliminaPuerto(fo, true, cn);
                    if ((Boolean) resultado2.get(0) == false) {

                        ArrayList<Object> resultado3 = new ArrayList<Object>();
                        resultado3 = gA.BuscarFormulario("puerto", true, cn);
                        if ((Boolean) resultado3.get(0) == false) {

                            ArrayList<Object> resultado4 = new ArrayList<Object>();
                            String valor_anterior = "id=" + resultado.get(1) + "&nombre_corto=" + fo.getNombreCorto() + "&descripcion=" + fo.getDescripcion() + "&id_departamento=" + fo.getIdDepartamento() + "&id_pais=" + fo.getIdPais() + "&id_Municipio=" + fo.getIdMunicipio() + "&id_Sucursal=" + fo.getIdSucursal().toString();
                            resultado4 = gA.IngresaAuditoria("Eliminar", valor_anterior, "", fo.getIdUsu(), Integer.valueOf(gA.getIdFormulario().toString()), String.valueOf(fo.getIdPuerto()), true, cn);
                            if ((Boolean) resultado4.get(0) == false) {

                                ArrayList<Object> resultado5 = new ArrayList<Object>();
                                resultado5 = gr.commint(cn);
                                if ((Boolean) resultado5.get(0) == false) {

                                    ArrayList<Object> resultado6 = new ArrayList<Object>();
                                    resultado6 = gr.autoCommint(true, cn);
                                    if ((Boolean) resultado6.get(0) == false) {

                                        request.setAttribute("respuesta", "Registro eliminado correctamente.");
                                        System.out.println("Action Eliminar Puerto");
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
