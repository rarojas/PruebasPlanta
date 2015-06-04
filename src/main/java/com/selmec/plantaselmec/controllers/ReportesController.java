/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.controllers;

import com.selmec.plantaselmec.Models.Ensamble;
import com.selmec.plantaselmec.Models.Prueba;
import com.selmec.plantaselmec.dto.LecturaDTO;
import com.selmec.plantaselmec.dto.LecturasDTO;
import com.selmec.plantaselmec.dto.PruebaDTO;
import com.selmec.plantaselmec.services.IEnsambleService;
import com.selmec.plantaselmec.services.IPruebaServices;
import com.selmec.plantaselmec.services.IReportServices;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import ma.glasnost.orika.MapperFacade;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author dchavez
 */
@Controller
@RequestMapping("/reportes/pruebas")
public class ReportesController {

    @Autowired
    @Qualifier("dataSourceMySQL")
    DataSource datasource;

    @RequestMapping(value = "/test-sumary/html/{id}", method = RequestMethod.GET)
    public ModelAndView doTestSummaryReport(@PathVariable("id") String id) {
        ModelAndView result = null;
        System.out.println("--->");
        Connection conn = null;
        try {
            conn = datasource.getConnection("ricardo", "ricardo");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println(conn);
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        parameterMap.put("planta_id", id);
        parameterMap.put("REPORT_CONNECTION", conn);
        result = new ModelAndView("testSummaryHTMLReport", parameterMap);
        return result;
    }

    @RequestMapping(value = "/test-sumary/pdf/{id}", method = RequestMethod.GET)
    public ModelAndView doTestSummarypdfReport(@PathVariable("id") String id) {
        ModelAndView result = null;
        System.out.println("--->");
        Connection conn = null;
        try {
            conn = datasource.getConnection("ricardo", "ricardo");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println(conn);
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        parameterMap.put("planta_id", id);
        parameterMap.put("REPORT_CONNECTION", conn);

        result = new ModelAndView("testSummaryPdfReport", parameterMap);
        return result;
    }

    @RequestMapping(value = "/CargaSubita/{id}", method = RequestMethod.GET)
    public void CargaSubita(@PathVariable("id") int id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String rutaReportes = request.getSession().getServletContext().getRealPath("/reportes/noConformidad/") + "/";
        reportServices.SetReportPath(rutaReportes);
        Prueba prueba = pruebaServices.Get(id);
        PruebaDTO pruebaDTO = mapper.map(prueba, PruebaDTO.class);
        Ensamble ensamble = ensambleService.Get(prueba.getEnsamble().getId());
        Map parameters = new HashMap();
        List<LecturaDTO> lecturaDtos = pruebaServices.Lecturas(prueba.getId());
        JasperPrint reporteResumen = reportServices.fillJasperPrint(rutaReportes + "/cargarSubita.jasper", reportServices.SetReportSubita(lecturaDtos, "", pruebaDTO), parameters);

        Map<Integer, List<LecturaDTO>> lecturaByIteracion = new HashMap<>();
        for (LecturaDTO lectura : lecturaDtos) {
            Integer key = lectura.Iteracion;
            if (lecturaByIteracion.containsKey(key)) {
                List<LecturaDTO> list = lecturaByIteracion.get(key);
                list.add(lectura);

            } else {
                List<LecturaDTO> list = new ArrayList<>();
                list.add(lectura);
                lecturaByIteracion.put(key, list);
            }

        }

        for (Entry<Integer, List<LecturaDTO>> item : lecturaByIteracion.entrySet()) {
            reporteResumen = reportServices.agregarPaginas(reporteResumen, reportServices.fillJasperPrint(rutaReportes + "/cargarSubita_subreport1.jasper", new JRBeanCollectionDataSource(item.getValue()), parameters));
        }

        byte[] reporte = reportServices.generate(reporteResumen);
        reportServices.SetResponseAttachment(response, reporte, id);
    }

    @Autowired
    IReportServices reportServices;

    @Autowired
    IPruebaServices pruebaServices;
    @Autowired
    IEnsambleService ensambleService;

    @Autowired
    MapperFacade mapper;

    private final Logger logger = Logger.getLogger(ReportesController.class);
}
