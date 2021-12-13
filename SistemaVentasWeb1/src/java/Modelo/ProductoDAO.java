package Modelo;

import config.Conexion;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;


public class ProductoDAO {
    
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;
    
    
    public Producto buscar(int id){
        Producto pro = new Producto();
        String sql="select * from producto where idproducto="+id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                pro.setId(rs.getInt(1)); //Se utiliza el metodo set para obtener los valores de los campos y guardarlos en el objeto em
                pro.setNom(rs.getString(2));
                pro.setPrecio(rs.getDouble(3));
                pro.setStock(rs.getInt(4));
                pro.setEstado(rs.getString(5));// todo el objeto se agrega a la variable lista
            }
        } catch (Exception e) {

        }
        
        return pro;
    }
    
     public Producto buscarNombre(String nombre){
        Producto pro = new Producto();
        String sql="select * from producto where Nombres like '%"+nombre+"%'";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                pro.setId(rs.getInt(1)); //Se utiliza el metodo set para obtener los valores de los campos y guardarlos en el objeto em
                pro.setNom(rs.getString(2));
                pro.setPrecio(rs.getDouble(3));
                pro.setStock(rs.getInt(4));
                pro.setEstado(rs.getString(5));// todo el objeto se agrega a la variable lista
            }
        } catch (Exception e) {

        }
        
        return pro;
    }
    
    
    public int actualizarstock(int id, int stock){
        String sql="update producto set Stock=? where idproducto=?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, stock);
            ps.setInt(2, id);
            ps.executeUpdate();//Con este metodo se van a agregar los datos a la BD

        } catch (Exception e) {

        }
        
        return r;
    }
    
     
    
    
    //Operaciones CRUD
    public List listar() {
        String sql = "select * from producto";
        // la variable list guarda el objeto empleado y lo instancia como un array
        List<Producto> producto = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Producto pro = new Producto(); //Se instancia el obj empleado
                pro.setId(rs.getInt(1)); //Se utiliza el metodo set para obtener los valores de los campos y guardarlos en el objeto em
                pro.setNom(rs.getString(2));
                pro.setPrecio(rs.getDouble(3));
                pro.setStock(rs.getInt(4));
                pro.setEstado(rs.getString(5));// todo el objeto se agrega a la variable lista
                pro.setDescripcion(rs.getString(6));
                producto.add(pro);
            }
        } catch (Exception e) {

        }
        return producto;
    }

    // el metodo agregar tiene como paramentro el objeto emplado
    public int agregar(Producto pro) {
        String sql = "insert into producto(Nombres,Precio,Stock,Estado,Descripcion)values(?,?,?,?,?)";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, pro.getNom());
            ps.setDouble(2, pro.getPrecio());
            ps.setInt(3, pro.getStock());
            ps.setString(4, pro.getEstado());
            ps.setString(5, pro.getDescripcion());
            ps.executeUpdate();//Con este metodo se van a agregar los datos a la BD

        } catch (Exception e) {

        }
        return r;
    }

    public Producto listarId(int id) {
        Producto prod = new Producto();
        String sql = "select * from producto where IdProducto="+id+"";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                prod.setId(rs.getInt(1));
                prod.setNom(rs.getString(2));
                prod.setPrecio(rs.getDouble(3));
                prod.setStock(rs.getInt(4));
                prod.setEstado(rs.getString(5));
                prod.setDescripcion(rs.getString(6));
            }
        } catch (Exception e) {

        }
        return prod;
    }

    
    public int actualizar(Producto pro) {
        String sql = "update producto set Nombres=?, Precio=?, Stock=?, Estado=?, Descripcion=? where idProducto=?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, pro.getNom());
            ps.setDouble(2, pro.getPrecio());
            ps.setInt(3, pro.getStock());
            ps.setString(4, pro.getEstado());
            ps.setString(5, pro.getDescripcion());
            ps.setInt(6, pro.getId());
            ps.executeUpdate();//Con este metodo se van a agregar los datos a la BD

        } catch (Exception e) {

        }
        return r;

    }

    // el metodo delete tiene como paramentro el id del objeto
    public void delete(int id) {
        String sql = "delete from producto where IdProducto="+id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();// Este metodo va a elimninar al usuario que sea seleccionado de nuestra BD
        } catch (Exception e) {

        }

    }
}
