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

public class ValidationFilter extends AbstractFilter{

    public ValidationFilter(IPipe<Object> input, IPipe<Object> output) {
        super(input, output);
    }

    @Override
    protected Object transformOne(Object in) throws CustomExceptions {
        
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Object>> violations = validator.validate(in);

        List<String> erros = new ArrayList<String>();

        for (ConstraintViolation<Object> violation : violations) {
            erros.add(violation.getMessage());
        }
        if(erros.size() != 0){
            return erros;
        //    throw new CustomExceptions(erros);
        }

        return in;
        
    }


}
