package com.selmec.plantaselmec.controllers;

import com.selmec.plantaselmec.Models.AutoConfigPlanta;
import com.selmec.plantaselmec.Models.Planta;
import com.selmec.plantaselmec.dto.LecturaPSC;
import com.selmec.plantaselmec.dto.PlantaDTO;
import com.selmec.plantaselmec.dto.ValoresEsperados;
import com.selmec.plantaselmec.services.IEnsambleService;
import com.selmec.plantaselmec.services.ILecturasService;
import com.selmec.plantaselmec.services.IPlantaServices;
import com.selmec.plantaselmec.services.IPruebaServices;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.SessionFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/Planta")
public class PlantaController extends BaseController<Planta, String, PlantaDTO> {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    IEnsambleService ensambleService;

    IPlantaServices PlantaServices;

    @Autowired
    ILecturasService LecturasService;

    private final Logger logger = Logger.getLogger(PlantaController.class);

    @RequestMapping(value = "Planta", method = RequestMethod.GET)
    public String Index() {
        return "Plantas/index";
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    @Override
    List<PlantaDTO> Get() {
        return PlantaServices.GetPlantas();
    }

    @RequestMapping(value = "ByOp", method = RequestMethod.GET)
    public @ResponseBody
    List<PlantaDTO> GetByOp(@RequestParam("noOP") String noOP) {
        return PlantaServices.GetPlantaByOP(noOP);
    }

    @RequestMapping(value = "ByNoSerie", method = RequestMethod.GET)
    public @ResponseBody
    List<PlantaDTO> GetByNoSerie(@RequestParam("Serie") String Serie) {
        return PlantaServices.GetPlantaByField("noSerie", Serie);
    }

    @Transactional(readOnly = true)
    @RequestMapping(value = "/Valores/{id}", method = RequestMethod.GET)
    public @ResponseBody
    ValoresEsperados EsperadoSC(@PathVariable String id) {
        Planta Planta = (Planta) sessionFactory.getCurrentSession().get(Planta.class, id);
        ValoresEsperados valores = new ValoresEsperados();
        Double corriente;
        corriente = (Planta.getMotores().getAmp()) * 1.0;
        valores.Max.I3 = valores.Max.I2 = valores.Max.I1 = corriente;
        valores.Min.I3 = valores.Min.I2 = valores.Min.I1 = corriente;
        valores.Min.L1N = valores.Min.L2N = valores.Min.L3N = Planta.getVoltaje() * Planta.getvLow() * 0.01;
        valores.Max.L1N = valores.Max.L2N = valores.Max.L3N = Planta.getVoltaje() * Planta.getvHi() * 0.01;
        valores.Min.L3L1 = valores.Min.L2L3 = valores.Min.L1L2 = Planta.getVoltajeFases() * Planta.getvLow() * 0.01;
        valores.Max.L3L1 = valores.Max.L2L3 = valores.Max.L1L2 = Planta.getVoltajeFases() * Planta.getvHi() * 0.01;
        valores.Min.RMP = Planta.getMotores().getRpm() * 0.95;
        valores.Max.RMP = Planta.getMotores().getRpm() * 1.05;
        valores.Min.HZ = Planta.getMotores().getFrecuenciaOperacion() * Planta.getFrecLow() * 0.01;
        valores.Max.HZ = Planta.getMotores().getFrecuenciaOperacion() * Planta.getFrecHi() * 0.01;
        valores.Min.Presion = Planta.getOilSd();
        valores.Max.Presion = 20d;
        valores.Max.Temp = Planta.getTempSd().doubleValue();
        valores.Min.Temp = 0d;
        valores.oilWrn = Planta.getOilWrn();
        valores.tempWrn = Planta.getTempWrn();
        valores.Min.bateria = 11d;
        valores.Max.bateria = 24d;
        return valores;
    }

    @Transactional
    @RequestMapping(value = "On/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String On(@PathVariable("id") int id) {
        ensambleService.TurnOnCarril(id);
        return "true";
    }

    @Transactional
    @RequestMapping(value = "SPControl/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String SPControl(@PathVariable("id") int id) {
        ensambleService.ExcuteSPControl(id);
        return "true";
    }

    @Transactional
    @RequestMapping(value = "Off/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String Off(@PathVariable("id") int id) {
        ensambleService.TurnOffCarril(id);
        return "true";
    }

    @RequestMapping(value = "/GetValues/{id}/{seg}/{ite}/{equ}", method = RequestMethod.GET)
    public @ResponseBody
    LecturaPSC GetValues(@PathVariable("id") int id, @PathVariable("seg") int seg, @PathVariable("ite") int ite, @PathVariable("equ") String equipo) {
        LecturaPSC result = PlantaServices.LecturaPlanta(equipo);
        result.segundo = seg;
        result.iteracion = ite;
        long start = System.currentTimeMillis();
        LecturasService.Save(result, id);
        long elapsedTime = System.currentTimeMillis() - start;
        logger.debug("Tiempo de llamada: " + elapsedTime + " ms");
        return result;
    }

    @RequestMapping(value = "/GetValuesReadOnly/{id}/{seg}/{ite}/{equ}", method = RequestMethod.GET)
    public @ResponseBody
    LecturaPSC GetValuesReadOnly(@PathVariable("id") int id, @PathVariable("seg") int seg, @PathVariable("ite") int ite, @PathVariable("equ") String equipo) {
        long start = System.currentTimeMillis();
        LecturaPSC result = PlantaServices.LecturaPlanta(equipo);
        result.segundo = seg;
        result.iteracion = ite;
        long elapsedTime = System.currentTimeMillis() - start;
        logger.debug("Tiempo de llamada: " + elapsedTime + " ms");
        return result;
    }

    @RequestMapping(value = "/AutoConfig/{equ}", method = RequestMethod.GET)
    @ResponseBody
    public AutoConfigPlanta AutoConfig(@PathVariable("equ") String equipo) {
        return PlantaServices.AutoConfig(equipo);
    }

    @Autowired
    IPruebaServices pruebaServics;

    /**
     * @param PlantaServices the PlantaServices to set
     */
    @Autowired
    public void setPlantaServices(IPlantaServices PlantaServices) {
        this.PlantaServices = PlantaServices;
        this.baseService = PlantaServices;
        this.setDTO(PlantaDTO.class);
    }
}
