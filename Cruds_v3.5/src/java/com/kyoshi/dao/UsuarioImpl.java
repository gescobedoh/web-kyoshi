package com.kyoshi.dao;

import com.kyoshi.entidades.Direccion;
import com.kyoshi.entidades.Tipo;
import com.kyoshi.entidades.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class UsuarioImpl implements UsuarioDao{
    
    private EntityManagerFactory factoria;
    private EntityManager manager;

    public UsuarioImpl() {
        factoria = Persistence.createEntityManagerFactory("KyoshiPU");
        manager = factoria.createEntityManager();
    }
    

    public Usuario login(Usuario us) {
        
        Usuario u = new Usuario();
        List<Usuario> lista = new ArrayList<>();
        try {
            Query q = manager.createQuery("from Usuario u where u.correoUsuario=?1 and u.passwordUsuario=?2");
            q.setParameter(1, us.getCorreoUsuario());
            q.setParameter(2, us.getPasswordUsuario());
            lista = (List<Usuario>) q.getResultList();
            if (lista != null && !lista.isEmpty()) {
                u = lista.get(0);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return u;        
    }
    

    public void insertar(Usuario usuario) {
        try {
            manager.getTransaction().begin();
            manager.persist(usuario);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }


    public void eliminar(int idUsuario) {    
        Usuario u = new Usuario();
        try {
            manager.getTransaction().begin();
            u = manager.getReference(Usuario.class, idUsuario);
            manager.remove(u);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }


    public void actualizar(Usuario usuario) {
    try {
            manager.getTransaction().begin();
            manager.merge(usuario);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Usuario> Listar() {
        List<Usuario> lista = new ArrayList<>();
        try {
            Query q = manager.createQuery("from Usuario u");
            lista = (List<Usuario>) q.getResultList();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return lista;
    }

    public Usuario ListarId(int idUsuario) {
        Usuario u = new Usuario();
        try {
            u = manager.find(Usuario.class, idUsuario);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return u;
    }   

    @Override
    public List<Usuario> Buscar(String nombreUsuario) {
        List<Usuario>busqueda=new ArrayList<>();
        try{
            Query q = manager.createQuery("from Usuario us where us.nombreUsuario like ?1");
            q.setParameter(1, nombreUsuario+"%");
            busqueda=(List<Usuario>)q.getResultList();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return busqueda;
    }

    @Override
    public List<Direccion> listaDireccion(int idUsuario) {    
        List<Direccion> listaDireccion = new ArrayList<>();
        try{
            Query q = manager.createQuery("from Direccion d where d.usuario.idUsuario=?1");
            q.setParameter(1, idUsuario);
            listaDireccion = (List<Direccion>) q.getResultList();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return listaDireccion;
    }
    
    @Override
    public void eliminarDir(int iddir){
        Direccion dir = new Direccion();
        try {
            manager.getTransaction().begin();
            dir = manager.getReference(Direccion.class, iddir);
            manager.remove(dir);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
