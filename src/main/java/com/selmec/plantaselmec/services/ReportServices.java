package com.selmec.plantaselmec.services;

import com.selmec.plantaselmec.dto.CargasubitaDTO;
import com.selmec.plantaselmec.dto.EnsambleDTO;
import com.selmec.plantaselmec.dto.LecturaDTO;
import com.selmec.plantaselmec.dto.PruebaControlDTO;
import com.selmec.plantaselmec.dto.PruebaDTO;
import com.selmec.plantaselmec.vo.LecturaVo;
import com.selmec.plantaselmec.vo.ReportePruebaControlVo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author rrojase
 */
@Service
public class ReportServices implements IReportServices {

    @Autowired
    private ExporterReport exporter;

    public ReportServices() {
        this.dateFormat = new SimpleDateFormat("dd'-'MMMM'-'yyyy");
        this.hourFormat = new SimpleDateFormat("HH:mm:ss 'hrs'");
    }

    @Override
    public byte[] generate(JasperPrint jasperPrint) throws JRException {
        return exporter.ExportToStream(jasperPrint);
    }

    @Override
    public String encodeToString(String file) throws FileNotFoundException, IOException {
        return exporter.encodeToString(file);
    }

    @Override
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

    @Override
    public JasperPrint fillJasperPrint(String jasperpath, JRDataSource dataSource, Map parameters) throws IOException, JRException {
        JasperPrint jasperprint;
        jasperprint = JasperFillManager.fillReport((JasperReport) JRLoader.loadObject(new File(jasperpath)), parameters, dataSource);
        return jasperprint;
    }

    @Override
    public JasperPrint agregarPaginas(JasperPrint reporte, JasperPrint paginas) {
        List<JRPrintPage> pages = paginas.getPages();
        for (JRPrintPage page : pages) {
            reporte.addPage(page);
        }
        return reporte;
    }

    @Override
    public JRBeanCollectionDataSource SetReportSinCarga(List<LecturaDTO> lecturaDtos, EnsambleDTO ensambleDTO, PruebaDTO pruebaDTO) {

        LecturaVo lecturaVo = new LecturaVo();
        List<LecturaVo> lecturaVos = new ArrayList<>();
        if (lecturaDtos != null && lecturaDtos.size() > 0) {
            LecturaDTO lecturaDTO = lecturaDtos.get(lecturaDtos.size() - 1);
            lecturaVo.setFolio(ensambleDTO.folio);
            lecturaVo.setFecha(dateFormat.format(pruebaDTO.dtInicio));
            lecturaVo.setInicio(hourFormat.format(pruebaDTO.dtInicio));
            lecturaVo.setFin(hourFormat.format(pruebaDTO.dtFin));
            lecturaVo.setDuracionPrueba("");
            lecturaVo.setX(lecturaDTO.getX() + "");
            lecturaVo.setI1(lecturaDTO.getI1() + "");
            lecturaVo.setI2(lecturaDTO.getI2() + "");
            lecturaVo.setI3(lecturaDTO.getI3() + "");
            lecturaVo.setL1n(lecturaDTO.getL1n() + "");
            lecturaVo.setL2n(lecturaDTO.getL2n() + "");
            lecturaVo.setL3n(lecturaDTO.getL3n() + "");
            lecturaVo.setPresion(lecturaDTO.getPresion() + "");
            lecturaVo.setTemp(lecturaDTO.getTemp() + "");
            lecturaVo.setHz(lecturaDTO.getHz() + "");
            lecturaVo.setL1l2(lecturaDTO.getL1l2() + "");
            lecturaVo.setL2l3(lecturaDTO.getL2l3() + "");
            lecturaVo.setL3l1(lecturaDTO.getL3l1() + "");
            lecturaVo.setBateria(lecturaDTO.getBateria() + "");
            lecturaVo.setBateriaMax("N/A");
            lecturaVo.setBateriaMin("N/A");
            lecturaVo.setI1max("0.0");
            lecturaVo.setI2max("0.0");
            lecturaVo.setI3max("0.0");
            lecturaVo.setI1min("0.0");
            lecturaVo.setI2min("0.0");
            lecturaVo.setI3min("0.0");
            lecturaVo.setL1l2max((ensambleDTO.planta.voltaje * 1.01) + "");
            lecturaVo.setL2l3max((ensambleDTO.planta.voltaje * 1.01) + "");
            lecturaVo.setL3l1max((ensambleDTO.planta.voltaje * 1.01) + "");
            lecturaVo.setL1l2min((ensambleDTO.planta.voltaje * 0.99) + "");
            lecturaVo.setL2l3min((ensambleDTO.planta.voltaje * 0.99) + "");
            lecturaVo.setL3l1min((ensambleDTO.planta.voltaje * 0.99) + "");
            lecturaVo.setPresionMax((ensambleDTO.planta.motores.presionMax) + "");
            lecturaVo.setTempMax("100");
            lecturaVo.setHzMax((ensambleDTO.planta.motores.frecuenciaOperacion * 1.01) + "");
            lecturaVo.setPresionMin((15 * 14.5038) + "");
            lecturaVo.setTempMin("N/A");
            lecturaVo.setHzMin((ensambleDTO.planta.motores.frecuenciaOperacion * 0.99) + "");
            lecturaVo.setLecturaDTOs(lecturaDtos);
            lecturaVo.setRutaReporte(this.rutaReportes);
            lecturaVo.setEstatus(pruebaDTO.estatus + "");
            lecturaVo.setCodIncidencia(pruebaDTO.incidencias != null ? pruebaDTO.incidencias.descripcion : "");
            lecturaVo.setComentarios(pruebaDTO.comentario);
            lecturaVos.add(lecturaVo);
        }
        return new JRBeanCollectionDataSource(lecturaVos);
    }

    @Override
    public JRBeanCollectionDataSource SetReportControl(EnsambleDTO ensambleDTO, PruebaDTO pruebaDTO) {
        List<ReportePruebaControlVo> pruebaControlVos = new ArrayList<>();
        ReportePruebaControlVo pruebaControlVo = new ReportePruebaControlVo();
        PruebaControlDTO pruebaControlDTO = pruebacontrolServices.DTO(pruebacontrolServices.GetById(pruebaDTO.id));
        if (pruebaControlDTO != null) {
            pruebaControlVo.setFolio(ensambleDTO.folio);
            pruebaControlVo.setFecha(dateFormat.format(pruebaDTO.dtInicio));
            pruebaControlVo.setInicio(hourFormat.format(pruebaDTO.dtInicio));
            pruebaControlVo.setFin(hourFormat.format(pruebaDTO.dtFin));
            pruebaControlVo.setTermometro(pruebaControlDTO.termometro + "");
            pruebaControlVo.setPresion(pruebaControlDTO.presion + "");
            pruebaControlVo.setSaquemarcha(pruebaControlDTO.saquemarcha + "");
            pruebaControlVo.setSobrevelocidad(pruebaControlDTO.sobrevelocidad + "");
            pruebaControlVo.setUbt(pruebaControlDTO.ubt);

            Field[] fields = pruebaControlDTO.getClass().getDeclaredFields();

            for (Field field : fields) {
                if (field.getType() == Boolean.class) {
                    try {
                        Boolean value = (Boolean) field.get(pruebaControlDTO);
                        if (value == null) {
                            value = false;
                        }
                        pruebaControlVo.getClass().getDeclaredField(field.getName() + "Op").set(pruebaControlVo, value ? "X" : "");

                    } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException ex) {
                        java.util.logging.Logger.getLogger(ReportServices.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            pruebaControlVo.setIntentosarranque(pruebaControlDTO.intentosarranque == null ? "" : pruebaControlDTO.intentosarranque.toString());
            pruebaControlVo.setDuraciontotal(pruebaControlDTO.duraciontotal == null ? "" : pruebaControlDTO.duraciontotal.toString());
            pruebaControlVo.setPulsos("0");
        }
        pruebaControlVos.add(pruebaControlVo);
        return new JRBeanCollectionDataSource(pruebaControlVos);
    }

    @Override
    public JRBeanCollectionDataSource SetReportConCarga(List<LecturaDTO> lecturaDtos, EnsambleDTO ensambleDTO, PruebaDTO pruebaDTO) {
        LecturaVo lecturaVo = new LecturaVo();
        List<LecturaVo> lecturaVos = new ArrayList<>();
        if (lecturaDtos != null && lecturaDtos.size() > 0) {
            List<LecturaDTO> lecturaDTOs = pruebaServices.LecturasCC(pruebaDTO.id);
            lecturaVo.setFolio(ensambleDTO.folio);
            lecturaVo.setFecha(dateFormat.format(pruebaDTO.dtInicio));
            lecturaVo.setInicio(hourFormat.format(pruebaDTO.dtInicio));
            lecturaVo.setFin(hourFormat.format(pruebaDTO.dtFin));
            lecturaVo.setLecturaDTOs(lecturaDtos);
            lecturaVo.setLecturaDtoIteracion(lecturaDTOs);
            lecturaVo.setRutaReporte(this.rutaReportes);
            lecturaVos.add(lecturaVo);
        }
        return new JRBeanCollectionDataSource(lecturaVos);
    }

    @Override
    public JRBeanCollectionDataSource SetReportSubita(List<LecturaDTO> lecturaDtos, String folio, PruebaDTO pruebaDTO) throws Exception {
        LecturaVo lecturaVo = new LecturaVo();
        List<LecturaVo> lecturaVos = new ArrayList<>();
        List<CargasubitaDTO> listaCargaSubitaVo;
        List<CargasubitaDTO> listaCargaSubitaDTO = new ArrayList<>();
        CargasubitaDTO cargasubitaDTO;

        if (lecturaDtos.size() > 0) {
            lecturaVo.setFolio(folio);
            lecturaVo.setFecha(dateFormat.format(pruebaDTO.dtInicio));
            lecturaVo.setInicio(hourFormat.format(pruebaDTO.dtInicio));
            lecturaVo.setFin(hourFormat.format(pruebaDTO.dtFin));
            listaCargaSubitaVo = pruebaServices.recuperarInfoCargaSubita(pruebaDTO.id);
            if (listaCargaSubitaVo.size() > 0) {
                for (CargasubitaDTO listaCargaSubitaDTO1 : listaCargaSubitaVo) {
                    cargasubitaDTO = new CargasubitaDTO();
                    cargasubitaDTO.setHfinal(listaCargaSubitaDTO1.getHfinal());
                    cargasubitaDTO.setHinicio(listaCargaSubitaDTO1.getHinicio());
                    cargasubitaDTO.setIcarga(listaCargaSubitaDTO1.getIcarga());
                    cargasubitaDTO.setId(listaCargaSubitaDTO1.getId());
                    cargasubitaDTO.setPrueba(pruebaDTO);
                    cargasubitaDTO.setSeg(listaCargaSubitaDTO1.getSeg());
                    cargasubitaDTO.setVfinal(listaCargaSubitaDTO1.getVfinal());
                    cargasubitaDTO.setVinicio(listaCargaSubitaDTO1.getVinicio());
                    listaCargaSubitaDTO.add(cargasubitaDTO);
                }
            }
            lecturaVo.setListaCargaSubitaVo(listaCargaSubitaDTO);
            lecturaVo.setLecturaDTOs(lecturaDtos);
            lecturaVo.setRutaReporte(this.rutaReportes);
            lecturaVo.setEstatus(pruebaDTO.estatus + "");
            lecturaVo.setCodIncidencia(pruebaDTO.incidencias != null ? pruebaDTO.incidencias.descripcion : "");
            lecturaVo.setComentarios(pruebaDTO.comentario);
            lecturaVos.add(lecturaVo);
        }
        return new JRBeanCollectionDataSource(lecturaVos);
    }

    private final Logger logger = Logger.getLogger(ReportServices.class);
    SimpleDateFormat hourFormat;
    SimpleDateFormat dateFormat;
    @Autowired
    IPruebacontrolServices pruebacontrolServices;
    @Autowired
    IPruebaServices pruebaServices;

    private String rutaReportes;

    /**
     * @return the rutaReportes
     */
    public String getRutaReportes() {
        return rutaReportes;
    }

    /**
     * @param rutaReportes the rutaReportes to set
     */
    public void setRutaReportes(String rutaReportes) {
        this.rutaReportes = rutaReportes;
    }

    @Override
    public void SetReportPath(String path) {
        this.rutaReportes = path;
    }
}
