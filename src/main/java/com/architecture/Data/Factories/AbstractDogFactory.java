package com.architecture.Data.Factories;

import com.architecture.Domain.Entities.IDog;

public abstract class AbstractDogFactory{


    public abstract IDog CreateObject(long id, String title,String description, double price,double size);

    public IDog CreateCat(long id, String title,String description, double price,double size)
    {
        
        IDog product = CreateObject(id, title, description, price,size);
        // Now, use the product.
        System.out.println("Created Object "+ product.toString());

        return product;
    }

    public abstract IDog CreateObject(String title,String description, double pric,double size);

    public IDog CreateDog(String title,String description, double pric,double size)
    {
        
        IDog product = CreateObject(title,description, pric, size);
        // Now, use the product.
        System.out.println("Created Object "+ product.toString());

        return product;
    }

}