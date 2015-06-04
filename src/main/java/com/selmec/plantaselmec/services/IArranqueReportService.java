/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.services;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author ricardo
 */
public interface IArranqueReportService {

    public void ReportArranque(int EnsambleArranqueID,HttpServletResponse response) throws IOException, JRException;

    public void setRutaReportes(String rutaReportes);
}
