package com.kyoshi.controlador;

import com.kyoshi.dao.TipoDao;
import com.kyoshi.dao.TipoImpl;
import com.kyoshi.dao.UsuarioDao;
import com.kyoshi.dao.UsuarioImpl;
import com.kyoshi.entidades.Direccion;
import com.kyoshi.entidades.Tipo;
import com.kyoshi.entidades.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped 
public class UsuarioBean implements Serializable{
    private UsuarioDao dao;
    private TipoDao daoTip;
    private List<Usuario> listaUsuarios;
    private List<Tipo> listaTipos;
    private List<Direccion> listaDirecciones;
    private Usuario usuario;
    private Tipo tipo;
    private Direccion direccion;
    private String titulo = "Nuevo";
    private Usuario usbuscado;
    
    public UsuarioBean(){
        dao = new UsuarioImpl();
        listaUsuarios = new ArrayList<>();
        usuario = new Usuario();
        daoTip = new TipoImpl();
        tipo = new Tipo();
        listaTipos = new ArrayList<>();
        listaDirecciones = new ArrayList<>();
        direccion = new Direccion();
        usbuscado=new Usuario();
        
        listar();
        listarTipos();
    }
    
    public void listar(){
        listaUsuarios = dao.Listar();
    }
    public void eliminar(int idUsuario){
        dao.eliminar(idUsuario);
        listar();
    }
    
    public void agregar(){
        Boolean seagrega = true;
        String calle = direccion.getCalledirec();
        String distrito = direccion.getDistritodirec();
        int nro = direccion.getNrodirec();
        int dpto = direccion.getDptodirec();
        
        for (int i = 0; i < listaDirecciones.size(); i++) {
            if(listaDirecciones.get(i).getDistritodirec().equalsIgnoreCase(distrito) &&
               listaDirecciones.get(i).getCalledirec().equalsIgnoreCase(calle) &&
               listaDirecciones.get(i).getNrodirec()==nro && listaDirecciones.get(i).getDptodirec()==dpto)
                seagrega=false;
        }
        
        if(listaDirecciones.size()<3 && seagrega==true){
            Direccion dir = new Direccion();
            dir.setCalledirec(direccion.getCalledirec());
            dir.setDistritodirec(direccion.getDistritodirec());
            dir.setNrodirec(direccion.getNrodirec());
            dir.setDptodirec(direccion.getDptodirec());
            dir.setUsuario(usuario);
            
            listaDirecciones.add(dir);                
        }        
    }
    public void eliminardir(Direccion dir){        
        for (int i = 0; i < listaDirecciones.size(); i++) {
            String calle = listaDirecciones.get(i).getCalledirec();
            String distrito = listaDirecciones.get(i).getDistritodirec();
            int nro = listaDirecciones.get(i).getNrodirec();
            int dpto = listaDirecciones.get(i).getDptodirec();
            if(calle.equalsIgnoreCase(dir.getCalledirec()) && nro==dir.getNrodirec() && dpto==dir.getDptodirec() && distrito.equalsIgnoreCase(dir.getDistritodirec()))
                listaDirecciones.remove(i);
        }
        if (dir.getIddireccion()!=0) {
            dao.eliminarDir(dir.getIddireccion());
        }
    }
    
    public void limpiar(){
        this.usuario = new Usuario();
        listaTipos = new ArrayList<>();
        listaUsuarios = new ArrayList<>();
        listaDirecciones = new ArrayList<>();
        usuario = new Usuario();
        tipo = new Tipo();
        direccion = new Direccion();
        titulo = "Nuevo";
    }
    public void listarTipos(){
        listaTipos = daoTip.Listar();
    }
    public void listarId(int idUsuario){
        usuario = dao.ListarId(idUsuario);
        listaDirecciones=dao.listaDireccion(idUsuario);
        titulo = "Actualizar";
    }
    
    public void operar() {
        usuario.setLista(listaDirecciones);
        if (usuario.getIdUsuario()> 0) {
            dao.actualizar(usuario);
        } else {
            dao.insertar(usuario);
        }
    }
    
    public void operarRegistrar(){
        usuario.setLista(listaDirecciones);
        usuario.setTipo(listaTipos.get(1));
        dao.insertar(usuario);
    }
    
    
    
    //Para comenzar a insertar al carrito a una cuenta temporal de invitado
    public void operarSinUsuario(){
        usuario.setNombreUsuario("Cliente");
        usuario.setApellidoUsuario("Invitado");
        usuario.setCorreoUsuario("CI");
        usuario.setPasswordUsuario("guest");
        usuario.setTelefonoUsuario("0000000");
        usuario.setTipo(listaTipos.get(2));
        dao.insertar(usuario);
    }
    public void ValoresRealesInvitado(){ //colocar los valores reales a la cuenta de invitado
        dao.actualizar(usuario);
    }
    
    
    
    public String login(){
        Usuario u = new Usuario();
        String redireccion=null;
        try{
            u = dao.login(usuario);
            if(u.getIdUsuario()!=0){
                //Almacenar
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", u);
                if(u.getTipo().getIdTipo()==1){
                    redireccion="/ingrediente?faces-redirect=true";
                } else{
                    redireccion="/index_logeado?faces-redirect=true";
                }                
            } else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Aviso","Credenciales incorrectas"));                
            }                
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return redireccion;
    }
    
    public String usuariologeado(){
        Usuario us = new Usuario();
        String logeado;
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            us = (Usuario) context.getExternalContext().getSessionMap().get("usuario");            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        logeado=us.getNombreUsuario()+" "+us.getApellidoUsuario();
        return logeado;
    }
    public Usuario sacarusuario(){
        Usuario us = new Usuario();
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            us = (Usuario) context.getExternalContext().getSessionMap().get("usuario");            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return us;
    }
    
    public Usuario currentUser(){
        Usuario us = new Usuario();        
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            us = (Usuario) context.getExternalContext().getSessionMap().get("usuario");            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }        
        return us;
    }
    public void setUsuarioLogeado()
    {
        setUsuario(currentUser());
        
    }        
    public void verificarSesion(){
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            Usuario us = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
            if(us==null){
                context.getExternalContext().redirect("index.xhtml");                
            } else if(us.getTipo().getIdTipo()!=1){
                context.getExternalContext().redirect("index_logeado.xhtml");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void cerrarSesion(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }
    
    public void busqueda(){
        listaUsuarios=dao.Buscar(usbuscado.getNombreUsuario());
    }
    
    
    

    public UsuarioDao getDao() {
        return dao;
    }

    public void setDao(UsuarioDao dao) {
        this.dao = dao;
    }

    public TipoDao getDaoTip() {
        return daoTip;
    }

    public void setDaoTip(TipoDao daoTip) {
        this.daoTip = daoTip;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public List<Tipo> getListaTipos() {
        return listaTipos;
    }

    public void setListaTipos(List<Tipo> listaTipos) {
        this.listaTipos = listaTipos;
    }

    public List<Direccion> getListaDirecciones() {
        return listaDirecciones;
    }

    public void setListaDirecciones(List<Direccion> listaDirecciones) {
        this.listaDirecciones = listaDirecciones;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Usuario getUsbuscado() {
        return usbuscado;
    }

    public void setUsbuscado(Usuario usbuscado) {
        this.usbuscado = usbuscado;
    }
}


