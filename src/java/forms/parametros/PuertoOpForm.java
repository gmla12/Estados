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
public class PuertoOpForm extends ActionForm{
    private String bIdPuerto;
    private String bNombreCorto;
    private String bDescripcion;
    private String bIdMunicipio;
    private String bIdDepartamento;
    private String bIdPais;
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

    public String getbIdPuerto() {
        return bIdPuerto;
    }

    public void setbIdPuerto(String bIdPuerto) {
        this.bIdPuerto = bIdPuerto;
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

    public String getbIdMunicipio() {
        return bIdMunicipio;
    }

    public void setbIdMunicipio(String bIdMunicipio) {
        this.bIdMunicipio = bIdMunicipio;
    }

    public String getbIdDepartamento() {
        return bIdDepartamento;
    }

    public void setbIdDepartamento(String bIdDepartamento) {
        this.bIdDepartamento = bIdDepartamento;
    }

    public String getbIdPais() {
        return bIdPais;
    }

    public void setbIdPais(String bIdPais) {
        this.bIdPais = bIdPais;
    }

}
