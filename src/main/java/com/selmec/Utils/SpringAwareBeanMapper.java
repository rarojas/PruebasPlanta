/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.Utils;

import java.util.Map;
import javax.persistence.Converter;
import ma.glasnost.orika.Mapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 *
 * @author rrojase
 */
@Component
public class SpringAwareBeanMapper extends ConfigurableMapper implements ApplicationContextAware {

    private MapperFactory factory;

    @Override
    protected void configureFactoryBuilder(final DefaultMapperFactory.Builder factoryBuilder) {
        super.configureFactoryBuilder(factoryBuilder);
    }

    @Override
    protected void configure(final MapperFactory factory) {
        super.configure(factory);
        this.factory = factory;
    }

    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        addAllSpringBeans(applicationContext);
    }

    /**
     * Adds all managed beans of type {@link Mapper} or {@link Converter} to the
     * parent {@link MapperFactory}.
     *
     * @param applicationContext The application context to look for managed
     * beans in.
     */
    private void addAllSpringBeans(final ApplicationContext applicationContext) {
//        final Map<String, Converter> converters = applicationContext.getBeansOfType(Converter.class);
//        for (final Converter converter : converters.values()) {
//            addConverter(converter);
//        }

        final Map<String, Mapper> mappers = applicationContext.getBeansOfType(Mapper.class);
        for (final Mapper<?, ?> mapper : mappers.values()) {
            addMapper(mapper);
        }
    }

//    /**
//     * Add a {@link Converter}.
//     *
//     * @param converter The converter.
//     */
//    public void addConverter(final CostumConve converter) {
//        factory.getConverterFactory().registerConverter(converter);
//    }
    /**
     * Add a {@link Mapper}.
     *
     * @param mapper The mapper.
     */
    public void addMapper(final Mapper<?, ?> mapper) {
        factory.registerMapper(mapper);
    }
}
