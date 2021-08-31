package com.architecture.factory;

import com.architecture.entities.ICat;

public abstract class AbstractCatFactory{

    public abstract ICat CreateObject(String title,String description, double price);

    public ICat CreateCat(String title,String description, double price)
    {
        
        ICat product = CreateObject(title, description, price);
        // Now, use the product.
        System.out.println("Created Object "+ product.toString());

        return product;
    }

}