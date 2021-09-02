package com.architecture.Data.Factories;

import java.util.concurrent.atomic.AtomicLong;

import com.architecture.Entities.Dog;
import com.architecture.Entities.IDog;

public class DogFactory extends AbstractDogFactory {

    private static volatile DogFactory instance;

    private static final AtomicLong counter = new AtomicLong();
   
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
        return new Dog(counter.incrementAndGet(), title, description, pric,size);
    }

}

