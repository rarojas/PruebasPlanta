package com.selmec.plantaselmec.controllers;

import com.selmec.plantaselmec.Models.Ensamble;
import com.selmec.plantaselmec.Models.EstadoPrueba;
import com.selmec.plantaselmec.Models.Reports.TipoConexion;
import com.selmec.plantaselmec.Models.Usuarios;
import com.selmec.plantaselmec.dto.ArrayJson;
import com.selmec.plantaselmec.dto.EnsambleDTO;
import com.selmec.plantaselmec.dto.LecturaDTO;
import com.selmec.plantaselmec.dto.PruebaDTO;
import com.selmec.plantaselmec.dto.ReporteEnsambleDTO;
import com.selmec.plantaselmec.services.IEnsambleService;
import com.selmec.plantaselmec.services.IPruebaServices;
import com.selmec.plantaselmec.services.IReportServices;
import com.selmec.plantaselmec.services.IUsuariosServices;
import com.selmec.plantaselmec.vo.PruebaVo;
import java.io.IOException;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.hibernate.SessionFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("api/Ensambles")
public class EnsamblesController extends BaseControllers<Ensamble, EnsambleDTO> {

    private final Logger logger = Logger.getLogger(EnsamblesController.class);

    @Autowired
    SessionFactory session;
    @Autowired
    private IUsuariosServices usuariosServices;
    @Autowired
    private IEnsambleService ensambleServices;
    @Autowired
    IPruebaServices pruebaServices;

    @Autowired
    IReportServices reportServices;

    private static final Integer REP_SIN_CARGA_TIPO = 0;
    private static final Integer REP_CON_CARGA_TIPO = 1;
    private static final Integer CARGA_SUBITA_TIPO = 2;
    private static final Integer PRUEBAS_CONTROL_TIPO = 3;

    @RequestMapping("/Pruebas")
    public ModelAndView Pruebas() {
        return new ModelAndView("/Pruebas/index");
    }

    @RequestMapping(method = RequestMethod.GET)
    @Transactional(readOnly = true)
    public @ResponseBody
    List<EnsambleDTO> Get(Principal principal) {
        Usuarios usuarios = usuariosServices.GetByUsername(principal.getName());
        List<Ensamble> pruebas = ensambleServices.GetByUser(usuarios);
        return DTO(pruebas, Ensamble.class, EnsambleDTO.class);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Transactional(readOnly = true)
    public @ResponseBody
    EnsambleDTO Get(@PathVariable("id") int id) {
        return Get(id, Ensamble.class, EnsambleDTO.class);
    }

    @Transactional
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public EnsambleDTO Post(@RequestBody Ensamble Ensamble, Principal principal) {
        DateFormat df = new SimpleDateFormat("yyMMddss");
        Date today = Calendar.getInstance().getTime();
        String reportDate = df.format(today);
        Usuarios usuario = usuariosServices.GetByUsername(principal.getName());
        Ensamble.setUsuarios(usuario);
        Ensamble.setFolio(reportDate);
        sessionFactory.getCurrentSession().save(Ensamble);
        return DTO(Ensamble, Ensamble.class, EnsambleDTO.class);
    }

    @RequestMapping(value = "/TipoConexion", method = RequestMethod.GET)
    @ResponseBody
    public List<ArrayJson> TipoConexion() {
        List<ArrayJson> tiposConexion;
        tiposConexion = new ArrayList<>();
        for (TipoConexion tipo : TipoConexion.values()) {
            ArrayJson item = new ArrayJson(tipo.ordinal(), tipo.toString());
            item.id = tipo.name();
            tiposConexion.add(item);
        }
        return tiposConexion;
    }

    @RequestMapping("/Aprobar/{id}")
    @ResponseBody
    @Transactional
    public void Aprobar(@PathVariable("id") int id, Principal principal) {
        Usuarios usuario = usuariosServices.GetByUsername(principal.getName());
        ensambleServices.Aprobar(id, usuario);
    }

    @RequestMapping("/Rechazar/{id}")
    @ResponseBody
    @Transactional
    public void Rechazar(@PathVariable("id") int id, Principal principal) {
        Usuarios usuario = usuariosServices.GetByUsername(principal.getName());
        ensambleServices.Rechazar(id, usuario);
    }

    @RequestMapping(value = "/QR/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String QR(@PathVariable("id") int id, HttpServletRequest request, HttpServletResponse response) {
        String appPath = request.getSession().getServletContext().getRealPath("/img/");
        return ensambleServices.GenerateQR(id, appPath);
    }

    @Transactional(readOnly = true)
    @RequestMapping(value = "/Reporte/{id}", method = RequestMethod.GET)
    @ResponseBody
    public void generarReporteEnsamble(@PathVariable("id") int id, Principal principal, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, JRException {
        logger.info("Inicio");
        String msjEx;
        List<ReporteEnsambleDTO> ensambleDTOs = new ArrayList<>();
        ReporteEnsambleDTO reporteEnsambleDTO = new ReporteEnsambleDTO();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd'-'MM'-'yyyy");
        Usuarios usuario = usuariosServices.GetByUsername(principal.getName());
        String rutaReportes = request.getSession().getServletContext().getRealPath("/reportes/ensamble/") + "/";
        reportServices.SetReportPath(rutaReportes);
        Map parameters = new HashMap();
        String appPath = request.getSession().getServletContext().getRealPath("/img/");
        ensambleServices.GenerateQR(id, appPath);
        reporteEnsambleDTO.setQr(reportServices.encodeToString(appPath + "/QR" + id + ".png"));
        parameters.put("SUBREPORT_DIR", rutaReportes);
        try {
            EnsambleDTO ensambleDTO = Get(id, Ensamble.class, EnsambleDTO.class);
            reporteEnsambleDTO.setFolio(ensambleDTO.folio);
            reporteEnsambleDTO.setFechaReporte(dateFormat.format(ensambleDTO.dtProgramadaReal != null ? ensambleDTO.dtProgramadaReal : new Date()));
            reporteEnsambleDTO.setPeModelo(ensambleDTO.planta.modelo);
            reporteEnsambleDTO.setPeNoSerie(ensambleDTO.planta.noSerie);
            reporteEnsambleDTO.setPeNoOp(ensambleDTO.planta.noOp);
            reporteEnsambleDTO.setmMarcar(ensambleDTO.planta.motores.marca);
            reporteEnsambleDTO.setmModelo(ensambleDTO.planta.motores.modelo);
            reporteEnsambleDTO.setmNoSerie(ensambleDTO.planta.motorSerie);
            reporteEnsambleDTO.setgMarca(ensambleDTO.planta.motores.generadorMarca);
            reporteEnsambleDTO.setgModelo(ensambleDTO.planta.motores.modelo);
            reporteEnsambleDTO.setgNoSerie(ensambleDTO.planta.generadorSerie);
            reporteEnsambleDTO.setKw(ensambleDTO.planta.motores.kw + " KW");
            reporteEnsambleDTO.setKva(ensambleDTO.planta.motores.kva + " KA");
            reporteEnsambleDTO.setTipoControl(ensambleDTO.planta.motores.tipoControl + "");
            reporteEnsambleDTO.setRadiador(ensambleDTO.rediador);
            reporteEnsambleDTO.setcInterruptor(ensambleDTO.planta.capInt + " Amp");
            reporteEnsambleDTO.setCombustible(ensambleDTO.planta.motores.combustible + "");
            reporteEnsambleDTO.setNoFases(ensambleDTO.planta.motores.noFases + "");
            reporteEnsambleDTO.setPatin(ensambleDTO.patin);
            reporteEnsambleDTO.setvPrueba(ensambleDTO.planta.voltaje + " V");
            reporteEnsambleDTO.setFrecuencia(ensambleDTO.planta.motores.frecuenciaOperacion + " hz");
            reporteEnsambleDTO.setAltPrueba(ensambleDTO.altitud + " msnm");
            reporteEnsambleDTO.setGuardas(ensambleDTO.guardas);
            reporteEnsambleDTO.setRealizo(usuario.getNombres() + " " + usuario.getApellidos());
            reporteEnsambleDTO.setSuperviso(ensambleDTO.usuarioId.toString());
            reporteEnsambleDTO.setFecha(dateFormat.format(ensambleDTO.dtProgramadaReal != null ? ensambleDTO.dtProgramadaReal : new Date()));
            ensambleDTOs.add(reporteEnsambleDTO);
            JasperPrint reporteResumen = reportServices.fillJasperPrint(rutaReportes + "/resumen.jasper", new JRBeanCollectionDataSource(ensambleDTOs), parameters);

            if (ensambleDTO.pruebas != null && ensambleDTO.pruebas.size() > 0) {
                List<PruebaDTO> pruebaDTOs = ordenarPruebasEnsambleTipo(ensambleDTO.pruebas);
                for (PruebaDTO pruebaDTO : pruebaDTOs) {
                    logger.info("pruebaDTO.tipo: " + pruebaDTO.tipo + " - " + pruebaDTO.id + " entro aqui....  " + EstadoPrueba.AutorizadaSupervisor);
                    List<LecturaDTO> lecturaDtos = pruebaServices.Lecturas(pruebaDTO.id);
                    if (pruebaDTO.tipo == REP_SIN_CARGA_TIPO) {
                        logger.info("REP_SIN_CARGA_TIPO");
                        reporteResumen = agregarPaginas(reporteResumen, reportServices.fillJasperPrint(rutaReportes + "/sinCarga.jasper", reportServices.SetReportSinCarga(lecturaDtos, ensambleDTO, pruebaDTO), parameters));
                    } else if (pruebaDTO.tipo == REP_CON_CARGA_TIPO) {
                        logger.info("REP_CON_CARGA_TIPO");
                        reporteResumen = agregarPaginas(reporteResumen, reportServices.fillJasperPrint(rutaReportes + "/conCarga.jasper", reportServices.SetReportConCarga(lecturaDtos, ensambleDTO, pruebaDTO), parameters));
                    } else if (pruebaDTO.tipo == CARGA_SUBITA_TIPO) {
                        logger.info("CARGA_SUBITA_TIPO");
                        reporteResumen = agregarPaginas(reporteResumen, reportServices.fillJasperPrint(rutaReportes + "/cargarSubita.jasper", reportServices.SetReportSubita(lecturaDtos, ensambleDTO.folio, pruebaDTO), parameters));
                    } else if (pruebaDTO.tipo == PRUEBAS_CONTROL_TIPO) {
                        logger.info("PRUEBAS_CONTROL_TIPO");
                        reporteResumen = agregarPaginas(reporteResumen, reportServices.fillJasperPrint(rutaReportes + "/pruebasControl.jasper", reportServices.SetReportControl(ensambleDTO, pruebaDTO), parameters));
                    }
                }
            }

            byte[] reporte = reportServices.generate(reporteResumen);
            reportServices.SetResponseAttachment(response, reporte, id);
        } catch (JRException ex) {
            msjEx = "Ocurrio un error al exportar el report";
            logger.info(msjEx, ex);
        } catch (Exception ex) {
            msjEx = "Ocurrio un error inespedado.";
            logger.info(msjEx, ex);
        }
        logger.info("Fin");
    }

    @Transactional(readOnly = true)
    @RequestMapping(value = "/ReporteNoConformidad/{id}", method = RequestMethod.GET, produces = "application/pdf")
    public void generarReporteNoConformidad(@PathVariable("id") int id, Principal principal, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, JRException {
        String msjEx;
        List<ReporteEnsambleDTO> ensambleDTOs = new ArrayList<>();
        ReporteEnsambleDTO reporteEnsambleDTO = new ReporteEnsambleDTO();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd'-'MM'-'yyyy");
        SimpleDateFormat dateFormatPruebaVo = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        String rutaReportes = request.getSession().getServletContext().getRealPath("/reportes/noConformidad/") + "/";
        reportServices.SetReportPath(rutaReportes);
        Map parameters = new HashMap();
        parameters.put("SUBREPORT_DIR", rutaReportes);
        String appPath = request.getSession().getServletContext().getRealPath("/img/");
        ensambleServices.GenerateQR(id, appPath);
        logger.info(appPath + "/QR" + id + ".png");
        String base64 = reportServices.encodeToString(appPath + "/QR" + id + ".png");
        reporteEnsambleDTO.setQr(base64);
        try {
            EnsambleDTO ensambleDTO = Get(id, Ensamble.class, EnsambleDTO.class);
            reporteEnsambleDTO.setFolio(ensambleDTO.folio);
            reporteEnsambleDTO.setFechaReporte(dateFormat.format(ensambleDTO.dtProgramadaReal != null ? ensambleDTO.dtProgramadaReal : new Date()));
            reporteEnsambleDTO.setPeModelo(ensambleDTO.planta.modelo);
            reporteEnsambleDTO.setPeNoSerie(ensambleDTO.planta.noSerie);
            reporteEnsambleDTO.setPeNoOp(ensambleDTO.planta.noOp);
            reporteEnsambleDTO.setmMarcar(ensambleDTO.planta.motores.marca);
            reporteEnsambleDTO.setmModelo(ensambleDTO.planta.motores.modelo);
            reporteEnsambleDTO.setmNoSerie(ensambleDTO.planta.motorSerie);
            reporteEnsambleDTO.setgMarca(ensambleDTO.planta.motores.generadorMarca);
            reporteEnsambleDTO.setgModelo(ensambleDTO.planta.motores.modelo);
            reporteEnsambleDTO.setgNoSerie(ensambleDTO.planta.generadorSerie);
            reporteEnsambleDTO.setKw(ensambleDTO.planta.motores.kw + " KW");
            reporteEnsambleDTO.setKva(ensambleDTO.planta.motores.kva + " KA");
            reporteEnsambleDTO.setTipoControl(ensambleDTO.planta.motores.tipoControl + "");
            reporteEnsambleDTO.setRadiador(ensambleDTO.rediador);
            reporteEnsambleDTO.setcInterruptor(ensambleDTO.planta.capInt + " Amp");
            reporteEnsambleDTO.setCombustible(ensambleDTO.planta.motores.combustible + "");
            reporteEnsambleDTO.setNoFases(ensambleDTO.planta.motores.noFases + "");
            reporteEnsambleDTO.setPatin(ensambleDTO.patin);
            reporteEnsambleDTO.setvPrueba(ensambleDTO.planta.voltaje + " V");
            reporteEnsambleDTO.setFrecuencia(ensambleDTO.planta.motores.frecuenciaOperacion + " hz");
            reporteEnsambleDTO.setAltPrueba(ensambleDTO.altitud + " ms nm");
            reporteEnsambleDTO.setGuardas(ensambleDTO.guardas);
            reporteEnsambleDTO.setSuperviso("");
            reporteEnsambleDTO.setFecha(dateFormat.format(ensambleDTO.dtProgramadaReal != null ? ensambleDTO.dtProgramadaReal : new Date()));
            reporteEnsambleDTO.setRutaReporte(rutaReportes);
            reporteEnsambleDTO.setEjecutor(ensambleDTO.usuarioId.nombres);
            reporteEnsambleDTO.setfProgramada(ensambleDTO.dtProgramada != null ? ensambleDTO.dtProgramada + "" : null);
            reporteEnsambleDTO.setfReal(dateFormatPruebaVo.format(ensambleDTO.dtProgramadaReal != null ? ensambleDTO.dtProgramadaReal : new Date()));
            reporteEnsambleDTO.setEstatusPlanta(ensambleDTO.Estatus.toString());

            Collections.sort(ensambleDTO.pruebas, new Comparator<PruebaDTO>() {
                @Override
                public int compare(PruebaDTO prueba1, PruebaDTO prueba2) {
                    return new Integer(prueba1.getId()).compareTo(prueba2.getId());
                }
            });

            PruebaVo pruebaVo;

            for (PruebaDTO pruebaDTO : ensambleDTO.pruebas) {
                pruebaVo = new PruebaVo();
                pruebaVo.setId(pruebaDTO.getId() + "");
                pruebaVo.setFecha(dateFormatPruebaVo.format(pruebaDTO.dtInicio));
                pruebaVo.setComentario(pruebaDTO.comentario);
                pruebaVo.setCodIncidencia(pruebaDTO.incidencias != null ? pruebaDTO.incidencias.descripcion : "");
                switch (pruebaDTO.tipo) {
                    case 0:
                        reporteEnsambleDTO.addSinCarga(pruebaVo);
                        break;
                    case 1:
                        reporteEnsambleDTO.addConCarga(pruebaVo);
                        break;
                    case 2:
                        reporteEnsambleDTO.addCargaSubita(pruebaVo);
                        break;
                    case 3:
                        reporteEnsambleDTO.addPruebasDeControl(pruebaVo);
                        break;
                }
            }
            ensambleDTOs.add(reporteEnsambleDTO);
            JasperPrint reporteResumen = reportServices.fillJasperPrint(rutaReportes + "/resumen.jasper", new JRBeanCollectionDataSource(ensambleDTOs), parameters);
            if (ensambleDTO.pruebas != null && ensambleDTO.pruebas.size() > 0) {
                List<PruebaDTO> pruebaDTOs = ordenarPruebasNoConformidadTipo(ensambleDTO.pruebas);
                for (PruebaDTO pruebaDTO : pruebaDTOs) {
                    logger.info("pruebaDTO.tipo: " + pruebaDTO.tipo + " - " + pruebaDTO.id + " entro aqui....  " + EstadoPrueba.AutorizadaSupervisor);
                    List<LecturaDTO> lecturaDtos = pruebaServices.Lecturas(pruebaDTO.id);
                    if (pruebaDTO.tipo == REP_SIN_CARGA_TIPO) {
                        logger.info("REP_SIN_CARGA_TIPO");
                        reporteResumen = agregarPaginas(reporteResumen, reportServices.fillJasperPrint(rutaReportes + "/sinCarga.jasper", reportServices.SetReportSinCarga(lecturaDtos, ensambleDTO, pruebaDTO), parameters));
                    } else if (pruebaDTO.tipo == REP_CON_CARGA_TIPO) {
                        logger.info("REP_CON_CARGA_TIPO");
                        reporteResumen = agregarPaginas(reporteResumen, reportServices.fillJasperPrint(rutaReportes + "/conCarga.jasper", reportServices.SetReportConCarga(lecturaDtos, ensambleDTO, pruebaDTO), parameters));
                    } else if (pruebaDTO.tipo == CARGA_SUBITA_TIPO) {
                        logger.info("CARGA_SUBITA_TIPO");
                        reporteResumen = agregarPaginas(reporteResumen, reportServices.fillJasperPrint(rutaReportes + "/cargarSubita.jasper", reportServices.SetReportSubita(lecturaDtos, ensambleDTO.folio, pruebaDTO), parameters));
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
                        for (Map.Entry<Integer, List<LecturaDTO>> item : lecturaByIteracion.entrySet()) {
                            reporteResumen = reportServices.agregarPaginas(reporteResumen, reportServices.fillJasperPrint(rutaReportes + "/cargarSubita_subreport1.jasper", new JRBeanCollectionDataSource(item.getValue()), parameters));
                        }

                    } else if (pruebaDTO.tipo == PRUEBAS_CONTROL_TIPO) {
                        logger.info("PRUEBAS_CONTROL_TIPO");
                        reporteResumen = agregarPaginas(reporteResumen, reportServices.fillJasperPrint(rutaReportes + "/pruebasControl.jasper", reportServices.SetReportControl(ensambleDTO, pruebaDTO), parameters));
                    }
                }
            }

            byte[] reporte = reportServices.generate(reporteResumen);
            reportServices.SetResponseAttachment(response, reporte, id);
        } catch (JRException ex) {
            msjEx = "Ocurrio un error al exportar el report";
            logger.info(msjEx, ex);
        } catch (Exception ex) {
            msjEx = "Ocurrio un error inesperado al genera el reporte.";
            logger.info(msjEx, ex);
        }
        logger.info("fin");
    }

    private JasperPrint agregarPaginas(JasperPrint reporte, JasperPrint paginas) {
        List<JRPrintPage> pages = paginas.getPages();
        for (JRPrintPage page : pages) {
            reporte.addPage(page);
        }
        return reporte;
    }

    private List<PruebaDTO> ordenarPruebasEnsambleTipo(List<PruebaDTO> pruebaDTOs) {
        List<PruebaDTO> list = new ArrayList();
        int count = 0;
        do {
            for (PruebaDTO pruebaDTO : pruebaDTOs) {
                if (pruebaDTO.tipo == count && pruebaDTO.estatus == EstadoPrueba.AutorizadaSupervisor) {
                    list.add(pruebaDTO);
                }
            }
            count++;
        } while (count <= 3);
        return list;
    }

    private List<PruebaDTO> ordenarPruebasNoConformidadTipo(List<PruebaDTO> pruebaDTOs) {
        List<PruebaDTO> list = new ArrayList();
        int count = 0;
        do {
            for (PruebaDTO pruebaDTO : pruebaDTOs) {
                if (pruebaDTO.tipo == count && pruebaDTO.estatus != EstadoPrueba.AutorizadaSupervisor) {
                    list.add(pruebaDTO);
                }
            }
            count++;
        } while (count <= 3);
        return list;
    }

}
