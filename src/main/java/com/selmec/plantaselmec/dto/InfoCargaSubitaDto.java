/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.selmec.plantaselmec.dto;

import com.selmec.plantaselmec.vo.CargaSubitaVo;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author habil
 */
public class InfoCargaSubitaDto implements Serializable{
    
    private Integer pruebaId;
    private List<CargasubitaDTO> listaCargasubitaDTO;
    private List<LecturaDTO> listaLecturaDTO;
    
    
    //GETTERS AND SETTERS----------------------------------------------------------------------------------
    /**
     * @return the pruebaId
     */
    public Integer getPruebaId() {
        return pruebaId;
    }

    /**
     * @param pruebaId the pruebaId to set
     */
    public void setPruebaId(Integer pruebaId) {
        this.pruebaId = pruebaId;
    }

    /**
     * @return the listaLecturaDTO
     */
    public List<LecturaDTO> getListaLecturaDTO() {
        return listaLecturaDTO;
    }

    /**
     * @param listaLecturaDTO the listaLecturaDTO to set
     */
    public void setListaLecturaDTO(List<LecturaDTO> listaLecturaDTO) {
        this.listaLecturaDTO = listaLecturaDTO;
    }

    /**
     * @return the listaCargasubitaDTO
     */
    public List<CargasubitaDTO> getListaCargasubitaDTO() {
        return listaCargasubitaDTO;
    }

    /**
     * @param listaCargasubitaDTO the listaCargasubitaDTO to set
     */
    public void setListaCargasubitaDTO(List<CargasubitaDTO> listaCargasubitaDTO) {
        this.listaCargasubitaDTO = listaCargasubitaDTO;
    }
    
    
}
