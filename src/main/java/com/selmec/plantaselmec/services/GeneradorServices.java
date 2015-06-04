/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.services;

import com.selmec.plantaselmec.Models.Generador;
import com.selmec.utils.dao.IGenericDao;
import com.selmec.utils.services.BaseServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ricardo
 */
@Service
public class GeneradorServices extends BaseServices<Generador, Integer> implements IGeneradorServices {

    @Autowired
    public void setDao(IGenericDao<Generador, Integer> daoToSet) {
        dao = daoToSet;
        dao.setClazz(Generador.class);
       
    }

}
