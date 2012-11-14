/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package forms;

import org.apache.struts.action.ActionForm;

/**
 *
 * @author Mario
 */
public class PuertoForm extends ActionForm{
    private int idPuerto;
    private String nombre;
    private String op;

    public int getIdPuerto() {
        return idPuerto;
    }

    public void setIdPuerto(int idPuerto) {
        this.idPuerto = idPuerto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }
}
