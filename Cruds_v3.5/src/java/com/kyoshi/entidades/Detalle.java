package com.kyoshi.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import javax.persistence.OneToOne;


@Entity
@Table(name="Detalle")
public class Detalle implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idDetalle;        
    
    @ManyToOne
    @JoinColumn(name="idPedido",nullable=false)
    private Pedido pedido;
    
    @ManyToOne
    @JoinColumn(name="idProducto",nullable=false)
    private Producto producto;
    
    @Column (name="cantidadProducto", nullable = false)
    private int cantidadProducto;

    public Detalle() {
    }

    public Detalle(int idDetalle, Pedido pedido, Producto producto, int cantidadProducto) {
        this.idDetalle = idDetalle;
        this.pedido = pedido;
        this.producto = producto;
        this.cantidadProducto = cantidadProducto;
    }

    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(int cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }        
    
}
