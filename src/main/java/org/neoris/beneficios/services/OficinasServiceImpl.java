package org.neoris.beneficios.services;

import java.util.ArrayList;

import org.neoris.beneficios.dao.OficinaDAO;
import org.neoris.beneficios.entity.Oficina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("oficinasService")
@Transactional(propagation = Propagation.REQUIRED)
public class OficinasServiceImpl implements OficinasService {

	@Autowired(required = true)
	private OficinaDAO oficinaDAO;

	public ArrayList<Oficina> getOficinas() {
		return oficinaDAO.getOficinas();

	}

}
