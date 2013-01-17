/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actions.parametros;

import forms.parametros.PuertoOpForm;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.parametros.GestionDepartamento;
import modelo.parametros.GestionMunicipio;
import modelo.parametros.GestionPais;
import modelo.parametros.GestionPuerto;
import modelo.parametros.GestionSucursal;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author mario
 */
public class ActionPuertoOp extends Action {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public ActionPuertoOp() {

        super();

    }

    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception {

        PuertoOpForm fo = (PuertoOpForm) form;
        GestionPuerto gr = new GestionPuerto();
        GestionMunicipio gm = new GestionMunicipio();
        GestionDepartamento gd = new GestionDepartamento();
        GestionPais gp = new GestionPais();
        GestionSucursal gs = new GestionSucursal();
        HttpSession session = request.getSession();

        System.out.println("********************************************");
        System.out.println("*********  ActionOpPuerto  **********");
        System.out.println("********************************************");
        request.setAttribute("respuesta", "");

        if (request.getAttribute("getOp") == "buscar") {
            fo.setOp("buscar");
            request.setAttribute("getOp", "buscar2");
        }

        if (fo.getOp() != null) {

            if (fo.getOp().equals("modificar")) {

                ArrayList<Object> resultado;
                resultado = gr.MostrarPuertoFormulario(fo.getId(), false, null);
                if ((Boolean) resultado.get(0) == false) {

                    ArrayList<Object> resultado2;
                    resultado2 = gm.MostrarMunicipio((String) gr.getIdDepartamento(), (String) gr.getIdPais(), false, null);
                    if ((Boolean) resultado2.get(0) == false) {

                        ArrayList<Object> resultado3;
                        resultado3 = gd.MostrarDepartamento((String) gr.getIdPais(), false, null);
                        if ((Boolean) resultado3.get(0) == false) {

                            ArrayList<Object> resultado4;
                            resultado4 = gp.MostrarPais(false, null);
                            if ((Boolean) resultado4.get(0) == false) {

                                ArrayList<Object> resultado5;
                                resultado5 = gs.MostrarSucursal(false, null);
                                if ((Boolean) resultado5.get(0) == false) {

                                    request.setAttribute("getIdPuerto", gr.getIdPuerto());
                                    request.setAttribute("getNombreCorto", gr.getNombreCorto());
                                    request.setAttribute("getDescripcion", gr.getDescripcion());
                                    request.setAttribute("getIdMunicipio", gr.getIdMunicipio());
                                    request.setAttribute("getIdDepartamento", gr.getIdDepartamento());
                                    request.setAttribute("getIdPais", gr.getIdPais());
                                    request.setAttribute("getIdSucursal", gr.getIdSucursal());
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

                                    session.setAttribute("CMB_SUCURSAL", resultado5.get(1));
                                    session.setAttribute("CMB_MUNICIPIO", resultado2.get(1));
                                    session.setAttribute("CMB_DEPARTAMENTO", resultado3.get(1));
                                    session.setAttribute("CMB_PAIS", resultado4.get(1));

                                    return mapping.findForward("modificar");

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

                    request.setAttribute("error", resultado.get(1));
                    return mapping.findForward("error");

                }

            } else if (fo.getOp().equals("buscar")) {

                if (fo.getbNombreCorto() == null) {
                    fo.setbIdPuerto((String) session.getAttribute("getbIdPuerto"));
                    fo.setbNombreCorto((String) session.getAttribute("getbNombreCorto"));
                    fo.setbDescripcion((String) session.getAttribute("getbDescripcion"));
                    fo.setbIdMunicipio((String) session.getAttribute("getbIdMunicipio"));
                    fo.setbIdDepartamento((String) session.getAttribute("getbIdDepartamento"));
                    fo.setbIdPais((String) session.getAttribute("getbIdPais"));
                    fo.setbIdSucursal((String) session.getAttribute("getbIdSucursal"));
                }

                ArrayList<Object> resultado;
                resultado = gr.MostrarPuertoOP(fo, false, null);
                if ((Boolean) resultado.get(0) == false) {

                    ArrayList<Object> resultado2;
                    resultado2 = gm.MostrarMunicipio(fo.getbIdDepartamento(), fo.getbIdPais(), false, null);
                    if ((Boolean) resultado2.get(0) == false) {

                        ArrayList<Object> resultado3;
                        resultado3 = gd.MostrarDepartamento(fo.getbIdPais(), false, null);
                        if ((Boolean) resultado3.get(0) == false) {

                            ArrayList<Object> resultado4;
                            resultado4 = gp.MostrarPais(false, null);
                            if ((Boolean) resultado4.get(0) == false) {

                                ArrayList<Object> resultado5;
                                resultado5 = gs.MostrarSucursal(false, null);
                                if ((Boolean) resultado5.get(0) == false) {

                                    session.setAttribute("getbIdPuerto", fo.getbIdPuerto());
                                    session.setAttribute("getbNombreCorto", fo.getbNombreCorto());
                                    session.setAttribute("getbDescripcion", fo.getbDescripcion());
                                    session.setAttribute("getbIdMunicipio", fo.getbIdMunicipio());
                                    session.setAttribute("getbIdDepartamento", fo.getbIdDepartamento());
                                    session.setAttribute("getbIdPais", fo.getbIdPais());
                                    session.setAttribute("getbIdSucursal", fo.getbIdSucursal());

                                    session.setAttribute("CMB_SUCURSAL", resultado5.get(1));
                                    session.setAttribute("CMB_MUNICIPIO", resultado2.get(1));
                                    session.setAttribute("CMB_DEPARTAMENTO", resultado3.get(1));
                                    session.setAttribute("CMB_PAIS", resultado4.get(1));

                                    session.setAttribute("GR_PUERTO", resultado.get(1));
                                    return mapping.findForward("ok");

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

                    request.setAttribute("error", resultado.get(1));
                    return mapping.findForward("error");

                }

            } else {

                request.setAttribute("getIdPuerto", "");
                request.setAttribute("getNombreCorto", "");
                request.setAttribute("getDescripcion", "");
                request.setAttribute("getIdMunicipio", "");
                request.setAttribute("getIdDepartamento", "");
                request.setAttribute("getIdPais", "");
                request.setAttribute("getIdSucursal", "");
                request.setAttribute("getNombreUsu", "");
                request.setAttribute("getFechaModificacion", "");

                return mapping.findForward("nuevo");

            }

        } else {

            session.setAttribute("getbIdPuerto", "");
            session.setAttribute("getbNombreCorto", "");
            session.setAttribute("getbDescripcion", "");
            session.setAttribute("getbIdMunicipio", "");
            session.setAttribute("getbIdDepartamento", "");
            session.setAttribute("getbIdPais", "");
            session.setAttribute("getbIdSucursal", "");
            fo.setbNombreCorto("");
            fo.setbDescripcion("");
            fo.setbIdMunicipio("");
            fo.setbIdDepartamento("");
            fo.setbIdPais("");
            fo.setbIdSucursal("");

            ArrayList<Object> resultado;
            resultado = gr.MostrarPuertoOP(fo, false, null);
            if ((Boolean) resultado.get(0) == false) {

                ArrayList<Object> resultado2;
                resultado2 = gm.MostrarMunicipio("", "", false, null);
                if ((Boolean) resultado2.get(0) == false) {

                    ArrayList<Object> resultado3;
                    resultado3 = gd.MostrarDepartamento("", false, null);
                    if ((Boolean) resultado3.get(0) == false) {

                        ArrayList<Object> resultado4;
                        resultado4 = gp.MostrarPais(false, null);
                        if ((Boolean) resultado4.get(0) == false) {

                            ArrayList<Object> resultado5;
                            resultado5 = gs.MostrarSucursal(false, null);
                            if ((Boolean) resultado5.get(0) == false) {

                                session.setAttribute("CMB_MUNICIPIO", resultado2.get(1));
                                session.setAttribute("CMB_DEPARTAMENTO", resultado3.get(1));
                                session.setAttribute("CMB_PAIS", resultado4.get(1));
                                session.setAttribute("CMB_SUCURSAL", resultado5.get(1));

                                session.setAttribute("GR_PUERTO", resultado.get(1));
                                return mapping.findForward("ok");

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

                request.setAttribute("error", resultado.get(1));
                return mapping.findForward("error");

            }

        }

    }
}
