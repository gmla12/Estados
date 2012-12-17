/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actions.parametros;

import forms.parametros.SucursalOpForm;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.parametros.GestionSucursal;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author mario
 */
public class ActionSucursalOp extends Action {

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
    public ActionSucursalOp() {

        super();

    }

    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception {

        SucursalOpForm fo = (SucursalOpForm) form;
        GestionSucursal gr = new GestionSucursal();
        HttpSession session = request.getSession();

        System.out.println("********************************************");
        System.out.println("*********  ActionOpSucursal  **********");
        System.out.println("********************************************");
        request.setAttribute("respuesta", "");

        if (request.getAttribute("getOp") == "buscar") {
            fo.setOp("buscar");
            request.setAttribute("getOp", "buscar2");
        }

        if (fo.getOp() != null) {

            if (fo.getOp().equals("modificar")) {

                ArrayList<Object> resultado = new ArrayList<Object>();
                resultado = gr.MostrarSucursalFormulario(fo.getId(), false, null);
                if ((Boolean) resultado.get(0) == false) {

                    request.setAttribute("getIdSucursal", gr.getIdSucursal());
                    request.setAttribute("getNombreCorto", gr.getNombreCorto());
                    request.setAttribute("getDescripcion", gr.getDescripcion());
                    request.setAttribute("getFechaModificacion", gr.getFechaModificacion());
                    request.setAttribute("getNombreUsu", gr.getNombreUsu());
                    //para validar si se modifico un campo
                    session.setAttribute("getSucursalIdSucursal", gr.getIdSucursal());
                    session.setAttribute("getSucursalNombreCorto", gr.getNombreCorto());
                    session.setAttribute("getSucursalDescripcion", gr.getDescripcion());

                    return mapping.findForward("modificar");

                } else {

                    request.setAttribute("error", resultado.get(1));
                    return mapping.findForward("error");

                }

            } else if (fo.getOp().equals("buscar")) {

                if (fo.getbNombreCorto() == null) {
                    fo.setbIdSucursal((String) session.getAttribute("getbIdSucursal"));
                    fo.setbNombreCorto((String) session.getAttribute("getbNombreCorto"));
                    fo.setbDescripcion((String) session.getAttribute("getbDescripcion"));
                }

                ArrayList<Object> resultado = new ArrayList<Object>();
                resultado = gr.MostrarSucursalOP(fo, false, null);
                if ((Boolean) resultado.get(0) == false) {

                    session.setAttribute("getbIdSucursal", fo.getbIdSucursal());
                    session.setAttribute("getbNombreCorto", fo.getbNombreCorto());
                    session.setAttribute("getbDescripcion", fo.getbDescripcion());

                    session.setAttribute("GR_SUCURSAL", resultado.get(1));
                    return mapping.findForward("ok");

                } else {

                    request.setAttribute("error", resultado.get(1));
                    return mapping.findForward("error");

                }

            } else {

                request.setAttribute("getIdSucursal", "");
                request.setAttribute("getNombreCorto", "");
                request.setAttribute("getDescripcion", "");
                request.setAttribute("getNombreUsu", "");
                request.setAttribute("getFechaModificacion", "");

                return mapping.findForward("nuevo");

            }

        } else {

            session.setAttribute("getbIdSucursal", "");
            session.setAttribute("getbNombreCorto", "");
            session.setAttribute("getbDescripcion", "");
            fo.setbNombreCorto("");
            fo.setbDescripcion("");

            ArrayList<Object> resultado = new ArrayList<Object>();
            resultado = gr.MostrarSucursalOP(fo, false, null);
            if ((Boolean) resultado.get(0) == false) {

                session.setAttribute("GR_SUCURSAL", resultado.get(1));
                return mapping.findForward("ok");

            } else {

                request.setAttribute("error", resultado.get(1));
                return mapping.findForward("error");

            }

        }

    }
}
