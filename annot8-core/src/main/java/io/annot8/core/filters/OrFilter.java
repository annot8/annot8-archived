package io.annot8.core.filters;

import java.util.Arrays;

public class OrFilter<T> implements Filter<T> {

    private Filter<T>[] filters;

    public OrFilter(Filter<T>... filters) {
        this.filters = filters;
    }

    @Override
    public boolean test(T t) {
        return Arrays.stream(filters).anyMatch(f -> f.test(t));
    }
}
