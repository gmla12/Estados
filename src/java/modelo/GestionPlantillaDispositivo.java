/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import forms.PlantillaDispositivoForm;
import forms.PlantillaDispositivoOpForm;
import forms.bean.BeanPlantillaDispositivo;
import java.sql.*;
import java.util.ArrayList;
import util.ConeccionMySql;

/**
 *
 * @author Mario
 */
public class GestionPlantillaDispositivo extends ConeccionMySql {

    Connection cn = null;
    Statement st = null;

    public ArrayList<Object> IngresaPlantillaDispositivo(PlantillaDispositivoForm f, Boolean transac, Connection tCn) {

        int mod = -99;
        ArrayList<Object> resultado = new ArrayList<Object>();
        PreparedStatement psInsertar = null;

        try {

            if (transac == false) { //si no es una transaccion busca una nueva conexion

                ArrayList<Object> resultad = new ArrayList<Object>();
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

            psInsertar = cn.prepareStatement("insert into plantillaDispositivo     (idPlantillaDispositivo, nombre, descripcion, serHija) values (null,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            psInsertar.setString(1, f.getNombre());
            psInsertar.setString(2, f.getDescripcion());
            psInsertar.setBoolean(3, f.getHija());
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
            resultado.add(claveGenerada); // clave Generada
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
    private ArrayList<Object> GR_PLANTILLADISPOSITIVO;

    public ArrayList<Object> MostrarPlantillaDispositivo(Boolean transac, Connection tCn) {

        ArrayList<Object> resultado = new ArrayList<Object>();
        PreparedStatement psSelectConClave = null;

        try {

            GR_PLANTILLADISPOSITIVO = new ArrayList<Object>();

            if (transac == false) { //si no es una transaccion busca una nueva conexion

                ArrayList<Object> resultad = new ArrayList<Object>();
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

            psSelectConClave = cn.prepareStatement("SELECT p.idPlantillaDispositivo, p.nombre, p.descripcion, p.serHija FROM plantillaDispositivo p");
            ResultSet rs = psSelectConClave.executeQuery();

            BeanPlantillaDispositivo bu;
            while (rs.next()) {
                bu = new BeanPlantillaDispositivo();

                bu.setIdPlantillaDispositivo(rs.getObject("p.idPlantillaDispositivo"));
                bu.setNombre(rs.getObject("p.nombre"));
                bu.setDescripcion(rs.getObject("p.descripcion"));
                bu.setHija(rs.getObject("p.serHija"));

                GR_PLANTILLADISPOSITIVO.add(bu);

            }

            if (transac == false) { // si no es una transaccion cierra la conexion

                cn.close();

            }

            resultado.add(false); //si no hubo un error asigna false
            resultado.add(GR_PLANTILLADISPOSITIVO); // y registros consultados

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

    public ArrayList<Object> MostrarPlantillaDispositivoOP(PlantillaDispositivoOpForm f, Boolean transac, Connection tCn) {

        ArrayList<Object> resultado = new ArrayList<Object>();
        PreparedStatement psSelectConClave = null;

        try {

            GR_PLANTILLADISPOSITIVO = new ArrayList<Object>();

            if (transac == false) { //si no es una transaccion busca una nueva conexion

                ArrayList<Object> resultad = new ArrayList<Object>();
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

            String query = "SELECT p.idPlantillaDispositivo, p.nombre, p.descripcion, p.serHija ";
            query += "FROM PlantillaDispositivo p ";
            String query2 = "";
            if (f.getbNombre().isEmpty() != true) {
                query2 = "p.nombre LIKE CONCAT('%',?,'%')";
            }
            if (f.getbHija().isEmpty() != true) {
                if (query2.equals("") != true) {
                    query2 = query2 + " AND ";
                }
                query2 = query2 + "p.serHija = ?";
            }
            if (query2.isEmpty() != true) {
                query += "WHERE " + query2;
            }
            psSelectConClave = cn.prepareStatement(query);
            if (f.getbNombre().isEmpty() != true) {
                psSelectConClave.setString(1, f.getbNombre());
                if (f.getbHija().isEmpty() != true) {
                    psSelectConClave.setBoolean(2, Boolean.parseBoolean(f.getbHija()));
                }
            } else {
                if (f.getbHija().isEmpty() != true) {
                    psSelectConClave.setBoolean(1, Boolean.parseBoolean(f.getbHija()));
                }
            }
            ResultSet rs = psSelectConClave.executeQuery();

            BeanPlantillaDispositivo bu;
            while (rs.next()) {
                bu = new BeanPlantillaDispositivo();

                bu.setIdPlantillaDispositivo(rs.getObject("p.idPlantillaDispositivo"));
                bu.setNombre(rs.getObject("p.nombre"));
                bu.setHija(rs.getObject("p.serHija"));

                GR_PLANTILLADISPOSITIVO.add(bu);

            }

            if (transac == false) { // si no es una transaccion cierra la conexion

                cn.close();

            }

            resultado.add(false); //si no hubo un error asigna false
            resultado.add(GR_PLANTILLADISPOSITIVO); // y registros consultados

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

    public ArrayList<Object> MostrarPlantillaDispositivoHija(int idPlantillaDispositivo, Boolean transac, Connection tCn) {

        ArrayList<Object> resultado = new ArrayList<Object>();
        PreparedStatement psSelectConClave = null;

        try {

            GR_PLANTILLADISPOSITIVO = new ArrayList<Object>();

            if (transac == false) { //si no es una transaccion busca una nueva conexion

                ArrayList<Object> resultad = new ArrayList<Object>();
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

            String query = "SELECT p.idPlantillaDispositivo, p.nombre, p.descripcion, p.serHija FROM plantillaDispositivo p WHERE p.idPlantillaDispositivo <> ? AND p.serHija = true AND p.idPlantillaDispositivo <> ALL (SELECT c.idPlantillaDispositivoHija FROM plantillaDispositivoHija c WHERE c.idPlantillaDispositivo = ?) AND p.idPlantillaDispositivo <> ALL (SELECT c.idPlantillaDispositivo FROM plantillaDispositivoHija c WHERE c.idPlantillaDispositivoHija = ?)";
            psSelectConClave = cn.prepareStatement(query);
            psSelectConClave.setInt(1, idPlantillaDispositivo);
            psSelectConClave.setInt(2, idPlantillaDispositivo);
            psSelectConClave.setInt(3, idPlantillaDispositivo);
            ResultSet rs = psSelectConClave.executeQuery();

            BeanPlantillaDispositivo bu;
            while (rs.next()) {
                bu = new BeanPlantillaDispositivo();

                bu.setIdPlantillaDispositivo(rs.getObject("p.idPlantillaDispositivo"));
                bu.setNombre(rs.getObject("p.nombre"));
                bu.setHija(rs.getObject("p.serHija"));

                GR_PLANTILLADISPOSITIVO.add(bu);

            }

            if (transac == false) { // si no es una transaccion cierra la conexion

                cn.close();

            }

            resultado.add(false); //si no hubo un error asigna false
            resultado.add(GR_PLANTILLADISPOSITIVO); // y registros consultados

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

    public ArrayList<Object> ModificaPlantillaDispositivo(PlantillaDispositivoForm f, Boolean transac, Connection tCn) {

        int mod = -99;
        ArrayList<Object> resultado = new ArrayList<Object>();
        PreparedStatement psUpdate = null;

        try {

            if (transac == false) { //si no es una transaccion busca una nueva conexion

                ArrayList<Object> resultad = new ArrayList<Object>();
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

            String query = "UPDATE plantillaDispositivo SET nombre = ?";
            query += ", descripcion = ?";
            query += ", serHija = ?";
            query += " WHERE idPlantillaDispositivo = ?";
            psUpdate = cn.prepareStatement(query);
            psUpdate.setString(1, f.getNombre());
            psUpdate.setString(2, f.getDescripcion());
            psUpdate.setBoolean(3, f.getHija());
            psUpdate.setInt(4, f.getIdPlantillaDispositivo());
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

    public ArrayList<Object> EliminaPlantillaDispositivo(PlantillaDispositivoForm f, Boolean transac, Connection tCn) {

        int mod = -99;
        ArrayList<Object> resultado = new ArrayList<Object>();
        PreparedStatement psDelete = null;

        try {

            if (transac == false) { //si no es una transaccion busca una nueva conexion

                ArrayList<Object> resultad = new ArrayList<Object>();
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

            psDelete = cn.prepareStatement("DELETE FROM plantillaDispositivo WHERE  idPlantillaDispositivo = ?");
            psDelete.setInt(1, f.getIdPlantillaDispositivo());
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

    public ArrayList<Object> MostrarPlantillaDispositivoFormulario(int IdPlantillaDispositivo, Boolean transac, Connection tCn) {

        ArrayList<Object> resultado = new ArrayList<Object>();
        PreparedStatement psSelectConClave = null;

        try {

            if (transac == false) { //si no es una transaccion busca una nueva conexion

                ArrayList<Object> resultad = new ArrayList<Object>();
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

            psSelectConClave = cn.prepareStatement("SELECT p.idPlantillaDispositivo, p.nombre, p.descripcion, p.serHija FROM plantillaDispositivo p WHERE  p.idPlantillaDispositivo = ?");
            psSelectConClave.setInt(1, IdPlantillaDispositivo);
            ResultSet rs = psSelectConClave.executeQuery();

            while (rs.next()) {

                setIdPlantillaDispositivo(rs.getObject("p.idPlantillaDispositivo"));
                setNombre(rs.getObject("p.nombre"));
                setDescripcion(rs.getObject("p.descripcion"));
                setHija(rs.getObject("p.serHija"));

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

            ArrayList<Object> resultad = new ArrayList<Object>();
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
    private Object idPlantillaDispositivo;
    private Object nombre;
    private Object descripcion;
    private Object hija;

    public Object getNombre() {
        return nombre;
    }

    public void setNombre(Object nombre) {
        this.nombre = nombre;
    }

    public Object getHija() {
        return hija;
    }

    public void setHija(Object hija) {
        this.hija = hija;
    }

    public Object getIdPlantillaDispositivo() {
        return idPlantillaDispositivo;
    }

    public void setIdPlantillaDispositivo(Object idPlantillaDispositivo) {
        this.idPlantillaDispositivo = idPlantillaDispositivo;
    }

    public Object getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(Object descripcion) {
        this.descripcion = descripcion;
    }
}
