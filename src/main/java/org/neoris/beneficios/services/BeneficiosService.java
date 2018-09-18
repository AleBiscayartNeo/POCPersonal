package org.neoris.beneficios.services;

import java.util.ArrayList;

import org.neoris.beneficios.dto.BeneficioCategoriaDTO;
import org.neoris.beneficios.entity.Beneficio;
import org.neoris.beneficios.entity.BeneficioOficina;

public interface BeneficiosService {

	public ArrayList<Beneficio> getBeneficios();
	
	public ArrayList<Beneficio> getBeneficiosCategoria(Integer categoriaId);
	
	public ArrayList<Beneficio> getBeneficiosOficina(Integer categoriaId, Integer oficinaId);
	
	public ArrayList<BeneficioCategoriaDTO> getCantidadPorCategoria(Integer idOficina);

}
