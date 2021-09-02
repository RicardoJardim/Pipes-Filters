package com.architecture.Data.Factories;

import java.util.concurrent.atomic.AtomicLong;

import com.architecture.Entities.Cat;
import com.architecture.Entities.ICat;

public final class CatFactory extends AbstractCatFactory {

    private static volatile CatFactory instance;

    private static final AtomicLong counter = new AtomicLong();
   
    private CatFactory() {

    }

    
    public static CatFactory getInstance() {

        CatFactory result = instance;
        if (result != null) {
            return result;
        }
        synchronized(CatFactory.class) {
            if (instance == null) {
                instance = new CatFactory();
            }
            return instance;
        }
    }

    public ICat CreateObject(String title,String description, double pric) {
        return new Cat(counter.incrementAndGet(), title, description, pric);
    }
    
}

