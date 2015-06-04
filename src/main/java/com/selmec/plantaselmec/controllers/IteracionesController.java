/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.controllers;

import com.selmec.plantaselmec.Models.Iteraciones;
import com.selmec.plantaselmec.services.IIteracionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author plantas
 */
@Controller
@RequestMapping("api/Iteraciones")
public class IteracionesController extends BaseController<Iteraciones, Integer, Iteraciones> {

    IIteracionService iteracionService;

    @Autowired
    public void setIteracionesServices(IIteracionService service) {
        this.baseService = service;
        this.iteracionService = service;
    }
}
