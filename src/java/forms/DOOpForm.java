/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package forms;

import org.apache.struts.action.ActionForm;

/**
 *
 * @author mario
 */
public class DOOpForm extends ActionForm{
    private int bSucursal;
    private int bPuerto;
    private String bIdCliente;
    private String bDO;
    private String bReferencia;
    private String bBL;
    private String bLote;
    private String bDescripcion;
    private String op;
    private String id;

    public String getbBL() {
        return bBL;
    }

    public void setbBL(String bBL) {
        this.bBL = bBL;
    }

    public String getbLote() {
        return bLote;
    }

    public void setbLote(String bLote) {
        this.bLote = bLote;
    }

    public String getbDescripcion() {
        return bDescripcion;
    }

    public void setbDescripcion(String bDescripcion) {
        this.bDescripcion = bDescripcion;
    }

    public String getbIdCliente() {
        return bIdCliente;
    }

    public void setbIdCliente(String bIdCliente) {
        this.bIdCliente = bIdCliente;
    }

    public int getbSucursal() {
        return bSucursal;
    }

    public void setbSucursal(int bSucursal) {
        this.bSucursal = bSucursal;
    }

    public int getbPuerto() {
        return bPuerto;
    }

    public void setbPuerto(int bPuerto) {
        this.bPuerto = bPuerto;
    }

    public String getbDO() {
        return bDO;
    }

    public void setbDO(String bDO) {
        this.bDO = bDO;
    }

    public String getbReferencia() {
        return bReferencia;
    }

    public void setbReferencia(String bReferencia) {
        this.bReferencia = bReferencia;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
