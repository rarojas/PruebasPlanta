/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.dto.mapper;

import com.selmec.plantaselmec.Models.Planta;
import com.selmec.plantaselmec.dto.MotorDTO;
import com.selmec.plantaselmec.dto.PlantaDTO;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;

/**
 *
 * @author rrojase
 */
//@Componentl
public class PlantaDTOMapper extends CustomMapper<Planta, PlantaDTO> {

    @Override
    public void mapAtoB(Planta source, PlantaDTO destination, MappingContext context) {
        //destination = this.mapperFacade.map(source, PlantaDTO.class);
        destination.noOp = source.getNoOp();
        destination.noSerie = source.getNoSerie();
        destination.voltaje  = source.getVoltaje();
        destination.motorSerie = source.getMotorSerie();                
        destination.motores = this.mapperFacade.map(source.getMotores(), MotorDTO.class);
    }
}
