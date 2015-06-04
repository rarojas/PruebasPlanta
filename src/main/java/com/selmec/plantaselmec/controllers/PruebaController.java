package com.selmec.plantaselmec.controllers;

import com.selmec.plantaselmec.Models.EstadoPrueba;
import com.selmec.plantaselmec.Models.Prueba;
import com.selmec.plantaselmec.Models.Usuarios;
import com.selmec.plantaselmec.dto.CargasubitaDTO;
import com.selmec.plantaselmec.dto.InfoCargaSubitaDto;
import com.selmec.plantaselmec.dto.LecturaDTO;
import com.selmec.plantaselmec.dto.PruebaDTO;
import com.selmec.plantaselmec.services.IPruebaServices;
import com.selmec.plantaselmec.services.IUsuariosServices;
import java.security.Principal;
import java.util.List;
import javax.validation.Valid;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author GEID@R
 */
@Controller
@RequestMapping("api/Pruebas")
public class PruebaController extends BaseControllers<Prueba, PruebaDTO> {

    private final Logger logger = Logger.getLogger(PruebaController.class);
    @Autowired
    IUsuariosServices usuariosServices;
    @Autowired
    IPruebaServices pruebaServices;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public List<PruebaDTO> Get(Principal principal) {
        Usuarios usuarios = usuariosServices.GetByUsername(principal.getName());
        List<Prueba> pruebas = pruebaServices.GetByUser(usuarios);
        return this.DTO(pruebas, Prueba.class, PruebaDTO.class);
    }

    @ResponseBody
    @Transactional
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public PruebaDTO Get(@PathVariable("id") int id) {
        return Get(id, Prueba.class, PruebaDTO.class);
    }

    @PreAuthorize("hasRole('Tecnico Pruebas Ensamble')")
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public PruebaDTO Post(@Valid @RequestBody Prueba prueba) {
        pruebaServices.Save(prueba);
        return DTO(prueba, Prueba.class, PruebaDTO.class);
    }

    @PreAuthorize("hasRole('Tecnico Pruebas Ensamble')")
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public PruebaDTO Put(@RequestBody Prueba prueba) {
        pruebaServices.Update(prueba);
        return DTO(prueba, Prueba.class, PruebaDTO.class);
    }

    @RequestMapping(value = "Lecturas/{id}", method = RequestMethod.GET)
    @ResponseBody
    public List<LecturaDTO> Lecturas(@PathVariable("id") int id) {
        List<LecturaDTO> result;
        result = pruebaServices.LecturasSummary(id);
        return result;
    }

    @RequestMapping(value = "LecturasCC/{id}", method = RequestMethod.GET)
    @ResponseBody
    public List<LecturaDTO> LecturasCC(@PathVariable("id") int id) {
        List<LecturaDTO> result = null;
        result = pruebaServices.LecturasCC(id);
        return result;
    }

    @RequestMapping(value = "LecturasCS/{id}", method = RequestMethod.GET)
    @ResponseBody
    public InfoCargaSubitaDto LecturasCS(@PathVariable("id") int id) throws Exception {
        logger.debug("Inicio");

        InfoCargaSubitaDto result = null;
        String msjEx = null;

        List<CargasubitaDTO> listaCargasubitaDTO = null;
        List<LecturaDTO> listaLecturaDTO = null;

        try {

            result = new InfoCargaSubitaDto();

            result.setPruebaId(id);

            listaCargasubitaDTO = pruebaServices.recuperarInfoCargaSubita(id);

            if (listaCargasubitaDTO == null) {
                msjEx = "La lista con la información de las pruebas de carga subita es nula o esta vacia.";
                throw new Exception(msjEx);
            }
            result.setListaCargasubitaDTO(listaCargasubitaDTO);

            listaLecturaDTO = pruebaServices.Lecturas(id);

            if (listaLecturaDTO == null) {
                msjEx = "La lista con las lecturas de las pruebas de carga subita es nula o esta vacia.";
                throw new Exception(msjEx);
            }

            result.setListaLecturaDTO(listaLecturaDTO);

        } catch (Exception ex) {
            msjEx = "Ocurrio un error al: recuperar la información de la prueba con carga subita.";
            logger.error(msjEx, ex);
            throw ex;
        }

        logger.debug("Fin");
        return result;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void Delete(@PathVariable Integer id) {
        pruebaServices.Delete(id);
    }

    @PreAuthorize("hasRole('Tecnico Pruebas Ensamble')")
    @RequestMapping(value = "AutorizarE/{id}", method = RequestMethod.POST)
    @ResponseBody
    public void AutorizarPruebaEjecutor(@PathVariable("id") int id, Principal principal) {
        pruebaServices.cambioEstatusPrueba(id, principal.getName(), EstadoPrueba.AutorizadoEjecutor);
    }

    @PreAuthorize("hasRole('Tecnico Pruebas Ensamble')")
    @RequestMapping(value = "RechazarE/{id}", method = RequestMethod.POST)
    @ResponseBody
    public void RechazarPruebaEjecutor(@PathVariable("id") int id, Principal principal) {
        pruebaServices.cambioEstatusPrueba(id, principal.getName(), EstadoPrueba.RechazadaEjecutor);

    }

    @PreAuthorize("hasRole('Supervisor Pruebas Ensamble')")
    @RequestMapping(value = "AutorizarS/{id}", method = RequestMethod.POST)
    @ResponseBody
    public void AutorizarPruebasupervisor(@PathVariable("id") int id, Principal principal) {
        pruebaServices.cambioEstatusPrueba(id, principal.getName(), EstadoPrueba.AutorizadaSupervisor);

    }

    @PreAuthorize("hasRole('Supervisor Pruebas Ensamble')")
    @RequestMapping(value = "RechazarS/{id}", method = RequestMethod.POST)
    @ResponseBody
    public void RechazarPruebasupervisor(@PathVariable("id") int id, Principal principal) {
        pruebaServices.cambioEstatusPrueba(id, principal.getName(), EstadoPrueba.RechazadaSupervisor);
    }
}
