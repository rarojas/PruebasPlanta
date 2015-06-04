/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.dto.mapper;

import com.selmec.plantaselmec.Models.Ensamblearranque;
import com.selmec.plantaselmec.dto.EnsamblearranqueDTO;
import com.selmec.plantaselmec.dto.KitDTO;
import com.selmec.plantaselmec.dto.PlantaDTO;
import com.selmec.plantaselmec.dto.PruebaArranqueDTO;
import com.selmec.plantaselmec.dto.UbicacionDTO;
import com.selmec.plantaselmec.dto.UsuarioDTO;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.stereotype.Component;

/**
 *
 * @author rrojase
 */
@Component
public class EnsamblearranqueDTOMapper extends CustomMapper<Ensamblearranque, EnsamblearranqueDTO> {

    @Override
    public void mapAtoB(Ensamblearranque source, EnsamblearranqueDTO destination, MappingContext context) {
        destination.id = source.getId();
        destination.folio = source.getFolio();
        destination.planta = new PlantaDTO(source.getPlanta());
        destination.kit = new KitDTO(source.getKit());
        destination.dtCreacion = source.getDtCreacion();
        destination.dtProgramada = source.getDtProgramada();
        destination.dtProgramadaReal = source.getDtProgramadaReal();
        destination.folio = source.getFolio();
        destination.usuarios = new UsuarioDTO(source.getUsuarios());
        destination.pruebaarranques = mapperFacade.mapAsList(source.getPruebaarranques(), PruebaArranqueDTO.class);
        destination.ubicacion = mapperFacade.map(source.getUbicaciones(), UbicacionDTO.class);

    }
}
