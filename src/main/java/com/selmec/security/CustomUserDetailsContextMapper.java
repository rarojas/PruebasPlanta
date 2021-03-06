/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.security;

import com.selmec.plantaselmec.Models.Usuarios;
import java.util.Collection;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;

/**
 *
 * @author ricardo
 */
public class CustomUserDetailsContextMapper implements UserDetailsContextMapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomUserDetailsContextMapper.class);

    private String email = null;
    private String description = null;

    private Usuarios userDetails = null;

    public CustomUserDetailsContextMapper() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public UserDetails mapUserFromContext(DirContextOperations ctx, String username,
            Collection<? extends GrantedAuthority> authorities) {

        Attributes attributes = ctx.getAttributes();
        LOGGER.debug("Attributes: {}", attributes);
        try {
            email = (String) attributes.get("mail").get();
            description = (String) attributes.get("description").get();
        } catch (NamingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Usuarios userDetails = new Usuarios();
        this.userDetails = userDetails;
        return userDetails;
    }

    @Override
    public void mapUserToContext(UserDetails user, DirContextAdapter ctx) {
        // TODO Auto-generated method stub

    }

    public Usuarios getUserDetails() {
        return this.userDetails;
    }

}
