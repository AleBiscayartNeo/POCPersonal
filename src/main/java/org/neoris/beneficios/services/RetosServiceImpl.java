package org.neoris.beneficios.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("retosService")
@Transactional(propagation = Propagation.REQUIRED)
public class RetosServiceImpl implements RetosService{

}
