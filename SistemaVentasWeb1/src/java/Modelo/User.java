package Modelo;


public class User {
    
    int id;
    String dni;
    String nom;
    String tel;
    String dir;
    int estadoId;
    String user;
    String pass;
    int rolId;

    public User() {
    }

    public User(int id, String dni, String nom, String tel, String dir, int estadoId, String user, String pass, int rolId) {
        this.id = id;
        this.dni = dni;
        this.nom = nom;
        this.tel = tel;
        this.dir = dir;
        this.estadoId = estadoId;
        this.user = user;
        this.pass = pass;
        this.rolId = rolId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public int getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(int estadoId) {
        this.estadoId = estadoId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getRolId() {
        return rolId;
    }

    public void setRolId(int rolId) {
        this.rolId = rolId;
    }

   
}
