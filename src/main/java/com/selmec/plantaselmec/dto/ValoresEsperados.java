/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.dto;

/**
 *
 * @author rrojase
 */
public class ValoresEsperados {

    public ValoresEsperados() {
        this.Max = new LecturaPSC();
        this.Min = new LecturaPSC();
    }
    public LecturaPSC Max;
    public LecturaPSC Min;
    
    public Double oilWrn;
    public Integer tempWrn;
}
