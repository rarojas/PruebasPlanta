package com.selmec.plantaselmec.services;

import com.selmec.plantaselmec.Models.Cargasubita;
import com.selmec.plantaselmec.dto.CargasubitaDTO;
import com.selmec.utils.dao.IGenericDao;
import com.selmec.utils.services.BaseServices;
import java.util.List;
import ma.glasnost.orika.MapperFacade;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author GEIDAR
 */
@Service
public class CargasubitaService extends BaseServices<Cargasubita, Integer> implements ICargasubitaService {

    @Autowired
    MapperFacade mapper;
    
    @Autowired
    public void setDao(IGenericDao< Cargasubita, Integer> daoToSet) {
        dao = daoToSet;
        dao.setClazz(Cargasubita.class);
    }

    @Transactional
    @Override
    public List<CargasubitaDTO> recuperarInfoCargaSubita(int id) throws Exception {
//        List<Cargasubita> cargasubitas= dao.getCurrentSession().createCriteria(Cargasubita.class).add(Restrictions.eq("prueba.id", id)).list();
        List<CargasubitaDTO> result = mapper.mapAsList(dao.getCurrentSession().createCriteria(Cargasubita.class).add(Restrictions.eq("prueba.id", id)).addOrder(Order.asc("id")).list(), CargasubitaDTO.class);
        return null;
    }
    
    
}
