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
public class PuertoOpForm extends ActionForm {

    private String bIdPuerto;
    private String bIdPais;
    private String bIdDepartamento;
    private String bIdMunicipio;
    private String bNombre;
    private String op;
    private int id;

    public String getbIdPais() {
        return bIdPais;
    }

    public void setbIdPais(String bIdPais) {
        this.bIdPais = bIdPais;
    }

    public String getbIdDepartamento() {
        return bIdDepartamento;
    }

    public void setbIdDepartamento(String bIdDepartamento) {
        this.bIdDepartamento = bIdDepartamento;
    }

    public String getbIdMunicipio() {
        return bIdMunicipio;
    }

    public void setbIdMunicipio(String bIdMunicipio) {
        this.bIdMunicipio = bIdMunicipio;
    }

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

    public String getbIdPuerto() {
        return bIdPuerto;
    }

    public void setbIdPuerto(String bIdPuerto) {
        this.bIdPuerto = bIdPuerto;
    }

    public String getbNombre() {
        return bNombre;
    }

    public void setbNombre(String bNombre) {
        this.bNombre = bNombre;
    }
}
