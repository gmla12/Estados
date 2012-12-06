/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actions.log;

import forms.log.AuditoriaForm;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.log.GestionAuditoria;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author mario
 */
public class ActionAuditoria extends Action {

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
    public ActionAuditoria() {

        super();

    }

    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception {

        AuditoriaForm fo = (AuditoriaForm) form;
        GestionAuditoria g = new GestionAuditoria();
        HttpSession session = request.getSession();

        System.out.println("********************************************");
        System.out.println("*********  ActionAuditoria  **********");
        System.out.println("********************************************");
        request.setAttribute("respuesta", "");

        String oop = request.getParameter("getOp").toString();
        if (oop.equals("buscar2") == true) {
            fo.setOp("buscar");
            fo.setAccion(request.getParameter("accion").toString());
            fo.setFormulario(request.getParameter("formulario").toString());
            fo.setReferencia(request.getParameter("referencia").toString());
        }

        if (fo.getOp() != null) {

            if (fo.getOp().equals("buscar")) {

                ArrayList<Object> resultado = new ArrayList<Object>();
                resultado = g.BuscarFormulario(fo.getFormulario(), false, null);
                if ((Boolean) resultado.get(0) == false) {

                    ArrayList<Object> resultado1 = new ArrayList<Object>();
                    resultado1 = g.MostrarAuditoriaReferencia(fo.getReferencia(), Integer.valueOf(g.getIdFormulario().toString()), false, null);
                    if ((Boolean) resultado1.get(0) == false) {

                        session.setAttribute("GR_AUDITORIA", resultado1.get(1));

                        return mapping.findForward("ok");

                    } else {

                        request.setAttribute("error", resultado1.get(1));
                        return mapping.findForward("error");

                    }

                } else {

                    request.setAttribute("error", resultado.get(1));
                    return mapping.findForward("error");

                }


            } else {

                return mapping.findForward("ok");

            }

        } else {

            return mapping.findForward("ok");

        }
    }
}