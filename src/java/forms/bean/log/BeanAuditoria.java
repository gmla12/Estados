/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package forms.bean.log;

import java.io.Serializable;

/**
 *
 * @author mario
 */
public class BeanAuditoria implements Serializable{

    private Object nombreUsu;
    private Object fecha;
    private Object accion;
    private Object valorAnterior;
    private Object valorNuevo;

    public Object getNombreUsu() {
        return nombreUsu;
    }

    public void setNombreUsu(Object nombreUsu) {
        this.nombreUsu = nombreUsu;
    }

    public Object getFecha() {
        return fecha;
    }

    public void setFecha(Object fecha) {
        this.fecha = fecha;
    }

    public Object getAccion() {
        return accion;
    }

    public void setAccion(Object accion) {
        this.accion = accion;
    }

    public Object getValorAnterior() {
        return valorAnterior;
    }

    public void setValorAnterior(Object valorAnterior) {
        this.valorAnterior = valorAnterior;
    }

    public Object getValorNuevo() {
        return valorNuevo;
    }

    public void setValorNuevo(Object valorNuevo) {
        this.valorNuevo = valorNuevo;
    }

}
