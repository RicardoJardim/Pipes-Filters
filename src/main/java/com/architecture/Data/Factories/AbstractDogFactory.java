package com.architecture.Data.Factories;

import com.architecture.Entities.IDog;

public abstract class AbstractDogFactory{

    public abstract IDog CreateObject(String title,String description, double pric,double size);

    public IDog CreateDog(String title,String description, double pric,double size)
    {
        
        IDog product = CreateObject(title,description, pric, size);
        // Now, use the product.
        System.out.println("Created Object "+ product.toString());

        return product;
    }

}