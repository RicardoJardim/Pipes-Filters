package com.architecture.CrossCutting.PipesFiltersAkka;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.architecture.CrossCutting.CustomExceptions;
import com.architecture.CrossCutting.PipesFiltersAkka.Examples.FirstActor;

public class ValidateActor extends AbstractActor {
    
    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    public static Props props() {
        return Props.create(FirstActor.class);
    }

    public static final class Validade {
        Object line;

        public Validade(Object line) {
            this.line = line;
        }
    }

    @Override
    public void preStart() {
        log.info("Actor started");
    }

    @Override
    public void postStop() {
        log.info("Actor stopped");
    }

    // Messages will not be handled
    @Override
    public Receive createReceive() {
        return receiveBuilder()
        .match(Validade.class, r -> {
            System.out.println(r.line);
            try {
                log.info("Validator received: " + getSender());
                Object object = validate(r.line);
                getSender().tell(object, getSelf());
            } catch (Exception ex) {
                getSender().tell(new akka.actor.Status.Failure(ex), getSelf());   
                throw ex;
            }
        })
        .build();
    }

    private Object validate(Object in) throws Exception{
        if (in == null) {
            throw new CustomExceptions(new ArrayList<String>( Arrays.asList("Value cannot be null", "brian", "charles") ));
        }

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Object>> violations = validator.validate(in);

        List<String> erros = new ArrayList<String>();

        System.out.println(in);

        for (ConstraintViolation<Object> violation : violations) {
            System.out.println(violation.getMessage()); 
            erros.add(violation.getMessage());
        }
        if(erros.size() != 0){
            throw new CustomExceptions(erros);
        }

        return in;
    }
}
