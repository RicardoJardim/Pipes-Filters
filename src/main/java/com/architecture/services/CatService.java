package com.architecture.Services;

import com.architecture.Entities.Cat;
import com.architecture.Data.Factories.AbstractCatFactory;
import com.architecture.Data.Factories.CatFactory;
import com.architecture.Data.Repositories.CatRepository;
import com.architecture.Data.Repositories.IRepository;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CatService implements ICatService {
  
    private static final AbstractCatFactory factory = CatFactory.getInstance();
    
    private static final IRepository<Cat> repository = new CatRepository();

    //TODO: Adicionar PipeLine
    //TODO: Verifica os valores se estão vazios, null ou formato incorreto incorretos caso sejam necessários retorna erro
    //TODO: Cria o objeto com os campos com os valores minimos aceitaveis (factory)
    //TODO: Devolve o Objeto apos ser validado pela pipeline ou entao executa uma ação

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
