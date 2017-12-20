package com.kyoshi.dao;

import com.kyoshi.entidades.Detalle;
import com.kyoshi.entidades.Pedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.kyoshi.entidades.Producto;
import com.kyoshi.entidades.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class PedidoImpl implements PedidoDao{
    private EntityManagerFactory factoria;
    private EntityManager manager;

    public PedidoImpl() {
        factoria = Persistence.createEntityManagerFactory("KyoshiPU");
        manager = factoria.createEntityManager();
    }
    
    @Override
    public void insertar(Pedido pedido) {
        try {
            manager.getTransaction().begin();
            manager.persist(pedido);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void eliminar(int idPedido) {    
        Pedido p = new Pedido();
        try {
            manager.getTransaction().begin();
            p = manager.getReference(Pedido.class, idPedido);
            manager.remove(p);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void actualizar(Pedido pedido) {
    try {
            manager.getTransaction().begin();
            manager.merge(pedido);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public List<Pedido> Listar() {
        List<Pedido> lista = new ArrayList<>();
        try {
            Query q = manager.createQuery("from Pedido p ORDER BY p.fechaPedido asc");
            lista = (List<Pedido>) q.getResultList();
            sumatoria(lista);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return lista;
    }
    
    public void sumatoria(List<Pedido>lista){
        for (int i = 0; i < lista.size(); i++) {
            float suma=0;
            List<Detalle>lista2=new ArrayList<>();
            Query q=manager.createQuery("from Detalle det where det.pedido.idPedido=?1");
            q.setParameter(1, lista.get(i).getIdPedido());
            lista2=(List<Detalle>)q.getResultList();

            for (int j = 0; j < lista2.size(); j++) {
                suma=suma + lista2.get(j).getProducto().getPrecioProducto() * lista2.get(j).getCantidadProducto();
            }
            
            Pedido pedupdate = new Pedido();
            pedupdate.setIdPedido(lista.get(i).getIdPedido());
            pedupdate.setPromocionPedido(lista.get(i).getPromocionPedido());
            pedupdate.setUsuario(lista.get(i).getUsuario());
            pedupdate.setAtenderPedido(lista.get(i).getAtenderPedido());
            pedupdate.setFechaPedido(lista.get(i).getFechaPedido());
            pedupdate.setPreciofPedido(suma+6);

            manager.merge(pedupdate);
        }
    }

    @Override
    public Pedido ListarId(int idPedido) {
        Pedido p = new Pedido();
        try {
            p = manager.find(Pedido.class, idPedido);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return p;
    }
    
    @Override
    public float CalcularMonto(int idpedido) { 
        float monto=0;
        try
        {   
            Query q = manager.createQuery("SELECT SUM(d.cantidadProducto * d.producto.precioProducto) FROM Detalle d WHERE d.pedido.idPedido=?1 GROUP BY d.pedido.idPedido");            
            q.setParameter(1, idpedido);   
            monto= Float.parseFloat(q.getSingleResult().toString());
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return monto;
    }

    @Override
    public List<Detalle> listaDetalle(int idped) {
        List<Detalle> listadetalle = new ArrayList<>();
        try {
            Query q = manager.createQuery("from Detalle d where d.pedido.idPedido=?1");
            q.setParameter(1, idped);
            listadetalle = (List<Detalle>) q.getResultList();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return listadetalle;
    }   

    @Override
    public void eliminarpro(int iddet) {
        Detalle det = new Detalle();
        try {
            manager.getTransaction().begin();
            det = manager.getReference(Detalle.class, iddet);
            manager.remove(det);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
