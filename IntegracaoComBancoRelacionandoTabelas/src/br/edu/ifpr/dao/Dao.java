/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.dao;

import java.util.List;

/**
 *
 * @author lucas
 * @param <PK>
 * @param <T>
 */
public interface Dao <PK, T>{
    
    public void create(T entity);
    public T retrieve(PK pk);
    public void update(T entity);
    public void delete(PK pk);
    public List<T> findAll();
}