/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package forms.log;

import org.apache.struts.action.ActionForm;

/**
 *
 * @author mario
 */
public class AuditoriaForm extends ActionForm{
    private String op;
    private String accion;
    private String formulario;
    private String referencia;

    public String getFormulario() {
        return formulario;
    }

    public void setFormulario(String formulario) {
        this.formulario = formulario;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

}
