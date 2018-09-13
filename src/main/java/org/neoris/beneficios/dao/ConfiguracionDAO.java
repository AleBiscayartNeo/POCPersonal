package org.neoris.beneficios.dao;

import java.util.ArrayList;
import org.neoris.beneficios.entity.Configuracion;

public interface ConfiguracionDAO {

	public Configuracion getConfiguracion(Integer configuracionId);
	public ArrayList<Configuracion> getConfiguraciones();
	
}
