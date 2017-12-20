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
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="pedido")
public class Pedido implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idPedido;
            
    @Column(name = "promocionPedido", length = 50)
    private String promocionPedido;
    
    @ManyToOne
    @JoinColumn(name="idUsuario", nullable=false)
    private Usuario usuario;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "fechaPedido", length = 50, nullable = false)
    private Date fechaPedido;
    
    @Column(name = "atenderPedido",nullable = false)
    private Boolean atenderPedido;
    
    @Column(name = "preciofPedido",nullable = false)
    private float preciofPedido;
    
    @OneToMany(mappedBy = "pedido", cascade = {CascadeType.ALL})
    @JoinColumn(name = "idDetalle")
    private List<Detalle> lista;
    
    public Pedido() {
    }

    public Pedido(int idPedido, String promocionPedido, Usuario usuario, Date fechaPedido, Boolean atenderPedido, float preciofPedido, List<Detalle> lista) {
        this.idPedido = idPedido;
        this.promocionPedido = promocionPedido;
        this.usuario = usuario;
        this.fechaPedido = fechaPedido;
        this.atenderPedido = atenderPedido;
        this.preciofPedido = preciofPedido;
        this.lista = lista;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public String getPromocionPedido() {
        return promocionPedido;
    }

    public void setPromocionPedido(String promocionPedido) {
        this.promocionPedido = promocionPedido;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public Boolean getAtenderPedido() {
        return atenderPedido;
    }

    public void setAtenderPedido(Boolean atenderPedido) {
        this.atenderPedido = atenderPedido;
    }

    public float getPreciofPedido() {
        return preciofPedido;
    }

    public void setPreciofPedido(float preciofPedido) {
        this.preciofPedido = preciofPedido;
    }

    public List<Detalle> getLista() {
        return lista;
    }

    public void setLista(List<Detalle> lista) {
        this.lista = lista;
    }
    
}
