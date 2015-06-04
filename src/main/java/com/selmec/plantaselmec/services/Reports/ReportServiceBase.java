/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.services.Reports;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author ricardo
 */
public class ReportServiceBase {

    public JasperPrint fillJasperPrint(String jasperpath, JRDataSource dataSource, Map parameters) throws IOException, JRException {
        JasperPrint jasperprint;
        
        jasperprint = JasperFillManager.fillReport((JasperReport) JRLoader.loadObject(new File(jasperpath)), parameters, dataSource);
        return jasperprint;
    }

    public JasperPrint agregarPaginas(JasperPrint reporte, JasperPrint paginas) {
        List<JRPrintPage> pages = paginas.getPages();
        for (JRPrintPage page : pages) {
            reporte.addPage(page);
        }
        return reporte;
    }

    public HttpServletResponse SetResponseAttachment(HttpServletResponse response, byte[] reporte, int id) throws IOException {
        response.setContentType("application/pdf");
        response.setContentLength(reporte.length);
        try (ServletOutputStream out = response.getOutputStream()) {
            out.write(reporte, 0, reporte.length);
            out.flush();
            out.close();
        }
        return response;
    }

}
