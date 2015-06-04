/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.services;

import com.selmec.plantaselmec.Models.Produccion;
import com.selmec.utils.dao.IGenericDao;
import com.selmec.utils.services.BaseServices;
import java.util.List;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author rrojase
 */
@Service
public class ProduccionServices extends BaseServices<Produccion, Integer>
        implements IProduccionServices {

    @Autowired
    public void setDao(IGenericDao<Produccion, Integer> dao) {
        this.dao = dao;
        this.dao.setClazz(Produccion.class);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Produccion> GetByOp(String OP) {
        return dao.getCurrentSession().createCriteria(Produccion.class).add(Restrictions.like("OP", "%" + OP + "%")).list();
    }

}
