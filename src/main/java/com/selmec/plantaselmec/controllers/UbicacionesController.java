/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.controllers;

import com.selmec.plantaselmec.dto.UbicacionDTO;
import com.selmec.plantaselmec.Models.Ubicaciones;
import com.selmec.plantaselmec.services.IUbicacionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author GEIDAR
 */
@Controller
@RequestMapping("api/Ubicaciones")
public class UbicacionesController extends BaseController<Ubicaciones, Integer, UbicacionDTO> {

    IUbicacionesService ubicacionesService;

    public UbicacionesController() {
        this.setDTO(UbicacionDTO.class);

    }

    /**
     * @param ubicacionesService the ubicacionesService to set
     */
    @Autowired
    public void setUbicacionesService(IUbicacionesService ubicacionesService) {
        this.ubicacionesService = ubicacionesService;
        this.baseService = ubicacionesService;
    }
}
