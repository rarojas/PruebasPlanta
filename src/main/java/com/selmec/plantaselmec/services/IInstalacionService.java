/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.services;

import com.selmec.plantaselmec.Models.Instalacion;
import com.selmec.plantaselmec.dto.InstalacionDTO;
import com.selmec.utils.services.IBaseServices;

/**
 *
 * @author GEIDAR
 */
public interface IInstalacionService extends IBaseServices<Instalacion, Integer> {

    InstalacionDTO GetByID(int id);
    
}
