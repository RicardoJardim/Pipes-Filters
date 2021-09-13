package com.architecture.Data.Repositories;
import java.util.List;

import com.architecture.Database.DogDatabase;
import com.architecture.Entities.Dog;

import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;

public class DogRepository implements IRepository<Dog> {

    private static final DogDatabase database = DogDatabase.getInstance();
    private static final Logger log = LoggerFactory.getLogger(DogRepository.class);

    @Override
    public Dog addEntity(Dog Dog) throws Exception {

        log.info("Adding Dog Entity to database: " + database.getClass().getName() + " with entity: "+ Dog.toString());
        
        Dog.setId(database.incrementAndGetCounter());

        boolean check = database.getDogList().add(Dog);
        if(check){
            return Dog;
        }
        else{
            log.info("Error adding Dog Entity to database: " + database.getClass().getName() + " with entity: "+ Dog.toString());
            throw new Exception("Error insertiong Dog");
        }
    }

    @Override
    public boolean removeEntity(long id)throws Exception {
       
        log.info("Removing Dog Entity in database: " + database.getClass().getName() + " with id: "+ String.valueOf(id));

        Dog dog = database.getDogList().stream().filter(dogs -> id == dogs.getId()).findFirst().orElse(null);

        if(dog != null){
            boolean check = database.getDogList().remove(dog);
            if(check){
                return check;
            }else{
                log.info("Error removing Dog Entity to database: " + database.getClass().getName() + " with entity: "+ dog.toString());
                throw new Exception("Error removing Dog");
            }
        }else{
            log.info("Error finding Dog Entity to database: " + database.getClass().getName() + " with entity: "+ String.valueOf(id));
            throw new Exception("Was not possible to find a Dog with the id of " + id);
        }
    }

    @Override
    public Dog getEntity(long id) throws Exception {
        log.info("Finding Dog Entity in database: " + database.getClass().getName() + " with id: "+ id);
         
        Dog Dog = database.getDogList().stream().filter(Dogs -> id == Dogs.getId()).findFirst().orElse(null);
       
        if(Dog != null){
            return Dog;
        }
        else{
            log.info("Error finding Dog Entity to database: " + database.getClass().getName() + " with id: " + String.valueOf(id));
            throw new Exception("Was not possible to find a Dog with the id of: " + id );
        }
    }

    @Override
    public List<Dog> getAllEntities() throws Exception {
        log.info("Finding Dogs Entity in database: " + database.getClass().getName() );
         
        List<Dog> Dogs = database.getDogList();

        return Dogs;
    }

    @Override
    public Dog updateEntity(long id, Dog dog) throws Exception {
        log.info("Updating Dog Entity in database: " + database.getClass().getName() + " with entity: "+ dog.toString() );
        
        Dog findDog = database.getDogList().stream().filter(dogs -> id == dogs.getId()).findFirst().orElse(null);

        if(findDog != null){
            int index = database.getDogList().indexOf(findDog);
            Dog newDog = database.getDogList().set(index, dog);
            
            return newDog;
           
        }else{
            log.info("Error finding Dog Entity to database: " + database.getClass().getName() + " with entity: "+ dog);
            throw new Exception("Was not possible to find a Dog with the id of: " + id);
        }
    }

}
