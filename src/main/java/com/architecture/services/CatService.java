package com.architecture.services;

import com.architecture.entities.Cat;
import com.architecture.factory.AbstractCatFactory;
import com.architecture.factory.CatFactory;
import com.architecture.repository.CatRepository;
import com.architecture.repository.IRepository;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CatService implements ICatService {
  
    private static final AbstractCatFactory factory = CatFactory.getInstance();
    
    private static final IRepository<Cat> repository = new CatRepository();


    public Cat addCat(String title,String description, double pric) throws Exception{

        try{
            Cat cat = (Cat) factory.CreateObject(title, description, pric);

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

    
    public Cat updateCat(long id, String title,String description, double pric) throws Exception {
        try{
            Cat cat = (Cat) factory.CreateObject(title, description, pric);

            return repository.updateEntity(id,cat);

         }catch(Exception ex){
              throw new Exception(ex);
         }
    }

}
