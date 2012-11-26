/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import forms.PuertoOpForm;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.GestionDepartamento;
import modelo.GestionMunicipio;
import modelo.GestionPais;
import modelo.GestionPuerto;
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

                ArrayList<Object> resultado = new ArrayList<Object>();
                resultado = gr.MostrarPuertoFormulario(fo.getId(), false, null);
                if ((Boolean) resultado.get(0) == false) {

                    request.setAttribute("getIdPuerto", gr.getIdPuerto());
                    request.setAttribute("getNombre", gr.getNombre());
                    request.setAttribute("getIdPais", gr.getIdPais());
                    request.setAttribute("getIdDepartamento", gr.getIdDepartamento());
                    request.setAttribute("getIdMunicipio", gr.getIdMunicipio());

                    return mapping.findForward("modificar");

                } else {

                    request.setAttribute("error", resultado.get(1));
                    return mapping.findForward("error");

                }

            } else if (fo.getOp().equals("buscar")) {

                if (fo.getbNombre() == null) {
                    fo.setbIdPuerto((String) session.getAttribute("getbIdPuerto"));
                    fo.setbNombre((String) session.getAttribute("getbNombre"));
                    fo.setbIdPais((String) session.getAttribute("getbIdPais"));
                    fo.setbIdDepartamento((String) session.getAttribute("getIdDepartamento"));
                    fo.setbIdMunicipio((String) session.getAttribute("getbIdMunicipio"));
                }

                ArrayList<Object> resultado = new ArrayList<Object>();
                resultado = gr.MostrarPuertoOP(fo, false, null);
                if ((Boolean) resultado.get(0) == false) {

                    ArrayList<Object> resultado2 = new ArrayList<Object>();
                    resultado2 = gm.MostrarMunicipio(fo.getbIdPais(), fo.getbIdDepartamento(), false, null);
                    if ((Boolean) resultado2.get(0) == false) {

                        ArrayList<Object> resultado3 = new ArrayList<Object>();
                        resultado3 = gd.MostrarDepartamento(fo.getbIdPais(), false, null);
                        if ((Boolean) resultado3.get(0) == false) {

                            ArrayList<Object> resultado4 = new ArrayList<Object>();
                            resultado4 = gp.MostrarPais(false, null);
                            if ((Boolean) resultado4.get(0) == false) {

                                session.setAttribute("getbIdPuerto", fo.getbIdPuerto());
                                session.setAttribute("getbNombre", fo.getbNombre());
                                session.setAttribute("getbIdPais", fo.getbIdPais());
                                session.setAttribute("getbIdDepartamento", fo.getbIdDepartamento());
                                session.setAttribute("getbIdMunicipio", fo.getbIdMunicipio());

                                session.setAttribute("GR_PUERTO", resultado.get(1));
                                session.setAttribute("GR_MUNICIPIO", resultado2.get(1));
                                session.setAttribute("CMB_DEPARTAMENTO", resultado3.get(1));


                                session.setAttribute("CMB_PAIS", resultado4.get(1));
                                return mapping.findForward("ok");
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
                request.setAttribute("getNombre", "");
                request.setAttribute("getIdPais", "");
                request.setAttribute("getIdDepartamento", "");
                request.setAttribute("getIdMunicipio", "");

                return mapping.findForward("nuevo");

            }

        } else {

            session.setAttribute("getbIdPuerto", "");
            session.setAttribute("getbNombre", "");
            session.setAttribute("getbIdPais", "");
            session.setAttribute("getbIdDepartamento", "");
            session.setAttribute("getbIdMunicipio", "");
            fo.setbNombre("");
            fo.setbIdPais("");
            fo.setbIdDepartamento("");
            fo.setbIdMunicipio("");

            ArrayList<Object> resultado = new ArrayList<Object>();
            resultado = gr.MostrarPuertoOP(fo, false, null);
            if ((Boolean) resultado.get(0) == false) {
                ArrayList<Object> resultado1 = new ArrayList<Object>();
                resultado1 = gm.MostrarMunicipio("", "", false, null);
                if ((Boolean) resultado1.get(0) == false) {

                    ArrayList<Object> resultado2 = new ArrayList<Object>();
                    resultado2 = gd.MostrarDepartamento("", false, null);
                    if ((Boolean) resultado2.get(0) == false) {

                        ArrayList<Object> resultado3 = new ArrayList<Object>();
                        resultado3 = gp.MostrarPais(false, null);
                        if ((Boolean) resultado3.get(0) == false) {

                            session.setAttribute("GR_MUNICIPIO", resultado1.get(1));
                            session.setAttribute("CMB_DEPARTAMENTO", resultado2.get(1));
                            session.setAttribute("CMB_PAIS", resultado3.get(1));
                            


                            session.setAttribute("GR_PUERTO", resultado.get(1));
                            return mapping.findForward("ok");
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

        }

    }
}
