package com.architecture.services;

import com.architecture.entities.Cat;
import com.architecture.factory.CatFactory;
import com.architecture.repository.CatRepository;
import com.architecture.repository.IRepository;
import java.util.*;

public class CatService {
    private static final CatFactory factory = CatFactory.getInstance();
    private static final IRepository<Cat> repository = new CatRepository();


    public Cat addCat(String title,String description, double pric) throws Exception{

        try{
            Cat cat = factory.CreateObject(title, description, pric);

            return repository.addEntity(cat);

        }catch(Exception ex){
             throw new Exception(ex);
        }
    }

    
    public boolean removeCat(long id) throws Exception {
         
        try{

            return repository.removeEntity(id);

         }catch(Exception ex){
              throw new Exception(ex);
         }
    }

    
    public Cat getCat(long id) throws Exception {
        try{

            return repository.getEntity(id);
            
         }catch(Exception ex){
              throw new Exception(ex);
         }
    }

    
    public List<Cat> getAllCats() throws Exception {
        try{
            return repository.getAllEntities();
            
         }catch(Exception ex){
              throw new Exception(ex);
         }
    }

    
    public Cat updateCat(String title,String description, double pric) throws Exception {
        try{
            Cat cat = factory.CreateObject(title, description, pric);

            return repository.updateEntity(cat);

         }catch(Exception ex){
              throw new Exception(ex);
         }
    }

}
