/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.log;

import forms.bean.log.BeanAuditoria;
import java.sql.*;
import java.util.ArrayList;
import util.ConeccionMySql;

/**
 *
 * @author Mario
 */
public class GestionAuditoria extends ConeccionMySql {

    Connection cn = null;
    Statement st = null;

    public ArrayList<Object> IngresaAuditoria(String accion, String valor_anterior, String valor_nuevo, int idUsuario, int idFormulario, String referencia, Boolean transac, Connection tCn) {

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

            psInsertar = cn.prepareStatement("insert into lauditoria (id, fecha, accion, valor_anterior, valor_nuevo, susuarios_id, sformularios_id, referencia) values (null, now(), ?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            psInsertar.setString(1, accion);
            psInsertar.setString(2, valor_anterior);
            psInsertar.setString(3, valor_nuevo);
            psInsertar.setInt(4, idUsuario);
            psInsertar.setInt(5, idFormulario);
            psInsertar.setString(6, referencia);
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
    private ArrayList<Object> GR_AUDITORIA;

    public ArrayList<Object> MostrarAuditoriaReferencia(String referencia, int idFormulario, Boolean transac, Connection tCn) {

        ArrayList<Object> resultado = new ArrayList<Object>();
        PreparedStatement psSelectConClave = null;

        try {

            GR_AUDITORIA = new ArrayList<Object>();

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

            String query = "SELECT p.id, p.susuarios_id, IF(e.primer_nombre <> NULL AND e.primer_apellido <> NULL, e.razon_Social, CONCAT(IF(e.primer_nombre <> NULL,'',CONCAT(e.primer_nombre,' ')), IF(e.segundo_nombre <> NULL,'',CONCAT(e.segundo_nombre,' ')), IF(e.primer_apellido <> NULL,'',CONCAT(e.primer_apellido,' ')), IF(e.segundo_apellido <> NULL,'',CONCAT(e.segundo_apellido,' ')))) as nombre_usu, p.fecha, p.accion, p.valor_anterior, p.valor_nuevo, p.sFormularios_id, p.referencia FROM lauditoria p INNER JOIN susuarios r ON p.susuarios_id = r.id INNER JOIN entidades e ON r.id_tipo_documento = e.id_tipo_documento AND r.identificacion = e.identificacion ";
            query += "WHERE referencia=? AND sFormularios_id=? ";
            psSelectConClave = cn.prepareStatement(query);
            psSelectConClave.setString(1, referencia);
            psSelectConClave.setInt(2, idFormulario);
            ResultSet rs = psSelectConClave.executeQuery();

            BeanAuditoria bu;
            while (rs.next()) {
                bu = new BeanAuditoria();

                bu.setNombreUsu(rs.getObject("nombre_usu"));
                bu.setFecha(rs.getObject("p.fecha"));
                bu.setAccion(rs.getObject("p.accion"));
                bu.setValorAnterior(rs.getObject("p.valor_anterior"));
                bu.setValorNuevo(rs.getObject("p.valor_nuevo"));

                GR_AUDITORIA.add(bu);


            }

            if (transac == false) { // si no es una transaccion cierra la conexion

                cn.close();

            }

            resultado.add(false); //si no hubo un error asigna false
            resultado.add(GR_AUDITORIA); // y registros consultados

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

    public ArrayList<Object> MostrarAuditoriaEliminadas(int idFormulario, Boolean transac, Connection tCn) {

        ArrayList<Object> resultado = new ArrayList<Object>();
        PreparedStatement psSelectConClave = null;

        try {

            GR_AUDITORIA = new ArrayList<Object>();

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

            String query = "SELECT p.id, p.susuarios_id, IF(e.primer_nombre <> NULL AND e.primer_apellido <> NULL, e.razon_Social, CONCAT(IF(e.primer_nombre <> NULL,'',CONCAT(e.primer_nombre,' ')), IF(e.segundo_nombre <> NULL,'',CONCAT(e.segundo_nombre,' ')), IF(e.primer_apellido <> NULL,'',CONCAT(e.primer_apellido,' ')), IF(e.segundo_apellido <> NULL,'',CONCAT(e.segundo_apellido,' ')))) as nombre_usu, p.fecha, p.accion, p.valor_anterior, p.valor_nuevo, p.sFormularios_id, p.referencia FROM lauditoria p INNER JOIN susuarios r ON p.susuarios_id = r.id INNER JOIN entidades e ON r.id_tipo_documento = e.id_tipo_documento AND r.identificacion = e.identificacion ";
            query += "WHERE sFormularios_id=? AND p.accion = 'Eliminar'";
            psSelectConClave = cn.prepareStatement(query);
            psSelectConClave.setInt(1, idFormulario);
            ResultSet rs = psSelectConClave.executeQuery();

            BeanAuditoria bu;
            while (rs.next()) {
                bu = new BeanAuditoria();

                bu.setNombreUsu(rs.getObject("nombre_usu"));
                bu.setFecha(rs.getObject("p.fecha"));
                bu.setAccion(rs.getObject("p.accion"));
                bu.setValorAnterior(rs.getObject("p.valor_anterior"));
                bu.setValorNuevo(rs.getObject("p.valor_nuevo"));

                GR_AUDITORIA.add(bu);


            }

            if (transac == false) { // si no es una transaccion cierra la conexion

                cn.close();

            }

            resultado.add(false); //si no hubo un error asigna false
            resultado.add(GR_AUDITORIA); // y registros consultados

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

    public ArrayList<Object> BuscarFormulario(String Formulario, Boolean transac, Connection tCn) {

        ArrayList<Object> resultado = new ArrayList<Object>();
        PreparedStatement psSelectConClave = null;

        try {

            GR_AUDITORIA = new ArrayList<Object>();

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

            String query = "SELECT p.id, p.nombre FROM sFormularios p ";
            query += "WHERE nombre=? ";
            psSelectConClave = cn.prepareStatement(query);
            psSelectConClave.setString(1, Formulario);
            ResultSet rs = psSelectConClave.executeQuery();

            while (rs.next()) {

                setIdFormulario(rs.getObject("p.id"));
                setFormularioNombre(rs.getObject("p.nombre"));

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

    private Object idFormulario;
    private Object formularioNombre;

    public Object getFormularioNombre() {
        return formularioNombre;
    }

    public void setFormularioNombre(Object formularioNombre) {
        this.formularioNombre = formularioNombre;
    }

    public Object getIdFormulario() {
        return idFormulario;
    }

    public void setIdFormulario(Object idFormulario) {
        this.idFormulario = idFormulario;
    }
}
