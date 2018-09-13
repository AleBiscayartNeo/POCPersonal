package org.neoris.beneficios.dao;

import org.neoris.beneficios.entity.Beneficio;
import org.neoris.beneficios.entity.BeneficioSeniority;

public interface BeneficioSeniorityDAO {
	
	
	public BeneficioSeniority getBeneficioSeniority(Integer idBeneficio);
	public Beneficio getBeneficiosSeniority(Integer idSeniority);
	
}
