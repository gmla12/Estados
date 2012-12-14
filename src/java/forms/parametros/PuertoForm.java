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
public class PuertoForm extends ActionForm{
    private int idPuerto;
    private String nombreCorto;
    private String descripcion;
    private String idMunicipio;
    private String idDepartamento;
    private String idPais;
    private Integer idUsu;
    private String op;

    public int getIdPuerto() {
        return idPuerto;
    }

    public void setIdPuerto(int idPuerto) {
        this.idPuerto = idPuerto;
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

    public String getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(String idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public String getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(String idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getIdPais() {
        return idPais;
    }

    public void setIdPais(String idPais) {
        this.idPais = idPais;
    }

    public Integer getIdUsu() {
        return idUsu;
    }

    public void setIdUsu(Integer idUsu) {
        this.idUsu = idUsu;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }
}
