/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.controllers;

import com.selmec.plantaselmec.Models.Pruebaarranque;
import com.selmec.plantaselmec.services.IArranqueReportService;
import com.selmec.plantaselmec.services.IPruebaarranqueService;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author rrojase
 */
@Controller
@RequestMapping("api/Pruebasarranque")
public class PruebaarranqueController extends BaseController<Pruebaarranque, Integer, Pruebaarranque> {

    IPruebaarranqueService PruebaService;

    @Autowired
    IArranqueReportService arranqueReports;

    @Autowired
    public void PruebaService(IPruebaarranqueService PruebaService) {
        this.baseService = PruebaService;
        this.PruebaService = PruebaService;
    }

    @RequestMapping(value = "/ApruebaT/{pruebaid}", method = RequestMethod.POST)
    @ResponseBody
    public void ApruebaPruebaTecnico(@PathVariable("pruebaid") int PruebaID) {
        PruebaService.ApruebaT(PruebaID);
    }

    @RequestMapping(value = "/RechazaT/{pruebaid}", method = RequestMethod.POST)
    @ResponseBody
    public void RechazaPruebaTecnico(@PathVariable("pruebaid") int PruebaID) {
        PruebaService.RechazaT(PruebaID);
    }

    @RequestMapping(value = "/report/{id}", method = RequestMethod.GET)
    public void PruebaarranqueReport(@PathVariable("id") int id, HttpServletRequest request, HttpServletResponse response) throws IOException, JRException {
        String rutaReportes = request.getSession().getServletContext().getRealPath("/reportes/Arranque/") + "/";
        arranqueReports.setRutaReportes(rutaReportes);
        arranqueReports.ReportArranque(id, response);
    }
}
