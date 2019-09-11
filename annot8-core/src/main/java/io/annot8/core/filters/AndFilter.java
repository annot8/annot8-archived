package io.annot8.core.filters;

import io.annot8.core.annotations.Annotation;
import io.annot8.core.filters.Filter;

import java.util.Arrays;

public class AndFilter<T> implements Filter<T> {

    private Filter<T>[] filters;

    public AndFilter(Filter<T>... filters) {
        this.filters = filters;
    }

    @Override
    public boolean test(T t) {
        return Arrays.stream(filters).allMatch(f -> f.test(t));
    }
}
