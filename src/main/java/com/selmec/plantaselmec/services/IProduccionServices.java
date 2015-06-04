/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.services;

import com.selmec.plantaselmec.Models.Produccion;
import com.selmec.utils.services.IBaseServices;
import java.util.List;

/**
 *
 * @author rrojase
 */
public interface IProduccionServices extends IBaseServices<Produccion, Integer> {

   List<Produccion> GetByOp(String OP);
}
