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
    private Object nombreCorto;
    private Object descripcion;

    public Object getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Object idSucursal) {
        this.idSucursal = idSucursal;
    }

    public Object getNombreCorto() {
        return nombreCorto;
    }

    public void setNombreCorto(Object nombreCorto) {
        this.nombreCorto = nombreCorto;
    }

    public Object getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(Object descripcion) {
        this.descripcion = descripcion;
    }

}
