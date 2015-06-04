package com.selmec.plantaselmec.controllers;

import com.selmec.plantaselmec.Models.Cariles;
import com.selmec.plantaselmec.dto.CarrilDTO;
import com.selmec.plantaselmec.services.ICarrilesService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/Carriles")
public class CarrilController extends BaseControllers<Cariles, CarrilDTO> {

    @Autowired
    ICarrilesService CarrilesService;
 

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    List<CarrilDTO> Get() {
        return DTO(CarrilesService.GetAll(), Cariles.class, CarrilDTO.class);
    }

    @Transactional(readOnly = true)
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CarrilDTO Get(@PathVariable("id") int id) {
        return Get(id, Cariles.class, CarrilDTO.class);
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    Cariles Post(@Valid @RequestBody Cariles cariles) {
        CarrilesService.Save(cariles);
        return cariles;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public @ResponseBody
    Cariles Put(@Valid @RequestBody Cariles cariles) {
        CarrilesService.Update(cariles);
        return cariles;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    void Delete(@PathVariable int id) {
        CarrilesService.Delete(id);
    }

}
