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
    private int idItems;
    private String referencia;
    private String pedido;
    private String descripcion;
    private String clienteFinal;
    private int idProveedor;
    private String fechaEstimadaArribo;
    private String fechaLlegada;
    private String fechaDocumentos;
    private String fechaDocumentosOK;
    private String fechaAceptacion;
    private String fechaSolicitudAnticipo;
    private String fechaPagoTributo;
    private String fechaLevante;
    private String fechaPoder;
    private String fechaEnvioDocumentos;
    private String fechaAutenticacion;
    private String fechaLiberacionBL;
    private String fechaPlanilla;
    private String fechaSOAT;
    private String fechaTransitoLibre;
    private String fechaDespacho;
    private String fechaEntregaFacturacion;
    private String fechaFacturacion;
    private String numeroFactura;
    private String op;
    private int idUsu;

    public int getIdItems() {
        return idItems;
    }

    public void setIdItems(int idItems) {
        this.idItems = idItems;
    }

    public String getFechaEntregaFacturacion() {
        return fechaEntregaFacturacion;
    }

    public void setFechaEntregaFacturacion(String fechaEntregaFacturacion) {
        this.fechaEntregaFacturacion = fechaEntregaFacturacion;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getPedido() {
        return pedido;
    }

    public void setPedido(String pedido) {
        this.pedido = pedido;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getClienteFinal() {
        return clienteFinal;
    }

    public void setClienteFinal(String clienteFinal) {
        this.clienteFinal = clienteFinal;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getFechaEstimadaArribo() {
        return fechaEstimadaArribo;
    }

    public void setFechaEstimadaArribo(String fechaEstimadaArribo) {
        this.fechaEstimadaArribo = fechaEstimadaArribo;
    }

    public String getFechaLlegada() {
        return fechaLlegada;
    }

    public void setFechaLlegada(String fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    public String getFechaDocumentos() {
        return fechaDocumentos;
    }

    public void setFechaDocumentos(String fechaDocumentos) {
        this.fechaDocumentos = fechaDocumentos;
    }

    public String getFechaDocumentosOK() {
        return fechaDocumentosOK;
    }

    public void setFechaDocumentosOK(String fechaDocumentosOK) {
        this.fechaDocumentosOK = fechaDocumentosOK;
    }

    public String getFechaAceptacion() {
        return fechaAceptacion;
    }

    public void setFechaAceptacion(String fechaAceptacion) {
        this.fechaAceptacion = fechaAceptacion;
    }

    public String getFechaSolicitudAnticipo() {
        return fechaSolicitudAnticipo;
    }

    public void setFechaSolicitudAnticipo(String fechaSolicitudAnticipo) {
        this.fechaSolicitudAnticipo = fechaSolicitudAnticipo;
    }

    public String getFechaPagoTributo() {
        return fechaPagoTributo;
    }

    public void setFechaPagoTributo(String fechaPagoTributo) {
        this.fechaPagoTributo = fechaPagoTributo;
    }

    public String getFechaLevante() {
        return fechaLevante;
    }

    public void setFechaLevante(String fechaLevante) {
        this.fechaLevante = fechaLevante;
    }

    public String getFechaPoder() {
        return fechaPoder;
    }

    public void setFechaPoder(String fechaPoder) {
        this.fechaPoder = fechaPoder;
    }

    public String getFechaEnvioDocumentos() {
        return fechaEnvioDocumentos;
    }

    public void setFechaEnvioDocumentos(String fechaEnvioDocumentos) {
        this.fechaEnvioDocumentos = fechaEnvioDocumentos;
    }

    public String getFechaAutenticacion() {
        return fechaAutenticacion;
    }

    public void setFechaAutenticacion(String fechaAutenticacion) {
        this.fechaAutenticacion = fechaAutenticacion;
    }

    public String getFechaLiberacionBL() {
        return fechaLiberacionBL;
    }

    public void setFechaLiberacionBL(String fechaLiberacionBL) {
        this.fechaLiberacionBL = fechaLiberacionBL;
    }

    public String getFechaPlanilla() {
        return fechaPlanilla;
    }

    public void setFechaPlanilla(String fechaPlanilla) {
        this.fechaPlanilla = fechaPlanilla;
    }

    public String getFechaSOAT() {
        return fechaSOAT;
    }

    public void setFechaSOAT(String fechaSOAT) {
        this.fechaSOAT = fechaSOAT;
    }

    public String getFechaTransitoLibre() {
        return fechaTransitoLibre;
    }

    public void setFechaTransitoLibre(String fechaTransitoLibre) {
        this.fechaTransitoLibre = fechaTransitoLibre;
    }

    public String getFechaDespacho() {
        return fechaDespacho;
    }

    public void setFechaDespacho(String fechaDespacho) {
        this.fechaDespacho = fechaDespacho;
    }

    public String getFechaFacturacion() {
        return fechaFacturacion;
    }

    public void setFechaFacturacion(String fechaFacturacion) {
        this.fechaFacturacion = fechaFacturacion;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

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
