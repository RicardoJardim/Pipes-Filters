package com.architecture.CrossCutting.PipesFilters.Sinks;

import com.architecture.CrossCutting.PipesFilters.Pipes.IPipe;

public class SinkObject<I> extends ISink<I> {
    
    public SinkObject(IPipe<I> input) {
        super(input);
    }

    @Override
    public Object takeFrom(IPipe<I> pipe) {
        try {
            I in;
            while ((in = pipe.nextOrNullIfEmptied()) != null) {
                System.out.println(in);
                return (Object) in;
            }
            System.out.println("sink finished");
        } catch (InterruptedException e) {
            System.err.println("interrupted");
            e.printStackTrace();
        }
        return pipe; 
    }




}
