/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.controllers;

import com.selmec.plantaselmec.Models.Produccion;
import com.selmec.plantaselmec.services.IProduccionServices;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author rrojase
 */
@Controller
@RequestMapping("/api/Produccion")
public class ProduccionController extends BaseController<Produccion, Integer, Produccion> {

    IProduccionServices produccionServices;

    @Autowired
    public void setProduccionServices(IProduccionServices produccionServices) {
        this.baseService = produccionServices;
        this.produccionServices = produccionServices;
        this.DTO = Produccion.class;
    }

    @RequestMapping(value = "ByOp", method = RequestMethod.GET)
    @ResponseBody
    public List<Produccion> GetByOp(@RequestParam("noOP") String OP) {
        return produccionServices.GetByOp(OP);
    }
}
