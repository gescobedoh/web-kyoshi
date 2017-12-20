package com.kyoshi.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="usuario")
public class Usuario implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuario;
    
    @Column(name = "correoUsuario", length = 50, nullable = false)
    private String correoUsuario;
    
    @Column(name = "passwordUsuario", length = 50, nullable = false)
    private String passwordUsuario;
    
    @Column(name = "nombreUsuario", length = 50, nullable = false)
    private String nombreUsuario;
    
    @Column(name = "apellidoUsuario", length = 50, nullable = false)
    private String apellidoUsuario;
    
    @Column(name = "telefonoUsuario", length = 50, nullable = false)
    private String telefonoUsuario;
    
    @JoinColumn(name = "idTipo",nullable = false)
    private Tipo tipo;
       
    @OneToMany(mappedBy = "usuario", cascade = {CascadeType.ALL})
    @JoinColumn(name = "iddireccion")
    private List<Direccion> lista;
      
    public Usuario() {
    }    

    public Usuario(int idUsuario, String correoUsuario, String passwordUsuario, String nombreUsuario, String apellidoUsuario, String telefonoUsuario, Tipo tipo, List<Direccion> lista) {
        this.idUsuario = idUsuario;
        this.correoUsuario = correoUsuario;
        this.passwordUsuario = passwordUsuario;
        this.nombreUsuario = nombreUsuario;
        this.apellidoUsuario = apellidoUsuario;
        this.telefonoUsuario = telefonoUsuario;
        this.tipo = tipo;
        this.lista = lista;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public String getPasswordUsuario() {
        return passwordUsuario;
    }

    public void setPasswordUsuario(String passwordUsuario) {
        this.passwordUsuario = passwordUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getApellidoUsuario() {
        return apellidoUsuario;
    }

    public void setApellidoUsuario(String apellidoUsuario) {
        this.apellidoUsuario = apellidoUsuario;
    }

    public String getTelefonoUsuario() {
        return telefonoUsuario;
    }

    public void setTelefonoUsuario(String telefonoUsuario) {
        this.telefonoUsuario = telefonoUsuario;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public List<Direccion> getLista() {
        return lista;
    }

    public void setLista(List<Direccion> lista) {
        this.lista = lista;
    }
}
    
    