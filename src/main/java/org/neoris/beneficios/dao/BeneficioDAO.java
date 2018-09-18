package org.neoris.beneficios.dao;

import java.util.ArrayList;

import org.neoris.beneficios.entity.Beneficio;


public interface BeneficioDAO {
	
	public Beneficio getBeneficio(Integer beneficioId);
	public ArrayList<Beneficio> getBeneficios();
	public ArrayList<Beneficio> getBeneficiosCategoria(Integer categoriaId);
	public long getCantidadPorCategoria(Integer categoriaId);

}
