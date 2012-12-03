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
public class BeanPuerto implements Serializable{

    private Object idPuerto;
    private Object nombre;

    public Object getIdPuerto() {
        return idPuerto;
    }

    public void setIdPuerto(Object idPuerto) {
        this.idPuerto= idPuerto;
    }

    public Object getNombre() {
        return nombre;
    }

    public void setNombre(Object nombre) {
        this.nombre = nombre;
    }

}
