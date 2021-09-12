package com.architecture.CrossCutting.PipesFilters.Filters;

import java.util.concurrent.Callable;
import com.architecture.CrossCutting.PipesFilters.CustomExceptions;
import com.architecture.CrossCutting.PipesFilters.Pipes.IPipe;

public abstract class AbstractFilter<I,T> implements Callable<Object> {
	protected IPipe<I> input;
	protected IPipe<T> output;

	public AbstractFilter(IPipe<I> input, IPipe<T> output) {
		this.input = input;
		this.output = output;
	}

    @Override
	public T call() throws Exception {
		
		T out = null;
		try {
			I in;
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

    protected abstract T transformOne(I in) throws CustomExceptions;

}
