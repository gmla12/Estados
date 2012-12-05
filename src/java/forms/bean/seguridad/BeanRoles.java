/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package forms.bean.seguridad;

import java.io.Serializable;

/**
 *
 * @author mario
 */
public class BeanRoles implements Serializable{

    private Object idRoles;
    private Object nombre;
    private Object descripcion;

    public Object getIdRoles() {
        return idRoles;
    }

    public void setIdRoles(Object idRoles) {
        this.idRoles = idRoles;
    }

    public Object getNombre() {
        return nombre;
    }

    public void setNombre(Object nombre) {
        this.nombre = nombre;
    }

    public Object getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(Object descripcion) {
        this.descripcion = descripcion;
    }

}
