package com.selmec.plantaselmec.services;

import com.selmec.plantaselmec.Models.Cargasubita;
import com.selmec.plantaselmec.dto.CargasubitaDTO;
import com.selmec.utils.services.IBaseServices;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author GEIDAR
 */
public interface ICargasubitaService extends IBaseServices<Cargasubita, Integer> {
   
    List<CargasubitaDTO> recuperarInfoCargaSubita(@PathVariable("id") int id) throws Exception;
    
    
}
