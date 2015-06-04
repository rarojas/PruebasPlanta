package com.selmec.plantaselmec.controllers;

import com.selmec.plantaselmec.Models.Cargasubita;
import com.selmec.plantaselmec.dto.CargasubitaDTO;
import com.selmec.plantaselmec.services.ICargasubitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author GEIDAR
 */
@Controller
@RequestMapping("/api/Cargasubita")
public class CargasubitaController extends BaseController<Cargasubita, Integer, CargasubitaDTO> {

    @Autowired
    ICargasubitaService CargaSubitaService;

    @Autowired
    public void CargaSubitaService(ICargasubitaService CargaSubitaService) {
        this.baseService = CargaSubitaService;
        this.CargaSubitaService = CargaSubitaService;
        this.DTO = CargasubitaDTO.class;
    }

}
