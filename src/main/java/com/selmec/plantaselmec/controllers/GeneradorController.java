/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.controllers;

import com.selmec.plantaselmec.Models.Generador;
import com.selmec.plantaselmec.dto.GeneradorDTO;
import com.selmec.plantaselmec.services.IGeneradorServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author ricardo
 */
@Controller
@RequestMapping("api/Generador")
public class GeneradorController extends BaseController<Generador, Integer, GeneradorDTO> {

    IGeneradorServices generadorServices;

    @Autowired
    public void setGeneradorServices(IGeneradorServices generadorServices) {
        this.baseService = generadorServices;
        this.generadorServices = generadorServices;
        this.setDTO(GeneradorDTO.class);
    }

}
