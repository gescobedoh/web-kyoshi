/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kyoshi.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "DetalleP")
public class DetalleP implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int iddetallep;
    
    @ManyToOne
    @JoinColumn(name = "idProducto",nullable = false)
    private Producto producto;
    
    @ManyToOne
    @JoinColumn(name = "idIngrediente",nullable = false)
    private Ingrediente ingrediente;
    
    @Column(name = "cantIng",nullable = false)
    private float cantIng;
    
    public DetalleP(){}

    public DetalleP(int iddetallep, Producto producto, Ingrediente ingrediente, float cantIng) {
        this.iddetallep = iddetallep;
        this.producto = producto;
        this.ingrediente = ingrediente;
        this.cantIng = cantIng;
    }

    public int getIddetallep() {
        return iddetallep;
    }

    public void setIddetallep(int iddetallep) {
        this.iddetallep = iddetallep;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Ingrediente getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(Ingrediente ingrediente) {
        this.ingrediente = ingrediente;
    }

    public float getCantIng() {
        return cantIng;
    }

    public void setCantIng(float cantIng) {
        this.cantIng = cantIng;
    }

    
}
