/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.services;

import com.selmec.plantaselmec.Models.Ensamblearranque;
import com.selmec.plantaselmec.Models.Usuarios;
import com.selmec.plantaselmec.dto.EnsamblearranqueDTO;
import com.selmec.utils.services.IBaseServices;
import java.util.List;

/**
 *
 * @author rrojase
 */
public interface IEnsamblearranqueServices extends IBaseServices<Ensamblearranque, Integer> {

    List<EnsamblearranqueDTO> GetByUser(Usuarios usuario);

    EnsamblearranqueDTO GetById(int id);
}
