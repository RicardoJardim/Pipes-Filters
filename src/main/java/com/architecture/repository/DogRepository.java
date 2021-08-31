package com.architecture.repository;
import java.util.List;

import com.architecture.database.DogDatabase;
import com.architecture.entities.Dog;

 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;

public class DogRepository implements IRepository<Dog> {

    private static final DogDatabase database = DogDatabase.getInstance();
    private static final Logger log = LoggerFactory.getLogger(DogRepository.class);

    @Override
    public Dog addEntity(Dog Dog) throws Exception {

        log.info("Adding Dog Entity to database: " + database.getClass().getName() + " with entity: "+ Dog.toString());
         
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

        boolean check = database.getDogList().remove(dog);
        if(check){
            return check;
        }
        else{
            log.info("Error adding Cat Entity to database: " + database.getClass().getName() + " with id: "+ String.valueOf(id));
            throw new Exception("Error insertiong cat");
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
            log.info("Error adding Dog Entity to database: " + database.getClass().getName() + " with id: " + String.valueOf(id));
            throw new Exception("Did not find Dog with id: " + String.valueOf(id) );
        }
    }

    @Override
    public List<Dog> getAllEntities() throws Exception {
        log.info("Finding Dogs Entity in database: " + database.getClass().getName() );
         
        List<Dog> Dogs = database.getDogList();

        return Dogs;
    }

    @Override
    public Dog updateEntity(Dog dog) throws Exception {
        log.info("Updating Dog Entity in database: " + database.getClass().getName() + " with entity: "+ dog.toString() );
        
        Dog findDog = database.getDogList().stream().filter(cats -> dog.getId() == cats.getId()).findFirst().orElse(null);

        if(findDog != null){
            int index = database.getDogList().indexOf(findDog);
            Dog newDog = database.getDogList().set(index, dog);
            
            return newDog;
           
        }else{
            log.info("Error finding Cat Entity to database: " + database.getClass().getName() + " with entity: "+ dog);
            throw new Exception("Error finding cat");
        }
    }


}
