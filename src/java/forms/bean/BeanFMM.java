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
public class BeanFMM implements Serializable{

    private Object idFMM;
    private Object FMM;
    private Object cliente;
    private Object pedido;
    private Object lote;

    public Object getFMM() {
        return FMM;
    }

    public void setFMM(Object FMM) {
        this.FMM = FMM;
    }

    public Object getCliente() {
        return cliente;
    }

    public void setCliente(Object cliente) {
        this.cliente = cliente;
    }

    public Object getIdFMM() {
        return idFMM;
    }

    public void setIdFMM(Object idFMM) {
        this.idFMM = idFMM;
    }

    public Object getLote() {
        return lote;
    }

    public void setLote(Object lote) {
        this.lote = lote;
    }

    public Object getPedido() {
        return pedido;
    }

    public void setPedido(Object pedido) {
        this.pedido = pedido;
    }

}
