/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package forms.bean;

import java.io.Serializable;

/**
 *
 * @author mario
 */
public class BeanAuditoria implements Serializable{

    private Object idUsuario;
    private Object fecha;
    private Object cambios;
    private Object opcion;

    public Object getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Object idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Object getFecha() {
        return fecha;
    }

    public void setFecha(Object fecha) {
        this.fecha = fecha;
    }

    public Object getCambios() {
        return cambios;
    }

    public void setCambios(Object cambios) {
        this.cambios = cambios;
    }

    public Object getOpcion() {
        return opcion;
    }

    public void setOpcion(Object opcion) {
        this.opcion = opcion;
    }

}
