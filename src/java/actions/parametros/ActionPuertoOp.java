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
import modelo.parametros.GestionPuerto;
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

                    return mapping.findForward("modificar");

                } else {

                    request.setAttribute("error", resultado.get(1));
                    return mapping.findForward("error");

                }

            } else if (fo.getOp().equals("buscar")) {

                if (fo.getbNombre() == null) {
                    fo.setbIdPuerto((String) session.getAttribute("getbIdPuerto"));
                    fo.setbNombre((String) session.getAttribute("getbNombre"));
                }

                ArrayList<Object> resultado = new ArrayList<Object>();
                resultado = gr.MostrarPuertoOP(fo, false, null);
                if ((Boolean) resultado.get(0) == false) {

                    session.setAttribute("getbIdPuerto", fo.getbIdPuerto());
                    session.setAttribute("getbNombre", fo.getbNombre());

                    session.setAttribute("GR_PUERTO", resultado.get(1));
                    return mapping.findForward("ok");

                } else {

                    request.setAttribute("error", resultado.get(1));
                    return mapping.findForward("error");

                }

            } else {

                request.setAttribute("getIdPuerto", "");
                request.setAttribute("getNombre", "");

                return mapping.findForward("nuevo");

            }

        } else {

            session.setAttribute("getbIdPuerto", "");
            session.setAttribute("getbNombre", "");
            fo.setbNombre("");

            ArrayList<Object> resultado = new ArrayList<Object>();
            resultado = gr.MostrarPuertoOP(fo, false, null);
            if ((Boolean) resultado.get(0) == false) {

                session.setAttribute("GR_PUERTO", resultado.get(1));
                return mapping.findForward("ok");

            } else {

                request.setAttribute("error", resultado.get(1));
                return mapping.findForward("error");

            }

        }

    }
}
