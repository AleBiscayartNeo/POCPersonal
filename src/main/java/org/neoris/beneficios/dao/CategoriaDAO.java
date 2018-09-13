package org.neoris.beneficios.dao;

import java.util.ArrayList;

import org.neoris.beneficios.entity.Beneficio;
import org.neoris.beneficios.entity.Categoria;

public interface CategoriaDAO {

	public Categoria getCategoria(Integer idCategoria);
	public ArrayList<Categoria> getCategorias();
	
}
