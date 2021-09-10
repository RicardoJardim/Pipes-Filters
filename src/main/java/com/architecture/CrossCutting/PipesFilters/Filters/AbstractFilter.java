package com.architecture.CrossCutting.PipesFilters.Filters;

import java.util.List;

import com.architecture.CrossCutting.CustomExceptions;
import com.architecture.CrossCutting.PipesFilters.Threads;
import com.architecture.CrossCutting.PipesFilters.Pipes.IPipe;

public abstract class AbstractFilter extends Threads {
    protected IPipe<Object> input;
	protected IPipe<Object> output;

	public AbstractFilter(IPipe<Object> input, IPipe<Object> output) {
		this.input = input;
		this.output = output;
	}

	@Override
	public void run() {
		try {
            Object in;
            while ((in = input.nextOrNullIfEmptied()) != null) {
				Object out = transformOne(in);
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

    protected abstract Object transformOne(Object in);
}
