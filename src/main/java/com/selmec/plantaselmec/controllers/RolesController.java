/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.controllers;

import com.selmec.plantaselmec.Models.Rol;
import com.selmec.plantaselmec.dto.RolDTO;
import com.selmec.plantaselmec.services.IRolesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author rrojase
 */
@Controller
@RequestMapping("/api/Roles")
public class RolesController extends BaseController<Rol, Integer, RolDTO> {

    IRolesServices rolesServices;

    @Autowired
    public void serRolesServices(IRolesServices rolesServices) {
        this.rolesServices = rolesServices;
        this.baseService = rolesServices;
        this.setDTO(RolDTO.class);
    }

}
