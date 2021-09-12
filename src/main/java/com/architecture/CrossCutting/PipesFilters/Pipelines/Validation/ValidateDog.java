package com.architecture.CrossCutting.PipesFilters.Pipelines.Validation;

import com.architecture.CrossCutting.PipesFilters.Pipes.IPipe;
import com.architecture.CrossCutting.PipesFilters.Sinks.ISink;
import com.architecture.CrossCutting.PipesFilters.Sinks.SinkDog;
import com.architecture.Entities.Dog;

public class ValidateDog extends ValidateObject<Dog> {


    @Override
    protected ISink<Dog> ChooseSink(IPipe<Dog> filterToOut, long id) throws Exception {
        return new SinkDog(filterToOut,id);
    }

}