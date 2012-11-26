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
public class GestionDO extends ConeccionMySql {

    Connection cn = null;
    Statement st = null;

    public ArrayList<Object> IngresaDO(DOForm f, Boolean transac, Connection tCn) {

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

            psInsertar = cn.prepareStatement("insert into DOs (do, idCliente, idSucursal, lote, bl, idPuerto, observaciones, idDO) values (?,?,?,?,?,?,?,null)", PreparedStatement.RETURN_GENERATED_KEYS);
            psInsertar.setString(1, f.getDO());
            psInsertar.setInt(2, f.getIdCliente());
            psInsertar.setInt(3, f.getIdSucursal());
            psInsertar.setString(4, f.getLote());
            psInsertar.setString(5, f.getBL());
            psInsertar.setInt(6, f.getIdPuerto());
            psInsertar.setString(7, f.getObservaciones());
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

            psSelectConClave = cn.prepareStatement("SELECT p.DO, p.idCliente FROM DOs p");
            ResultSet rs = psSelectConClave.executeQuery();

            BeanDO bu;
            while (rs.next()) {
                bu = new BeanDO();

                bu.setDO(rs.getObject("p.DO"));
                bu.setIdCliente(rs.getObject("p.idCliente"));

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

            String query = "SELECT p.idDO, p.DO, p.idCliente, IF(r.pedido <> NULL, r.pedido, '') as 'r.pedido', IF(r.referencia <> NULL, r.referencia, '') as 'r.referencia', IF(r.descripcion <> NULL, r.descripcion, '') as 'r.descripcion' ";
            query += "FROM DOs p LEFT JOIN itemsDOs r ON p.idDO = r.idDO ";
            String query2 = "";
            if (f.getbDO().isEmpty() != true) {
                query2 = "p.DO = ?";
            }
            if (f.getbIdCliente().isEmpty() != true) {
                if (query2.equals("") != true) {
                    query2 = query2 + " AND ";
                }
                query2 = query2 + "p.idCliente = ?";
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

                bu.setIdDO(rs.getObject("p.idDO"));
                bu.setDO(rs.getObject("p.DO"));
                bu.setIdCliente(rs.getObject("p.idCliente"));
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

//    public ArrayList<Object> ModificaFactura(FacturaForm f, Boolean transac, Connection tCn) {
//
//        int mod = -99;
//        ArrayList<Object> resultado = new ArrayList<Object>();
//        PreparedStatement psUpdate = null;
//
//        try {
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
//            String query = "UPDATE factura SET idEntidad = ?";
//            query += ", descripcion = ?";
//            query += ", serHija = ?";
//            query += " WHERE idPlantillaDispositivo = ?";
//            psUpdate = cn.prepareStatement(query);
//            psUpdate.setString(1, f.getNombre());
//            psUpdate.setString(2, f.getDescripcion());
//            psUpdate.setBoolean(3, f.getHija());
//            psUpdate.setInt(4, f.getIdPlantillaDispositivo());
//            psUpdate.executeUpdate();
//
//            mod = psUpdate.getUpdateCount();
//
//            if (transac == false) { // si no es una transaccion cierra la conexion
//
//                cn.close();
//
//            }
//
//            resultado.add(false); //si no hubo un error asigna false
//            resultado.add(mod); // y el numero de registros consultados
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

            psDelete = cn.prepareStatement("DELETE FROM DOs WHERE  idDO = ?");
            psDelete.setInt(1, f.getIdDO());
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

            psSelectConClave = cn.prepareStatement("SELECT p.DO, p.idCliente, p.idSucursal, p.lote, p.BL, p.idPuerto, p.observaciones FROM DOs p WHERE p.idDO = ?");
            psSelectConClave.setString(1, DO);
            ResultSet rs = psSelectConClave.executeQuery();

            while (rs.next()) {

                setDO(rs.getObject("p.DO"));
                setIdCliente(rs.getObject("p.idCliente"));
                setIdSucursal(rs.getObject("p.idSucursal"));
                setLote(rs.getObject("p.lote"));
                setBL(rs.getObject("p.BL"));
                setIdPuerto(rs.getObject("p.idPuerto"));
                setObservaciones(rs.getObject("p.observaciones"));

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
    private Object DO;
    private Object idCliente;
    private Object idSucursal;
    private Object Lote;
    private Object BL;
    private Object idPuerto;
    private Object observaciones;
    
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
