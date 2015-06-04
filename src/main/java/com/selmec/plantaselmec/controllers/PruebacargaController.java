package com.selmec.plantaselmec.controllers;

import com.selmec.plantaselmec.Models.Pruebacarga;
import com.selmec.plantaselmec.dto.PruebacargaDTO;
import com.selmec.plantaselmec.services.IPruebacargaService;
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

/**
 *
 * @author GEIDAR
 */
@Controller
@RequestMapping("api/Pruebacarga")//determinar como se va a llamar la carpeta de Pruebascarga
public class PruebacargaController extends BaseControllers<Pruebacarga,PruebacargaDTO>{

    @Autowired
    IPruebacargaService PruebacargaService;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    List<PruebacargaDTO> Get() {
        return DTO(PruebacargaService.GetAll(), Pruebacarga.class, PruebacargaDTO.class);
    }

    @Transactional(readOnly = true)
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public PruebacargaDTO Get(@PathVariable("id") int id) {
        return Get(id, Pruebacarga.class, PruebacargaDTO.class);
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    Pruebacarga Post(@Valid @RequestBody Pruebacarga pruebaCarga) {
        PruebacargaService.Save(pruebaCarga);
        return pruebaCarga;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public @ResponseBody
    Pruebacarga Put(@Valid @RequestBody Pruebacarga pruebaCarga) {
        PruebacargaService.Update(pruebaCarga);
        return pruebaCarga;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    void Delete(@PathVariable String id) {
        PruebacargaService.Delete(id);
    }

}
