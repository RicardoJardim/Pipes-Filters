package com.architecture.Data.Factories;

import com.architecture.Entities.Dog;
import com.architecture.Entities.IDog;

public class DogFactory extends AbstractDogFactory {

    private static volatile DogFactory instance;
   
    private DogFactory() {

    }

    public static DogFactory getInstance() {

        DogFactory result = instance;
        if (result != null) {
            return result;
        }
        synchronized(DogFactory.class) {
            if (instance == null) {
                instance = new DogFactory();
            }
            return instance;
        }
    }

    public IDog CreateObject(String title,String description, double pric, double size) {
        return new Dog(0, title, description, pric,size);
    }

    public IDog CreateObject(long id, String title,String description, double price, double size) {

        return new Dog(id, title, description, price, size);
    }
}

