/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.controllers;

import com.selmec.plantaselmec.Models.Instalacion;
import com.selmec.plantaselmec.dto.InstalacionDTO;
import com.selmec.plantaselmec.services.IInstalacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author GEIDAR
 */
@Controller
@RequestMapping("api/Instalaciones")
public class InstalacionController extends BaseController<Instalacion, Integer, InstalacionDTO> {

    IInstalacionService instalacionService;

    @Autowired
    public void setInstalacionService(IInstalacionService instalacionService) {
        this.instalacionService = instalacionService;
        this.baseService = instalacionService;
    }

    /**
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    @Override
    public InstalacionDTO Get(@PathVariable("id") Integer id) {
        return instalacionService.GetByID(id);
    }

}
