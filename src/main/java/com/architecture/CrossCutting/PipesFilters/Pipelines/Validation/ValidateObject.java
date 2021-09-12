package com.architecture.CrossCutting.PipesFilters.Pipelines.Validation;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.architecture.CrossCutting.PipesFilters.DataInsert.AbstractGenerator;
import com.architecture.CrossCutting.PipesFilters.DataInsert.Generator;
import com.architecture.CrossCutting.PipesFilters.Filters.AbstractFilter;
import com.architecture.CrossCutting.PipesFilters.Pipes.IPipe;
import com.architecture.CrossCutting.PipesFilters.Pipes.Pipe;
import com.architecture.CrossCutting.PipesFilters.Sinks.ISink;
import com.architecture.CrossCutting.PipesFilters.Filters.ValidationFilter;

public abstract class ValidateObject<T> {
   private IPipe<T> genToFilter;
   private IPipe<T> filterToOut;
   private ExecutorService executorService;
   private AbstractGenerator<T,T> generator;
   private AbstractFilter<T,T> filter;
   private ISink<T> sink;

   protected  void setupPipeline(long id, T httpObject) throws Exception{
        genToFilter = new Pipe<T>();
        filterToOut = new Pipe<T>();
        executorService = Executors.newFixedThreadPool(10);
        generator = new Generator(genToFilter,httpObject);
        filter = new ValidationFilter(genToFilter, filterToOut);
    }

    protected  T runPipeline() throws Exception{
        executorService.execute(generator);
            
        executorService.submit(filter).get();

        Future<Object> future = executorService.submit(sink);

        Object result = future.get();

        executorService.shutdown();
        return (T) result; 
    }
    
    protected abstract ISink<T> ChooseSink(IPipe<T> filterToOut, long id) throws Exception;


    public T execute(long id, T httpObject) throws Exception {

        this.setupPipeline(id, httpObject);
        sink = this.ChooseSink(filterToOut,id);
        T result = runPipeline();
        return result;
    }

    
    
}

