package com.architecture.Services;
import java.util.*;

import com.architecture.Entities.Dog;

public interface IDogService {
    public Dog addDog(String title,String description, double pric, double size) throws Exception;

    public boolean removeDog(long id) throws Exception;
    
    public Dog getDog(long id) throws Exception;

    public List<Dog> getAllDogs() throws Exception;

    public Dog updateDog(long id, String title,String description, double pric, double size) throws Exception;

}
