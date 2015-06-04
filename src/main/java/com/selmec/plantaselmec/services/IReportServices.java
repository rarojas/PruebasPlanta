/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.services;

import com.selmec.plantaselmec.dto.EnsambleDTO;
import com.selmec.plantaselmec.dto.LecturaDTO;
import com.selmec.plantaselmec.dto.PruebaDTO;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author rrojase
 */
public interface IReportServices {

    byte[] generate(JasperPrint jasperPrint) throws JRException;

    HttpServletResponse SetResponseAttachment(HttpServletResponse response, byte[] reporte, int id) throws IOException;

    JasperPrint fillJasperPrint(String jasperpath, JRDataSource dataSource, Map parameters) throws IOException, JRException;

    JRBeanCollectionDataSource SetReportControl(EnsambleDTO ensambleDTO, PruebaDTO pruebaDTO);

    JRBeanCollectionDataSource SetReportSinCarga(List<LecturaDTO> lecturaDtos, EnsambleDTO ensambleDTO, PruebaDTO pruebaDTO);

    JRBeanCollectionDataSource SetReportConCarga(List<LecturaDTO> lecturaDtos, EnsambleDTO ensambleDTO, PruebaDTO pruebaDTO);

    JRBeanCollectionDataSource SetReportSubita(List<LecturaDTO> lecturaDtos, String folio, PruebaDTO pruebaDTO) throws Exception;

    void SetReportPath(String path);

    String encodeToString(String file) throws FileNotFoundException, IOException;

    JasperPrint agregarPaginas(JasperPrint reporte, JasperPrint paginas);

}
