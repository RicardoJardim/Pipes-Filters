package com.architecture.CrossCutting.PipesFilters.Sinks;

import java.util.concurrent.Callable;

import com.architecture.CrossCutting.PipesFilters.Threads;
import com.architecture.CrossCutting.PipesFilters.Pipes.IPipe;


public abstract class ISink<T> implements Callable<Object> {
    protected IPipe<T> input;
    public ISink(IPipe<T> input) {
        this.input = input;
    }


    public Object call() throws Exception {
       
        return takeFrom(input);
    }

    public abstract Object takeFrom(IPipe<T> pipe);

}
