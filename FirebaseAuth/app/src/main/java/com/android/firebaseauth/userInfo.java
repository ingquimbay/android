package com.android.firebaseauth;

/**
 * Created by carlitos on 10/17/17.
 */

public class userInfo {

    private String nombre, apellido;
    private int edad, peso;
    private Double estatura;

    public userInfo() {
    }

    public userInfo(String nombre, String apellido, int edad, int peso, Double estatura) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.peso = peso;
        this.estatura = estatura;
    }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }

    public void setApellido(String apellido) { this.apellido = apellido;}

    public int getEdad() { return edad; }

    public void setEdad(int edad) { this.edad = edad; }

    public int getPeso() { return peso; }

    public void setPeso(int peso) { this.peso = peso; }

    public Double getEstatura() { return estatura; }

    public void setEstatura(Double estatura) { this.estatura = estatura; }
}
