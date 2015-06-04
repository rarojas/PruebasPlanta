/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.services;

import com.selmec.plantaselmec.Models.Cariles;
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
public class CarrilesService  implements ICarrilesService {

    private IGenericDao<Cariles, Integer> dao;

    @Autowired
    public void setDao(IGenericDao< Cariles, Integer> daoToSet) {
        dao = daoToSet;
        dao.setClazz(Cariles.class);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Cariles> GetAll() {
        return dao.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Cariles GetById(int id) {
        return dao.findOne(id);
    }

    @Transactional
    @Override
    public void Save(Cariles cariles) {
        dao.create(cariles);
    }

    @Transactional
    @Override
    public void Update(Cariles cariles) {
        dao.update(cariles);
    }

    @Transactional
    @Override
    public void Delete(int id) {
        dao.deleteById(id);
    }
}