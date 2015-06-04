/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.services;

import com.selmec.plantaselmec.Models.Kit;
import com.selmec.plantaselmec.dto.KitDTO;
import com.selmec.utils.dao.IGenericDao;
import com.selmec.utils.services.BaseServices;
import java.util.List;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author rrojase
 */
@Service
@Configurable
public class KitsServices extends BaseServices<Kit, Integer> implements IKitsServices {

    @Autowired
    public void setDao(IGenericDao<Kit, Integer> daoToSet) {
        this.dao = daoToSet;
        this.dao.setClazz(Kit.class);
    }

    @Override
    @Transactional
    public List<KitDTO> GetKist() {
        return mapper.mapAsList(dao.findAll(), KitDTO.class);
    }

    @Autowired
    MapperFacade mapper;

}
