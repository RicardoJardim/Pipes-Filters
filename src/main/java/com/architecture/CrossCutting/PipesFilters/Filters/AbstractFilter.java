package com.architecture.CrossCutting.PipesFilters.Filters;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import com.architecture.CrossCutting.CustomExceptions;
import com.architecture.CrossCutting.PipesFilters.Pipes.IPipe;

public abstract class AbstractFilter implements Callable<Object> {
    protected IPipe<Object> input;
	protected IPipe<Object> output;

	public AbstractFilter(IPipe<Object> input, IPipe<Object> output) {
		this.input = input;
		this.output = output;
	}

    @Override
	public Object call() throws Exception {
		
		Object out = null;
		try {
			Object in;
            while ((in = input.nextOrNullIfEmptied()) != null) {
				out = transformOne(in);
				output.put(out);
				return out;
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            throw e;
        }catch(CustomExceptions erros){
			System.out.println(erros.getMessage());
			throw erros;
		}
		output.closeForWriting();
		Thread.currentThread().interrupt();
		return out;

    }

    protected abstract Object transformOne(Object in) throws CustomExceptions;

}
