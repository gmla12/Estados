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
    private String bNombreCorto;
    private String bDescripcion;
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

    public String getbNombreCorto() {
        return bNombreCorto;
    }

    public void setbNombreCorto(String bNombreCorto) {
        this.bNombreCorto = bNombreCorto;
    }

    public String getbDescripcion() {
        return bDescripcion;
    }

    public void setbDescripcion(String bDescripcion) {
        this.bDescripcion = bDescripcion;
    }

}
