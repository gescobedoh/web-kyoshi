package com.kyoshi.controlador;

import com.kyoshi.dao.TipoDao;
import com.kyoshi.dao.TipoImpl;
import com.kyoshi.entidades.Tipo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class TipoBean implements Serializable{
    private TipoDao dao;
    private List<Tipo> listaTipos;
    private String tituloDialogo = "Registrar Tipos";
    private Tipo tipo;
    
    public TipoBean(){
        dao = new TipoImpl();
        listaTipos = new ArrayList<>();
        tipo = new Tipo();
        listar();
    }
    public void listar(){
        listaTipos = dao.Listar();
    }
    public void agregar(Tipo tipo){
        tituloDialogo ="Modificar Tipo";
        this.tipo.setIdTipo(tipo.getIdTipo());
        this.tipo.setNombreTipo(tipo.getNombreTipo());
    }
    public void aceptar(){
        if (tipo.getIdTipo()> 0) {
            dao.actualizar(tipo);
        } else {
            dao.insertar(tipo);
        }
    }
    public void eliminar(Tipo tipo){
        dao.eliminar(tipo.getIdTipo());
        listar();
    }
    public void limpiar(){
        this.tipo=new Tipo();
        this.tituloDialogo="Nuevo Tipo";
    }

    public List<Tipo> getListaTipos() {
        return listaTipos;
    }

    public void setListaTipos(List<Tipo> listaTipos) {
        this.listaTipos = listaTipos;
    }

    public String getTituloDialogo() {
        return tituloDialogo;
    }

    public void setTituloDialogo(String tituloDialogo) {
        this.tituloDialogo = tituloDialogo;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
    
}
