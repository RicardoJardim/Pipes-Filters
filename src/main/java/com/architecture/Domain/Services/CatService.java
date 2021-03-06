package com.architecture.Domain.Services;

import com.architecture.CrossCutting.PipesFilters.Pipelines.Validation.ValidateObject;
import com.architecture.Data.Factories.AbstractCatFactory;
import com.architecture.Data.Factories.CatFactory;
import com.architecture.Data.Repositories.CatRepository;
import com.architecture.Data.Repositories.ICatRepository;
import com.architecture.Domain.Entities.Cat;
import com.architecture.CrossCutting.PipesFilters.CustomExceptions;
import com.architecture.CrossCutting.PipesFilters.Pipelines.Validation.ValidateCat;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
public class CatService implements ICatService {
  
    private static final AbstractCatFactory factory = CatFactory.getInstance();
    
    private static final ICatRepository repository = CatRepository.getInstance();

    
    @Override
    public Cat addCat(Cat catHttp) throws Exception {

        try{

            Cat cat =  verifyCatPipeline(catHttp, new ValidateCat());

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

            Cat catTemp = (Cat) factory.CreateObject( title, description, pric);

            Cat cat =  verifyCatPipeline(catTemp, new ValidateCat());

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

    private Cat verifyCatPipeline(Cat catHttp, ValidateObject<Cat> validateMethod) throws Exception{  
        return (Cat) validateMethod.execute(0, catHttp); 
    }



}
