package com.architecture.CrossCutting.PipesFilters.Sinks;

import com.architecture.CrossCutting.PipesFilters.Pipes.IPipe;
import com.architecture.Data.Factories.AbstractDogFactory;
import com.architecture.Data.Factories.DogFactory;
import com.architecture.Domain.Entities.Dog;

public class SinkDog extends ISink<Dog> {
    private static final AbstractDogFactory factory = DogFactory.getInstance();
    private long id;
    public SinkDog(IPipe<Dog> filterToOut, long id) {
        super(filterToOut);
        this.id = id;
    }

    @Override
    public Object takeFrom(IPipe<Dog> pipe) {
        try {
            Dog in;
            while ((in = pipe.nextOrNullIfEmptied()) != null) {
                    
                Dog cat = (Dog) factory.CreateObject(id, in.getTitle(), in.getDescription(), in.getPrice(),in.getPrice());

                return cat;
            }
            System.out.println("sink finished");
        } catch (InterruptedException e) {
            System.err.println("interrupted");
            e.printStackTrace();
        }
        return pipe; 
    }


}
