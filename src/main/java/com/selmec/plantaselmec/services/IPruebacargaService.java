/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.services;

import com.selmec.plantaselmec.Models.Pruebacarga;
import java.util.List;

/**
 *
 * @author GEIDAR
 */
public interface IPruebacargaService {

    List<Pruebacarga> GetAll();

    Pruebacarga GetById(String id);

    void Save(Pruebacarga pruebaCarga);

    void Update(Pruebacarga pruebaCarga);

    void Delete(String id);
}
