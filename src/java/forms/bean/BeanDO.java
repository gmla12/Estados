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
public class BeanDO implements Serializable{

    private Object idDOs;
    private Object DO;
    private Object idCliente;
    private Object pedido;
    private Object descripcion;
    private Object idItems;
    private Object idDO;
    private Object referencia;

    public Object getIdItems() {
        return idItems;
    }

    public void setIdItems(Object idItems) {
        this.idItems = idItems;
    }

    public Object getIdDO() {
        return idDO;
    }

    public void setIdDO(Object idDO) {
        this.idDO = idDO;
    }

    public Object getIdDOs() {
        return idDOs;
    }

    public void setIdDOs(Object idDOs) {
        this.idDOs = idDOs;
    }

    public Object getPedido() {
        return pedido;
    }

    public void setPedido(Object pedido) {
        this.pedido = pedido;
    }

    public Object getReferencia() {
        return referencia;
    }

    public void setReferencia(Object referencia) {
        this.referencia = referencia;
    }

    public Object getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(Object descripcion) {
        this.descripcion = descripcion;
    }

    public Object getDO() {
        return DO;
    }

    public void setDO(Object DO) {
        this.DO = DO;
    }

    public Object getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Object idCliente) {
        this.idCliente = idCliente;
    }

}
