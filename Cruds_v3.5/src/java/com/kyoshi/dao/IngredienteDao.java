package com.kyoshi.dao;

import com.kyoshi.entidades.Ingrediente;
import java.util.List;

public interface IngredienteDao {
    public void insertar(Ingrediente Ingrediente);
    public void eliminar(int idIngrediente);
    public void actualizar(Ingrediente Ingrediente);
    public List<Ingrediente>Listar();
    public Ingrediente ListarId(int idIngrediente);
    public List<Ingrediente>busqueda(String nombreing);
}
