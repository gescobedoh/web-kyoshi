/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kyoshi.controlador;

import com.kyoshi.dao.PedidoDao;
import com.kyoshi.dao.PedidoImpl;
import com.kyoshi.dao.ProductoDao;
import com.kyoshi.dao.ProductoImpl;
import com.kyoshi.dao.UsuarioDao;
import com.kyoshi.dao.UsuarioImpl;
import com.kyoshi.entidades.Detalle;
import com.kyoshi.entidades.Pedido;
import com.kyoshi.entidades.Producto;
import com.kyoshi.entidades.Usuario;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

@ManagedBean
@ViewScoped
public class PedidoBean implements Serializable{
    private UsuarioBean ub;
    private UsuarioDao udao;
    private PedidoDao pedao;
    private ProductoDao prdao;
    private Usuario us;
    private List<Pedido>listapedidos;
    private List<Producto>listaproductos;
    private List<Detalle>listaSeleccion;
    private List<Detalle>listaCarrito;
    private Pedido pedido;
    private Producto producto;
    private Detalle detalle;
    private String titulo = "Nuevo";
    private List<String>listapromociones;
    private Pedido pedbuscado;
    
    public PedidoBean(){
        pedao=new PedidoImpl();
        prdao=new ProductoImpl();
        listapedidos=new ArrayList<>();
        listaproductos=new ArrayList<>();
        pedido=new Pedido();
        producto =new Producto();
        us=new Usuario();
        detalle=new Detalle();
        listaSeleccion=new ArrayList<>();
        listaCarrito=new ArrayList<>();
        udao=new UsuarioImpl();
        
        listapromociones=new ArrayList<>();
        listapromociones.add("NADA");
        listapromociones.add("2x1");
        listapromociones.add("3x2");
        
        listar();
        listarproductos();
    }
    
    public void listar() {
        listapedidos = pedao.Listar();
    }
    
    public void eliminar(int idped) {
        pedao.eliminar(idped);
        listar();
    }
    
    //Esto hace con el calendar
    public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }
     
    public void click() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
         
        requestContext.update("form:display");
        requestContext.execute("PF('dlg').show()");
    }
    
    
    public void agregar() {
        Boolean seagrega=true;
        
        for (int i = 0; i < listaSeleccion.size(); i++) {
            if(listaSeleccion.get(i).getProducto()==producto)
                seagrega=false;
        }
        
        if(seagrega==true && detalle.getCantidadProducto()>0){
            Detalle det = new Detalle();
            det.setCantidadProducto(detalle.getCantidadProducto());
            det.setPedido(pedido);
            det.setProducto(producto);
            listaSeleccion.add(det);
        }        
    }
    public void eliminarpro(Detalle det){
        for (int i = 0; i < listaSeleccion.size(); i++) {
            if(listaSeleccion.get(i).getProducto().getNombreProducto().equals(det.getProducto().getNombreProducto()))
                listaSeleccion.remove(i);
        }
        if(det.getIdDetalle()!=0){
            pedao.eliminarpro(det.getIdDetalle());
        }
    }
    
    public void limpiar() {
        this.pedido = new Pedido();
        listapedidos = new ArrayList<>();
        listaproductos = new ArrayList<>();
        listaSeleccion = new ArrayList<>();
        listaCarrito=new ArrayList<>();
        detalle = new Detalle();
        producto = new Producto();
        us=new Usuario();
        udao=new UsuarioImpl();
        titulo = "Nuevo";
    }
    
    public void listarproductos() {
        listaproductos = prdao.Listar();
    }

    public void listarId(int idped) {
        pedido = pedao.ListarId(idped);
        listaSeleccion=pedao.listaDetalle(idped);
        titulo="Actualizar";
    }
    
    public void operar() {
        pedido.setLista(listaSeleccion);
        if(pedido.getIdPedido()>0){
            pedao.actualizar(pedido);
        }else{
            pedao.insertar(pedido);
        }
    }
    public void operardelcarrito(Usuario usi) {
        pedido.setLista(listaSeleccion);
        pedido.setPromocionPedido("NADA");
        Date fecha = new Date();
        pedido.setFechaPedido(fecha);
        pedido.setUsuario(usi);
        pedido.setAtenderPedido(Boolean.TRUE);
        pedao.insertar(pedido);
    }
    
    /*
    //Para trabajar con el carrito
    public void operarAlCarrito(){
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            Usuario us = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
            if(us==null){
                //llamar  UsuarioBean operarSinUsuario() y luego login() si es que no hay usuario logeado
                ub.operarSinUsuario();
                ub.login();
                //creas usuario invitado
            }
            //esto se manda al carrito con un valor inicial de 1. Ya en el carrito se puede cambiar
            Detalle det = new Detalle();
            det.setCantidadProducto(1);
            det.setPedido(pedido);
            det.setProducto(producto);
            listaSeleccion.add(det);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    //listar en el carrito
    public void listarEnCarrito(){
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            Usuario us = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
            for (int i = 0; i < listaSeleccion.size(); i++) {
                if(listaSeleccion.get(i).getPedido().getUsuario()==us)
                    listaCarrito.add(listaSeleccion.get(i));
            }            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    //insertar el pedido
    public void HacerPedido(){
        if(pedido.getUsuario().getTelefonoUsuario()!="0000000"){
            pedido.setAtenderPedido(Boolean.TRUE);
            Date fecha = new Date();
            pedido.setFechaPedido(fecha);
            pedao.actualizar(pedido);
        }        
    }
    */
    
    
    public UsuarioBean getUb() {
        return ub;
    }

    public void setUb(UsuarioBean ub) {
        this.ub = ub;
    }

    public PedidoDao getPedao() {
        return pedao;
    }

    public void setPedao(PedidoDao pedao) {
        this.pedao = pedao;
    }

    public ProductoDao getPrdao() {
        return prdao;
    }

    public void setPrdao(ProductoDao prdao) {
        this.prdao = prdao;
    }

    public Usuario getUs() {
        return us;
    }

    public void setUs(Usuario us) {
        this.us = us;
    }

    public List<Pedido> getListapedidos() {
        return listapedidos;
    }

    public void setListapedidos(List<Pedido> listapedidos) {
        this.listapedidos = listapedidos;
    }

    public List<Producto> getListaproductos() {
        return listaproductos;
    }

    public void setListaproductos(List<Producto> listaproductos) {
        this.listaproductos = listaproductos;
    }

    public List<Detalle> getListaSeleccion() {
        return listaSeleccion;
    }

    public void setListaSeleccion(List<Detalle> listaSeleccion) {
        this.listaSeleccion = listaSeleccion;
    }

    public List<Detalle> getListaCarrito() {
        return listaCarrito;
    }

    public void setListaCarrito(List<Detalle> listaCarrito) {
        this.listaCarrito = listaCarrito;
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

    public Detalle getDetalle() {
        return detalle;
    }

    public void setDetalle(Detalle detalle) {
        this.detalle = detalle;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<String> getListapromociones() {
        return listapromociones;
    }

    public void setListapromociones(List<String> listapromociones) {
        this.listapromociones = listapromociones;
    }

    public Pedido getPedbuscado() {
        return pedbuscado;
    }

    public void setPedbuscado(Pedido pedbuscado) {
        this.pedbuscado = pedbuscado;
    }

    public UsuarioDao getUdao() {
        return udao;
    }

    public void setUdao(UsuarioDao udao) {
        this.udao = udao;
    }

}
