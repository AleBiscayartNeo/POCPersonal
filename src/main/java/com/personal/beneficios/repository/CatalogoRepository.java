package com.personal.beneficios.repository;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.personal.beneficios.dto.CatalogoPorNivelDTO;
import com.personal.beneficios.entity.Categoria;
import com.personal.beneficios.entity.Descuento;

@Service("catalogoRepository")
@Transactional(propagation=Propagation.REQUIRED)
public class CatalogoRepository {
	
	@PersistenceContext
	public EntityManager entityManager;
	
	@Transactional(readOnly=true)
	public CatalogoPorNivelDTO getCatalogoFiltrado(Integer idNivel, Integer idCategoria) {
		
		ArrayList<CatalogoPorNivelDTO> resultado = new ArrayList<CatalogoPorNivelDTO>();
		ArrayList<Categoria> categorias = null;
		ArrayList<Descuento> descuentos = null;
		CatalogoPorNivelDTO catalogoDto = null;
		
		Query queryCategorias = entityManager.createQuery("select ca from Categoria ca");
		categorias = (ArrayList<Categoria>) queryCategorias.getResultList();
		
		if(idNivel != null && idCategoria != null){
			
			Query queryDescuentos = entityManager.createQuery("select de from Descuento de "
					+ "where de.nivel.id=:idNivel and de.categoria.id=:idCategoria");
			queryDescuentos.setParameter("idNivel", idNivel);
			queryDescuentos.setParameter("idCategoria", idCategoria);
			descuentos = (ArrayList<Descuento>) queryDescuentos.getResultList();
			
			catalogoDto = new CatalogoPorNivelDTO();
			catalogoDto.setDescuentos(descuentos);
			
		} else if(idNivel == null && idCategoria != null){
			
			Query queryDescuentos = entityManager.createQuery("select de from Descuento de "
					+ "where de.categoria.id=:idCategoria");
			queryDescuentos.setParameter("idCategoria", idCategoria);
			descuentos = (ArrayList<Descuento>) queryDescuentos.getResultList();
			
			catalogoDto = new CatalogoPorNivelDTO();
			catalogoDto.setDescuentos(descuentos);
			
		} else if(idNivel != null && idCategoria == null){
			
			Query queryDescuentos = entityManager.createQuery("select de from Descuento de "
					+ "where de.nivel.id=:idNivel");
			queryDescuentos.setParameter("idNivel", idNivel);
			descuentos = (ArrayList<Descuento>) queryDescuentos.getResultList();
			
			catalogoDto = new CatalogoPorNivelDTO();
			catalogoDto.setDescuentos(descuentos);
			
		}
		
		catalogoDto.setCategorias(categorias);
	
		return catalogoDto;
	}

}
