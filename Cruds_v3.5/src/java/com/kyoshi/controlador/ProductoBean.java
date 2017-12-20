/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kyoshi.controlador;

import com.kyoshi.dao.IngredienteDao;
import com.kyoshi.dao.IngredienteImpl;
import com.kyoshi.dao.ProductoDao;
import com.kyoshi.dao.ProductoImpl;
import com.kyoshi.entidades.DetalleP;
import com.kyoshi.entidades.Ingrediente;
import com.kyoshi.entidades.Producto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.UploadedFile;

@ManagedBean
@ViewScoped
public class ProductoBean implements Serializable{
    private ProductoDao pdao;
    private IngredienteDao idao;
    private List<Producto>listaproductos;
    private List<Ingrediente>listaing;
    private Producto producto;
    private DetalleP dp;
    private Ingrediente ing;
    private List<DetalleP>listaSeleccion;
    private UploadedFile file;
    private String titulo = "Nuevo";
    private Producto probuscado;
    
    public ProductoBean(){
        pdao = new ProductoImpl();
        idao = new IngredienteImpl();
        listaproductos = new ArrayList<>();
        listaing = new ArrayList<>();
        producto = new Producto();
        dp = new DetalleP();
        ing = new Ingrediente();
        listaSeleccion = new ArrayList<>();
        probuscado=new Producto();
        
        
        listar();
        listarIngredientes();
    }
    
    public void listar() {
        listaproductos = pdao.Listar();
    }
    
    public void eliminar(int idpro) {
        pdao.eliminar(idpro);
        listar();
    }
    
    public void agregar() {
        Boolean seagrega = true;        
        
        for (int i = 0; i < listaSeleccion.size(); i++) {
            if(listaSeleccion.get(i).getIngrediente()==ing)
                seagrega=false;
        }
        
        if(seagrega==true && dp.getCantIng()>0){
            DetalleP dep = new DetalleP();
            dep.setCantIng(dp.getCantIng());
            dep.setIngrediente(ing);
            dep.setProducto(producto);
            listaSeleccion.add(dep);
        }            
    }
    public void eliminardetallep(DetalleP dp){
        for (int i = 0; i < listaSeleccion.size(); i++) {
            if(listaSeleccion.get(i).getIngrediente().getNombreIngrediente().equals(dp.getIngrediente().getNombreIngrediente()))
                listaSeleccion.remove(i);
        }
        if(dp.getIddetallep()!=0){
            pdao.eliminaring(dp.getIddetallep());
        }
    }

    public void limpiar() {
        this.producto = new Producto();
        listaproductos = new ArrayList<>();
        listaing = new ArrayList<>();
        listaSeleccion = new ArrayList<>();        
        dp = new DetalleP();
        ing = new Ingrediente();
        titulo = "Nuevo";
    }
    
    public void listarIngredientes() {
        listaing = idao.Listar();
    }

    public void listarId(int idProducto) {
        producto = pdao.ListarId(idProducto);
        listaSeleccion = pdao.listaDetalleP(idProducto);
        titulo = "Actualizar";
    }
    
    public void operar() {
        producto.setLista(listaSeleccion);
        if (file.getContents().length > 0) {
            producto.setFotoProducto(file.getContents());
        }
        if(producto.getIdProducto()>0){
            pdao.actualizar(producto);
        }else{
            pdao.insertar(producto);
        }
    }
    
    public void busqueda(){
        listaproductos = pdao.Buscar(probuscado.getNombreProducto());
    }

    public ProductoDao getPdao() {
        return pdao;
    }

    public void setPdao(ProductoDao pdao) {
        this.pdao = pdao;
    }

    public IngredienteDao getIdao() {
        return idao;
    }

    public void setIdao(IngredienteDao idao) {
        this.idao = idao;
    }

    public List<Producto> getListaproductos() {
        return listaproductos;
    }

    public void setListaproductos(List<Producto> listaproductos) {
        this.listaproductos = listaproductos;
    }

    public List<Ingrediente> getListaing() {
        return listaing;
    }

    public void setListaing(List<Ingrediente> listaing) {
        this.listaing = listaing;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public DetalleP getDp() {
        return dp;
    }

    public void setDp(DetalleP dp) {
        this.dp = dp;
    }

    public Ingrediente getIng() {
        return ing;
    }

    public void setIng(Ingrediente ing) {
        this.ing = ing;
    }

    public List<DetalleP> getListaSeleccion() {
        return listaSeleccion;
    }

    public void setListaSeleccion(List<DetalleP> listaSeleccion) {
        this.listaSeleccion = listaSeleccion;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Producto getProbuscado() {
        return probuscado;
    }

    public void setProbuscado(Producto probuscado) {
        this.probuscado = probuscado;
    }    
}
