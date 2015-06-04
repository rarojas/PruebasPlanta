/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.services;

import com.selmec.plantaselmec.Models.Cariles;
import java.util.List;

/**
 *
 * @author rrojase
 */

//Changes ALL 

public interface ICarrilesService {
    List<Cariles> GetAll();

    Cariles GetById(int id);

    void Save(Cariles caril);

    void Update(Cariles caril);

    void Delete(int id);
}
