/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import forms.DOForm;
import forms.DOOpForm;
import forms.bean.BeanDO;
import java.sql.*;
import java.util.ArrayList;
import util.ConeccionMySql;

/**
 *
 * @author Mario
 */
public class GestionItem extends ConeccionMySql {

    Connection cn = null;
    Statement st = null;

    public ArrayList<Object> IngresaItem(DOForm f, Boolean transac, Connection tCn) {

        int mod;
        ArrayList<Object> resultado = new ArrayList<Object>();
        PreparedStatement psInsertar;

        try {

            if (transac == false) { //si no es una transaccion busca una nueva conexion

                ArrayList<Object> resultad;
                resultad = (ArrayList) getConection();

                if ((Boolean) resultad.get(0) == false) { // si no hubo error al obtener la conexion

                    cn = (Connection) resultad.get(1);

                } else { //si hubo error al obtener la conexion retorna el error para visualizar

                    resultado.add(true);
                    resultado.add(resultad.get(1));
                    return resultado;

                }

            } else { //si es una transaccion asigna la conexion utilizada

                cn = tCn;

            }
            String query = "insert into items_do (dos_id, referencia, pedido, descripcion, ";
            query += "cliente_final, proveedor_id, fecha_estimada_arribo, fecha_llegada, fecha_documentos, ";
            query += "fecha_documentos_ok, fecha_aceptacion, fecha_solicitud_anticipo, fecha_pago_tributo, ";
            query += "fecha_levante, fecha_poder, fecha_envio_documentos, fecha_autenticacion, fecha_liberacion_bl, ";
            query += "fecha_planilla, fecha_soat, fecha_transito_libre, fecha_despacho, fecha_entrega_facturacion, ";
            query += "fecha_facturacion, numero_factura, ";
            query += "sUsuarios_id, fecha_modificacion, id) ";
            query += "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?, now(), null)";
            psInsertar = cn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            psInsertar.setInt(1, f.getIdDOs());
            psInsertar.setString(2, f.getReferencia());
            psInsertar.setString(3, f.getPedido());
            psInsertar.setString(4, f.getClienteFinal());
            psInsertar.setInt(5, f.getIdProveedor());
            psInsertar.setDate(6, f.getFechaEstimadaArribo());
            psInsertar.setDate(7, f.getFechaLlegada());
            psInsertar.setDate(8, f.getFechaSolicitudAnticipo());
            psInsertar.setDate(9, f.getFechaPagoTributo());
            psInsertar.setDate(10, f.getFechaLevante());
            psInsertar.setDate(11, f.getFechaPoder());
            psInsertar.setDate(12, f.getFechaEnvioDocumentos());
            psInsertar.setDate(13, f.getFechaAutenticacion());
            psInsertar.setDate(14, f.getFechaLiberacionBL());
            psInsertar.setDate(15, f.getFechaPlanilla());
            psInsertar.setDate(16, f.getFechaSOAT());
            psInsertar.setDate(17, f.getFechaTransitoLibre());
            psInsertar.setDate(18, f.getFechaDespacho());
            psInsertar.setDate(19, f.getFechaEntregaFacturacion());
            psInsertar.setDate(20, f.getFechaFacturacion());
            psInsertar.setString(21, f.getNumeroFactura());
            psInsertar.setInt(22, f.getIdUsu());
            psInsertar.executeUpdate(); // Se ejecuta la inserción.

            // Se obtiene la clave generada
            int claveGenerada = -1;
            ResultSet rs = psInsertar.getGeneratedKeys();
            while (rs.next()) {
                claveGenerada = rs.getInt(1);
            }
            mod = psInsertar.getUpdateCount();

            if (transac == false) { // si no es una transaccion cierra la conexion

                cn.close();

            }

            resultado.add(false); //si no hubo un error asigna false
            resultado.add(claveGenerada); // clave generada
            resultado.add(mod); // y el numero de registros consultados

        } catch (Exception e) {

            resultado.add(true); //si hubo error asigna true
            resultado.add(e); //y asigna el error para retornar y visualizar

            if (cn != null) {
                cn.rollback();
                cn.close();
            }

        } finally {

            return resultado;

        }

    }
    private ArrayList<Object> GR_DO;

    public ArrayList<Object> MostrarDO(Boolean transac, Connection tCn) {

        ArrayList<Object> resultado = new ArrayList<Object>();
        PreparedStatement psSelectConClave;

        try {

            GR_DO = new ArrayList<Object>();

            if (transac == false) { //si no es una transaccion busca una nueva conexion

                ArrayList<Object> resultad;
                resultad = (ArrayList) getConection();

                if ((Boolean) resultad.get(0) == false) { // si no hubo error al obtener la conexion

                    cn = (Connection) resultad.get(1);

                } else { //si hubo error al obtener la conexion retorna el error para visualizar

                    resultado.add(true);
                    resultado.add(resultad.get(1));
                    return resultado;

                }

            } else { //si es una transaccion asigna la conexion utilizada

                cn = tCn;

            }

            psSelectConClave = cn.prepareStatement("SELECT p.DO, p.entidades_id FROM DOs p");
            ResultSet rs = psSelectConClave.executeQuery();

            BeanDO bu;
            while (rs.next()) {
                bu = new BeanDO();

                bu.setDO(rs.getObject("p.DO"));
                bu.setIdCliente(rs.getObject("p.entidades_id"));

                GR_DO.add(bu);

            }

            if (transac == false) { // si no es una transaccion cierra la conexion

                cn.close();

            }

            resultado.add(false); //si no hubo un error asigna false
            resultado.add(GR_DO); // y registros consultados

        } catch (Exception e) {

            resultado.add(true); //si hubo error asigna true
            resultado.add(e); //y asigna el error para retornar y visualizar

            if (cn != null) {
                cn.rollback();
                cn.close();
            }

        } finally {

            return resultado;

        }

    }

    public ArrayList<Object> MostrarDOOP(DOOpForm f, Boolean transac, Connection tCn) {

        ArrayList<Object> resultado = new ArrayList<Object>();
        PreparedStatement psSelectConClave;

        try {

            GR_DO = new ArrayList<Object>();

            if (transac == false) { //si no es una transaccion busca una nueva conexion

                ArrayList<Object> resultad;
                resultad = (ArrayList) getConection();

                if ((Boolean) resultad.get(0) == false) { // si no hubo error al obtener la conexion

                    cn = (Connection) resultad.get(1);

                } else { //si hubo error al obtener la conexion retorna el error para visualizar

                    resultado.add(true);
                    resultado.add(resultad.get(1));
                    return resultado;

                }

            } else { //si es una transaccion asigna la conexion utilizada

                cn = tCn;

            }

            String query = "SELECT p.id, p.DO, p.entidades_id, IF(r.pedido <> NULL, r.pedido, '') as 'r.pedido', IF(r.referencia <> NULL, r.referencia, '') as 'r.referencia', IF(r.descripcion <> NULL, r.descripcion, '') as 'r.descripcion' ";
            query += "FROM DOs p LEFT JOIN items_DOs r ON p.id = r.DOs_id ";
            String query2 = "";
            if (f.getbDO().isEmpty() != true) {
                query2 = "p.DO = ?";
            }
            if (f.getbIdCliente().isEmpty() != true) {
                if (query2.equals("") != true) {
                    query2 = query2 + " AND ";
                }
                query2 = query2 + "p.entidades_id = ?";
            }

            if (query2.isEmpty() != true) {
                query += " WHERE " + query2;
            }
            psSelectConClave = cn.prepareStatement(query);
            if (f.getbDO().isEmpty() != true) {
                psSelectConClave.setString(1, f.getbDO());
                if (f.getbIdCliente().isEmpty() != true) {
                    psSelectConClave.setString(2, f.getbIdCliente());
                }
            } else {
                if (f.getbIdCliente().isEmpty() != true) {
                    psSelectConClave.setString(1, f.getbIdCliente());
                }
            }
            ResultSet rs = psSelectConClave.executeQuery();

            BeanDO bu;
            while (rs.next()) {
                bu = new BeanDO();

                bu.setIdDOs(rs.getObject("p.id"));
                bu.setDO(rs.getObject("p.DO"));
                bu.setIdCliente(rs.getObject("p.entidades_id"));
                bu.setPedido(rs.getObject("r.pedido"));
                bu.setReferencia(rs.getObject("r.referencia"));
                bu.setDescripcion(rs.getObject("r.descripcion"));

                GR_DO.add(bu);

            }

            if (transac == false) { // si no es una transaccion cierra la conexion

                cn.close();

            }

            resultado.add(false); //si no hubo un error asigna false
            resultado.add(GR_DO); // y registros consultados

        } catch (Exception e) {

            resultado.add(true); //si hubo error asigna true
            resultado.add(e); //y asigna el error para retornar y visualizar

            if (cn != null) {
                cn.rollback();
                cn.close();
            }

        } finally {

            return resultado;

        }

    }

    public ArrayList<Object> ModificaDO(DOForm f, Boolean transac, Connection tCn) {

        int mod = -99;
        ArrayList<Object> resultado = new ArrayList<Object>();
        PreparedStatement psUpdate;

        try {

            if (transac == false) { //si no es una transaccion busca una nueva conexion

                ArrayList<Object> resultad;
                resultad = (ArrayList) getConection();

                if ((Boolean) resultad.get(0) == false) { // si no hubo error al obtener la conexion

                    cn = (Connection) resultad.get(1);

                } else { //si hubo error al obtener la conexion retorna el error para visualizar

                    resultado.add(true);
                    resultado.add(resultad.get(1));
                    return resultado;

                }

            } else { //si es una transaccion asigna la conexion utilizada

                cn = tCn;

            }

            String query = "UPDATE DOs SET  do = ?, lote = ?, bl = ?, observaciones = ?";
            if (f.getIdCliente() != 0) {
                query += ", entidades_id = ?";
            }
            if (f.getIdSucursal() != 0) {
                query += ", sucursal_id = ?";
            }
            if (f.getIdPuerto() != 0) {
                query += ", puerto_id = ?";
            }
            if (f.getIdTipoMercancia() != 0) {
                query += ", tipo_mercancia_id = ?";
            }
            query += ", sUsuarios_id = ?, fecha_modificacion = now() WHERE id = ?";
            psUpdate = cn.prepareStatement(query);
            psUpdate.setString(1, f.getDO());
            psUpdate.setString(2, f.getLote());
            psUpdate.setString(3, f.getBL());
            psUpdate.setString(4, f.getObservaciones());
            int i = 5;
            if (f.getIdCliente() != 0) {
                psUpdate.setInt(i, f.getIdCliente());
                i++;
            }
            if (f.getIdSucursal() != 0) {
                psUpdate.setInt(i, f.getIdSucursal());
                i++;
            }
            if (f.getIdPuerto() != 0) {
                psUpdate.setInt(i, f.getIdPuerto());
                i++;
            }
            if (f.getIdTipoMercancia() != 0) {
                psUpdate.setInt(i, f.getIdTipoMercancia());
                i++;
            }
            psUpdate.setInt(i, f.getIdUsu());
            i++;
            psUpdate.setInt(i, f.getIdDOs());
            psUpdate.executeUpdate();

            mod = psUpdate.getUpdateCount();

            if (transac == false) { // si no es una transaccion cierra la conexion

                cn.close();

            }

            resultado.add(false); //si no hubo un error asigna false
            resultado.add(mod); // y el numero de registros consultados

        } catch (Exception e) {

            resultado.add(true); //si hubo error asigna true
            resultado.add(e); //y asigna el error para retornar y visualizar

            if (cn != null) {
                cn.rollback();
                cn.close();
            }

        } finally {

            return resultado;

        }

    }

    public ArrayList<Object> EliminaDO(DOForm f, Boolean transac, Connection tCn) {

        int mod;
        ArrayList<Object> resultado = new ArrayList<Object>();
        PreparedStatement psDelete;

        try {

            if (transac == false) { //si no es una transaccion busca una nueva conexion

                ArrayList<Object> resultad;
                resultad = (ArrayList) getConection();

                if ((Boolean) resultad.get(0) == false) { // si no hubo error al obtener la conexion

                    cn = (Connection) resultad.get(1);

                } else { //si hubo error al obtener la conexion retorna el error para visualizar

                    resultado.add(true);
                    resultado.add(resultad.get(1));
                    return resultado;

                }

            } else { //si es una transaccion asigna la conexion utilizada

                cn = tCn;

            }

            psDelete = cn.prepareStatement("DELETE FROM DOs WHERE id = ?");
            psDelete.setInt(1, f.getIdDOs());
            psDelete.executeUpdate();

            mod = psDelete.getUpdateCount();

            if (transac == false) { // si no es una transaccion cierra la conexion

                cn.close();

            }

            resultado.add(false); //si no hubo un error asigna false
            resultado.add(mod); // y el numero de registros consultados

        } catch (Exception e) {

            resultado.add(true); //si hubo error asigna true
            resultado.add(e); //y asigna el error para retornar y visualizar

            if (cn != null) {
                cn.rollback();
                cn.close();
            }

        } finally {

            return resultado;

        }

    }

    public ArrayList<Object> MostrarDOFormulario(String DO, Boolean transac, Connection tCn) {

        ArrayList<Object> resultado = new ArrayList<Object>();
        PreparedStatement psSelectConClave;

        try {

            if (transac == false) { //si no es una transaccion busca una nueva conexion

                ArrayList<Object> resultad;
                resultad = (ArrayList) getConection();

                if ((Boolean) resultad.get(0) == false) { // si no hubo error al obtener la conexion

                    cn = (Connection) resultad.get(1);

                } else { //si hubo error al obtener la conexion retorna el error para visualizar

                    resultado.add(true);
                    resultado.add(resultad.get(1));
                    return resultado;

                }

            } else { //si es una transaccion asigna la conexion utilizada

                cn = tCn;

            }

            psSelectConClave = cn.prepareStatement("SELECT p.id, p.DO, p.entidades_id, p.sucursal_id, p.lote, p.BL, p.puerto_id, p.tipo_mercancia_id, p.observaciones, p.susuarios_id, IF(e.primer_nombre <> NULL AND e.primer_apellido <> NULL, e.razon_Social, CONCAT(IF(e.primer_nombre <> NULL,'',CONCAT(e.primer_nombre,' ')), IF(e.segundo_nombre <> NULL,'',CONCAT(e.segundo_nombre,' ')), IF(e.primer_apellido <> NULL,'',CONCAT(e.primer_apellido,' ')), IF(e.segundo_apellido <> NULL,'',CONCAT(e.segundo_apellido,' ')))) as nombre_usu, p.fecha_modificacion FROM DOs p INNER JOIN susuarios r ON p.susuarios_id = r.id INNER JOIN entidades e ON r.id_tipo_documento = e.id_tipo_documento AND r.identificacion = e.identificacion WHERE p.id = ?");
            psSelectConClave.setString(1, DO);
            ResultSet rs = psSelectConClave.executeQuery();

            while (rs.next()) {

                setIdDOs(rs.getObject("p.id"));
                setDO(rs.getObject("p.DO"));
                setIdCliente(rs.getObject("p.entidades_id"));
                setIdSucursal(rs.getObject("p.sucursal_id"));
                setLote(rs.getObject("p.lote"));
                setBL(rs.getObject("p.BL"));
                setIdPuerto(rs.getObject("p.puerto_id"));
                setIdTipoMercancia(rs.getObject("p.tipo_mercancia_id"));
                setObservaciones(rs.getObject("p.observaciones"));
                setNombreUsu(rs.getObject("nombre_usu"));
                setFechaModificacion(rs.getObject("p.fecha_modificacion"));

            }

            if (transac == false) { // si no es una transaccion cierra la conexion

                cn.close();

            }

            resultado.add(false); //si no hubo un error asigna false

        } catch (Exception e) {

            resultado.add(true); //si hubo error asigna true
            resultado.add(e); //y asigna el error para retornar y visualizar

            if (cn != null) {
                cn.rollback();
                cn.close();
            }

        } finally {

            return resultado;

        }

    }

    public ArrayList<Object> commint(Connection tCn) {

        ArrayList<Object> resultado = new ArrayList<Object>();

        try {

            tCn.commit();
            resultado.add(false); //si no hubo un error asigna false

        } catch (SQLException e) {

            resultado.add(true); //si hubo error asigna true
            resultado.add(e); //y asigna el error para retornar y visualizar

            if (cn != null) {
                cn.rollback();
                cn.close();
            }

        } finally {

            return resultado;

        }

    }

    public ArrayList<Object> autoCommint(boolean valor, Connection tCn) {

        ArrayList<Object> resultado = new ArrayList<Object>();

        try {

            tCn.setAutoCommit(valor);
            resultado.add(false); //si no hubo un error asigna false

        } catch (Exception e) {

            resultado.add(true); //si hubo error asigna true
            resultado.add(e); //y asigna el error para retornar y visualizar

        } finally {

            return resultado;

        }

    }

    public ArrayList<Object> rollback(Connection tCn) {

        ArrayList<Object> resultado = new ArrayList<Object>();

        try {

            tCn.rollback();
            resultado.add(false); //si no hubo un error asigna false

        } catch (Exception e) {

            resultado.add(true); //si hubo error asigna true
            resultado.add(e); //y asigna el error para retornar y visualizar

        } finally {

            return resultado;

        }

    }

    public ArrayList<Object> ObtenerConexion() {

        ArrayList<Object> resultado = new ArrayList<Object>();

        try {

            ArrayList<Object> resultad;
            resultad = (ArrayList) getConection();

            if ((Boolean) resultad.get(0) == false) { // si no hubo error al obtener la conexion

                cn = (Connection) resultad.get(1);
                resultado.add(false); //si no hubo un error asigna false
                resultado.add(cn); // y se envia la nueva conexion

            } else { //si hubo error al obtener la conexion retorna el error para visualizar

                resultado.add(true);
                resultado.add(resultad.get(1));
                return resultado;

            }

        } catch (Exception e) {

            resultado.add(true); //si hubo error asigna true
            resultado.add(e); //y asigna el error para retornar y visualizar

        } finally {

            return resultado;

        }

    }
//    private ArrayList<Object> GR_USUARIOS2;
//
//    public ArrayList<Object> MostrarUsuarios2(String aux, String aux2) {
//        try {
//            GR_USUARIOS2 = new ArrayList<Object>();
//            cn = getConection();
//
//            String query = "SELECT p.idUsuarios, p.tipoDocumentacion, p.documento, p.nombre1, p.nombre2, p.apellido1, ";
//            query += "p.apellido2, p.foto ";
//            query += "FROM usuarios p, cargo c ";
//            query += "WHERE p.id_cargo = c.id_cargo ";
//            if (aux.equals("nombre")) {
//                query += " AND p.nombres = '" + aux2 + "'";
//            } else if (aux.equals("cargo")) {
//                query += " AND p.id_cargo='" + aux2 + "'";
//            }
//
//            System.out.println("***********************************************");
//            System.out.println("*****       Cargando grilla  GR_USUARIOS  *****");
//            System.out.println("***********************************************");
//
//            System.out.println(query);
//            st = cn.createStatement();
//            ResultSet rs = st.executeQuery(query);
//
//            BeanUsuarios bu;
//            while (rs.next()) {
//                bu = new BeanUsuarios();
//
//                bu.setIdUsuarios(rs.getObject("p.idUsuarios"));
//                bu.setTipoDocumentacion(rs.getObject("p.tipoDocumentacion"));
//                bu.setDocumento(rs.getObject("p.documento"));
//                bu.setNombre1(rs.getObject("p.nombre1"));
//                bu.setNombre2(rs.getObject("p.nombre2"));
//                bu.setApellido1(rs.getObject("p.apellido1"));
//                bu.setApellido2(rs.getObject("p.apellido2"));
//                bu.setFoto(rs.getObject("p.foto"));
//
//                GR_USUARIOS2.add(bu);
//
//            }
//
//            st.close();
//            cn.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//        }
//        return GR_USUARIOS2;
//    }
//}
    private Object idDOs;
    private Object DO;
    private Object idCliente;
    private Object idSucursal;
    private Object Lote;
    private Object BL;
    private Object idPuerto;
    private Object idTipoMercancia;
    private Object observaciones;
    private Object nombreUsu;
    private Object fechaModificacion;

    public Object getIdTipoMercancia() {
        return idTipoMercancia;
    }

    public void setIdTipoMercancia(Object idTipoMercancia) {
        this.idTipoMercancia = idTipoMercancia;
    }

    public Object getNombreUsu() {
        return nombreUsu;
    }

    public void setNombreUsu(Object nombreUsu) {
        this.nombreUsu = nombreUsu;
    }

    public Object getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Object fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Object getIdDOs() {
        return idDOs;
    }

    public void setIdDOs(Object idDOs) {
        this.idDOs = idDOs;
    }

    public Object getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(Object observaciones) {
        this.observaciones = observaciones;
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

    public Object getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Object idSucursal) {
        this.idSucursal = idSucursal;
    }

    public Object getLote() {
        return Lote;
    }

    public void setLote(Object Lote) {
        this.Lote = Lote;
    }

    public Object getBL() {
        return BL;
    }

    public void setBL(Object BL) {
        this.BL = BL;
    }

    public Object getIdPuerto() {
        return idPuerto;
    }

    public void setIdPuerto(Object idPuerto) {
        this.idPuerto = idPuerto;
    }
}
