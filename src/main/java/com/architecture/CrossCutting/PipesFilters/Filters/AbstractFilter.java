package com.architecture.CrossCutting.PipesFilters.Filters;

import java.util.List;

import com.architecture.CrossCutting.CustomExceptions;
import com.architecture.CrossCutting.PipesFilters.Threads;
import com.architecture.CrossCutting.PipesFilters.Pipes.IPipe;

public abstract class AbstractFilter<I, O> extends Threads {
    protected IPipe<I> input;
	protected IPipe<O> output;

	public AbstractFilter(IPipe<I> input, IPipe<O> output) {
		this.input = input;
		this.output = output;
	}

	@Override
	public void run() {
		try {
            I in;
            while ((in = input.nextOrNullIfEmptied()) != null) {
				O out = transformOne(in);
				output.put(out);
            }
        } catch (InterruptedException e) {
            System.out.println(e);
            e.printStackTrace();
            return;
        }catch(CustomExceptions erros){
			throw new RuntimeException(erros.getMessage());
		}
        output.closeForWriting();
	}

    protected abstract O transformOne(I in);
}
