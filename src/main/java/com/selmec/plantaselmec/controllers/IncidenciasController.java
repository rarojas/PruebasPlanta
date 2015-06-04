/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.controllers;

import com.selmec.plantaselmec.Models.Incidencias;
import com.selmec.plantaselmec.dto.IncidenciaDTO;
import com.selmec.plantaselmec.services.IIncidenciasService;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
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
public class IncidenciasController extends BaseControllers<Incidencias, IncidenciaDTO> {

    static final String BasePath = "api/Incidencias";
    static final String BasePathID = BasePath + "/{id}";

    @Autowired
    IIncidenciasService IncidenciasService;

    @RequestMapping(value = "Incidencias", method = RequestMethod.GET)
    public String Index() {
        return "Incidencias/Index";
    }

    @RequestMapping(value = BasePath, method = RequestMethod.GET)
    @ResponseBody
    public List<IncidenciaDTO> Get()
            throws NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        return this.DTO(IncidenciasService.getDao().findAll(), Incidencias.class, IncidenciaDTO.class);
    }

    @RequestMapping(value = BasePathID, method = RequestMethod.GET)
    @ResponseBody
    public IncidenciaDTO Get(@PathVariable String id)
            throws NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        return this.DTO(IncidenciasService.getDao().findOne(id), Incidencias.class, IncidenciaDTO.class);
    }

    @RequestMapping(value = BasePath, method = RequestMethod.POST)
    @ResponseBody
    public Incidencias Post(@RequestBody Incidencias Incidencia) {
        IncidenciasService.getDao().create(Incidencia);
        return Incidencia;
    }

    @PreAuthorize("hasRole('Administrador')")
    @RequestMapping(value = BasePath, method = RequestMethod.PUT)
    @ResponseBody
    public Incidencias Put(@RequestBody Incidencias Incidencia) {
        IncidenciasService.getDao().update(Incidencia);
        return Incidencia;
    }

    @RequestMapping(value = BasePathID, method = RequestMethod.DELETE)
    @ResponseBody
    public void Delete(@PathVariable String id)
            throws NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        IncidenciasService.getDao().deleteById(id);
    }

}
