package com.kyoshi.dao;

import com.kyoshi.entidades.Tipo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class TipoImpl implements TipoDao{
    
    private EntityManagerFactory factoria;
    private EntityManager manager;

    public TipoImpl() {
        factoria = Persistence.createEntityManagerFactory("KyoshiPU");
        manager = factoria.createEntityManager();
    }
    
    public void insertar(Tipo tipo) {
     try {
            manager.getTransaction().begin();
            manager.persist(tipo);
            manager.getTransaction().commit();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }


    public void eliminar(int idTipo) {
        Tipo tip = new Tipo();
        try {
            manager.getTransaction().begin();
            tip = manager.getReference(Tipo.class, idTipo);
            manager.remove(tip);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }


    public void actualizar(Tipo tipo) {
        try {
            manager.getTransaction().begin();
            manager.merge(tipo);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    public List<Tipo> Listar() {
        List<Tipo> lista = new ArrayList<>();
        try {
            Query q = manager.createQuery("from Tipo ti");
            lista = (List<Tipo>) q.getResultList();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return lista;

    }

    public Tipo ListarId(int idTipo) {
        List<Tipo> lista = new ArrayList<>();
        Tipo tip = new Tipo();
        try {
            tip = manager.find(Tipo.class, idTipo);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return tip;
    }
}