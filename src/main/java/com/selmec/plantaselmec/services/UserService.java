package com.selmec.plantaselmec.services;

import com.selmec.plantaselmec.Models.Usuarios;
import com.selmec.utils.dao.IGenericDao;
import java.util.List;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author rrojase
 */
public class UserService implements IUserService {

    private IGenericDao<Usuarios, Integer> dao;

    @Autowired
    public void setDao(IGenericDao< Usuarios, Integer> daoToSet) {
        dao = daoToSet;
        dao.setClazz(Usuarios.class);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuarios user = this.findByName(username);
        if (null == user) {
            throw new UsernameNotFoundException("The user with name " + username + " was not found");
        }
        return user;
    }

    @Transactional(readOnly = true)
    @Override
    public Usuarios findByName(String name) {
        List<Usuarios> users = dao.getCurrentSession()
                .createCriteria(Usuarios.class).add(Restrictions.eq("email", name))
                .setFetchMode("roles", FetchMode.JOIN).list();
        if (users.isEmpty()) {
            return null;
        }
        return users.iterator().next();
    }

}
