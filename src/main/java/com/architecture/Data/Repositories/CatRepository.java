package com.architecture.Data.Repositories;
import java.util.List;

import com.architecture.Database.CatDatabase;
import com.architecture.Entities.Cat;

import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;

public class CatRepository implements IRepository<Cat> {

    private static final CatDatabase database = CatDatabase.getInstance();
    private static final Logger log = LoggerFactory.getLogger(CatRepository.class);

    @Override
    public Cat addEntity(Cat cat) throws Exception {

        log.info("Adding Cat Entity to database: " + database.getClass().getName() + " with entity: "+ cat.toString());
         
        boolean check = database.getCatList().add(cat);
        if(check){
            return cat;
        }
        else{
            log.info("Error adding Cat Entity to database: " + database.getClass().getName() + " with entity: "+ cat.toString());
            throw new Exception("Error insertiong cat");
        }
    }

    @Override
    public boolean removeEntity(long id)throws Exception {
        
        log.info("Removing Cat Entity in database: " + database.getClass().getName() + " with id: "+ String.valueOf(id));

        Cat cat = database.getCatList().stream().filter(cats -> id == cats.getId()).findFirst().orElse(null);

        if(cat != null){
            boolean check = database.getCatList().remove(cat);
            if(check){
                return check;
            }else{
                log.info("Error removing Cat Entity to database: " + database.getClass().getName() + " with entity: "+ cat.toString());
                throw new Exception("Error removing cat");
            }
        }else{
            log.info("Error finding Cat Entity to database: " + database.getClass().getName() + " with entity: "+ String.valueOf(id));
            throw new Exception("Error finding cat");
        }

        
        
    }

    @Override
    public Cat getEntity(long id) throws Exception {
        log.info("Finding Cat Entity in database: " + database.getClass().getName() + " with id: "+ String.valueOf(id));
         
        Cat cat = database.getCatList().stream().filter(cats -> id == cats.getId()).findFirst().orElse(null);
       
        if(cat != null){
            return cat;
        }
        else{
            log.info("Error adding Cat Entity to database: " + database.getClass().getName() + " with id: " + String.valueOf(id));
            throw new Exception("Did not find cat with id: " + String.valueOf(id) );
        }
    }

    @Override
    public List<Cat> getAllEntities() throws Exception {
        log.info("Finding Cats Entity in database: " + database.getClass().getName() );
         
        List<Cat> cats = database.getCatList();
       
        return cats;
    }

    @Override
    public Cat updateEntity(long id, Cat cat) throws Exception {
        log.info("Updating Cat Entity in database: " + database.getClass().getName() + " with entity: "+ cat.toString() );
        
        Cat findCat = database.getCatList().stream().filter(cats -> id == cats.getId()).findFirst().orElse(null);

        if(findCat != null){
            int index = database.getCatList().indexOf(findCat);
            Cat newCat = database.getCatList().set(index, cat);
            
            return newCat;
           
        }else{
            log.info("Error finding Cat Entity to database: " + database.getClass().getName() + " with entity: "+ String.valueOf(cat.getId()));
            throw new Exception("Error finding cat");
        }

    }
}
