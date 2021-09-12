package com.architecture.Data.Factories;

import com.architecture.Entities.Cat;
import com.architecture.Entities.ICat;

public final class CatFactory extends AbstractCatFactory {

    private static volatile CatFactory instance;
   
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

    public ICat CreateObject(String title,String description, double price) {

        return new Cat(0, title, description, price);
    }

    public ICat CreateObject(long id, String title,String description, double price) {

        return new Cat(id, title, description, price);
    }
    
    
}

