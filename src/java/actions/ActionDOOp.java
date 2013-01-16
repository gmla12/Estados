/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import forms.DOOpForm;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.GestionDO;
import modelo.GestionEntidad;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author mario
 */
public class ActionDOOp extends Action {

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
    public ActionDOOp() {

        super();

    }

    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception {

        DOOpForm fo = (DOOpForm) form;
        GestionDO gr = new GestionDO();
        GestionEntidad grEntidad = new GestionEntidad();
//        GestionPlantillaDispositivoHija grHija = new GestionPlantillaDispositivoHija();
        HttpSession session = request.getSession();

        System.out.println("********************************************");
        System.out.println("*********  ActionOpDO  **********");
        System.out.println("********************************************");
        request.setAttribute("respuesta", "");

        if (request.getAttribute("getOp") == "buscar") {
            System.out.println("Volver ActionDOOP");
            fo.setOp("buscar");
            request.setAttribute("getOp", "buscar2");
        }

        if (fo.getOp() != null) {

            if (fo.getOp().equals("modificar")) {
                System.out.println("Modificar ActionDOOP");

                ArrayList<Object> resultado;
                resultado = gr.MostrarDOFormulario(fo.getId(), false, null);
                if ((Boolean) resultado.get(0) == false) {

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

//                    ArrayList<Object> GR_CARACTERISTICAPLANTILLA;
//                    ArrayList<Object> GR_PlantillaDisponible;
//                    ArrayList<Object> GR_PlantillaHija;
//
//                    ArrayList<Object> resultado2;
//                    resultado2 = grCaract.MostrarCaracteristicaPlantilla(Integer.valueOf(String.valueOf(gr.getIdPlantillaDispositivo())), false, null);
//                    if ((Boolean) resultado2.get(0) == false) {
//
//                        GR_CARACTERISTICAPLANTILLA = (ArrayList) resultado2.get(1);
//
//                        ArrayList<Object> resultado3;
//                        resultado3 = gr.MostrarPlantillaDispositivoHija(Integer.valueOf(String.valueOf(gr.getIdPlantillaDispositivo())), false, null);
//                        if ((Boolean) resultado3.get(0) == false) {
//
//                            GR_PlantillaDisponible = (ArrayList) resultado3.get(1);
//
//                            ArrayList<Object> resultado4;
//                            resultado4 = grHija.MostrarPlantillaDispositivoHija(Integer.valueOf(String.valueOf(gr.getIdPlantillaDispositivo())), false, null);
//                            if ((Boolean) resultado4.get(0) == false) {
//
//                                GR_PlantillaHija = (ArrayList) resultado4.get(1);
//
//                                session.setAttribute("GR_CARACTERISTICAPLANTILLA", GR_CARACTERISTICAPLANTILLA);
//                                session.setAttribute("GR_PlantillaDisponible", GR_PlantillaDisponible);
//                                session.setAttribute("GR_PlantillaHija", GR_PlantillaHija);

                    return mapping.findForward("modificar");

//                            } else {
//
//                                request.setAttribute("error", resultado4.get(1));
//                                return mapping.findForward("error");
//
//                            }
//
//                        } else {
//
//                            request.setAttribute("error", resultado3.get(1));
//                            return mapping.findForward("error");
//
//                        }
//
//                    } else {
//
//                        request.setAttribute("error", resultado2.get(1));
//                        return mapping.findForward("error");
//
//                    }

                } else {

                    request.setAttribute("error", resultado.get(1));
                    return mapping.findForward("error");

                }

            } else if (fo.getOp().equals("buscar")) {
                System.out.println("Buscar ActionDOOP");

//                if (fo.getbIdEntidad() != null) {
//                    session.setAttribute("getbIdEntidad", fo.getbIdEntidad());
//                    session.setAttribute("getbNumFactura", fo.getbNumFactura());
//                    session.setAttribute("getbFecha", fo.getbFecha());
//                } else {
//                    fo.setbIdEntidad((String) session.getAttribute("getbIdEntidad"));
//                    fo.setbNumFactura((String) session.getAttribute("getbNumFactura"));
//                    fo.setbFecha((String) session.getAttribute("getbFecha"));
//                }
//
//                ArrayList<Object> resultado;
//                resultado = gr.MostrarFacturaOP(fo, false, null);
//                if ((Boolean) resultado.get(0) == false) {
//
//                    session.setAttribute("GR_FACTURA", resultado.get(1));
                return mapping.findForward("ok");
//
//                } else {
//
//                    request.setAttribute("error", resultado.get(1));
//                    return mapping.findForward("error");
//
//                }

            } else {
                System.out.println("Nuevo ActionDOOP");

                request.setAttribute("getIdDOs", "");
                request.setAttribute("getDO", "");
                request.setAttribute("getIdCliente", "");
                request.setAttribute("getCliente", "");
                request.setAttribute("getIdSucursal", "");
                request.setAttribute("getLote", "");
                request.setAttribute("getBL", "");
                request.setAttribute("getIdPuerto", "");
                request.setAttribute("getObservaciones", "");
                //Pendiente: colocar los campos de los items en vacio
                return mapping.findForward("nuevo");

            }

        } else {
            System.out.println("1er Ingreso ActionDOOP");

            session.setAttribute("getbDO", "");
            session.setAttribute("getbReferencia", "");
            session.setAttribute("getbIdCliente", "");
            session.setAttribute("getbCliente", "");
            session.setAttribute("getbBL", "");
            session.setAttribute("getbLote", "");
            session.setAttribute("getbDescripcion", "");
            fo.setbDO("");
            fo.setbReferencia("");
            fo.setbIdCliente("");
            fo.setbBL("");
            fo.setbLote("");
            fo.setbDescripcion("");

            ArrayList<Object> resultado;
            resultado = gr.MostrarDOOP(fo, false, null);
            if ((Boolean) resultado.get(0) == false) {

                ArrayList<Object> resultado1;
                resultado1 = grEntidad.MostrarEntidad(false, null);
                if ((Boolean) resultado1.get(0) == false) {

                    session.setAttribute("CMB_ENTIDAD", resultado1.get(1));
                    session.setAttribute("GR_DOs", resultado.get(1));
                    return mapping.findForward("ok");

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
