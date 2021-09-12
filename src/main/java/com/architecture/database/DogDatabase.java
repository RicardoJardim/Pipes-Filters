package com.architecture.Database;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.architecture.Entities.Dog;

public final class DogDatabase {
    
    private static volatile DogDatabase instance;

    private static final AtomicLong counter = new AtomicLong();

    private static List<Dog> dogList = new ArrayList<Dog>(); 

    private DogDatabase() {
    }

    public long incrementAndGetCounter() {
        return counter.incrementAndGet();
    }

    public List<Dog> getDogList() {
        return dogList;
    }

    public static DogDatabase getInstance() {

        DogDatabase result = instance;
        if (result != null) {
            return result;
        }
        synchronized(DogDatabase.class) {
            if (instance == null) {
                instance = new DogDatabase();
            }
            return instance;
        }
    }



}
