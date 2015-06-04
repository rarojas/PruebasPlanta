package com.selmec.plantaselmec.dto;

import com.selmec.plantaselmec.Models.Ensamble;
import com.selmec.plantaselmec.Models.EstadoEnsamble;
import com.selmec.plantaselmec.Models.Prueba;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author rrojase
 */
public class EnsambleDTO implements java.io.Serializable {

    public Date dtProgramadaReal;
    public Date dtProgramada;
    public EstadoEnsamble Estatus;
    
    public int id;
    public PlantaDTO planta;
    public String folio;
    public UsuarioDTO usuarioId;
    public Date dtCreacion;
    public CarrilDTO carriles;
    public int altitud;
    public String rediador;
    public String patin;
    public String guardas;
    public List<PruebaDTO> pruebas;
    
    public EnsambleDTO(Ensamble ensamble) {
        this.id = ensamble.getId();
        this.folio = ensamble.getFolio();
        if (ensamble.getPlanta() != null) {
            this.planta = new PlantaDTO(ensamble.getPlanta());
        }

        this.dtCreacion = ensamble.getDtCreacion();
        this.altitud = ensamble.getAltitud();
        this.carriles = new CarrilDTO(ensamble.getCariles());
        
        this.patin = ensamble.getPatin();
        this.rediador = ensamble.getRediador();
        this.Estatus = ensamble.getEstatus();
        this.dtProgramada = ensamble.getDtProgramada();
        this.dtProgramadaReal = ensamble.getDtProgramadaReal();
        if (ensamble.getUsuarios() != null) {
            this.usuarioId = new UsuarioDTO(ensamble.getUsuarios());
        }
        this.pruebas = new ArrayList<>();
        for (Object prueba : ensamble.getPruebas()) {
            this.pruebas.add(new PruebaDTO((Prueba) prueba));
        }
    }

    public EnsambleDTO(Ensamble ensamble, List<PruebaDTO> pruebas) {
        this.pruebas = pruebas;
        this.id = ensamble.getId();
        this.folio = ensamble.getFolio();
        if (ensamble.getPlanta() != null) {
            this.planta = new PlantaDTO(ensamble.getPlanta());
        }
        this.dtCreacion = ensamble.getDtCreacion();
        this.Estatus = ensamble.getEstatus();
        this.dtProgramada = ensamble.getDtProgramada();
        this.dtProgramadaReal = ensamble.getDtProgramadaReal();
        this.altitud = ensamble.getAltitud();
        this.carriles = new CarrilDTO(ensamble.getCariles());
        
        this.patin = ensamble.getPatin();
        this.rediador = ensamble.getRediador();
        if (ensamble.getUsuarios() != null) {
            this.usuarioId = new UsuarioDTO(ensamble.getUsuarios());
        }
    }

    
}
