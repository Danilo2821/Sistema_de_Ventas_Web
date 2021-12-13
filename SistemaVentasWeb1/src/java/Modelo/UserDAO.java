package Modelo;

import config.Conexion;
import config.Encriptador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class UserDAO {
    
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;
    
       public int getRolId(int idUser) {
       int idRol = 0;
        String sql = "select Rol_id from usuario where Idusuario=?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idUser);
            rs = ps.executeQuery();
            while (rs.next()) {
               idRol = rs.getInt("Rol_id");
            }
        } catch (Exception e) {

        }
        return idRol;//este metodo va a retornar el cliente encontrado en nuestra BD
    }
    
    
    public User validar(String user, String pass) {
        User us = new User();
        String sql = "select * from usuario where User=? and Pass=?";
         
        try {
            String passHash = this.comparePassword(user, pass);
            if(passHash == null){
                return null;
            }
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, passHash);
            rs = ps.executeQuery();
            while (rs.next()) {
                us.setId(rs.getInt("Idusuario"));
                us.setUser(rs.getString("User"));
                us.setPass(rs.getString("Pass"));
                us.setNom(rs.getString("Nombres"));
                us.setDni(rs.getString("Dni"));
                us.setEstadoId(rs.getInt("Estado_id"));
                us.setRolId(rs.getInt("Rol_id"));
            }
        } catch (Exception e) {
        }
        return us;

    }
    
        public String comparePassword(String user, String pass) {
        Encriptador encrip = new Encriptador();
        String passHash = null;
        boolean passwordValid = false;
        String sql = "select Pass from usuario where User=?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, user);
            rs = ps.executeQuery();
            while (rs.next()) {      
                passHash = rs.getString("Pass");
            }
            
           passwordValid = encrip.comparePasswords(pass, passHash);
           if(passwordValid){
              return passHash; 
           }
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
        
        public String getAutoridad(int idRol) {
        String sql = "select Autoridad from rol where idRol=?";
        String autoridad = null;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idRol);
            rs = ps.executeQuery();
            while (rs.next()) {      
                autoridad = rs.getString("Autoridad");
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return autoridad;
    }

     public int registrar(User us) {
        String sql = "insert into usuario(Dni,Nombres,Telefono,Direccion,Estado_id,User,Pass,Rol_id)values(?,?,?,?,?,?,?,?)";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, us.getDni());
            ps.setString(2, us.getNom());
            ps.setString(3, us.getTel());
            ps.setString(4, us.getDir());
            ps.setInt(5, us.getEstadoId());
            ps.setString(6, us.getUser());
            ps.setString(7, us.getPass());
            ps.executeUpdate();//Con este metodo se van a agregar los datos a la BD

        } catch (Exception e) {

        }
        return r;
    }
     
      
    //Operaciones CRUD
    public List listar() {
        String sql = "select * from usuario";
        // la variable list guarda el objeto empleado y lo instancia como un array
        List<User> users = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                User us = new User(); //Se instancia el obj empleado
                us.setId(rs.getInt(1)); //Se utiliza el metodo set para obtener los valores de los campos y guardarlos en el objeto em
                us.setDni(rs.getString(2));
                us.setNom(rs.getString(3));
                us.setTel(rs.getString(4));
                us.setDir(rs.getString(5));
                us.setEstadoId(rs.getInt(6));
                us.setUser(rs.getString(7));// todo el objeto se agrega a la variable lista
                us.setPass(rs.getString(8));
                us.setRolId(rs.getInt(9));
                users.add(us);
            }
        } catch (Exception e) {

        }
        return users;
    }

    // el metodo agregar tiene como paramentro el objeto emplado
    public int agregar(User us) {
        String sql = "insert into usuario(Dni,Nombres,Telefono,Direccion,Estado_id,User,Pass,Rol_id)values(?,?,?,?,?,?,?,?)";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, us.getDni());
            ps.setString(2, us.getNom());
            ps.setString(3, us.getTel());
            ps.setString(4, us.getDir());
            ps.setInt(5, us.getEstadoId());
            ps.setString(6, us.getUser());
            ps.setString(7, us.getPass());
            ps.setInt(8, us.getRolId());
            ps.executeUpdate();//Con este metodo se van a agregar los datos a la BD

        } catch (Exception e) {
            System.out.println(e);
        }
        return r;
    }

    public User listarId(int id) {
        User usr = new User();
        String sql = "select * from usuario where Idusuario="+id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                usr.setDni(rs.getString(2));
                usr.setNom(rs.getString(3));
                usr.setTel(rs.getString(4));
                usr.setDir(rs.getString(5));
                usr.setEstadoId(rs.getInt(6));
                usr.setUser(rs.getString(7));
                usr.setPass(rs.getString(8));
                usr.setRolId(rs.getInt(9));
            }
        } catch (Exception e) {

        }
        return usr;
    }

    public int actualizar(User us) {
        String sql = "update usuario set Dni=?, Nombres=?, Telefono=?, Direccion=?, Estado_id=?, User=?, Pass=?, Rol_id=? where Idusuario=?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, us.getDni());
            ps.setString(2, us.getNom());
            ps.setString(3, us.getTel());
            ps.setString(4, us.getDir());
            ps.setInt(5, us.getEstadoId());
            ps.setString(6, us.getUser());
            ps.setString(7, us.getPass());
            ps.setInt(8, us.getRolId());
            ps.setInt(9, us.getId());
            ps.executeUpdate();//Con este metodo se van a agregar los datos a la BD

        } catch (Exception e) {

        }
        return r;

    }

    // el metodo delete tiene como paramentro el id del objeto
    public void delete(int id) {
        String sql = "delete from usuario where Idusuario="+id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();// Este metodo va a elimninar al usuario que sea seleccionado de nuestra BD
        } catch (Exception e) {

        }

    }
    
}
