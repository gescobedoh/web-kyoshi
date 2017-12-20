package com.kyoshi.dao;

import com.kyoshi.entidades.Ingrediente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class IngredienteImpl implements IngredienteDao{
    private EntityManagerFactory factoria;
    private EntityManager manager;

    public IngredienteImpl() {
        factoria = Persistence.createEntityManagerFactory("KyoshiPU");
        manager = factoria.createEntityManager();
    }
    
    @Override
    public void insertar(Ingrediente ingrediente) {
        try {
            manager.getTransaction().begin();
            manager.persist(ingrediente);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void eliminar(int idIngrediente) {
    
        Ingrediente i = new Ingrediente();
        try {
            manager.getTransaction().begin();
            i = manager.getReference(Ingrediente.class, idIngrediente);
            manager.remove(i);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void actualizar(Ingrediente ingrediente) {
    try {
            manager.getTransaction().begin();
            manager.merge(ingrediente);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public List<Ingrediente> Listar() {
        List<Ingrediente> lista = new ArrayList<>();
        try {
            Query q = manager.createQuery("from Ingrediente i");
            lista = (List<Ingrediente>) q.getResultList();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return lista;

    }

    @Override
    public Ingrediente ListarId(int idIngrediente) {
        Ingrediente i = new Ingrediente();
        try {
            i = manager.find(Ingrediente.class, idIngrediente);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return i;
    }

    @Override
    public List<Ingrediente> busqueda(String nombreing) {
        List<Ingrediente>busqueda=new ArrayList<>();
        try{
            Query q = manager.createQuery("from Ingrediente i where i.nombreIngrediente like ?1");
            q.setParameter(1, nombreing+"%");
            busqueda=(List<Ingrediente>)q.getResultList();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return busqueda;
    }
   
}
