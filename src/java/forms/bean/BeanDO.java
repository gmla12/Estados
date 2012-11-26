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

    private Object idDO;
    private Object DO;
    private Object idCliente;
    private Object pedido;
    private Object referencia;
    private Object descripcion;

    public Object getIdDO() {
        return idDO;
    }

    public void setIdDO(Object idDO) {
        this.idDO = idDO;
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
