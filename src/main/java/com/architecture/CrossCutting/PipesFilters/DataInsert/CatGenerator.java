package com.architecture.CrossCutting.PipesFilters.DataInsert;

import com.architecture.CrossCutting.PipesFilters.Pipes.IPipe;
import com.architecture.Entities.Cat;

public class CatGenerator extends Generator<Object,Cat> {
    public CatGenerator(IPipe<Object> output, Cat in) {
        super(output, in);
    }

    @Override
    public void generateInto(IPipe<Object> pipe, Cat in) {
        pipe.put(in);
        pipe.closeForWriting();
        System.out.println("generator finished");
        
    }
}
