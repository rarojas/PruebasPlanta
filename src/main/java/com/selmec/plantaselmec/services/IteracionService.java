/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.services;

import com.selmec.plantaselmec.Models.Iteraciones;
import com.selmec.utils.dao.IGenericDao;
import com.selmec.utils.services.BaseServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author plantas
 */
@Service

public class IteracionService extends BaseServices<Iteraciones, Integer> implements IIteracionService {

    @Autowired
    public void setDao(IGenericDao<Iteraciones, Integer> dao) {
        this.dao = dao;
        this.dao.setClazz(Iteraciones.class);
    }
}
