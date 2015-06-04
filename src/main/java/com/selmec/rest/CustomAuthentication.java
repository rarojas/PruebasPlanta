/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.rest;

import com.selmec.plantaselmec.Models.Usuarios;
import java.util.Collection;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author rrojase
 */
public class CustomAuthentication extends UsernamePasswordAuthenticationToken{
   

    private Usuarios myUser = null;

     

    public CustomAuthentication(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities, Usuarios myUser){

        super(principal, credentials, authorities);
        this.myUser = myUser;

    }

 

    public Usuarios getMyUser() {

        return myUser;

    }

    public void setMyUser(Usuarios myUser) {

        this.myUser = myUser;

    }

 
}
