package com.architecture.Domain.Services;
import java.util.*;

import com.architecture.Domain.Entities.Cat;

public interface ICatService {
    public Cat addCat(Cat cat) throws Exception;

    public Cat addCat(String title,String description, double pric) throws Exception;

    public boolean removeCat(long id) throws Exception;

    public Cat getCat(long id) throws Exception;

    public List<Cat> getAllCats() throws Exception;

    public Cat updateCat(long id, Cat cat) throws Exception;
}
