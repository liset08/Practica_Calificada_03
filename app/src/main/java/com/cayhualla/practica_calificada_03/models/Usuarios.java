package com.cayhualla.practica_calificada_03.models;

/**
 * Created by Alumno on 15/05/2018.
 */

public class Usuarios {



    @Override
    public String toString() {
        return "Usuarios{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", distrito='" + distrito + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
    private Integer id;

    public Usuarios(Integer id, String nombre, String apellido, String username, String password, String distrito, String tipo) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.username = username;
        this.password = password;
        this.distrito = distrito;
        this.tipo = tipo;
    }

    private String nombre;
    private String apellido;
    private String username;

    private String password;
    private String distrito;
    private String tipo;


    public String getTipoUser() {
        return tipo;
    }

    public void setTipo(String tipoUser) {
        this.tipo = tipoUser;
    }





    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }




}
