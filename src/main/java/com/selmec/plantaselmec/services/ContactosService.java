/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.services;

import com.selmec.plantaselmec.Models.Contacto;
import com.selmec.utils.dao.IGenericDao;
import com.selmec.utils.services.BaseServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ricardo
 */
@Service
public class ContactosService extends BaseServices<Contacto, Integer> implements IContactosService {

    @Autowired
    public void setDao(IGenericDao<Contacto, Integer> daoToSet) {
        this.dao = daoToSet;
        this.dao.setClazz(Contacto.class);
    }
}
