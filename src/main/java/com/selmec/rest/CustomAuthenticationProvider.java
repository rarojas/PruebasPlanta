/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.rest;

import com.selmec.plantaselmec.Models.Usuarios;
import com.selmec.plantaselmec.services.IUserService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author rrojase
 */
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private Logger logger = Logger.getLogger(CustomAuthenticationProvider.class);

    @Autowired
    private IUserService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        CustomAuthentication auth = (CustomAuthentication) authentication;
        String username = String.valueOf(auth.getPrincipal());
        String password = String.valueOf(auth.getCredentials());

        Usuarios user =  userDetailsService.findByName(username);
        logger.info("username:" + username);
        logger.info("password:" + password);
        if (!user.getPassword().equals(password)) {
            throw new BadCredentialsException("Datos incorrectos");
        }
        /* what should happen here? */
        return null;
    }

    @Override
    public boolean supports(Class aClass) {
        return true;  //To indicate that this authenticationprovider can handle the auth request. since there's currently only one way of logging in, always return true
    }

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }
    
}
