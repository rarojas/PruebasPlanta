/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.controllers;

import com.selmec.plantaselmec.Models.Kit;
import com.selmec.plantaselmec.dto.KitDTO;
import com.selmec.plantaselmec.services.IKitsServices;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author rrojase
 */
@Controller
@RequestMapping("api/Kits")
public class KitsController extends BaseController<Kit, Integer, KitDTO> {

    IKitsServices kitsServices;

    @Autowired
    public void setKitsService(IKitsServices kitsServices) {
        this.kitsServices = kitsServices;
        this.baseService = kitsServices;
        this.setDTO(KitDTO.class);
    }
    

}
