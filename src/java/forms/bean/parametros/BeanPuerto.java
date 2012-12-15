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
    private Object nombreCorto;
    private Object descripcion;
    private Object idMunicipio;
    private Object idDepartamento;
    private Object idPais;
    private Object idSucursal;

    public Object getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Object idSucursal) {
        this.idSucursal = idSucursal;
    }

    public Object getIdPuerto() {
        return idPuerto;
    }

    public void setIdPuerto(Object idPuerto) {
        this.idPuerto= idPuerto;
    }

    public Object getNombreCorto() {
        return nombreCorto;
    }

    public void setNombreCorto(Object nombreCorto) {
        this.nombreCorto = nombreCorto;
    }

    public Object getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(Object descripcion) {
        this.descripcion = descripcion;
    }

    public Object getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(Object idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public Object getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Object idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public Object getIdPais() {
        return idPais;
    }

    public void setIdPais(Object idPais) {
        this.idPais = idPais;
    }

}
