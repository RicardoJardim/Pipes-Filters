package com.architecture.Database;

import java.util.ArrayList;
import java.util.List;

import com.architecture.Entities.Cat;

public final class CatDatabase  {
    
    private static volatile CatDatabase instance;

    private static List<Cat> catList = new ArrayList<Cat>(); 

    private CatDatabase() {
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
