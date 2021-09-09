package com.architecture.CrossCutting.PipesFilters.Sinks;

import com.architecture.CrossCutting.PipesFilters.Threads;
import com.architecture.CrossCutting.PipesFilters.Pipes.IPipe;


public abstract class ISink<T> extends Threads {
    protected IPipe<T> input;

    public ISink(IPipe<T> input) {
        this.input = input;
    }

    @Override
    public void run() {
        takeFrom(input);
    }

    public abstract void takeFrom(IPipe<T> pipe);
}
