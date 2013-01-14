/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package forms;

import java.util.Date;
import org.apache.struts.action.ActionForm;

/**
 *
 * @author Mario
 */
public class DOForm extends ActionForm{
    private int idDOs;
    private String DO;
    private int idCliente;
    private int idSucursal;
    private String lote;
    private String BL;
    private int idPuerto;
    private int idTipoMercancia;
    private String observaciones;
    private String op;
    private int idUsu;

    public int getIdTipoMercancia() {
        return idTipoMercancia;
    }

    public void setIdTipoMercancia(int idTipoMercancia) {
        this.idTipoMercancia = idTipoMercancia;
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

    public int getIdDOs() {
        return idDOs;
    }

    public void setIdDOs(int idDOs) {
        this.idDOs = idDOs;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getDO() {
        return DO;
    }

    public void setDO(String DO) {
        this.DO = DO;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getBL() {
        return BL;
    }

    public void setBL(String BL) {
        this.BL = BL;
    }

    public int getIdPuerto() {
        return idPuerto;
    }

    public void setIdPuerto(int idPuerto) {
        this.idPuerto = idPuerto;
    }

}
