package Modelo;


public class Cliente {
    
    int id;
    String dni;
    String nom;
    String dir;
    int estadoId;

    public Cliente() {
    }

    public Cliente(int id, String dni, String nom, String dir, int estadoId) {
        this.id = id;
        this.dni = dni;
        this.nom = nom;
        this.dir = dir;
        this.estadoId = estadoId;
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

   
    
}