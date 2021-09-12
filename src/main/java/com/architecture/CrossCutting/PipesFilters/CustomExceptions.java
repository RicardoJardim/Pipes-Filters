package com.architecture.CrossCutting.PipesFilters;

import java.util.List;

public class CustomExceptions extends RuntimeException  {

    private List<String> myStrings;

    public CustomExceptions(List<String> s) {
        this.myStrings = s;
    }

    public List<String> getMyStrings() {
        return myStrings;
    }

    public String getMessage() {
        return String.join(",", myStrings);
    }
}
