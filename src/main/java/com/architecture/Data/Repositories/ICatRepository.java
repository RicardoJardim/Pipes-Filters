package com.architecture.Data.Repositories;
import java.util.List;
import com.architecture.Domain.Entities.Cat;

public interface ICatRepository {

    public Cat addEntity(Cat type) throws Exception;
    public boolean removeEntity(long id) throws Exception;
    public Cat getEntity(long id) throws Exception;
    public List<Cat> getAllEntities() throws Exception;
    public Cat updateEntity(long id, Cat cat) throws Exception;
}
