/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import forms.PuertoForm;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.GestionPuerto;
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
        if (fo.getOp().equals("nuevo")) {

            request.setAttribute("getIdPuerto", fo.getIdPuerto());
            request.setAttribute("getNombre", fo.getNombre());
            request.setAttribute("getIdPais", fo.getIdPais());
            request.setAttribute("getIdDepartamento", fo.getIdDepartamento());
            request.setAttribute("getIdMunicipio", fo.getIdMunicipio());

            ArrayList<Object> resultado = new ArrayList<Object>();
            resultado = gr.IngresaPuerto(fo, false, null);
            if ((Boolean) resultado.get(0) == false) {
                if ((Integer) resultado.get(2) >= 1) {
                    request.setAttribute("getIdPuerto", resultado.get(1));
                    request.setAttribute("respuesta", "Registro ingresado correctamente.");
                    System.out.println("Action Ingreso Puerto");
                } else {
                    request.setAttribute("respuesta", "Registro no fue ingresado correctamente, vuelvalo a intentar o contacte al programador.");
                    System.out.println("Action Ingreso Puerto");
                }
                return mapping.findForward("ok");
            } else {

                request.setAttribute("error", resultado.get(1));
                return mapping.findForward("error");

            }

        } else if (fo.getOp().equals("modificar")) {

            request.setAttribute("getIdPuerto", fo.getIdPuerto());
            request.setAttribute("getNombre", fo.getNombre());
            request.setAttribute("getIdPais", fo.getIdPais());
            request.setAttribute("getIdDepartamento", fo.getIdDepartamento());
            request.setAttribute("getIdMunicipio", fo.getIdMunicipio());

            ArrayList<Object> resultado = new ArrayList<Object>();
            resultado = gr.ModificaPuerto(fo, false, null);
            if ((Boolean) resultado.get(0) == false) {
                if ((Integer) resultado.get(1) >= 1) {
                    request.setAttribute("respuesta", "Registro modificado correctamente.");
                    System.out.println("Action Modicar Puerto");
                } else {
                    request.setAttribute("respuesta", "Registro no fue modificado correctamente, vuelvalo a intentar o contacte al programador.");
                    System.out.println("Action Modicar puerto");
                }
                return mapping.findForward("ok");
            } else {

                request.setAttribute("error", resultado.get(1));
                return mapping.findForward("error");

            }

        } else if (fo.getOp().equals("eliminar")) {

            request.setAttribute("getIdSucursal", fo.getIdPuerto());
            request.setAttribute("getNombre", fo.getNombre());
            request.setAttribute("getIdPais", fo.getIdPais());
            request.setAttribute("getIdDepartamento", fo.getIdDepartamento());
            request.setAttribute("getIdMunicipio", fo.getIdMunicipio());

            ArrayList<Object> resultado = new ArrayList<Object>();
            resultado = gr.EliminaPuerto(fo, false, null);
            if ((Boolean) resultado.get(0) == false) {
                if ((Integer) resultado.get(1) >= 1) {
                    request.setAttribute("respuesta", "Registro eliminado correctamente.");
                    System.out.println("Action Eliminar Puerto");
                } else {
                    request.setAttribute("respuesta", "Registro no fue eliminado correctamente, vuelvalo a intentar o contacte al programador.");
                    System.out.println("Action Eliminar Puerto");
                }
                return mapping.findForward("ok");
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
