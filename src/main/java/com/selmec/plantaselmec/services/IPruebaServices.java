/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.services;

import com.selmec.plantaselmec.Models.EstadoPrueba;
import com.selmec.plantaselmec.Models.Prueba;
import com.selmec.plantaselmec.Models.Usuarios;
import com.selmec.plantaselmec.dto.CargasubitaDTO;
import com.selmec.plantaselmec.dto.LecturaDTO;
import com.selmec.plantaselmec.dto.LecturasDTO;
import com.selmec.utils.services.IBaseServices;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author rrojase
 */
public interface IPruebaServices extends IBaseServices<Prueba, Integer> {

    List<Prueba> GetByUser(Usuarios usuarios);

    String CarrilByPrueba(int id);

    void cambioEstatusPrueba(int id, String nombreUsuario, EstadoPrueba estatus);

    List<LecturaDTO> Lecturas(int id);
    
    List<LecturasDTO> Lectura(int id);

    List<LecturaDTO> LecturasCC(@PathVariable("id") int id);

    List<CargasubitaDTO> recuperarInfoCargaSubita(@PathVariable("id") int id) throws Exception;

    List<LecturaDTO> LecturasSummary(int id);

}
