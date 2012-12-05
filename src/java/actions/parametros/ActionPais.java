/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actions.parametros;

import forms.parametros.PaisForm;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.log.GestionAuditoria;
import modelo.parametros.GestionPais;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Mario
 */
public class ActionPais extends Action {

    public ActionPais() {

        super();

    }

    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception {

        PaisForm fo = (PaisForm) form;
        GestionPais gr = new GestionPais();
        GestionAuditoria gA = new GestionAuditoria();
        if (fo.getOp().equals("nuevo")) {

            request.setAttribute("getIdPais", fo.getIdPais());
            request.setAttribute("getNombre", fo.getNombre());

            ArrayList<Object> resultado = new ArrayList<Object>();
            Connection cn = null;
            resultado = gr.ObtenerConexion();
            if ((Boolean) resultado.get(0) == false) {

                cn = (Connection) resultado.get(1);
                ArrayList<Object> resultado1 = new ArrayList<Object>();
                resultado1 = gr.autoCommint(false, cn);
                if ((Boolean) resultado1.get(0) == false) {

                    ArrayList<Object> resultado2 = new ArrayList<Object>();
                    resultado2 = gr.IngresaPais(fo, true, cn);
                    if ((Boolean) resultado2.get(0) == false) {

                        ArrayList<Object> resultado3 = new ArrayList<Object>();
                        resultado3 = gA.BuscarFormulario("pais", true, cn);
                        if ((Boolean) resultado3.get(0) == false) {

                            ArrayList<Object> resultado4 = new ArrayList<Object>();
                            String valor_nuevo = "id=" + fo.getIdPais() + "&nombre=" + fo.getNombre();
                            resultado4 = gA.IngresaAuditoria("Nuevo", "", valor_nuevo, fo.getIdUsu(), Integer.valueOf(gA.getIdFormulario().toString()), fo.getIdPais(), true, cn);
                            if ((Boolean) resultado4.get(0) == false) {

                                ArrayList<Object> resultado5 = new ArrayList<Object>();
                                resultado5 = gr.commint(cn);
                                if ((Boolean) resultado5.get(0) == false) {

                                    ArrayList<Object> resultado6 = new ArrayList<Object>();
                                    resultado6 = gr.autoCommint(true, cn);
                                    if ((Boolean) resultado6.get(0) == false) {

                                        request.setAttribute("respuesta", "Registro ingresado correctamente.");
                                        System.out.println("Action Ingreso Pais");
                                        return mapping.findForward("ok");

                                    } else {

                                        request.setAttribute("error", resultado6.get(1));
                                        resultado6 = gr.rollback(cn);
                                        return mapping.findForward("error");

                                    }
                                } else {

                                    request.setAttribute("error", resultado5.get(1));
                                    resultado5 = gr.rollback(cn);
                                    return mapping.findForward("error");

                                }
                            } else {

                                request.setAttribute("error", resultado4.get(1));
                                resultado4 = gr.rollback(cn);
                                return mapping.findForward("error");

                            }
                        } else {

                            request.setAttribute("error", resultado3.get(1));
                            resultado3 = gr.rollback(cn);
                            return mapping.findForward("error");

                        }
                    } else {

                        request.setAttribute("error", resultado2.get(1));
                        resultado2 = gr.rollback(cn);
                        return mapping.findForward("error");

                    }
                } else {

                    request.setAttribute("error", resultado1.get(1));
                    resultado1 = gr.rollback(cn);
                    return mapping.findForward("error");

                }
            } else {

                request.setAttribute("error", resultado.get(1));
                resultado = gr.rollback(cn);
                return mapping.findForward("error");

            }

        } else if (fo.getOp().equals("modificar")) {

            request.setAttribute("getIdPais", fo.getIdPais());
            request.setAttribute("getNombre", fo.getNombre());

            ArrayList<Object> resultado = new ArrayList<Object>();
            resultado = gr.ModificaPais(fo, false, null);
            if ((Boolean) resultado.get(0) == false) {
                if ((Integer) resultado.get(1) >= 1) {
                    request.setAttribute("respuesta", "Registro modificado correctamente.");
                    System.out.println("Action Modicar Pais");
                } else {
                    request.setAttribute("respuesta", "Registro no fue modificado correctamente, vuelvalo a intentar o contacte al programador.");
                    System.out.println("Action Modicar Pais");
                }
                return mapping.findForward("ok");
            } else {

                request.setAttribute("error", resultado.get(1));
                return mapping.findForward("error");

            }

        } else if (fo.getOp().equals("eliminar")) {

            request.setAttribute("getIdPais", "");
            request.setAttribute("getNombre", "");

            ArrayList<Object> resultado = new ArrayList<Object>();
            resultado = gr.EliminaPais(fo, false, null);
            if ((Boolean) resultado.get(0) == false) {
                if ((Integer) resultado.get(1) >= 1) {
                    request.setAttribute("respuesta", "Registro eliminado correctamente.");
                    System.out.println("Action Eliminar Usuarios");
                } else {
                    request.setAttribute("respuesta", "Registro no fue eliminado correctamente, vuelvalo a intentar o contacte al programador.");
                    System.out.println("Action Eliminar Usuarios");
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
