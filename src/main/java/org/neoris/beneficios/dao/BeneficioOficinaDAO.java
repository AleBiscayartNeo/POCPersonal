package org.neoris.beneficios.dao;

import java.util.ArrayList;

import org.neoris.beneficios.entity.Beneficio;
import org.neoris.beneficios.entity.BeneficioOficina;

public interface BeneficioOficinaDAO {

	public BeneficioOficina getBeneficio(int idBeneficio);
	public ArrayList<Beneficio> getBeneficios(Integer categoriaId, Integer oficinaId);
	public Long getBeneficiosCategoria(Integer categoriaId, Integer oficinaId);
}
