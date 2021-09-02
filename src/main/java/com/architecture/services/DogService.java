package com.architecture.services;

import com.architecture.entities.Dog;
import com.architecture.factory.AbstractDogFactory;
import com.architecture.factory.DogFactory;
import com.architecture.repository.IRepository;
import org.springframework.stereotype.Service;
import com.architecture.repository.DogRepository;
import java.util.*;

@Service
public class DogService implements IDogService {
    private static final AbstractDogFactory factory = DogFactory.getInstance();
    private static final IRepository<Dog> repository = new DogRepository();


    public Dog addDog(String title,String description, double pric, double size) throws Exception{

        try{
            Dog Dog = (Dog) factory.CreateObject(title, description, pric, size);

            return repository.addEntity(Dog);

        }catch(Exception ex){
             throw new Exception(ex);
        }
    }

    
    public boolean removeDog(long id) throws Exception {
         
        try{

            return repository.removeEntity(id);

         }catch(Exception ex){
              throw new Exception(ex);
         }
    }

    
    public Dog getDog(long id) throws Exception {
        try{

            return repository.getEntity(id);
            
         }catch(Exception ex){
              throw new Exception(ex);
         }
    }

    
    public List<Dog> getAllDogs() throws Exception {
        try{
            return repository.getAllEntities();
            
         }catch(Exception ex){
              throw new Exception(ex);
         }
    }

    
    public Dog updateDog(long id, String title,String description, double pric, double size) throws Exception {
        try{
            Dog cat = (Dog) factory.CreateObject(title, description, pric, size);

            return repository.updateEntity(id,cat);

         }catch(Exception ex){
              throw new Exception(ex);
         }
    }

}
