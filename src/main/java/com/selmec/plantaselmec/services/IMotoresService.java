/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.services;

import com.selmec.plantaselmec.Models.Motores;
import java.util.List;

/**
 *
 * @author rrojase
 */
public interface IMotoresService {

    List<Motores> GetAll();

    Motores GetById(String id);

    void Save(Motores motor);

    void Update(Motores motor);

    void Delete(String id);
}
