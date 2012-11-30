/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package forms.parametros;

import org.apache.struts.action.ActionForm;

/**
 *
 * @author mario
 */
public class SucursalOpForm extends ActionForm{
    private String bIdSucursal;
    private String bNombre;
    private String op;
    private int id;

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getbIdSucursal() {
        return bIdSucursal;
    }

    public void setbIdSucursal(String bIdSucursal) {
        this.bIdSucursal = bIdSucursal;
    }

    public String getbNombre() {
        return bNombre;
    }

    public void setbNombre(String bNombre) {
        this.bNombre = bNombre;
    }

}
