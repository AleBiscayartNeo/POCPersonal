package org.neoris.beneficios.dao;

import java.util.ArrayList;
import org.neoris.beneficios.entity.Oficina;

public interface OficinaDAO {

	public Oficina getOficina(Integer oficinaId);
	public ArrayList<Oficina> getOficinas();
	
	
}
