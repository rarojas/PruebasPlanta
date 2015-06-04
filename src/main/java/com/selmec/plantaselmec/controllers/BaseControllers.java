/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.controllers;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseControllers<T, E> {

    @Autowired
    SessionFactory sessionFactory;

    public List<E> Get(Class<T> type, Class<E> out_type) {
        List<T> result = sessionFactory.getCurrentSession().createCriteria(type).list();
        return DTO(result, type, out_type);
    }

    public E Get(Serializable key, Class<T> type, Class<E> out_type) {
        T result = (T) sessionFactory.getCurrentSession().get(type, key);
        return DTO(result, type, out_type);
    }

    protected E DTO(T source, Class<T> type, Class<E> out_type) {
        try {
            return out_type.getConstructor(type).newInstance(source);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException ex) {
            Logger.getLogger(BaseControllers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    protected List<E> DTO(List<T> source, Class<T> type, Class<E> type_out) {
        List<E> result = new ArrayList<>();
        for (T item : source) {
            result.add(DTO(item, type, type_out));
        }
        return result;
    }
}
