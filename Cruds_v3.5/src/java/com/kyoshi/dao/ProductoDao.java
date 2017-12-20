package com.kyoshi.dao;

import com.kyoshi.entidades.DetalleP;
import com.kyoshi.entidades.Producto;
import java.util.List;

public interface ProductoDao {
    public void insertar(Producto Producto);
    public void eliminar(int idProducto);
    public void actualizar(Producto Producto);
    public List<Producto>Listar();
    public Producto ListarId(int idProducto);
    public List<Producto>Buscar(String nombreproduc);
    public List<DetalleP>listaDetalleP(int idpro);
    public void eliminaring(int iding);
}
