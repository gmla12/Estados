/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.parametros;

import forms.bean.parametros.BeanPuerto;
import forms.parametros.PuertoForm;
import forms.parametros.PuertoOpForm;
import java.sql.*;
import java.util.ArrayList;
import util.ConeccionMySql;

/**
 *
 * @author Mario
 */
public class GestionPuerto extends ConeccionMySql {

    Connection cn = null;
    Statement st = null;

    public ArrayList<Object> IngresaPuerto(PuertoForm f, Boolean transac, Connection tCn) {

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

            psInsertar = cn.prepareStatement("insert into pPuerto (id, nombre_corto, descripcion, pmunicipios_id, pmunicipios_departamentos_id, pmunicipios_departmentos_ppaises_id, susuarios_id, fecha_modificacion) values (null,?,?,?,?,?,?, now())", PreparedStatement.RETURN_GENERATED_KEYS);
            psInsertar.setString(1, f.getNombreCorto());
            psInsertar.setString(2, f.getDescripcion());
            psInsertar.setString(3, f.getIdMunicipio());
            psInsertar.setString(4, f.getIdDepartamento());
            psInsertar.setString(5, f.getIdPais());
            psInsertar.setInt(6, f.getIdUsu());
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
    private ArrayList<Object> GR_PUERTO;

    public ArrayList<Object> MostrarSucursal(Boolean transac, Connection tCn) {

        ArrayList<Object> resultado = new ArrayList<Object>();
        PreparedStatement psSelectConClave = null;

        try {

            GR_PUERTO = new ArrayList<Object>();

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

            psSelectConClave = cn.prepareStatement("SELECT p.id, p.nombre_corto, descripcion, pmunicipios_id, pmunicipios_departamentos_id, pmunicipios_departmentos_ppaises_id FROM pPuerto p ");
            ResultSet rs = psSelectConClave.executeQuery();

            BeanPuerto bu;
            while (rs.next()) {
                bu = new BeanPuerto();

                bu.setIdPuerto(rs.getObject("p.id"));
                bu.setNombreCorto(rs.getObject("p.nombre_corto"));
                bu.setDescripcion(rs.getObject("p.descripcion"));
                bu.setIdMunicipio(rs.getObject("p.pmunicipios_id"));
                bu.setIdDepartamento(rs.getObject("p.pmunicipios_departamento_id"));
                bu.setIdPais(rs.getObject("p.pmunicipios_departamento_ppaises_id"));

                GR_PUERTO.add(bu);


            }

            if (transac == false) { // si no es una transaccion cierra la conexion

                cn.close();

            }

            resultado.add(false); //si no hubo un error asigna false
            resultado.add(GR_PUERTO); // y registros consultados

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

    public ArrayList<Object> MostrarPuertoOP(PuertoOpForm f, Boolean transac, Connection tCn) {

        ArrayList<Object> resultado = new ArrayList<Object>();
        PreparedStatement psSelectConClave = null;

        try {

            GR_PUERTO = new ArrayList<Object>();

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

            String query = "SELECT p.id, p.nombre_corto, p.descripcion ";
            query += "FROM pPuerto p ";
            String query2 = "";
            if (f.getbNombreCorto().isEmpty() != true) {
                query2 = "p.nombre_corto LIKE CONCAT('%',?,'%')";
            }
            if (f.getbDescripcion().isEmpty() != true) {
                if (query2.isEmpty() != true) {
                    query2 += "AND ";
                }
                query2 = "p.descripcion LIKE CONCAT('%',?,'%')";
            }
            if (f.getbIdMunicipio().isEmpty() != true) {
                if (query2.isEmpty() != true) {
                    query2 += "AND ";
                }
                query2 = "p.pmunicipios_id LIKE CONCAT('%',?,'%')";
            }
            if (f.getbIdDepartamento().isEmpty() != true) {
                if (query2.isEmpty() != true) {
                    query2 += "AND ";
                }
                query2 += "p.pmunicipios_departamentos_id LIKE CONCAT('%',?,'%')";
            }
            if (f.getbIdPais().isEmpty() != true) {
                if (query2.isEmpty() != true) {
                    query2 += "AND ";
                }
                query2 += "p.pmunicipios_departamentos_ppaises_id LIKE CONCAT('%',?,'%')";
            }
            if (query2.isEmpty() != true) {
                query += "WHERE " + query2;
            }
            psSelectConClave = cn.prepareStatement(query);
            if (f.getbNombreCorto().isEmpty() != true) {
                psSelectConClave.setString(1, f.getbNombreCorto());
                if (f.getbDescripcion().isEmpty() != true) {
                    psSelectConClave.setString(2, f.getbDescripcion());
                    if (f.getbIdMunicipio().isEmpty() != true) {
                        psSelectConClave.setString(3, f.getbIdMunicipio());
                        if (f.getbIdDepartamento().isEmpty() != true) {
                            psSelectConClave.setString(4, f.getbIdDepartamento());
                            if (f.getbIdPais().isEmpty() != true) {
                                psSelectConClave.setString(5, f.getbIdPais());
                            }
                        } else {
                            if (f.getbIdPais().isEmpty() != true) {
                                psSelectConClave.setString(4, f.getbIdPais());
                            }
                        }
                    } else {
                        if (f.getbIdDepartamento().isEmpty() != true) {
                            psSelectConClave.setString(3, f.getbIdDepartamento());
                            if (f.getbIdPais().isEmpty() != true) {
                                psSelectConClave.setString(4, f.getbIdPais());
                            }
                        } else {
                            if (f.getbIdPais().isEmpty() != true) {
                                psSelectConClave.setString(3, f.getbIdPais());
                            }
                        }
                    }
                } else {
                    if (f.getbIdMunicipio().isEmpty() != true) {
                        psSelectConClave.setString(2, f.getbIdMunicipio());
                        if (f.getbIdDepartamento().isEmpty() != true) {
                            psSelectConClave.setString(3, f.getbIdDepartamento());
                            if (f.getbIdPais().isEmpty() != true) {
                                psSelectConClave.setString(4, f.getbIdPais());
                            }
                        } else {
                            if (f.getbIdPais().isEmpty() != true) {
                                psSelectConClave.setString(3, f.getbIdPais());
                            }
                        }
                    } else {
                        if (f.getbIdDepartamento().isEmpty() != true) {
                            psSelectConClave.setString(2, f.getbIdDepartamento());
                            if (f.getbIdPais().isEmpty() != true) {
                                psSelectConClave.setString(3, f.getbIdPais());
                            }
                        } else {
                            if (f.getbIdPais().isEmpty() != true) {
                                psSelectConClave.setString(2, f.getbIdPais());
                            }
                        }
                    }
                }
            } else {
                if (f.getbDescripcion().isEmpty() != true) {
                    psSelectConClave.setString(1, f.getbDescripcion());
                    if (f.getbIdMunicipio().isEmpty() != true) {
                        psSelectConClave.setString(2, f.getbIdMunicipio());
                        if (f.getbIdDepartamento().isEmpty() != true) {
                            psSelectConClave.setString(3, f.getbIdDepartamento());
                            if (f.getbIdPais().isEmpty() != true) {
                                psSelectConClave.setString(4, f.getbIdPais());
                            }
                        } else {
                            if (f.getbIdPais().isEmpty() != true) {
                                psSelectConClave.setString(3, f.getbIdPais());
                            }
                        }
                    } else {
                        if (f.getbIdDepartamento().isEmpty() != true) {
                            psSelectConClave.setString(2, f.getbIdDepartamento());
                            if (f.getbIdPais().isEmpty() != true) {
                                psSelectConClave.setString(3, f.getbIdPais());
                            }
                        } else {
                            if (f.getbIdPais().isEmpty() != true) {
                                psSelectConClave.setString(2, f.getbIdPais());
                            }
                        }
                    }
                } else {
                    if (f.getbIdMunicipio().isEmpty() != true) {
                        psSelectConClave.setString(1, f.getbIdMunicipio());
                        if (f.getbIdDepartamento().isEmpty() != true) {
                            psSelectConClave.setString(2, f.getbIdDepartamento());
                            if (f.getbIdPais().isEmpty() != true) {
                                psSelectConClave.setString(3, f.getbIdPais());
                            }
                        } else {
                            if (f.getbIdPais().isEmpty() != true) {
                                psSelectConClave.setString(2, f.getbIdPais());
                            }
                        }
                    } else {
                        if (f.getbIdDepartamento().isEmpty() != true) {
                            psSelectConClave.setString(1, f.getbIdDepartamento());
                            if (f.getbIdPais().isEmpty() != true) {
                                psSelectConClave.setString(2, f.getbIdPais());
                            }
                        } else {
                            if (f.getbIdPais().isEmpty() != true) {
                                psSelectConClave.setString(1, f.getbIdPais());
                            }
                        }
                    }
                }
            }
            ResultSet rs = psSelectConClave.executeQuery();

            BeanPuerto bu;
            while (rs.next()) {

                bu = new BeanPuerto();

                bu.setIdPuerto(rs.getObject("p.id"));
                bu.setNombreCorto(rs.getObject("p.nombre_corto"));
                bu.setDescripcion(rs.getObject("p.descripcion"));
                bu.setIdMunicipio(rs.getObject("p.pmunicipios_id"));
                bu.setIdDepartamento(rs.getObject("p.pmunicipios_departamentos_id"));
                bu.setIdPais(rs.getObject("p.pmunicipios_departamentos_ppaises_id"));

                GR_PUERTO.add(bu);

            }

            if (transac == false) { // si no es una transaccion cierra la conexion

                cn.close();

            }

            resultado.add(false); //si no hubo un error asigna false
            resultado.add(GR_PUERTO); // y registros consultados

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

    public ArrayList<Object> ModificaPuerto(PuertoForm f, Boolean transac, Connection tCn) {

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

            String query = "UPDATE Puerto SET nombre = ?";
            query += " WHERE idPuerto = ?";
            psUpdate = cn.prepareStatement(query);
            psUpdate.setString(1, f.getNombre());
            psUpdate.setInt(2, f.getIdPuerto());
            psUpdate.executeUpdate();

            mod = psUpdate.getUpdateCount();

            if (transac == false) { // si no es una transaccion cierra la conexion

                cn.close();

            }

            resultado.add(false); //si no hubo un error asigna false
            resultado.add(mod); // y el numero de registros consultados

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

    public ArrayList<Object> EliminaPuerto(PuertoForm f, Boolean transac, Connection tCn) {

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

            psDelete = cn.prepareStatement("DELETE FROM Puerto WHERE  idPuerto = ?");
            psDelete.setInt(1, f.getIdPuerto());
            psDelete.executeUpdate();

            mod = psDelete.getUpdateCount();

            if (transac == false) { // si no es una transaccion cierra la conexion

                cn.close();

            }

            resultado.add(false); //si no hubo un error asigna false
            resultado.add(mod); // y el numero de registros consultados

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

    public ArrayList<Object> MostrarPuertoFormulario(int IdPuerto, Boolean transac, Connection tCn) {

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

            psSelectConClave = cn.prepareStatement("SELECT p.idPuerto, p.nombre FROM Puerto p WHERE  p.idPuerto = ?");
            psSelectConClave.setInt(1, IdPuerto);
            ResultSet rs = psSelectConClave.executeQuery();

            BeanPuerto bu;
            while (rs.next()) {
                bu = new BeanPuerto();

                setIdSucursal(rs.getObject("p.idPuerto"));
                setNombre(rs.getObject("p.nombre"));

            }

            if (transac == false) { // si no es una transaccion cierra la conexion

                cn.close();

            }

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

        } catch (SQLException e) {

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

        } catch (SQLException e) {

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
    private Object idPuerto;
    private Object nombre;

    public Object getIdPuerto() {
        return idPuerto;
    }

    public void setIdSucursal(Object idPuerto) {
        this.idPuerto = idPuerto;
    }

    public Object getNombre() {
        return nombre;
    }

    public void setNombre(Object nombre) {
        this.nombre = nombre;
    }
}
