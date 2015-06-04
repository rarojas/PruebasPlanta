/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.services;

import com.selmec.plantaselmec.Models.Pruebacontrol;
import com.selmec.plantaselmec.dto.PruebaControlDTO;
import java.util.List;

/**
 *
 * @author rrojase
 */
//Changes
public interface IPruebacontrolServices {

    List<Pruebacontrol> GetAll();

    Pruebacontrol GetById(int id);

    void Save(Pruebacontrol prueba);

    void Update(Pruebacontrol prueba);

    void Delete(int id);

     public PruebaControlDTO DTO(Pruebacontrol prueba);
}
