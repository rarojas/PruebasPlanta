/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.services;

import com.selmec.plantaselmec.Models.Pruebacontrol;
import com.selmec.plantaselmec.dto.PruebaControlDTO;
import com.selmec.utils.dao.IGenericDao;
import java.util.List;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author rrojase
 */
@Service
public class PruebacontrolServices implements IPruebacontrolServices {
    
    private IGenericDao<Pruebacontrol, Integer> dao;
    
    @Autowired
    public void setDao(IGenericDao< Pruebacontrol, Integer> daoToSet) {//como determinar de qeu tipo es el segundo parametro
        dao = daoToSet;
        dao.setClazz(Pruebacontrol.class);
    }
    
    @Transactional(readOnly = true)
    @Override
    public List<Pruebacontrol> GetAll() {
        return dao.findAll();
    }
    
    @Transactional(readOnly = true)
    @Override
    public Pruebacontrol GetById(int id) {
        return dao.findOne(id);
    }
    
    @Transactional
    @Override
    public void Save(Pruebacontrol prueba) {
        dao.create(prueba);
    }
    
    @Transactional
    @Override
    public void Update(Pruebacontrol prueba) {
        dao.update(prueba);
    }
    
    public PruebaControlDTO DTO(Pruebacontrol prueba) {
        return mapper.map(prueba, PruebaControlDTO.class);
    }
    
    @Autowired
    private MapperFacade mapper;
    
    @Override
    public void Delete(int id) {
        dao.deleteById(id);
    }
}
