/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.services;

import com.selmec.plantaselmec.Models.Rol;
import com.selmec.utils.dao.IGenericDao;
import com.selmec.utils.services.BaseServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author rrojase
 */
@Service
public class RolesServices extends BaseServices<Rol,Integer> implements IRolesServices{

    @Autowired
    public void setDao(IGenericDao<Rol,Integer> dao){
        this.dao = dao;
        this.dao.setClazz(Rol.class);        
    }
}
