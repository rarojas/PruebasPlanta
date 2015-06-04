package com.selmec.plantaselmec.controllers;

import com.selmec.plantaselmec.Models.Motores;
import com.selmec.plantaselmec.dto.MotorDTO;
import com.selmec.plantaselmec.services.IMotoresService;
import org.springframework.stereotype.Controller;
import java.util.List;
import javax.validation.Valid;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/Motores")
public class MotoresController extends BaseControllers<Motores, MotorDTO> {

    private static final Logger logger = Logger.getLogger(MotoresController.class);

    @Autowired
    IMotoresService MotoresService;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    List<MotorDTO> Get() {
        return DTO(MotoresService.GetAll(), Motores.class, MotorDTO.class);
    }

    @RequestMapping(value = "/Get", method = RequestMethod.GET)
    public @ResponseBody
    MotorDTO Get(@RequestParam("modelo") String id) {
        logger.info(id);
        return DTO(MotoresService.GetById(id), Motores.class, MotorDTO.class);
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    Motores Post(@Valid @RequestBody Motores motor) {
        MotoresService.Save(motor);
        return motor;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public @ResponseBody
    Motores Put(@Valid @RequestBody Motores motor) {
        MotoresService.Update(motor);
        return motor;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    void Delete(@PathVariable String id) {
        MotoresService.Delete(id);
    }
}
