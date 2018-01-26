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
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public CatalogoPorNivelDTO getCatalogoFiltrado(Integer idNivel, Integer idCategoria) {
		
		ArrayList<Categoria> categorias = null;
		ArrayList<Descuento> descuentos = null;
		CatalogoPorNivelDTO catalogoDto = new CatalogoPorNivelDTO();;
		
		Query queryCategorias = entityManager.createQuery("select ca from Categoria ca");
		categorias = (ArrayList<Categoria>) queryCategorias.getResultList();
		
		if(idNivel != null && idCategoria != null){
			
			Query queryDescuentos = entityManager.createQuery("select de from Descuento de "
					+ "where de.nivel.id=:idNivel and de.categoria.id=:idCategoria and curdate() between de.vigenciaDesde and de.vigenciaHasta");
			queryDescuentos.setParameter("idNivel", idNivel);
			queryDescuentos.setParameter("idCategoria", idCategoria);
			descuentos = (ArrayList<Descuento>) queryDescuentos.getResultList();
			
		} else if(idNivel == null && idCategoria != null){
			
			Query queryDescuentos = entityManager.createQuery("select de from Descuento de "
					+ "where de.categoria.id=:idCategoria and curdate() between de.vigenciaDesde and de.vigenciaHasta");
			queryDescuentos.setParameter("idCategoria", idCategoria);
			descuentos = (ArrayList<Descuento>) queryDescuentos.getResultList();
			
		} else if(idNivel != null && idCategoria == null){
			
			Query queryDescuentos = entityManager.createQuery("select de from Descuento de "
					+ "where de.nivel.id=:idNivel and curdate() between de.vigenciaDesde and de.vigenciaHasta");
			queryDescuentos.setParameter("idNivel", idNivel);
			descuentos = (ArrayList<Descuento>) queryDescuentos.getResultList();
			
		} else if(idNivel == null && idCategoria == null){
			
			Query queryDescuentos = entityManager.createQuery("select de from Descuento de "
					+ "where curdate() between de.vigenciaDesde and de.vigenciaHasta");
			descuentos = (ArrayList<Descuento>) queryDescuentos.getResultList();
			
		}
		
		catalogoDto.setCategorias(categorias);
		catalogoDto.setDescuentos(descuentos);
	
		return catalogoDto;
	}

}
