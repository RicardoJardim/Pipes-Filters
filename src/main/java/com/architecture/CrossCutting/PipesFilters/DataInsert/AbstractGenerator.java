package com.architecture.CrossCutting.PipesFilters.DataInsert;

import com.architecture.CrossCutting.PipesFilters.Pipes.IPipe;

public abstract class AbstractGenerator<T,I> implements Runnable  {
    protected IPipe<T> output;
    protected I in;

    public AbstractGenerator(IPipe<T> output, I in) {
        this.output = output;
        this.in = in;
    }

    @Override
    public void run() {
        generateInto(output, in);
    }

    public abstract void generateInto(IPipe<T> pipe , I in);
}
