package Modelo;

import java.io.InputStream;


public class Producto {

    int id;
    String nom;
    double precio;
    int stock;
    String estado;
    String foto;
    String descripcion;

    public Producto() {
    }

    public Producto(int id, String nom, double precio, int stock, String estado, String foto, String descripcion) {
        this.id = id;
        this.nom = nom;
        this.precio = precio;
        this.stock = stock;
        this.estado = estado;
        this.foto = foto;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
      
    
}