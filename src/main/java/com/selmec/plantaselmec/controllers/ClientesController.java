/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.controllers;

import com.selmec.plantaselmec.Models.Clientes;
import com.selmec.plantaselmec.dto.ClienteDTO;
import com.selmec.plantaselmec.services.IClientesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author GEIDAR
 */
@Controller
@RequestMapping("api/Clientes")
public class ClientesController extends BaseController<Clientes, Integer, ClienteDTO> {

    IClientesService clienteService;

    public ClientesController() {
        this.setDTO(ClienteDTO.class);
    }

    /**
     * @param clienteService the clienteService to set
     */
    @Autowired
    public void setClienteService(IClientesService clienteService) {
        this.clienteService = clienteService;
        this.baseService = clienteService;
    }
}
