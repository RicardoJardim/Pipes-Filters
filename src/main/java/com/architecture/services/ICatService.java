package com.architecture.Services;
import java.util.*;

import com.architecture.Entities.Cat;

public interface ICatService {
    public Cat addCat(String title,String description, double pric) throws Exception;

    public boolean removeCat(long id) throws Exception;

    public Cat getCat(long id) throws Exception;

    public List<Cat> getAllCats() throws Exception;

    public Cat updateCat(long id, String title,String description, double pric) throws Exception;
}
