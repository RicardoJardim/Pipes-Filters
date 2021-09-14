package com.architecture.Database;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.architecture.Domain.Entities.Cat;

public final class CatDatabase  {
    
    private static volatile CatDatabase instance;

    private static final AtomicLong counter = new AtomicLong();

    private static List<Cat> catList = new ArrayList<Cat>(); 

    private CatDatabase() {
    }

    public long incrementAndGetCounter() {
        return counter.incrementAndGet();
    }

    public List<Cat> getCatList() {
        return catList;
    }

    public static CatDatabase getInstance() {

        CatDatabase result = instance;
        if (result != null) {
            return result;
        }
        synchronized(CatDatabase.class) {
            if (instance == null) {
                instance = new CatDatabase();
            }
            return instance;
        }
    }



}
