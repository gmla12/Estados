/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package forms.parametros;

import org.apache.struts.action.ActionForm;

/**
 *
 * @author Mario
 */
public class SucursalForm extends ActionForm{
    private int idSucursal;
    private String nombreCorto;
    private String descripcion;
    private int idUsu;
    private String op;

    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getNombreCorto() {
        return nombreCorto;
    }

    public void setNombreCorto(String nombreCorto) {
        this.nombreCorto = nombreCorto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdUsu() {
        return idUsu;
    }

    public void setIdUsu(int idUsu) {
        this.idUsu = idUsu;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }
}
