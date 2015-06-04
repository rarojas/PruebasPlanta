/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.services;

import com.selmec.plantaselmec.dto.LecturaPSC;

/**
 *
 * @author rrojase
 */
public interface ILecturasService {

    void Save(LecturaPSC result, int PruebaId);
}
