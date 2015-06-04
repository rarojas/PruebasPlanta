/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.services;

import com.selmec.plantaselmec.Models.Pruebacarga;
import com.selmec.utils.dao.IGenericDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author GEIDAR
 */
@Service
public class PruebacargaService implements IPruebacargaService {

    private IGenericDao<Pruebacarga, String> dao;//Como determinar de que tipo es el segunto parámetro, ya que en algunos veo que es de tipo String y en otros de tipo Integer

    @Autowired
    public void setDao(IGenericDao< Pruebacarga, String> daoToSet) {
        dao = daoToSet;
        dao.setClazz(Pruebacarga.class);
    }
    

    @Transactional(readOnly = true)
    @Override
    public List<Pruebacarga> GetAll() {
        return dao.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Pruebacarga GetById(String id) {
        return dao.findOne(id);
    }

    @Transactional
    @Override
    public void Save(Pruebacarga pruebaCarga) {
        dao.create(pruebaCarga);
    }

    @Transactional
    @Override
    public void Update(Pruebacarga pruebaCarga) {
        dao.update(pruebaCarga);
    }

    @Transactional
    @Override
    public void Delete(String id) {
        dao.deleteById(id);
    }

}
