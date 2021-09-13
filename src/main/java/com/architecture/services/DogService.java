package com.architecture.Services;

import com.architecture.Entities.Dog;
import com.architecture.CrossCutting.PipesFilters.CustomExceptions;
import com.architecture.CrossCutting.PipesFilters.Pipelines.Validation.ValidateDog;
import com.architecture.CrossCutting.PipesFilters.Pipelines.Validation.ValidateObject;
import com.architecture.Data.Factories.AbstractDogFactory;
import com.architecture.Data.Factories.DogFactory;
import com.architecture.Data.Repositories.DogRepository;
import com.architecture.Data.Repositories.IRepository;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
public class DogService implements IDogService {
    private static final AbstractDogFactory factory = DogFactory.getInstance();
    private static final IRepository<Dog> repository = new DogRepository();


    @Override
    public Dog addDog(Dog dogHttp) throws Exception {
        try{

            Dog dog =  verifyDogPipeline(dogHttp, new ValidateDog());

            return repository.addEntity(dog);

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

    public Dog addDog(String title,String description, double pric, double size) throws Exception{

        try{

            Dog dogTemp = (Dog) factory.CreateObject(title, description, pric, size);

            Dog dog =  verifyDogPipeline(dogTemp, new ValidateDog());

            return repository.addEntity(dog);

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
    public Dog updateDog(long id, Dog dogHttp) throws Exception {
        try{

            Dog dog =  verifyDogPipeline(id,dogHttp, new ValidateDog());

            return repository.updateEntity(id,dog);

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
    public boolean removeDog(long id) throws Exception {
         
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
    public Dog getDog(long id) throws Exception {
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
    public List<Dog> getAllDogs() throws Exception {
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
    
    private Dog verifyDogPipeline(long id, Dog catHttp, ValidateObject<Dog> validateMethod) throws Exception{
  
        return (Dog) validateMethod.execute(id, catHttp); 
    }

    private Dog verifyDogPipeline( Dog catHttp, ValidateObject<Dog> validateMethod) throws Exception{
  
        return (Dog) validateMethod.execute(0, catHttp); 
    }


 
}
