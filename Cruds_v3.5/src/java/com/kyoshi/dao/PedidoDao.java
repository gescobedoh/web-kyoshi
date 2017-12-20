package com.kyoshi.dao;

import com.kyoshi.entidades.Detalle;
import com.kyoshi.entidades.Pedido;
import java.util.List;

public interface PedidoDao {
    public void insertar(Pedido Pedido);
    public void eliminar(int idPedido);
    public void actualizar(Pedido Pedido);
    public List<Pedido>Listar();
    public Pedido ListarId(int idPedido);
    public float CalcularMonto(int idpedido);
    public List<Detalle> listaDetalle(int idped);
    public void eliminarpro(int idpro);
}
