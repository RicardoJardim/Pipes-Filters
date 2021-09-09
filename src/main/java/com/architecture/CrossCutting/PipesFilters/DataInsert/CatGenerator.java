package com.architecture.CrossCutting.PipesFilters.DataInsert;

import com.architecture.CrossCutting.PipesFilters.Pipes.IPipe;
import com.architecture.Entities.Cat;

public class CatGenerator extends Generator<Cat,Cat> {
    public CatGenerator(IPipe<Cat> output, Cat in) {
        super(output, in);
    }

    @Override
    public void generateInto(IPipe<Cat> pipe, Cat in) {
        pipe.put(in);
        pipe.closeForWriting();
        System.out.println("generator finished");
        
    }
}
