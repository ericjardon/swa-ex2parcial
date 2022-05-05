package model;

public class Banco {
    private String nombre;
    private double comision;

    public Banco(String nombre, double comision) {
        this.nombre = nombre;
        this.comision = comision;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getComision() {
        return comision;
    }

    public void setComision(double comision) {
        this.comision = comision;
    }
}
