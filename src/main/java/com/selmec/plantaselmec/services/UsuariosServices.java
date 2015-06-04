package com.selmec.plantaselmec.services;

import com.selmec.plantaselmec.Models.Usuarios;
import com.selmec.plantaselmec.dto.ChangePassword;
import com.selmec.plantaselmec.dto.ResultBoolean;
import com.selmec.plantaselmec.dto.UsuarioActualDTO;
import com.selmec.plantaselmec.dto.UsuarioDTO;
import com.selmec.utils.dao.IGenericDao;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import ma.glasnost.orika.MapperFacade;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuariosServices implements IUsuariosServices {

    IGenericDao<Usuarios, Integer> dao;

    Logger logger = Logger.getLogger(UsuariosServices.class);

    @Autowired
    private ShaPasswordEncoder passwordEncoder;

    @Autowired
    private MapperFacade mapper;

    @Autowired
    public void setDao(IGenericDao<Usuarios, Integer> daoToSet) {
        this.dao = daoToSet;
        this.dao.setClazz(Usuarios.class);
    } 

    @Override
    public Usuarios GetUsuario(int id) {
        List<Usuarios> usuarios = this.dao.getCurrentSession().createCriteria(Usuarios.class)
                .add(Restrictions.eq("id", id)).setFetchMode("roles", FetchMode.JOIN)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .list();
        if (usuarios.isEmpty()) {
            return null;
        }
        return (Usuarios) usuarios.get(0);
    }

    @Override
    public Usuarios GetByUsername(String username) {
        List<Usuarios> usuarios = this.dao.getCurrentSession()
                .createCriteria(Usuarios.class).add(Restrictions.eq("email", username))
                .setFetchMode("roles", FetchMode.SELECT).list();
        if (usuarios.isEmpty()) {
            return null;
        }
        return (Usuarios) usuarios.get(0);
    }

    @Transactional
    @Override
    public void Delete(int id) {
        this.dao.deleteById(id);
    }

    @Override
    public List<Usuarios> All() {
        return this.dao.getCurrentSession().createCriteria(Usuarios.class)
                .setFetchMode("roles", FetchMode.SELECT).list();
    }

    /**
     *
     * @param dto
     */
    @Transactional
    @Override
    public void Update(UsuarioDTO dto) {
        Usuarios usuarios = this.dao.findOne(dto.id);
        if (!dto.password.isEmpty()) {
            usuarios.setPassword(passwordEncoder.encodePassword(dto.password, null));
        }
        usuarios.setApellidos(dto.apellidos);
        usuarios.setEmail(dto.email);
        usuarios.setNombres(dto.nombres);
        usuarios.setRoles(new HashSet(dto.roles));
        this.dao.update(usuarios);
    }

    /**
     *
     * @param usuarios
     */
    @Transactional
    @Override
    public void Save(Usuarios usuarios) {
        String password = passwordEncoder.encodePassword(usuarios.getPassword(), null);
        usuarios.setPassword(password);
        this.dao.create(usuarios);
    }

    @Override
    public UsuarioDTO ToDTO(Usuarios usuario) {
        return mapper.map(usuario, UsuarioDTO.class);
    }

    @Override
    public List<UsuarioDTO> ToDTO(List<Usuarios> usuarios) {
        List<UsuarioDTO> result = new ArrayList<>();
        for (Usuarios usuario : usuarios) {
            result.add(ToDTO(usuario));
        }
        return result;
    }

    @Transactional(readOnly = true)
    @Override
    public UsuarioActualDTO getDataUser(String userName) {
        if (userName == null) {
            return null;
        }
        Usuarios usuario = this.GetByUsername(userName);
        return new UsuarioActualDTO(usuario);
    }

    @Transactional
    @Override
    public ResultBoolean changePassword(Principal current, ChangePassword obj) {
        Usuarios usuario = this.GetByUsername(current.getName());
        ResultBoolean result = new ResultBoolean();
        String oldPass = passwordEncoder.encodePassword(obj.oldPassword.trim(), null);
        String newPass = passwordEncoder.encodePassword(obj.newPassword.trim(), null);
        if (!oldPass.equals(usuario.getPassword().trim())) {
            result.result = false;
        } else {
            usuario.setPassword(newPass);
            result.result = true;
        }
        return result;
    }

}
