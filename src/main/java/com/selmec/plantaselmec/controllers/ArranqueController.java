/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.controllers;

import com.selmec.plantaselmec.Models.Arranque;
import com.selmec.plantaselmec.dto.ArranqueDTO;
import com.selmec.plantaselmec.services.IArranqueService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author ricardo
 */
@Controller
@RequestMapping("api/Arranque")
public class ArranqueController extends BaseController<Arranque, Integer, ArranqueDTO> {

    protected static Logger logger = Logger.getLogger(ArranqueController.class);

    IArranqueService arranqueService;

    @Autowired
    public void setInstalacionService(IArranqueService arranqueService) {
        this.arranqueService = arranqueService;
        this.baseService = arranqueService;
        this.DTO = ArranqueDTO.class;
    }

    @RequestMapping(value = "report", method = RequestMethod.POST)
    public void ArranqueReport(@PathVariable("id") int id) {

    }

    
}
