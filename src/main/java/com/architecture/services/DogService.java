package com.architecture.services;

import com.architecture.entities.Dog;
import com.architecture.factory.DogFactory;
import com.architecture.repository.IRepository;
import com.architecture.repository.DogRepository;
import java.util.*;

public class DogService {
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

    
    public Dog getEntity(long id) throws Exception {
        try{

            return repository.getEntity(id);
            
         }catch(Exception ex){
              throw new Exception(ex);
         }
    }

    
    public List<Dog> getAllEntity() throws Exception {
        try{
            return repository.getAllEntities();
            
         }catch(Exception ex){
              throw new Exception(ex);
         }
    }

    
    public Dog updateEntity(String title,String description, double pric, double size) throws Exception {
        try{
            Dog cat = (Dog) factory.CreateObject(title, description, pric, size);

            return repository.updateEntity(cat);

         }catch(Exception ex){
              throw new Exception(ex);
         }
    }

}
