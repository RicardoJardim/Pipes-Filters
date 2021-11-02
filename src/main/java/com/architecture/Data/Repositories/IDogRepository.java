package com.architecture.Data.Repositories;
import java.util.List;
import com.architecture.Domain.Entities.Dog;

public interface IDogRepository {
    public Dog addEntity(Dog type) throws Exception;
    public boolean removeEntity(long id) throws Exception;
    public Dog getEntity(long id) throws Exception;
    public List<Dog> getAllEntities() throws Exception;
    public Dog updateEntity(long id, Dog cat) throws Exception;

}


