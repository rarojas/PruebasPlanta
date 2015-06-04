/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.controllers;

import com.selmec.plantaselmec.dto.SetPassword;
import java.util.UUID;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author ricardo
 */
@Controller
public class AccountController {

    @RequestMapping(value = "/SetPassword/{ticket}", method = RequestMethod.GET)
    public ModelAndView SetPassword(@PathVariable UUID ticket) {
        SetPassword model = new SetPassword();
        model.setId(ticket);
        ModelAndView mv = new ModelAndView("setpassword", "command", model);
        mv.setViewName("Account/SetPassword");
        return mv;
    }

    @RequestMapping(value = "/SetPassword/{ticket}", method = RequestMethod.POST)
    public void SetPasswordPost() {

    }
}
