/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.services;

import com.selmec.plantaselmec.Models.Instalacion;
import com.selmec.plantaselmec.dto.InstalacionDTO;
import com.selmec.utils.dao.IGenericDao;
import com.selmec.utils.services.BaseServices;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InstalacionService extends BaseServices<Instalacion, Integer> implements IInstalacionService {

    @Autowired
    public void setDao(IGenericDao<Instalacion, Integer> daoToSet) {
        this.dao = daoToSet;
        this.dao.setClazz(Instalacion.class);
    }

    @Transactional
    @Override
    public InstalacionDTO GetByID(int id) {
        return mapper.map(dao.findOne(id), InstalacionDTO.class);
    }

    @Autowired
    MapperFacade mapper;
}
