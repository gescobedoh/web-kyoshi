package com.kyoshi.dao;

import com.kyoshi.entidades.Tipo;
import java.util.List;

public interface TipoDao {
    public void insertar(Tipo Tipo);
    public void eliminar(int idTipo);
    public void actualizar(Tipo Tipo);
    public List<Tipo>Listar();
    public Tipo ListarId(int idTipo);
}
