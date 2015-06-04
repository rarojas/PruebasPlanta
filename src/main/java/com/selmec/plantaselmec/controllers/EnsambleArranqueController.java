/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.controllers;

import com.selmec.plantaselmec.Models.Ensamblearranque;
import com.selmec.plantaselmec.Models.Usuarios;
import com.selmec.plantaselmec.dto.EnsamblearranqueDTO;
import com.selmec.plantaselmec.services.IEnsamblearranqueServices;
import com.selmec.plantaselmec.services.IUsuariosServices;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author rrojase
 */
@Controller
@RequestMapping("api/EnsambleArranque")
public class EnsambleArranqueController {

    IEnsamblearranqueServices ensambleService;

    @Autowired
    IUsuariosServices usuariosServices;

    @Transactional
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    List<EnsamblearranqueDTO> Get(Principal principal) {
        Usuarios usuarios = usuariosServices.GetByUsername(principal.getName());
        return ensambleService.GetByUser(usuarios);
    }
    
    @Transactional
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody
    EnsamblearranqueDTO Get(@PathVariable("id") int id) {
        return ensambleService.GetById(id);
    }

    @Autowired
    public void ensambleService(IEnsamblearranqueServices ensambleService) {
        this.ensambleService = ensambleService;
    }

    @RequestMapping(value = "/Create", method = RequestMethod.POST)
    @ResponseBody
    public EnsamblearranqueDTO Post(@RequestBody Ensamblearranque Ensamble, Principal principal) {
        DateFormat df = new SimpleDateFormat("yyMMddss");
        Date today = Calendar.getInstance().getTime();
        String reportDate = df.format(today);
        Usuarios usuario = usuariosServices.GetByUsername(principal.getName());
        Ensamble.setUsuarios(usuario);
        Ensamble.setFolio(reportDate);
        ensambleService.Save(Ensamble);
        return mapper.map(Ensamble, EnsamblearranqueDTO.class);
    }

    @Autowired
    MapperFacade mapper;
}
