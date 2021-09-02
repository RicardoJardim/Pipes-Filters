package com.architecture.Services;

import com.architecture.Entities.Dog;
import com.architecture.Data.Factories.AbstractDogFactory;
import com.architecture.Data.Factories.DogFactory;
import com.architecture.Data.Repositories.DogRepository;
import com.architecture.Data.Repositories.IRepository;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DogService implements IDogService {
    private static final AbstractDogFactory factory = DogFactory.getInstance();
    private static final IRepository<Dog> repository = new DogRepository();

    //TODO: Adicionar PipeLine
    //TODO: Verifica os valores se estão vazios, null ou formato incorreto incorretos caso sejam necessários retorna erro
    //TODO: Cria o objeto com os campos com os valores minimos aceitaveis (factory)
    //TODO: Devolve o Objeto apos ser validado pela pipeline ou entao executa uma ação
    
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
