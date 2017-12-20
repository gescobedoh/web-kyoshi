package com.kyoshi.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="ingrediente")
public class Ingrediente implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idIngrediente;
    
    @Column(name="cantidadIngrediente",length=11,nullable=false)
    private float cantidadIngrediente;
    
    @Column(name = "nombreIngrediente", length = 50, nullable = false)
    private String nombreIngrediente;
    
    @Column(name="costeIngrediente", precision=11, scale=2,nullable=false)
    private float costeIngrediente;
    
    public Ingrediente(){}

    public Ingrediente(int idIngrediente, float cantidadIngrediente, String nombreIngrediente, float costeIngrediente) {
        this.idIngrediente = idIngrediente;
        this.cantidadIngrediente = cantidadIngrediente;
        this.nombreIngrediente = nombreIngrediente;
        this.costeIngrediente = costeIngrediente;
    }

    public int getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdIngrediente(int idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    public float getCantidadIngrediente() {
        return cantidadIngrediente;
    }

    public void setCantidadIngrediente(float cantidadIngrediente) {
        this.cantidadIngrediente = cantidadIngrediente;
    }

    public String getNombreIngrediente() {
        return nombreIngrediente;
    }

    public void setNombreIngrediente(String nombreIngrediente) {
        this.nombreIngrediente = nombreIngrediente;
    }

    public float getCosteIngrediente() {
        return costeIngrediente;
    }

    public void setCosteIngrediente(float costeIngrediente) {
        this.costeIngrediente = costeIngrediente;
    }
    
    
    
}
