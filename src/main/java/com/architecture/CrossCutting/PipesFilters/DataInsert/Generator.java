package com.architecture.CrossCutting.PipesFilters.DataInsert;

import com.architecture.CrossCutting.PipesFilters.Pipes.IPipe;

public class Generator<I> extends AbstractGenerator<I,I> {
    public Generator(IPipe<I> output, I in) {
        super(output, in);
    }

    @Override
    public void generateInto(IPipe<I> pipe, I in) {
        pipe.put(in);
        pipe.closeForWriting();
        System.out.println("generator finished");
    }
}
