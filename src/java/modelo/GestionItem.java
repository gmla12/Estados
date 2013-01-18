/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import forms.DOForm;
import forms.DOOpForm;
import forms.bean.BeanDO;
import forms.bean.BeanItem;
import java.sql.*;
import java.text.SimpleDateFormat;
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
        //Ingresa el Item con todos los campos.

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
            psInsertar.setString(4, f.getDescripcion());
            psInsertar.setString(5, f.getClienteFinal());
            psInsertar.setInt(6, f.getIdProveedor());
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date dtf = sdf.parse(f.getFechaEstimadaArribo());
            java.sql.Timestamp momentoTimestamp = new java.sql.Timestamp(dtf.getTime());
            psInsertar.setTimestamp(7, momentoTimestamp);
            dtf = sdf.parse(f.getFechaLlegada());
            momentoTimestamp = new java.sql.Timestamp(dtf.getTime());
            psInsertar.setTimestamp(8, momentoTimestamp);
            dtf = sdf.parse(f.getFechaDocumentos());
            momentoTimestamp = new java.sql.Timestamp(dtf.getTime());
            psInsertar.setTimestamp(9, momentoTimestamp);
            dtf = sdf.parse(f.getFechaDocumentosOK());
            momentoTimestamp = new java.sql.Timestamp(dtf.getTime());
            psInsertar.setTimestamp(10, momentoTimestamp);
            dtf = sdf.parse(f.getFechaAceptacion());
            momentoTimestamp = new java.sql.Timestamp(dtf.getTime());
            psInsertar.setTimestamp(11, momentoTimestamp);
            dtf = sdf.parse(f.getFechaSolicitudAnticipo());
            momentoTimestamp = new java.sql.Timestamp(dtf.getTime());
            psInsertar.setTimestamp(12, momentoTimestamp);
            dtf = sdf.parse(f.getFechaPagoTributo());
            momentoTimestamp = new java.sql.Timestamp(dtf.getTime());
            psInsertar.setTimestamp(13, momentoTimestamp);
            dtf = sdf.parse(f.getFechaLevante());
            momentoTimestamp = new java.sql.Timestamp(dtf.getTime());
            psInsertar.setTimestamp(14, momentoTimestamp);
            dtf = sdf.parse(f.getFechaPoder());
            momentoTimestamp = new java.sql.Timestamp(dtf.getTime());
            psInsertar.setTimestamp(15, momentoTimestamp);
            dtf = sdf.parse(f.getFechaEnvioDocumentos());
            momentoTimestamp = new java.sql.Timestamp(dtf.getTime());
            psInsertar.setTimestamp(16, momentoTimestamp);
            dtf = sdf.parse(f.getFechaAutenticacion());
            momentoTimestamp = new java.sql.Timestamp(dtf.getTime());
            psInsertar.setTimestamp(17, momentoTimestamp);
            dtf = sdf.parse(f.getFechaLiberacionBL());
            momentoTimestamp = new java.sql.Timestamp(dtf.getTime());
            psInsertar.setTimestamp(18, momentoTimestamp);
            dtf = sdf.parse(f.getFechaPlanilla());
            momentoTimestamp = new java.sql.Timestamp(dtf.getTime());
            psInsertar.setTimestamp(19, momentoTimestamp);
            dtf = sdf.parse(f.getFechaSOAT());
            momentoTimestamp = new java.sql.Timestamp(dtf.getTime());
            psInsertar.setTimestamp(20, momentoTimestamp);
            dtf = sdf.parse(f.getFechaTransitoLibre());
            momentoTimestamp = new java.sql.Timestamp(dtf.getTime());
            psInsertar.setTimestamp(21, momentoTimestamp);
            dtf = sdf.parse(f.getFechaDespacho());
            momentoTimestamp = new java.sql.Timestamp(dtf.getTime());
            psInsertar.setTimestamp(22, momentoTimestamp);
            dtf = sdf.parse(f.getFechaEntregaFacturacion());
            momentoTimestamp = new java.sql.Timestamp(dtf.getTime());
            psInsertar.setTimestamp(23, momentoTimestamp);
            dtf = sdf.parse(f.getFechaFacturacion());
            momentoTimestamp = new java.sql.Timestamp(dtf.getTime());
            psInsertar.setTimestamp(24, momentoTimestamp);
            psInsertar.setString(25, f.getNumeroFactura());
            psInsertar.setInt(26, f.getIdUsu());
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
    private ArrayList<Object> GR_ITEMs;

    public ArrayList<Object> MostrarItem(Boolean transac, Connection tCn) {

         ArrayList<Object> resultado = new ArrayList<Object>();
        PreparedStatement psSelectConClave;

        try {

            GR_ITEMs = new ArrayList<Object>();

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
            
            String query = "id, dos_id, referencia, pedido, descripcion, ";
            query += "cliente_final, proveedor_id, fecha_estimada_arribo, fecha_llegada, fecha_documentos, ";
            query += "fecha_documentos_ok, fecha_aceptacion, fecha_solicitud_anticipo, fecha_pago_tributo, ";
            query += "fecha_levante, fecha_poder, fecha_envio_documentos, fecha_autenticacion, fecha_liberacion_bl, ";
            query += "fecha_planilla, fecha_soat, fecha_transito_libre, fecha_despacho, fecha_entrega_facturacion, ";
            query += "fecha_facturacion, numero_factura FROM items_DOs";
            psSelectConClave = cn.prepareStatement(query);
            ResultSet rs = psSelectConClave.executeQuery();

            BeanItem bu;
            while (rs.next()) {
                bu = new BeanItem();

                bu.setIdItems(rs.getObject("id"));
                bu.setIdDOs(rs.getObject("dos_id"));
                bu.setReferencia(rs.getObject("referencia"));
                bu.setPedido(rs.getObject("pedido"));
                bu.setDescripcion(rs.getObject("descripcion"));
                bu.setClienteFinal(rs.getObject("cliente_final"));
                bu.setIdProveedor(rs.getObject("proveedor_id"));
                bu.setFechaEstimadaArribo(rs.getObject("fecha_estimada_arribo"));
                bu.setFechaLlegada(rs.getObject("fecha_llegada"));
                bu.setFechaDocumentos(rs.getObject("fecha_documentos"));
                bu.setFechaDocumentosOK(rs.getObject("fecha_documentos_ok"));
                bu.setFechaAceptacion(rs.getObject("fecha_aceptacion"));
                bu.setFechaSolicitudAnticipo(rs.getObject("fecha_solicitud_anticipo"));
                bu.setFechaPagoTributo(rs.getObject("fecha_pago_tributo"));
                bu.setFechaLevante(rs.getObject("fecha_levante"));
                bu.setFechaPoder(rs.getObject("fecha_poder"));
                bu.setFechaEnvioDocumentos(rs.getObject("fecha_envio_documentos"));
                bu.setFechaAutenticacion(rs.getObject("fecha_autenticacion"));
                bu.setFechaLiberacionBL(rs.getObject("fecha_liberacion_bl"));
                bu.setFechaPlanilla(rs.getObject("fecha_planilla"));
                bu.setFechaSOAT(rs.getObject("fecha_soat"));
                bu.setFechaTransitoLibre(rs.getObject("fecha_transito_libre"));
                bu.setFechaDespacho(rs.getObject("fecha_despacho"));
                bu.setFechaEntregaFacturacion(rs.getObject("fecha_entrega_facturacion"));
                bu.setFechaFacturacion(rs.getObject("fecha_facturacion"));
                bu.setNumeroFactura(rs.getObject("numero_factura"));

                GR_ITEMs.add(bu);

            }

            if (transac == false) { // si no es una transaccion cierra la conexion

                cn.close();

            }

            resultado.add(false); //si no hubo un error asigna false
            resultado.add(GR_ITEMs); // y registros consultados

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

//    public ArrayList<Object> MostrarDOOP(DOOpForm f, Boolean transac, Connection tCn) {
//
//        ArrayList<Object> resultado = new ArrayList<Object>();
//        PreparedStatement psSelectConClave;
//
//        try {
//
//            GR_DO = new ArrayList<Object>();
//
//            if (transac == false) { //si no es una transaccion busca una nueva conexion
//
//                ArrayList<Object> resultad;
//                resultad = (ArrayList) getConection();
//
//                if ((Boolean) resultad.get(0) == false) { // si no hubo error al obtener la conexion
//
//                    cn = (Connection) resultad.get(1);
//
//                } else { //si hubo error al obtener la conexion retorna el error para visualizar
//
//                    resultado.add(true);
//                    resultado.add(resultad.get(1));
//                    return resultado;
//
//                }
//
//            } else { //si es una transaccion asigna la conexion utilizada
//
//                cn = tCn;
//
//            }
//
//            String query = "SELECT p.id, p.DO, p.entidades_id, IF(r.pedido <> NULL, r.pedido, '') as 'r.pedido', IF(r.referencia <> NULL, r.referencia, '') as 'r.referencia', IF(r.descripcion <> NULL, r.descripcion, '') as 'r.descripcion' ";
//            query += "FROM DOs p LEFT JOIN items_DOs r ON p.id = r.DOs_id ";
//            String query2 = "";
//            if (f.getbDO().isEmpty() != true) {
//                query2 = "p.DO = ?";
//            }
//            if (f.getbIdCliente().isEmpty() != true) {
//                if (query2.equals("") != true) {
//                    query2 = query2 + " AND ";
//                }
//                query2 = query2 + "p.entidades_id = ?";
//            }
//
//            if (query2.isEmpty() != true) {
//                query += " WHERE " + query2;
//            }
//            psSelectConClave = cn.prepareStatement(query);
//            if (f.getbDO().isEmpty() != true) {
//                psSelectConClave.setString(1, f.getbDO());
//                if (f.getbIdCliente().isEmpty() != true) {
//                    psSelectConClave.setString(2, f.getbIdCliente());
//                }
//            } else {
//                if (f.getbIdCliente().isEmpty() != true) {
//                    psSelectConClave.setString(1, f.getbIdCliente());
//                }
//            }
//            ResultSet rs = psSelectConClave.executeQuery();
//
//            BeanDO bu;
//            while (rs.next()) {
//                bu = new BeanDO();
//
//                bu.setIdDOs(rs.getObject("p.id"));
//                bu.setDO(rs.getObject("p.DO"));
//                bu.setIdCliente(rs.getObject("p.entidades_id"));
//                bu.setPedido(rs.getObject("r.pedido"));
//                bu.setReferencia(rs.getObject("r.referencia"));
//                bu.setDescripcion(rs.getObject("r.descripcion"));
//
//                GR_DO.add(bu);
//
//            }
//
//            if (transac == false) { // si no es una transaccion cierra la conexion
//
//                cn.close();
//
//            }
//
//            resultado.add(false); //si no hubo un error asigna false
//            resultado.add(GR_DO); // y registros consultados
//
//        } catch (Exception e) {
//
//            resultado.add(true); //si hubo error asigna true
//            resultado.add(e); //y asigna el error para retornar y visualizar
//
//            if (cn != null) {
//                cn.rollback();
//                cn.close();
//            }
//
//        } finally {
//
//            return resultado;
//
//        }
//
//    }
    public ArrayList<Object> ModificaItem(DOForm f, Boolean transac, Connection tCn) {

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
            String query = "UPDATE items_dos SET  dos_id = ?, referencia = ?, pedido = ?, descripcion = ?";
            query += "cliente_final = ?, proveedor_id = ?, fecha_estimada_arribo = ?, fecha_llegada = ?, fecha_documentos = ?, ";
            query += "fecha_documentos_ok = ?, fecha_aceptacion = ?, fecha_solicitud_anticipo = ?, fecha_pago_tributo = ?, ";
            query += "fecha_levante = ?, fecha_poder = ?, fecha_envio_documentos = ?, fecha_autenticacion = ?, fecha_liberacion_bl = ?, ";
            query += "fecha_planilla = ?, fecha_soat = ?, fecha_transito_libre = ?, fecha_despacho = ?, fecha_entrega_facturacion = ?, ";
            query += "fecha_facturacion = ?, numero_factura = ?, ";
            query += "sUsuarios_id = ?, fecha_modificacion = now() WHERE id = ?";
            psUpdate = cn.prepareStatement(query);
            psUpdate.setInt(1, f.getIdDOs());
            psUpdate.setString(2, f.getReferencia());
            psUpdate.setString(3, f.getPedido());
            psUpdate.setString(4, f.getDescripcion());
            psUpdate.setString(5, f.getClienteFinal());
            psUpdate.setInt(6, f.getIdProveedor());
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date dtf = sdf.parse(f.getFechaEstimadaArribo());
            java.sql.Timestamp momentoTimestamp = new java.sql.Timestamp(dtf.getTime());
            psUpdate.setTimestamp(7, momentoTimestamp);
            dtf = sdf.parse(f.getFechaLlegada());
            momentoTimestamp = new java.sql.Timestamp(dtf.getTime());
            psUpdate.setTimestamp(8, momentoTimestamp);
            dtf = sdf.parse(f.getFechaDocumentos());
            momentoTimestamp = new java.sql.Timestamp(dtf.getTime());
            psUpdate.setTimestamp(9, momentoTimestamp);
            dtf = sdf.parse(f.getFechaDocumentosOK());
            momentoTimestamp = new java.sql.Timestamp(dtf.getTime());
            psUpdate.setTimestamp(10, momentoTimestamp);
            dtf = sdf.parse(f.getFechaAceptacion());
            momentoTimestamp = new java.sql.Timestamp(dtf.getTime());
            psUpdate.setTimestamp(11, momentoTimestamp);
            dtf = sdf.parse(f.getFechaSolicitudAnticipo());
            momentoTimestamp = new java.sql.Timestamp(dtf.getTime());
            psUpdate.setTimestamp(12, momentoTimestamp);
            dtf = sdf.parse(f.getFechaPagoTributo());
            momentoTimestamp = new java.sql.Timestamp(dtf.getTime());
            psUpdate.setTimestamp(13, momentoTimestamp);
            dtf = sdf.parse(f.getFechaLevante());
            momentoTimestamp = new java.sql.Timestamp(dtf.getTime());
            psUpdate.setTimestamp(14, momentoTimestamp);
            dtf = sdf.parse(f.getFechaPoder());
            momentoTimestamp = new java.sql.Timestamp(dtf.getTime());
            psUpdate.setTimestamp(15, momentoTimestamp);
            dtf = sdf.parse(f.getFechaEnvioDocumentos());
            momentoTimestamp = new java.sql.Timestamp(dtf.getTime());
            psUpdate.setTimestamp(16, momentoTimestamp);
            dtf = sdf.parse(f.getFechaAutenticacion());
            momentoTimestamp = new java.sql.Timestamp(dtf.getTime());
            psUpdate.setTimestamp(17, momentoTimestamp);
            dtf = sdf.parse(f.getFechaLiberacionBL());
            momentoTimestamp = new java.sql.Timestamp(dtf.getTime());
            psUpdate.setTimestamp(18, momentoTimestamp);
            dtf = sdf.parse(f.getFechaPlanilla());
            momentoTimestamp = new java.sql.Timestamp(dtf.getTime());
            psUpdate.setTimestamp(19, momentoTimestamp);
            dtf = sdf.parse(f.getFechaSOAT());
            momentoTimestamp = new java.sql.Timestamp(dtf.getTime());
            psUpdate.setTimestamp(20, momentoTimestamp);
            dtf = sdf.parse(f.getFechaTransitoLibre());
            momentoTimestamp = new java.sql.Timestamp(dtf.getTime());
            psUpdate.setTimestamp(21, momentoTimestamp);
            dtf = sdf.parse(f.getFechaDespacho());
            momentoTimestamp = new java.sql.Timestamp(dtf.getTime());
            psUpdate.setTimestamp(22, momentoTimestamp);
            dtf = sdf.parse(f.getFechaEntregaFacturacion());
            momentoTimestamp = new java.sql.Timestamp(dtf.getTime());
            psUpdate.setTimestamp(23, momentoTimestamp);
            dtf = sdf.parse(f.getFechaFacturacion());
            momentoTimestamp = new java.sql.Timestamp(dtf.getTime());
            psUpdate.setTimestamp(24, momentoTimestamp);
            psUpdate.setString(25, f.getNumeroFactura());
            psUpdate.setInt(26, f.getIdUsu());
            psUpdate.setInt(27, f.getIdItems());
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

    public ArrayList<Object> EliminaItem(DOForm f, Boolean transac, Connection tCn) {

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

            psDelete = cn.prepareStatement("DELETE FROM items_dos WHERE id = ?");
            psDelete.setInt(1, f.getIdItems());
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

    public ArrayList<Object> MostrarItemFormulario(String idItem, String idDO, Boolean transac, Connection tCn) {

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
            String query = "SELECT p.id, p.dos_id, p.referencia, p.pedido, p.descripcion, ";
            query += "p.cliente_final, p.proveedor_id, p.fecha_estimada_arribo, p.fecha_llegada, p.fecha_documentos, ";
            query += "p.fecha_documentos_ok, p.fecha_aceptacion, p.fecha_solicitud_anticipo, p.fecha_pago_tributo, ";
            query += "p.fecha_levante, p.fecha_poder, p.fecha_envio_documentos, p.fecha_autenticacion, p.fecha_liberacion_bl, ";
            query += "p.fecha_planilla, p.fecha_soat, p.fecha_transito_libre, p.fecha_despacho, p.fecha_entrega_facturacion, ";
            query += "p.fecha_facturacion, p.numero_factura, ";
            query += "p.sUsuarios_id, p.susuarios_id, IF(e.primer_nombre <> NULL AND e.primer_apellido <> NULL, e.razon_Social, CONCAT(IF(e.primer_nombre <> NULL,'',CONCAT(e.primer_nombre,' ')), IF(e.segundo_nombre <> NULL,'',CONCAT(e.segundo_nombre,' ')), IF(e.primer_apellido <> NULL,'',CONCAT(e.primer_apellido,' ')), IF(e.segundo_apellido <> NULL,'',CONCAT(e.segundo_apellido,' ')))) as nombre_usu, p.fecha_modificacion FROM items_dos p INNER JOIN susuarios r ON p.susuarios_id = r.id INNER JOIN entidades e ON r.id_tipo_documento = e.id_tipo_documento AND r.identificacion = e.identificacion WHERE p.id = ? AND p.dos_id = ?";
            psSelectConClave = cn.prepareStatement(query);
            psSelectConClave.setString(1, idItem);
            psSelectConClave.setString(2, idDO);
            ResultSet rs = psSelectConClave.executeQuery();

            while (rs.next()) {

                setIdItems(rs.getObject("p.id"));
                setIdDOs(rs.getObject("p.dos_id"));
                setReferencia(rs.getObject("p.referencia"));
                setPedido(rs.getObject("p.pedido"));
                setDescripcion(rs.getObject("p.descripcion"));
                setClienteFinal(rs.getObject("p.cliente_final"));
                setIdProveedor(rs.getObject("p.proveedor_id"));
                setFechaEstimadaArribo(rs.getObject("p.fecha_estimada_arribo"));
                setFechaLlegada(rs.getObject("p.fecha_llegada"));
                setFechaDocumentos(rs.getObject("p.fecha_documentos"));
                setFechaDocumentosOK(rs.getObject("p.fecha_documentos_ok"));
                setFechaAceptacion(rs.getObject("p.fecha_aceptacion"));
                setFechaSolicitudAnticipo(rs.getObject("p.fecha_solicitud_anticipo"));
                setFechaPagoTributo(rs.getObject("p.fecha_pago_tributo"));
                setFechaLevante(rs.getObject("p.fecha_levante"));
                setFechaPoder(rs.getObject("p.fecha_poder"));
                setFechaEnvioDocumentos(rs.getObject("p.fecha_envio_documentos"));
                setFechaAutenticacion(rs.getObject("p.fecha_autenticacion"));
                setFechaLiberacionBL(rs.getObject("p.fecha_liberacion_bl"));
                setFechaPlanilla(rs.getObject("p.fecha_planilla"));
                setFechaSOAT(rs.getObject("p.fecha_soat"));
                setFechaTransitoLibre(rs.getObject("p.fecha_transito_libre"));
                setFechaDespacho(rs.getObject("p.fecha_despacho"));
                setFechaEntregaFacturacion(rs.getObject("p.fecha_entrega_facturacion"));
                setFechaFacturacion(rs.getObject("p.fecha_facturacion"));
                setNumeroFactura(rs.getObject("p.numero_factura"));
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
    private Object idItems;
    private Object idDOs;
    private Object referencia;
    private Object pedido;
    private Object descripcion;
    private Object clienteFinal;
    private Object idProveedor;
    private Object fechaEstimadaArribo;
    private Object fechaLlegada;
    private Object fechaDocumentos;
    private Object fechaDocumentosOK;
    private Object fechaAceptacion;
    private Object fechaSolicitudAnticipo;
    private Object fechaPagoTributo;
    private Object fechaLevante;
    private Object fechaPoder;
    private Object fechaEnvioDocumentos;
    private Object fechaAutenticacion;
    private Object fechaLiberacionBL;
    private Object fechaPlanilla;
    private Object fechaSOAT;
    private Object fechaTransitoLibre;
    private Object fechaDespacho;
    private Object fechaEntregaFacturacion;
    private Object fechaFacturacion;
    private Object numeroFactura;
    private Object nombreUsu;
    private Object fechaModificacion;

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

    public Object getIdItems() {
        return idItems;
    }

    public void setIdItems(Object idItems) {
        this.idItems = idItems;
    }

    public Object getIdDOs() {
        return idDOs;
    }

    public void setIdDOs(Object idDOs) {
        this.idDOs = idDOs;
    }

    public Object getReferencia() {
        return referencia;
    }

    public void setReferencia(Object referencia) {
        this.referencia = referencia;
    }

    public Object getPedido() {
        return pedido;
    }

    public void setPedido(Object pedido) {
        this.pedido = pedido;
    }

    public Object getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(Object descripcion) {
        this.descripcion = descripcion;
    }

    public Object getClienteFinal() {
        return clienteFinal;
    }

    public void setClienteFinal(Object clienteFinal) {
        this.clienteFinal = clienteFinal;
    }

    public Object getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Object idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Object getFechaEstimadaArribo() {
        return fechaEstimadaArribo;
    }

    public void setFechaEstimadaArribo(Object fechaEstimadaArribo) {
        this.fechaEstimadaArribo = fechaEstimadaArribo;
    }

    public Object getFechaLlegada() {
        return fechaLlegada;
    }

    public void setFechaLlegada(Object fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    public Object getFechaDocumentos() {
        return fechaDocumentos;
    }

    public void setFechaDocumentos(Object fechaDocumentos) {
        this.fechaDocumentos = fechaDocumentos;
    }

    public Object getFechaDocumentosOK() {
        return fechaDocumentosOK;
    }

    public void setFechaDocumentosOK(Object fechaDocumentosOK) {
        this.fechaDocumentosOK = fechaDocumentosOK;
    }

    public Object getFechaAceptacion() {
        return fechaAceptacion;
    }

    public void setFechaAceptacion(Object fechaAceptacion) {
        this.fechaAceptacion = fechaAceptacion;
    }

    public Object getFechaSolicitudAnticipo() {
        return fechaSolicitudAnticipo;
    }

    public void setFechaSolicitudAnticipo(Object fechaSolicitudAnticipo) {
        this.fechaSolicitudAnticipo = fechaSolicitudAnticipo;
    }

    public Object getFechaPagoTributo() {
        return fechaPagoTributo;
    }

    public void setFechaPagoTributo(Object fechaPagoTributo) {
        this.fechaPagoTributo = fechaPagoTributo;
    }

    public Object getFechaLevante() {
        return fechaLevante;
    }

    public void setFechaLevante(Object fechaLevante) {
        this.fechaLevante = fechaLevante;
    }

    public Object getFechaPoder() {
        return fechaPoder;
    }

    public void setFechaPoder(Object fechaPoder) {
        this.fechaPoder = fechaPoder;
    }

    public Object getFechaEnvioDocumentos() {
        return fechaEnvioDocumentos;
    }

    public void setFechaEnvioDocumentos(Object fechaEnvioDocumentos) {
        this.fechaEnvioDocumentos = fechaEnvioDocumentos;
    }

    public Object getFechaAutenticacion() {
        return fechaAutenticacion;
    }

    public void setFechaAutenticacion(Object fechaAutenticacion) {
        this.fechaAutenticacion = fechaAutenticacion;
    }

    public Object getFechaLiberacionBL() {
        return fechaLiberacionBL;
    }

    public void setFechaLiberacionBL(Object fechaLiberacionBL) {
        this.fechaLiberacionBL = fechaLiberacionBL;
    }

    public Object getFechaPlanilla() {
        return fechaPlanilla;
    }

    public void setFechaPlanilla(Object fechaPlanilla) {
        this.fechaPlanilla = fechaPlanilla;
    }

    public Object getFechaSOAT() {
        return fechaSOAT;
    }

    public void setFechaSOAT(Object fechaSOAT) {
        this.fechaSOAT = fechaSOAT;
    }

    public Object getFechaTransitoLibre() {
        return fechaTransitoLibre;
    }

    public void setFechaTransitoLibre(Object fechaTransitoLibre) {
        this.fechaTransitoLibre = fechaTransitoLibre;
    }

    public Object getFechaDespacho() {
        return fechaDespacho;
    }

    public void setFechaDespacho(Object fechaDespacho) {
        this.fechaDespacho = fechaDespacho;
    }

    public Object getFechaEntregaFacturacion() {
        return fechaEntregaFacturacion;
    }

    public void setFechaEntregaFacturacion(Object fechaEntregaFacturacion) {
        this.fechaEntregaFacturacion = fechaEntregaFacturacion;
    }

    public Object getFechaFacturacion() {
        return fechaFacturacion;
    }

    public void setFechaFacturacion(Object fechaFacturacion) {
        this.fechaFacturacion = fechaFacturacion;
    }

    public Object getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(Object numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

}
