/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.dto;

import java.util.Date;
import java.util.List;

/**
 *
 * @author plantas
 */
public class EnsamblearranqueDTO {

    public int id;
    public PlantaDTO planta;
    public String folio;  
    public UsuarioDTO usuarios;
    public Date dtCreacion;
    public Date dtProgramada;
    public Date dtProgramadaReal;
    public KitDTO kit;
    public String recibio;
    public String atendio;
    public String radiador;
    public String guardas;
    public UbicacionDTO ubicacion;
    public List<PruebaArranqueDTO> pruebaarranques;
}
