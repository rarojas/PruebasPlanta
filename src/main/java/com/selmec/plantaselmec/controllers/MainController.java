/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author rrojase
 */
@Controller
public class MainController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String Inicio() {
        return "Inicio/Inicio";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Datos incorrectos!!!");
        }

        if (logout != null) {
            model.addObject("msg", "Se ha cerrado correctamentamente la sesión!!!");
        }
        model.setViewName("login");

        return model;

    }
}
