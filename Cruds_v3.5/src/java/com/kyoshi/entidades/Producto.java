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
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;


@Entity
@Table(name="producto")
public class Producto implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProducto;
    
    @Column(name="nombreProducto",length = 50, nullable = false)
    private String nombreProducto;
    
    @Column(name="precioProducto", precision=11, scale=2 )
    private float precioProducto;
    
    @Column(name="tipoProducto",length = 50, nullable = false)
    private String tipoProducto;
    
    private byte[] fotoProducto;
    
    @OneToMany(mappedBy = "producto", cascade = {CascadeType.ALL})
    @JoinColumn(name = "iddetallep")
    private List<DetalleP> lista;
    
    
    public Producto(){}    

    public Producto(int idProducto, String nombreProducto, float precioProducto, String tipoProducto, byte[] fotoProducto, List<DetalleP> lista) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.precioProducto = precioProducto;
        this.tipoProducto = tipoProducto;
        this.fotoProducto = fotoProducto;
        this.lista = lista;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public float getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(float precioProducto) {
        this.precioProducto = precioProducto;
    }

    public String getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public byte[] getFotoProducto() {
        return fotoProducto;
    }

    public void setFotoProducto(byte[] fotoProducto) {
        this.fotoProducto = fotoProducto;
    }

    public List<DetalleP> getLista() {
        return lista;
    }

    public void setLista(List<DetalleP> lista) {
        this.lista = lista;
    }

    
}
