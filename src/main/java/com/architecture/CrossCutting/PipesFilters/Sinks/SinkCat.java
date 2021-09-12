package com.architecture.CrossCutting.PipesFilters.Sinks;

import com.architecture.Entities.Cat;
import com.architecture.CrossCutting.PipesFilters.Pipes.IPipe;
import com.architecture.Data.Factories.AbstractCatFactory;
import com.architecture.Data.Factories.CatFactory;

public class SinkCat extends ISink<Cat> {
    private static final AbstractCatFactory factory = CatFactory.getInstance();
    private long id;
    public SinkCat(IPipe<Cat> filterToOut, long id) {
        super(filterToOut);
        this.id = id;
    }

    @Override
    public Object takeFrom(IPipe<Cat> pipe) {
        try {
            Cat in;
            while ((in = pipe.nextOrNullIfEmptied()) != null) {
                    
                Cat cat = (Cat) factory.CreateObject(id, in.getTitle(), in.getDescription(), in.getPrice());

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
