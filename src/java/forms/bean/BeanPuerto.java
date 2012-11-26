/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package forms.bean;

import java.io.Serializable;

/**
 *
 * @author mario
 */
public class BeanPuerto implements Serializable{

    private Object idPuerto;
    private Object idPais;
    private Object idDepartamento;
    private Object idMunicipio;
    private Object nombre;
    private Object nombrePais;
    private Object nombreDepartamento;
    private Object nombreMunicipio;

    public Object getNombrePais() {
        return nombrePais;
    }

    public void setNombrePais(Object nombrePais) {
        this.nombrePais = nombrePais;
    }

    public Object getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(Object nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }

    public Object getNombreMunicipio() {
        return nombreMunicipio;
    }

    public void setNombreMunicipio(Object nombreMunicipio) {
        this.nombreMunicipio = nombreMunicipio;
    }

    public Object getIdPais() {
        return idPais;
    }

    public void setIdPais(Object idPais) {
        this.idPais = idPais;
    }

    public Object getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Object idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public Object getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(Object idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public Object getIdPuerto() {
        return idPuerto;
    }

    public void setIdPuerto(Object idPuerto) {
        this.idPuerto= idPuerto;
    }

    public Object getNombre() {
        return nombre;
    }

    public void setNombre(Object nombre) {
        this.nombre = nombre;
    }

}
