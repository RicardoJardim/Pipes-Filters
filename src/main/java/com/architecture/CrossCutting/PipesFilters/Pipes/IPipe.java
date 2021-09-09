package com.architecture.CrossCutting.PipesFilters.Pipes;

public interface IPipe<T> {
    public boolean put(T obj);
    public T nextOrNullIfEmptied() throws InterruptedException;
    public void closeForWriting();
}
