package com.architecture.CrossCutting.PipesFilters.Filters;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.architecture.CrossCutting.PipesFilters.Pipes.IPipe;
import com.architecture.CrossCutting.CustomExceptions;

public class ValidationFilter<I,O> extends AbstractFilter<I, I>{

    public ValidationFilter(IPipe<I> input, IPipe<I> output) {
        super(input, output);
    }

    @Override
    protected I transformOne(I in) throws CustomExceptions {
        
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<I>> violations = validator.validate(in);

        List<String> erros = new ArrayList<String>();

        for (ConstraintViolation<I> violation : violations) {
            System.out.println(violation.getMessage()); 
            erros.add(violation.getMessage());
        }
        if(erros.size() != 0){
            throw new CustomExceptions(erros);
        }

        return in;
        
    }


}
