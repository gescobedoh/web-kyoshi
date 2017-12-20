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
@Table(name = "Direccion")
public class Direccion implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int iddireccion;
    @ManyToOne
    @JoinColumn(name = "idUsuario",nullable = false)
    private Usuario usuario;
    @Column(name = "distritodirec",nullable = false)
    private String distritodirec;
    @Column(name = "calledirec",nullable = false)
    private String calledirec;
    @Column(name = "nrodirec",nullable = false)
    private int nrodirec;
    @Column(name = "dptodirec")
    private int dptodirec;
    
    public Direccion(){}

    public Direccion(int iddireccion, Usuario usuario,String distritodirec, String calledirec, int nrodirec, int dptodirec) {
        this.iddireccion = iddireccion;
        this.usuario = usuario;
        this.distritodirec = distritodirec;
        this.calledirec = calledirec;
        this.nrodirec = nrodirec;
        this.dptodirec = dptodirec;
    }

    public int getIddireccion() {
        return iddireccion;
    }

    public void setIddireccion(int iddireccion) {
        this.iddireccion = iddireccion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getDistritodirec() {
        return distritodirec;
    }

    public void setDistritodirec(String distritodirec) {
        this.distritodirec = distritodirec;
    }

    public String getCalledirec() {
        return calledirec;
    }

    public void setCalledirec(String calledirec) {
        this.calledirec = calledirec;
    }

    public int getNrodirec() {
        return nrodirec;
    }

    public void setNrodirec(int nrodirec) {
        this.nrodirec = nrodirec;
    }

    public int getDptodirec() {
        return dptodirec;
    }

    public void setDptodirec(int dptodirec) {
        this.dptodirec = dptodirec;
    }

    
}
