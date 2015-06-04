/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.controllers;

import com.selmec.plantaselmec.Models.Vacio;
import com.selmec.plantaselmec.dto.VacioDTO;
import com.selmec.plantaselmec.services.IVacioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author ricardo
 */
@Controller
@RequestMapping("api/Vacio")

public class PruebaVacioController extends BaseController<Vacio, Integer, VacioDTO>  {
  
    IVacioService PruebaService;

    @Autowired
    public void PruebaService(IVacioService PruebaService) {
        this.baseService = PruebaService;
        this.PruebaService = PruebaService;
        this.DTO = VacioDTO.class;
    }
}
