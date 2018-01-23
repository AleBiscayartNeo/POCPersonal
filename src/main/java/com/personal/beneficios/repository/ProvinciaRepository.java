/**
 * 
 */
package com.personal.beneficios.repository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * The class ProvinciaRepository
 *
 */
@Service("provinciaRepository")
@Transactional(propagation=Propagation.REQUIRED)
public class ProvinciaRepository {

	
}
