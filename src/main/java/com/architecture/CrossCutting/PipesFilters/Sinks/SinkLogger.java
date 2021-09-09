package com.architecture.CrossCutting.PipesFilters.Sinks;

import com.architecture.CrossCutting.PipesFilters.Pipes.IPipe;

public class SinkLogger<I> extends ISink<I> {
    
    public SinkLogger(IPipe<I> input) {
        super(input);
    }

    @Override
    public void takeFrom(IPipe<I> pipe) {
        try {
            I in;
            while ((in = pipe.nextOrNullIfEmptied()) != null) {
                System.out.println(in);
            }
            System.out.println("sink finished");
        } catch (InterruptedException e) {
            System.err.println("interrupted");
            e.printStackTrace();
        } 
    }



}
