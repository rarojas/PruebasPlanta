/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.dto.mapper;

import com.selmec.plantaselmec.Models.Lecturas;
import com.selmec.plantaselmec.dto.LecturaPSC;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.stereotype.Component;

/**
 *
 * @author rrojase
 */
@Component
public class LectuasPSCToLecturasMapper  extends CustomMapper<LecturaPSC, Lecturas> {
    
    @Override
    public void mapAtoB(LecturaPSC source, Lecturas destination, MappingContext context) {
         destination.setHz(source.HZ);
         destination.setBateria(source.bateria);
         destination.setI1(source.I1);
         destination.setI2(source.I2);
         destination.setI3(source.I3);
         destination.setL1(source.L1N);
         destination.setL2(source.L2N);
         destination.setL3(source.L3N);     
         destination.setL1l2(source.L1L2);
         destination.setL2l3(source.L2L3);
         destination.setL3l1(source.L3L1);         
         destination.setPresion(source.Presion);
         //destination.setTemp(source.Temp);
         destination.setSegundo(source.segundo);
         //destination.setRmp(source.RMP);
         destination.setIteracion(source.iteracion);
         //destination.setTemp(source.Timer);
         
    }
}
