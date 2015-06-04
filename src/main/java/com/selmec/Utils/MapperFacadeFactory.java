/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.Utils;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 *
 * @author rrojase
 */
//@Component
public class MapperFacadeFactory implements FactoryBean<MapperFacade> {

        
    @Override
    public MapperFacade getObject() throws Exception {        
        return new DefaultMapperFactory.Builder().build().getMapperFacade();
    }

    @Override
    public Class<?> getObjectType() {        
        return MapperFacade.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

}
