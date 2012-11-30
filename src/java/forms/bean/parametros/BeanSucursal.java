/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package forms.bean.parametros;

import java.io.Serializable;

/**
 *
 * @author mario
 */
public class BeanSucursal implements Serializable{

    private Object idSucursal;
    private Object nombre;

    public Object getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Object idSucursal) {
        this.idSucursal = idSucursal;
    }

    public Object getNombre() {
        return nombre;
    }

    public void setNombre(Object nombre) {
        this.nombre = nombre;
    }

}
