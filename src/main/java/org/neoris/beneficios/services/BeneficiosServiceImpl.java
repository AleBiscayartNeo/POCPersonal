package org.neoris.beneficios.services;

import java.util.ArrayList;

import org.neoris.beneficios.dao.BeneficioDAO;
import org.neoris.beneficios.dao.BeneficioDAOImpl;
import org.neoris.beneficios.dao.BeneficioOficinaDAO;
import org.neoris.beneficios.dao.CategoriaDAO;
import org.neoris.beneficios.dto.BeneficioCategoriaDTO;
import org.neoris.beneficios.entity.Beneficio;
import org.neoris.beneficios.entity.BeneficioOficina;
import org.neoris.beneficios.entity.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("beneficiosService")
@Transactional(propagation = Propagation.REQUIRED)
public class BeneficiosServiceImpl implements BeneficiosService {

	@Autowired(required = true)
	private BeneficioDAO beneficioDAO;


	@Autowired(required = true)
	private BeneficioOficinaDAO beneficioOficinaDAO;

	@Autowired(required = true)
	private CategoriaDAO categoriaDAO;

	public ArrayList<Beneficio> getBeneficios() {
		return beneficioDAO.getBeneficios();
	}
	
	public ArrayList<Beneficio> getBeneficiosCategoria(Integer categoriaId) {
		return beneficioDAO.getBeneficiosCategoria(categoriaId);
	}

	public ArrayList<Beneficio> getBeneficiosOficina(Integer categoriaId,
			Integer oficinaId) {
		return beneficioOficinaDAO.getBeneficios(categoriaId, oficinaId);
	}

	public ArrayList<BeneficioCategoriaDTO> getCantidadPorCategoria(Integer idOficina) {

		ArrayList<Categoria> categorias = categoriaDAO.getCategorias();
		ArrayList<BeneficioCategoriaDTO> listaBeneficiosArrayList = null;

		if (categorias != null) {

			listaBeneficiosArrayList = new ArrayList<BeneficioCategoriaDTO>();
			BeneficioCategoriaDTO beneficioCategoriaDTO = null;
			Long cantidad = (long) 0;

			for (Categoria categoria : categorias) {
				
				cantidad = beneficioOficinaDAO.getBeneficiosCategoria(categoria
						.getIdCategoria(),idOficina);


				beneficioCategoriaDTO = new BeneficioCategoriaDTO();
				beneficioCategoriaDTO.setCantidad(cantidad);
				beneficioCategoriaDTO.setIdOficina(idOficina);
				beneficioCategoriaDTO
						.setIdCategoria(categoria.getIdCategoria());
				beneficioCategoriaDTO.setNombreCategoria(categoria
						.getNombreCategoria());

				listaBeneficiosArrayList.add(beneficioCategoriaDTO);

			}

		}
		return listaBeneficiosArrayList;
	}

}
