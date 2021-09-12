package com.architecture.Services;

import com.architecture.Entities.Cat;
import com.architecture.CrossCutting.PipesFilters.Pipelines.Validation.ValidateObject;
import com.architecture.Data.Factories.AbstractCatFactory;
import com.architecture.Data.Factories.CatFactory;
import com.architecture.Data.Repositories.CatRepository;
import com.architecture.Data.Repositories.IRepository;
import com.architecture.CrossCutting.PipesFilters.CustomExceptions;
import com.architecture.CrossCutting.PipesFilters.Pipelines.Validation.ValidateCat;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
public class CatService implements ICatService {
  
    private static final AbstractCatFactory factory = CatFactory.getInstance();
    
    private static final IRepository<Cat> repository = new CatRepository();

    
    @Override
    public Cat addCat(Cat catHttp) throws Exception {
        try{
            long newID = repository.getNextEntityID();

            Cat cat =  verifyCatPipeline(newID,catHttp, new ValidateCat());

            return repository.addEntity(cat);

        }catch(ExecutionException ex){
            Throwable cause = ex.getCause();
            if (cause instanceof CustomExceptions) {
                CustomExceptions cause2 = (CustomExceptions) cause;
                throw cause2;
            }else{
                throw ex;
            }
        }
        catch(Exception ex){
             throw ex;
        }
    }

    @Override
    public Cat addCat(String title,String description, double pric) throws Exception{

        try{
            long newID = repository.getNextEntityID();

            Cat catTemp = (Cat) factory.CreateObject(newID, title, description, pric);

            Cat cat =  verifyCatPipeline(newID,catTemp, new ValidateCat());

            return repository.addEntity(cat);

        }catch(ExecutionException ex){
            Throwable cause = ex.getCause();
            if (cause instanceof CustomExceptions) {
                CustomExceptions cause2 = (CustomExceptions) cause;
                throw cause2;
            }else{
                throw ex;
            }
        }
        catch(Exception ex){
             throw ex;
        }
    }

    @Override
    public Cat updateCat(long id, Cat catHttp) throws Exception {
        try{

            Cat cat =  verifyCatPipeline(id,catHttp, new ValidateCat());

            return repository.updateEntity(id,cat);

         }catch(ExecutionException ex){
            Throwable cause = ex.getCause();
            if (cause instanceof CustomExceptions) {
                CustomExceptions cause2 = (CustomExceptions) cause;
                throw cause2;
            }else{
                throw ex;
            }
        }
        catch(Exception ex){
             throw ex;
        }
    }   

   

    @Override
    public boolean removeCat(long id) throws Exception {
         
        try{

            return repository.removeEntity(id);

         }catch(ExecutionException ex){
            Throwable cause = ex.getCause();
            if (cause instanceof CustomExceptions) {
                CustomExceptions cause2 = (CustomExceptions) cause;
                throw cause2;
            }else{
                throw ex;
            }
        }
        catch(Exception ex){
             throw ex;
        }
    }

    @Override
    public Cat getCat(long id) throws Exception {
        try{

            return repository.getEntity(id);
            
         }catch(ExecutionException ex){
            Throwable cause = ex.getCause();
            if (cause instanceof CustomExceptions) {
                CustomExceptions cause2 = (CustomExceptions) cause;
                throw cause2;
            }else{
                throw ex;
            }
        }
        catch(Exception ex){
             throw ex;
        }
    }

    @Override
    public List<Cat> getAllCats() throws Exception {
        try{
            return repository.getAllEntities();
            
         }catch(ExecutionException ex){
            Throwable cause = ex.getCause();
            if (cause instanceof CustomExceptions) {
                CustomExceptions cause2 = (CustomExceptions) cause;
                throw cause2;
            }else{
                throw ex;
            }
        }
        catch(Exception ex){
             throw ex;
        }
    }
    
    private Cat verifyCatPipeline(long id, Cat catHttp, ValidateObject<Cat> validateMethod) throws Exception{  
        return (Cat) validateMethod.execute(id, catHttp); 
    }



}
