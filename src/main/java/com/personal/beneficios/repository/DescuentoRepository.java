/**
 * 
 */
package com.personal.beneficios.repository;

import java.text.DecimalFormat;
import java.util.ArrayList;







import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.personal.beneficios.dto.DescuentoDTO;
import com.personal.beneficios.dto.DescuentoGeolocalizadoDTO;
import com.personal.beneficios.dto.SucursalDTO;
import com.personal.beneficios.entity.Categoria;
import com.personal.beneficios.entity.Descuento;
import com.personal.beneficios.entity.Nivel;
import com.personal.beneficios.entity.Proveedor;
import com.personal.beneficios.entity.Sucursal;


/**
 * The class DescuentoRepository
 *
 */
@Service("descuentoRepository")
@Transactional(propagation=Propagation.REQUIRED)
public class DescuentoRepository {

	@PersistenceContext
	public EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public ArrayList<Descuento> getDescuentos() {
		
	Query query  = entityManager.createQuery("SELECT descuento FROM Descuento descuento");
	return (ArrayList<Descuento>) query.getResultList();
		
	}
	
	@Transactional(readOnly=true)
	public Descuento getDescuentoPorID(Integer idDescuento) {
	
	Query query  = entityManager.createQuery("SELECT descuento FROM Descuento descuento where descuento.id=:id");
	query.setParameter("id", idDescuento);
	return  (Descuento) query.getSingleResult();
	}	
	
	public void agregarDescuento(DescuentoDTO descuento){
		Descuento descuentoNuevo = new Descuento();
		cargarDescuento(descuento, descuentoNuevo);
		entityManager.persist(descuentoNuevo);
	}
	
	public void editarDescuento(DescuentoDTO descuento){
		
		Descuento descuentoNuevo = entityManager.find(Descuento.class, descuento.getId());
		cargarDescuento(descuento, descuentoNuevo);
	}
	
	public void eliminarDescuento(Integer idDecuento){
		
		Descuento descuento = entityManager.find(Descuento.class, idDecuento);
		
		entityManager.remove(descuento);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public ArrayList<DescuentoGeolocalizadoDTO> getDescuentosGeolocalizados(
			String longuitud, String latitud, Integer idNivel,
			Integer idCategoria) {
		Query query = null;
		ArrayList<Descuento> descuentos = null;
		ArrayList<DescuentoGeolocalizadoDTO> descGeoDTOs = new ArrayList<DescuentoGeolocalizadoDTO>();
		if (idNivel != null && idCategoria != null) {
			query = entityManager
					.createQuery("select d from Descuento d where d.nivel.id=:idNivel and d.categoria.id=:idCategoria ");
			query.setParameter("idCategoria", idCategoria);
			query.setParameter("idNivel", idNivel);

			descuentos = (ArrayList<Descuento>) query.getResultList();
						
			} else if(idNivel == null && idCategoria != null){
				
				query = entityManager
						.createQuery("select d from Descuento d where d.nivel.id=:idNivel");
				query.setParameter("idNivel", idNivel);

				descuentos = (ArrayList<Descuento>) query.getResultList();

			} else if(idNivel != null && idCategoria == null){
				
				query = entityManager
						.createQuery("select d from Descuento d where d.categoria.id=:idCategoria ");
				query.setParameter("idCategoria", idCategoria);
				
				descuentos = (ArrayList<Descuento>) query.getResultList();
				
			 } else if(idNivel == null && idCategoria == null){
				 query = entityManager
							.createQuery("select d from Descuento d ");
				 
				 descuentos = (ArrayList<Descuento>) query.getResultList();
		}
		
		for (Descuento descuento : descuentos) {
			DescuentoGeolocalizadoDTO descGeoDTO = new DescuentoGeolocalizadoDTO();
			
			descGeoDTO.setId(descuento.getId());
			descGeoDTO.setNombre(descuento.getNombre());
			descGeoDTO.setDescripcion(descuento.getDescripcion());
			descGeoDTO.setDescripcionCorta(descuento.getDescripcionCorta());
			descGeoDTO.setVigenciaDesde(descuento.getVigenciaDesde());
			descGeoDTO.setVigenciaHasta(descuento.getVigenciaHasta());
			descGeoDTO.setImagen(descuento.getImagen());
			descGeoDTO.setLegales(descuento.getLegales());
			descGeoDTO.setNivel(descuento.getNivel());
			descGeoDTO.setProveedor(descuento.getProveedor());
			descGeoDTO.setCategoria(descuento.getCategoria());
			
			 query = entityManager
						.createQuery("select  s from Sucursal s where s.proveedor.id =:id" );
			 query.setParameter("id", descuento.getProveedor().getId());
						 

			 ArrayList<Sucursal> Sucursales = (ArrayList<Sucursal>) query.getResultList();
			 ArrayList<SucursalDTO> SucursalesDto =  new ArrayList<SucursalDTO>();
			 for (Sucursal sucursal : Sucursales) {
				 SucursalDTO sucursalDTO = new SucursalDTO();
				 query = entityManager
							.createQuery("select (acos(sin(radians(" + latitud + ")) * sin(radians(s.latitud)) + cos(radians(" + latitud + ")) * cos(radians(s.latitud)) *"
							+ "cos(radians(" + longuitud + ") - radians(s.longitud))) * 6378) from Sucursal s where s.id =:id" );
				 query.setParameter("id", sucursal.getId());
				 
				 Double distancia = (Double) query.getSingleResult();
				 
				 DecimalFormat df = new DecimalFormat("#.00");
				
				 
				 sucursalDTO.setDistancia(df.format(distancia));
				 sucursalDTO.setCalle(sucursal.getCalle());
				 sucursalDTO.setNumero(sucursal.getNumero());
				 if (sucursal.getInformacionAdicional() != null){
					 sucursalDTO.setInformacionAdicional(sucursal.getInformacionAdicional());
					}

				 sucursalDTO.setDescripcionProvincia(sucursal.getProvincia().getDescripcion());
				 sucursalDTO.setDescripcionLocalidad(sucursal.getLocalidad().getDescripcion());
				 if (sucursal.getBarrio() != null){
					 sucursalDTO.setDescripcionBarrio(sucursal.getBarrio());
					}
				 				 
				 sucursalDTO.setLatitud(sucursal.getLatitud());
				 sucursalDTO.setLongitud(sucursal.getLongitud());
				 sucursalDTO.setTelefono(sucursal.getTelefono());
				 SucursalesDto.add(sucursalDTO);
				
			}
			
			 descGeoDTO.setSucursales(SucursalesDto);
			 descGeoDTOs.add(descGeoDTO);
		}

		return descGeoDTOs;

	}
	
	private void cargarDescuento(DescuentoDTO descuento, Descuento descuentoNuevo){
		descuentoNuevo.setNombre(descuento.getNombre());
		descuentoNuevo.setDescripcion(descuento.getDescripcion());
		descuentoNuevo.setDescripcionCorta(descuento.getDescripcionCorta());
		descuentoNuevo.setVigenciaDesde(descuento.getVigenciaDesde());
		descuentoNuevo.setVigenciaHasta(descuento.getVigenciaHasta());
		descuentoNuevo.setImagen(descuento.getImagen());
		descuentoNuevo.setLegales(descuento.getLegales());
		
		Nivel nivel = new Nivel();
		nivel.setId(descuento.getIdNivel());
		descuentoNuevo.setNivel(nivel);
		
		Proveedor proveedor = new Proveedor();
		proveedor.setId(descuento.getIdProveedor());
		descuentoNuevo.setProveedor(proveedor);
		
		Categoria categoria = new Categoria();
		categoria.setId(descuento.getIdCategoria());
		descuentoNuevo.setCategoria(categoria);
	}

}