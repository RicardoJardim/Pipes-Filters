package com.architecture.repository;

import java.util.List;

public interface IRepository<T> {
    
    public T addEntity(T type) throws Exception;
    public boolean removeEntity(long id) throws Exception;
    public T getEntity(long id) throws Exception;
    public List<T> getAllEntities() throws Exception;
    public T updateEntity(T cat) throws Exception;

}
