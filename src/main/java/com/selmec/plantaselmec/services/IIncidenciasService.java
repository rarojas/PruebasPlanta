/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.services;

import com.selmec.plantaselmec.Models.Incidencias;
import com.selmec.utils.dao.IGenericDao;


/**
 *
 * @author rrojase
 */
public interface IIncidenciasService {
        
    IGenericDao<Incidencias,String> getDao();
    
}
