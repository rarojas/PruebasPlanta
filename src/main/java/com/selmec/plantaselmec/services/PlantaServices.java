package com.selmec.plantaselmec.services;

import com.selmec.Utils.Description;
import com.selmec.Utils.RandomGenerator;
import com.selmec.plantaselmec.Models.AutoConfigPlanta;
import com.selmec.plantaselmec.Models.Planta;
import com.selmec.plantaselmec.Models.Reports.TipoConexion;
import com.selmec.plantaselmec.dto.LecturaPSC;
import com.selmec.plantaselmec.dto.PlantaDTO;
import com.selmec.plantaselmec.dto.TablaLecturaDTO;
import com.selmec.utils.dao.IGenericDao;
import com.selmec.utils.services.BaseServices;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ma.glasnost.orika.MapperFacade;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlantaServices extends BaseServices<Planta, String> implements IPlantaServices {

    @Autowired
    public void setDao(IGenericDao<Planta, String> daoToSet) {
        this.dao = daoToSet;
        this.dao.setClazz(Planta.class);
    }

    @Autowired
    @Qualifier("jdbctemplateMySql")
    JdbcTemplate jdbctemplateMySql;

    @Autowired
    @Qualifier("jdbctemplate")
    JdbcTemplate jdbctemplate;

    @Autowired
    RandomGenerator random;

    @Transactional
    @Override
    public LecturaPSC LecturaPlanta(String Equipo) {

        LecturaPSC result = new LecturaPSC();
        result.Time = new Date().toString();
        String sql = "select tagvalue,tagname from tablalectura where tagname like '%" + Equipo + "|%';";

        List<TablaLecturaDTO> results = jdbctemplateMySql.query(sql, new BeanPropertyRowMapper(TablaLecturaDTO.class));
        Field[] fields;
        fields = LecturaPSC.class.getDeclaredFields();
        for (Field field : fields) {
            Description description = field.getAnnotation(Description.class);
            if (null != description) {
                for (TablaLecturaDTO r : results) {
                    if (r.tagname.contains(description.value())) {
                        try {
                            field.set(result, r.tagvalue);
                        } catch (IllegalArgumentException | IllegalAccessException ex) {
                            Logger.getLogger(PlantaServices.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        }
        return result;
    }

    @Override
    public LecturaPSC LecturaPlanta() {
        LecturaPSC result = new LecturaPSC();
        result.HZ = random.GenerateRamdom(60, .5);
        result.I1 = random.GenerateRamdom(60, .5);
        result.I2 = random.GenerateRamdom(60, .5);
        result.I3 = random.GenerateRamdom(60, .5);
        result.L1L2 = random.GenerateRamdom(220, .5);
        result.L2L3 = random.GenerateRamdom(220, .5);
        result.L3L1 = random.GenerateRamdom(220, .5);
        result.L1N = random.GenerateRamdom(110, .5);
        result.L2N = random.GenerateRamdom(110, .5);
        result.L3N = random.GenerateRamdom(110, .5);
        result.Temp = random.GenerateRamdom(90, .5);
        result.Presion = random.GenerateRamdom(25, .5);
        result.bateria = random.GenerateRamdom(24, .5);
        result.demanda = random.GenerateRamdom(24, .5);
        result.HZ = random.GenerateRamdom(60, .5);
        result.Time = new Date().toString();
        return result;
    }

    @Transactional(readOnly = true)
    @Override
    public List<PlantaDTO> GetPlantas() {
        return mapper.mapAsList(dao.findAll(), PlantaDTO.class
        );
    }

    @Transactional
    @Override
    public AutoConfigPlanta AutoConfig(String equipo) {
        String query = String.format("SELECT COMPONENTE,VARIABLE,TAGVALUE  \n"
                + "FROM EXT_COGNOS WHERE IDEQUIPO = '%s' \n"
                + "AND VARIABLE IN('TIPOCONEXION','PHN','PHPH')", equipo);
        AutoConfigPlanta autoconfig = new AutoConfigPlanta();
        autoconfig.TipoConexion = TipoConexion.Wire_3.ordinal();
        autoconfig.PHN = 120;
        autoconfig.PHPH = 240;
        return autoconfig;
    }

    @Autowired
    MapperFacade mapper;

    @Transactional
    @Override
    public List<PlantaDTO> GetPlantaByOP(String noOP) {
        List<Planta> Plantas = dao.getCurrentSession().createCriteria(Planta.class
        ).add(Restrictions.like("noOp", "%" + noOP + "%")).list();
        List<PlantaDTO> result = new ArrayList<>();
        for (Planta planta : Plantas) {
            result.add(new PlantaDTO(planta));
        }
        return result;
    }

    @Transactional
    @Override
    public List<PlantaDTO> GetPlantaByField(String field, Object value) {
        List<Planta> Plantas = dao.getCurrentSession().createCriteria(Planta.class
        ).add(Restrictions.like(field, "%" + value + "%")).list();
        List<PlantaDTO> result = new ArrayList<>();
        for (Planta planta : Plantas) {
            result.add(new PlantaDTO(planta));
        }
        return result;
    }

}
