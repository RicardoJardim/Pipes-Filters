package com.architecture.Data.Factories;

import com.architecture.Domain.Entities.ICat;

public abstract class AbstractCatFactory{

    

    public abstract ICat CreateObject(long id, String title,String description, double price);

    public ICat CreateCat(long id, String title,String description, double price)
    {
        
        ICat product = CreateObject(id, title, description, price);
        // Now, use the product.
        System.out.println("Created Object "+ product.toString());

        return product;
    }

    public abstract ICat CreateObject(String title,String description, double price);
    
    public ICat CreateCat(String title,String description, double price)
    {
        
        ICat product = CreateObject(title, description, price);
        // Now, use the product.
        System.out.println("Created Object "+ product.toString());

        return product;
    }

}