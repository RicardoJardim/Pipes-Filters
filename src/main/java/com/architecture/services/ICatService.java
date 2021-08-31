package com.architecture.services;
import com.architecture.entities.Cat;
import java.util.*;

public interface ICatService {
    public Cat addCat(String title,String description, double pric) throws Exception;

    public boolean removeCat(long id) throws Exception;

    public Cat getCat(long id) throws Exception;

    public List<Cat> getAllCats() throws Exception;

    public Cat updateCat(long id, String title,String description, double pric) throws Exception;
}
