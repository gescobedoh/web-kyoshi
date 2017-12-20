/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kyoshi.controlador;

import com.kyoshi.dao.IngredienteDao;
import com.kyoshi.dao.IngredienteImpl;
import com.kyoshi.entidades.Ingrediente;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean
@ViewScoped
public class IngredienteBean implements Serializable{
    private IngredienteDao idao;
    private List<Ingrediente> listaIngredientes;
    private String tituloDialogo = "Registrar Ingredientes";
    private Ingrediente ingrediente;
    private Ingrediente ingbuscado;
    
    /*
    <p:layoutUnit position="north">
        <h:form style="background-color: black">
                <p:messages autoUpdate="true" showDetail="true" />
                <p:outputLabel  value="  "/>
                <p:outputLabel value=" Bienvenido: #{usuarioBean.usuariologeado()}" style="color: black"/>      
                <p:commandButton actionListener ="#{usuarioBean.cerrarSesion()}" value="Cerrar Sesión" icon="ui-icon-power" style="float: right" action="index?faces-redirect=true" />
        </h:form>
    </p:layoutUnit>
    */
    
    public IngredienteBean(){
        idao = new IngredienteImpl();//cada vez que se invoque va a ser inciializado
        listaIngredientes = new ArrayList<>();
        ingrediente = new Ingrediente();
        ingbuscado=new Ingrediente();
        listar(); //apenas carga la pagina lista y llene el datatable
    }
    
    public void listar() {//es unmetodo
        listaIngredientes = idao.Listar();
    }
    
    public void agregar(Ingrediente ingrediente) {
        tituloDialogo = "Modificar Ingrendiente";
        this.ingrediente.setNombreIngrediente(ingrediente.getNombreIngrediente());
        this.ingrediente.setCosteIngrediente(ingrediente.getCosteIngrediente());
        this.ingrediente.setCantidadIngrediente(ingrediente.getCantidadIngrediente());
        this.ingrediente.setIdIngrediente(ingrediente.getIdIngrediente());
    }
    
    public void aceptar() {
        if(ingrediente.getCosteIngrediente()>0){
            if (ingrediente.getIdIngrediente()> 0) {
                idao.actualizar(ingrediente);
            } else {
                idao.insertar(ingrediente);
            }
        }
    }
    
    public void eliminar(Ingrediente ingrediente) {
        idao.eliminar(ingrediente.getIdIngrediente());
        listar();
    }
    
    public void limpiar() {
        this.ingrediente = new Ingrediente();
        this.tituloDialogo = "Nuevo Ingrediente";
    }
    
    public void buscar(){
        listaIngredientes=idao.busqueda(ingbuscado.getNombreIngrediente());
    }
    
    

    public IngredienteDao getIdao() {
        return idao;
    }

    public void setIdao(IngredienteDao idao) {
        this.idao = idao;
    }

    public List<Ingrediente> getListaIngredientes() {
        return listaIngredientes;
    }

    public void setListaIngredientes(List<Ingrediente> listaIngredientes) {
        this.listaIngredientes = listaIngredientes;
    }

    public String getTituloDialogo() {
        return tituloDialogo;
    }

    public void setTituloDialogo(String tituloDialogo) {
        this.tituloDialogo = tituloDialogo;
    }

    public Ingrediente getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(Ingrediente ingrediente) {
        this.ingrediente = ingrediente;
    }

    public Ingrediente getIngbuscado() {
        return ingbuscado;
    }

    public void setIngbuscado(Ingrediente ingbuscado) {
        this.ingbuscado = ingbuscado;
    }
}
