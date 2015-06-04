/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.controllers;

import com.selmec.utils.services.IBaseServices;
import java.util.List;
import ma.glasnost.orika.MapperFacade;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author rrojase
 * @param <E>
 * @param <K>
 * @param <DTO>
 */
public abstract class BaseController<E, K, DTO> {
     
    
    @Autowired
    MapperFacade mapper;

    public IBaseServices<E, K> baseService;

    Class<DTO> DTO;

    public void setDTO(Class<DTO> DTO) {
        this.DTO = DTO;
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    List<DTO> Get() {
        return DTO(baseService.Get());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody
    DTO Get(@PathVariable("id") K id) {
        return DTO(baseService.Get(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    E Post(@RequestBody E entity) {
        baseService.Save(entity);
        return entity;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public @ResponseBody
    E Put(@RequestBody E entity) {
        baseService.Update(entity);
        return entity;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    void Delete(@PathVariable("id") K id) {
        baseService.Delete(id);
    }

    public DTO DTO(E entity) {
        return mapper.map(entity, DTO);
    }

    public List<DTO> DTO(List<E> entities) {
        return mapper.mapAsList(entities, DTO);
    }

}
