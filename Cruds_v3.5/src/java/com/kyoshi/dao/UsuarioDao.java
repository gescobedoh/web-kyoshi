package com.kyoshi.dao;

import com.kyoshi.entidades.Direccion;
import com.kyoshi.entidades.Tipo;
import com.kyoshi.entidades.Usuario;
import java.util.List;

public interface UsuarioDao {
    public Usuario login (Usuario us);
    public void insertar(Usuario usuario);
    public void eliminar(int idUsuario);
    public void actualizar(Usuario usuario);
    public List<Usuario>Listar();
    public Usuario ListarId(int idUsuario);
    public List<Usuario>Buscar(String nombreUsuario);
    public List<Direccion> listaDireccion(int idUsuario);
    public void eliminarDir(int iddir);
}
