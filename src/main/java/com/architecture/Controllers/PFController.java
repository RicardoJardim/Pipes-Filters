package com.architecture.Controllers;


import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Flow;
import java.util.concurrent.TimeUnit;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.architecture.CrossCutting.CustomExceptions;
import com.architecture.CrossCutting.HttpDecorators.HttpDecorator;
import com.architecture.CrossCutting.HttpDecorators.HttpOkDecorator;
import com.architecture.CrossCutting.HttpDecorators.HttpErrorDecorator;
import com.architecture.CrossCutting.HttpDecorators.Objects.AbstractHttpObject;
import com.architecture.CrossCutting.HttpDecorators.Objects.HttpObjectError;
import com.architecture.CrossCutting.HttpDecorators.Objects.HttpObjectOk;
import com.architecture.CrossCutting.PipesFilters.DataInsert.Generator;
import com.architecture.CrossCutting.PipesFilters.Pipes.IPipe;
import com.architecture.CrossCutting.PipesFilters.Pipes.Pipe;
import com.architecture.CrossCutting.PipesFilters.Sinks.*;
import com.architecture.CrossCutting.PipesFilters.Filters.*;
import com.architecture.CrossCutting.PipesFilters.DataInsert.*;
import com.architecture.Entities.Cat;
import com.architecture.Services.ICatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.architecture.CrossCutting.PipesFiltersAkka.*;
import com.architecture.CrossCutting.PipesFiltersAkka.Examples.MyActor;
import com.architecture.CrossCutting.PipesFiltersAkka.Examples.ReadingActor;
import com.architecture.CrossCutting.PipesFiltersAkka.Examples.WordCounterActor;
import com.architecture.CrossCutting.PipesFiltersAkka.Examples.WordCounterActor.CountWords;

import static akka.pattern.PatternsCS.ask;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


// EXECUTE SOMETHING WITH THIS TYPE OF PIPELINE
// IPipe<Cat> genToFilter = new Pipe<Cat>();
// IPipe<Cat> filterToOut = new Pipe<Cat>();

// Generator<Cat,Cat> generator = new CatGenerator(genToFilter,catHttp);
// AbstractFilter<Cat,Cat> filter = new ValidationFilter(genToFilter, filterToOut);
// ISink<Cat> sink = new SinkLogger(filterToOut);  

// generator.start();
// filter.start(); 
// sink.start();

@RestController
@RequestMapping("/test")
public class PFController {
    

    @GetMapping("/akka/ask")
	public ResponseEntity<AbstractHttpObject> test() throws Exception {
        HttpDecorator httpResponse;

        ActorSystem system = ActorSystem.create("test-system");

        ActorRef wordCounterActorRef = system.actorOf(Props.create(WordCounterActor.class));

        CompletableFuture<Object> future =
                ask(wordCounterActorRef, new WordCounterActor.CountWords("this is a text"), 1000).toCompletableFuture();

       
        try {
            Integer numberOfWords = (Integer) future.get();
            httpResponse = new HttpOkDecorator(new HttpObjectOk(numberOfWords.toString()));
         } catch (ExecutionException e) {
             httpResponse = new HttpErrorDecorator(new HttpObjectError(400.0, e.getMessage()),HttpStatus.BAD_REQUEST);
         } 

        system.stop(wordCounterActorRef);

        return httpResponse.ReturnObjectMsg();

       
      
	}

    @GetMapping("/akka/tell")
	public ResponseEntity<AbstractHttpObject> testtell() throws Exception {
        HttpDecorator httpResponse;

        ActorSystem system = ActorSystem.create("test-system");

        ActorRef myActorRef = system.actorOf((Props.create(MyActor.class)));
        myActorRef.tell("printit", null);

        system.stop(myActorRef);

        httpResponse = new HttpErrorDecorator(new HttpObjectError(400.0, "sdas"),HttpStatus.BAD_REQUEST);
        return httpResponse.ReturnObjectMsg();
	}


    @GetMapping("/akka/ex")
	public ResponseEntity<AbstractHttpObject> testex() throws Exception {
        HttpDecorator httpResponse;

        ActorSystem system = ActorSystem.create("test-system");

        ActorRef wordCounterActorRef = system.actorOf(Props.create(WordCounterActor.class));

        CompletableFuture<Object> future =
                ask(wordCounterActorRef, new WordCounterActor.CountWords(null), 1000).toCompletableFuture();

        try {
           Object time =  future.get(1000, TimeUnit.MILLISECONDS);
           httpResponse = new HttpOkDecorator(new HttpObjectOk(time));
        } catch (ExecutionException e) {
            httpResponse = new HttpErrorDecorator(new HttpObjectError(400.0, e.getCause().getMessage()),HttpStatus.BAD_REQUEST);
        } 

        system.stop(wordCounterActorRef);

        return httpResponse.ReturnObjectMsg();
      
	}

    @GetMapping("/akka/test")
	public ResponseEntity<AbstractHttpObject> latex() throws Exception {
        HttpDecorator httpResponse;

        Cat cat = new Cat(1, "", "description", 1.4);
        ActorSystem system = ActorSystem.create("test-system");

        ActorRef validateObject = system.actorOf(Props.create(ValidateActor.class));

        CompletableFuture<Object> future =
                ask(validateObject, new ValidateActor.Validade(cat), 1000).toCompletableFuture();

        try {
           Object time =  future.get(2000, TimeUnit.MILLISECONDS);
           httpResponse = new HttpOkDecorator(new HttpObjectOk(time));
        } catch (ExecutionException e) {
            httpResponse = new HttpErrorDecorator(new HttpObjectError(400.0, e.getCause().getMessage()),HttpStatus.BAD_REQUEST);
        }catch (CustomExceptions e) {
            httpResponse = new HttpErrorDecorator(new HttpObjectError(400.0, e.getMessage()),HttpStatus.BAD_REQUEST);
        } 


        system.stop(validateObject);

        return httpResponse.ReturnObjectMsg();
      
	}


    @GetMapping("/akka/read")
	public ResponseEntity<AbstractHttpObject> testread() throws Exception {
        HttpDecorator httpResponse;

            ActorSystem system = ActorSystem.create("test-system");


            ActorRef myActorRef = system.actorOf(Props.create(MyActor.class), "my-actor");
            myActorRef.tell("printit", null);
    
            ActorRef readingActorRef = system.actorOf(ReadingActor.props("Lorem Ipsum is simply dummy text\n" +
            "of the printing and typesetting industry.\n" +
            "Lorem Ipsum has been the industry's standard dummy text\n" +
            "ever since the 1500s, when an unknown printer took a galley\n" +
            "of type and scrambled it to make a type specimen book.\n" +
            " It has survived not only five centuries, but also the leap\n" +
            "into electronic typesetting, remaining essentially unchanged.\n" +
            " It was popularised in the 1960s with the release of Letraset\n" +
            " sheets containing Lorem Ipsum passages, and more recently with\n" +
            " desktop publishing software like Aldus PageMaker including\n" +
            "versions of Lorem Ipsum."), "readingActor");
            readingActorRef.tell(new ReadingActor.ReadLines(), ActorRef.noSender());

     
        system.stop(readingActorRef);

        httpResponse = new HttpErrorDecorator(new HttpObjectError(400.0, "reading"),HttpStatus.BAD_REQUEST);

        return httpResponse.ReturnObjectMsg();
      
	}
    
    @PostMapping( value = "/akka/test", consumes = "application/json", produces = "application/json")
    public ResponseEntity<AbstractHttpObject> createCat(@RequestBody Cat catHttp) throws Exception {
 
        HttpDecorator httpResponse;

        Cat cat = new Cat(1, "", "description", 1.4);
        ActorSystem system = ActorSystem.create("test-system");

        ActorRef validateActor = system.actorOf(Props.create(ValidateActor.class));

        ActorRef catObject = system.actorOf(Props.create(CatSerializerActor.class));

   

        CompletableFuture<Object> future =
                ask(validateActor, new ValidateActor.Validade(cat), 1000).toCompletableFuture();

        try {
           Object time =  future.get(2000, TimeUnit.MILLISECONDS);
           httpResponse = new HttpOkDecorator(new HttpObjectOk(time));
        } catch (ExecutionException e) {
            httpResponse = new HttpErrorDecorator(new HttpObjectError(400.0, e.getCause().getMessage()),HttpStatus.BAD_REQUEST);
        }catch (CustomExceptions e) {
            httpResponse = new HttpErrorDecorator(new HttpObjectError(400.0, e.getMessage()),HttpStatus.BAD_REQUEST);
        } 


        system.stop(validateActor);

        return httpResponse.ReturnObjectMsg();
      
    }

  
}
