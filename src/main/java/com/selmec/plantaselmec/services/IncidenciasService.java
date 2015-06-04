package com.selmec.plantaselmec.services;

import com.selmec.plantaselmec.Models.Incidencias;
import com.selmec.utils.dao.IGenericDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author rrojase
 */
@Service
public class IncidenciasService  implements IIncidenciasService {

    private IGenericDao<Incidencias,String> dao;

    @Autowired
    public void setDao(IGenericDao< Incidencias,String> daoToSet) {
        dao = daoToSet;       
        getDao().setClazz(Incidencias.class);
    }

    /**
     * @return the dao
     */
    public IGenericDao<Incidencias,String> getDao() {
        return dao;
    }
    
    

        
    
}
