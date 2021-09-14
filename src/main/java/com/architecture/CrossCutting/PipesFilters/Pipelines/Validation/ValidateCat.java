package com.architecture.CrossCutting.PipesFilters.Pipelines.Validation;

import com.architecture.CrossCutting.PipesFilters.Pipes.IPipe;
import com.architecture.CrossCutting.PipesFilters.Sinks.ISink;
import com.architecture.CrossCutting.PipesFilters.Sinks.SinkCat;
import com.architecture.Domain.Entities.Cat;

public class ValidateCat extends ValidateObject<Cat> {


    @Override
    protected ISink<Cat> ChooseSink(IPipe<Cat> filterToOut, long id) throws Exception {
        return new SinkCat(filterToOut,id);
    }

}