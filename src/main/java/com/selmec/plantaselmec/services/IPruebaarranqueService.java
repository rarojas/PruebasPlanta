/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.services;

import com.selmec.plantaselmec.Models.Pruebaarranque;
import com.selmec.utils.services.IBaseServices;

/**
 *
 * @author rrojase
 */
public interface IPruebaarranqueService extends IBaseServices<Pruebaarranque, Integer> {

   
    public void ApruebaT(int PruebaID);

    public void RechazaT(int PruebaID);
}
