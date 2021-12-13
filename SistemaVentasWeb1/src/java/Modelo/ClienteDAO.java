package Modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;

    

    public Cliente buscar(String dni) {
        Cliente cl = new Cliente();
        String sql = "select * from cliente where Dni=" + dni; //dni que viene como parametro
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                cl.setId(rs.getInt(1));//se usa la var del obj cliente con el metodo set para obtener los datos de los camp y guardarlos en el obj
                cl.setDni(rs.getString(2));
                cl.setNom(rs.getString(3));
                cl.setDir(rs.getString(4));
                cl.setEstadoId(rs.getInt(5));
            }
        } catch (Exception e) {

        }
        return cl;//este metodo va a retornar el cliente encontrado en nuestra BD
    }

    public Cliente buscarNombreCliente(String clientes) {
        Cliente cl = new Cliente();
        String sql = "select * from cliente where Nombres like '%" + clientes + "%'";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                cl.setId(rs.getInt(1));//se usa la var del obj cliente con el metodo set para obtener los datos de los camp y guardarlos en el obj
                cl.setDni(rs.getString(2));
                cl.setNom(rs.getString(3));
                cl.setDir(rs.getString(4));
            }
        } catch (Exception e) {

        }

        return cl;
    }

    //Operaciones CRUD
    public List listar() {
        String sql = "select * from cliente";
        // la variable list guarda el objeto empleado y lo instancia como un array
        List<Cliente> clientes = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cliente cl = new Cliente(); //Se instancia el obj empleado
                cl.setId(rs.getInt(1)); //Se utiliza el metodo set para obtener los valores de los campos y guardarlos en el objeto em
                cl.setDni(rs.getString(2));
                cl.setNom(rs.getString(3));
                cl.setDir(rs.getString(4));
                cl.setEstadoId(rs.getInt(5));
                clientes.add(cl);
            }
        } catch (Exception e) {

        }
        return clientes;
    }

    // el metodo agregar tiene como paramentro el objeto emplado
    public int agregar(Cliente cl) {
        String sql = "insert into cliente(Dni,Nombres,Direccion,Estado_id)values(?,?,?,?)";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, cl.getDni());
            ps.setString(2, cl.getNom());
            ps.setString(3, cl.getDir());
            ps.setInt(4, cl.getEstadoId());
            ps.executeUpdate();//Con este metodo se van a agregar los datos a la BD

        } catch (Exception e) {

        }
        return r;
    }

    public Cliente listarId(int id) {
        Cliente cli = new Cliente();
        String sql = "select * from cliente where IdCliente=" + id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                cli.setDni(rs.getString(2));
                cli.setNom(rs.getString(3));
                cli.setDir(rs.getString(4));
                cli.setEstadoId(rs.getInt(5));
            }
        } catch (Exception e) {

        }
        return cli;
    }

    public int actualizar(Cliente cl) {
        String sql = "update cliente set Dni=?, Nombres=?, Direccion=?, Estado_id=? where idCliente=?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, cl.getDni());
            ps.setString(2, cl.getNom());
            ps.setString(3, cl.getDir());
            ps.setInt(4, cl.getEstadoId());
            ps.setInt(5, cl.getId());
            ps.executeUpdate();//Con este metodo se van a agregar los datos a la BD

        } catch (Exception e) {

        }
        return r;

    }

    // el metodo delete tiene como paramentro el id del objeto
    public void delete(int id) {
        String sql = "delete from cliente where IdCliente=" + id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();// Este metodo va a elimninar al usuario que sea seleccionado de nuestra BD
        } catch (Exception e) {

        }

    }

}
