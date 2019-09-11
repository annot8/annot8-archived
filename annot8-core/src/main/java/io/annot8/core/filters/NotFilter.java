package io.annot8.core.filters;

import io.annot8.core.filters.Filter;

public class NotFilter<T> implements Filter<T> {

    private final Filter<T> filter;

    public NotFilter(Filter<T> filter) {
        this.filter = filter;
    }

    @Override
    public boolean test(T t) {
        return !filter.test(t);
    }
}
