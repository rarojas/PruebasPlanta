/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.controllers;

import com.selmec.plantaselmec.Models.Contacto;
import com.selmec.plantaselmec.dto.ContactoDTO;
import com.selmec.plantaselmec.services.IContactosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author ricardo
 */
@Controller
@RequestMapping("/api/contactos")
public class ContactosController extends BaseController<Contacto, Integer, ContactoDTO> {

    IContactosService contactosService;

    public ContactosController() {
        this.setDTO(ContactoDTO.class);
    }

    /**
     * @param contactosService the clienteService to set
     */
    @Autowired
    public void setClienteService(IContactosService contactosService) {
        this.contactosService = contactosService;
        this.baseService = contactosService;
    }
}
