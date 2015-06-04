/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.dto;

import com.selmec.plantaselmec.Models.Cargasubita;
import java.text.DecimalFormat;

/**
 *
 * @author GEIDAR
 */
public class CargasubitaDTO implements java.io.Serializable {

    public int id;
    //
    public PruebaDTO prueba;
    public Integer seg;
    public String vinicio;
    public String vfinal;
    public String hfinal;
    public String hinicio;
    public String icarga;

    public CargasubitaDTO() {
    }

    public CargasubitaDTO(Cargasubita cargaSubita) {
        DecimalFormat formatoDosDecimales = new DecimalFormat("###,##0.00");
        
        
        this.id = cargaSubita.getId();
        
        this.seg = cargaSubita.getSeg();
        if(cargaSubita.getVinicio() != null){
            this.vinicio = formatoDosDecimales.format(cargaSubita.getVinicio());
        }
        if(cargaSubita.getVfinal() != null){
            this.vfinal = formatoDosDecimales.format(cargaSubita.getVfinal());
        }
        if(cargaSubita.getHinicio() != null){
            this.hfinal = formatoDosDecimales.format(cargaSubita.getHfinal());
        }
        if(cargaSubita.getHfinal() != null){
            this.hinicio = formatoDosDecimales.format(cargaSubita.getHinicio());
        }
        if(cargaSubita.getIcarga() != null){
            this.icarga = formatoDosDecimales.format(cargaSubita.getIcarga());
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PruebaDTO getPrueba() {
        return prueba;
    }

    public void setPrueba(PruebaDTO prueba) {
        this.prueba = prueba;
    }

    public Integer getSeg() {
        return seg;
    }

    public void setSeg(Integer seg) {
        this.seg = seg;
    }

    public String getVinicio() {
        return vinicio;
    }

    public void setVinicio(String vinicio) {
        this.vinicio = vinicio;
    }

    public String getVfinal() {
        return vfinal;
    }

    public void setVfinal(String vfinal) {
        this.vfinal = vfinal;
    }

    public String getHfinal() {
        return hfinal;
    }

    public void setHfinal(String hfinal) {
        this.hfinal = hfinal;
    }

    public String getHinicio() {
        return hinicio;
    }

    public void setHinicio(String hinicio) {
        this.hinicio = hinicio;
    }

    public String getIcarga() {
        return icarga;
    }

    public void setIcarga(String icarga) {
        this.icarga = icarga;
    }
    
    
}
