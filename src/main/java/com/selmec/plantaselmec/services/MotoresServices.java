/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.services;

import com.selmec.plantaselmec.Models.Motores;
import com.selmec.utils.dao.IGenericDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author rrojase
 */
@Service
public class MotoresServices implements IMotoresService {

    private IGenericDao<Motores, String> dao;

    @Autowired
    public void setDao(IGenericDao< Motores, String> daoToSet) {
        dao = daoToSet;
        dao.setClazz(Motores.class);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Motores> GetAll() {
        return dao.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Motores GetById(String id) {
        return dao.findOne(id);
    }

    @Transactional
    @Override
    public void Save(Motores motor) {
        dao.create(motor);
    }

    @Transactional
    @Override
    public void Update(Motores motor) {
        dao.update(motor);
    }

    @Transactional
    @Override
    public void Delete(String id) {
        dao.deleteById(id);
    }

}
