/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.services;

import com.selmec.plantaselmec.Models.Reports.ArranqueDTO;
import com.selmec.plantaselmec.dto.EnsamblearranqueDTO;
import com.selmec.plantaselmec.services.Reports.ReportServiceBase;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ricardo
 */
@Service
public class ArranqueReportService extends ReportServiceBase implements IArranqueReportService {

    @Override
    public void ReportArranque(int EnsambleArranqueID, HttpServletResponse response) throws IOException, JRException {
        EnsamblearranqueDTO model = arranqueService.GetById(EnsambleArranqueID);
        List<ArranqueDTO> dto = new ArrayList<>();
        ArranqueDTO reportdto = new ArranqueDTO();
        reportdto.setDate(model.dtCreacion);
        reportdto.setLatitude(model.ubicacion.latitude);
        reportdto.setLongitude(model.ubicacion.longitude);
        reportdto.setFolio(model.folio);
        dto.add(reportdto);
        report = this.fillJasperPrint(getRutaReportes() + "ArranqueResumen.jasper", new JRBeanCollectionDataSource(dto), new HashMap());
        this.agregarPaginas(report, this.Arranque(new JRBeanCollectionDataSource(dto)));
        this.agregarPaginas(report, this.Instalacion(new JRBeanCollectionDataSource(dto)));
        byte[] result = exporterReport.ExportToStream(report);
        this.SetResponseAttachment(response, result, EnsambleArranqueID);
    }

    public JasperPrint Arranque(JRDataSource datasource) throws IOException, JRException {
        JasperPrint arranque = this.fillJasperPrint(getRutaReportes() + "Arranque.jasper", datasource, new HashMap());
        return arranque;
    }

    public JasperPrint Instalacion(JRDataSource datasource) throws IOException, JRException {
        JasperPrint arranque = this.fillJasperPrint(getRutaReportes() + "Arranque.jasper", datasource, new HashMap());
        return arranque;
    }

    /**
     * @return the rutaReportes
     */
    public String getRutaReportes() {
        return rutaReportes;
    }

    /**
     * @param rutaReportes the rutaReportes to set
     */
    @Override
    public void setRutaReportes(String rutaReportes) {
        this.rutaReportes = rutaReportes;
    }

    @Autowired
    IEnsamblearranqueServices arranqueService;

    @Autowired
    IExporterReport exporterReport;

    private JasperPrint report;

    private String rutaReportes;

}
