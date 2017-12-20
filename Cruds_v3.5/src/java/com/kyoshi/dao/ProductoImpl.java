package com.kyoshi.dao;

import com.kyoshi.entidades.DetalleP;
import com.kyoshi.entidades.Producto;
import java.util.ArrayList;
import java.util.List;
import com.kyoshi.entidades.Ingrediente;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class ProductoImpl implements ProductoDao{
    private EntityManagerFactory factoria;
    private EntityManager manager;

    public ProductoImpl() {
        factoria = Persistence.createEntityManagerFactory("KyoshiPU");
        manager = factoria.createEntityManager();
    }
    
    @Override
    public void insertar(Producto producto) {
        try {
            manager.getTransaction().begin();
            manager.persist(producto);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void eliminar(int idProducto) {    
        Producto p = new Producto();
        try {
            manager.getTransaction().begin();
            p = manager.getReference(Producto.class, idProducto);
            manager.remove(p);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void actualizar(Producto producto) {
    try {
            manager.getTransaction().begin();
            manager.merge(producto);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public List<Producto> Listar() {
        List<Producto> lista = new ArrayList<>();
        try {
            Query q = manager.createQuery("from Producto p");
            lista = (List<Producto>) q.getResultList();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return lista;
    }

    @Override
    public Producto ListarId(int idProducto) {
        Producto p = new Producto();
        try {
            p = manager.find(Producto.class, idProducto);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return p;
    }

    @Override
    public List<Producto> Buscar(String nombreproduc) {
        List<Producto>busqueda=new ArrayList<>();
        try{
            Query q = manager.createQuery("from Producto pr where pr.nombreProducto like ?1");
            q.setParameter(1, nombreproduc+"%");
            busqueda=(List<Producto>)q.getResultList();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return busqueda;
    }

    @Override
    public List<DetalleP> listaDetalleP(int idpro) {
        List<DetalleP>lista=new ArrayList<>();
        try{
            Query q = manager.createQuery("from DetalleP dp where dp.producto.idProducto=?1");
            q.setParameter(1, idpro);
            lista = (List<DetalleP>) q.getResultList();
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        return lista;
    }

    @Override
    public void eliminaring(int iddp) {
        DetalleP dp = new DetalleP();
        try {
            manager.getTransaction().begin();
            dp = manager.getReference(DetalleP.class, iddp);
            manager.remove(dp);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
