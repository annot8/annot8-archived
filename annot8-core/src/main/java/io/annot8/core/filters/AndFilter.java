package io.annot8.core.filters;

import io.annot8.core.annotations.Annotation;
import io.annot8.core.filters.Filter;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Logical AND of filters
 *
 * @param <T> element type
 */
public class AndFilter<T> implements Filter<T> {

    private final Filter<T>[] filters;

    public AndFilter(Filter<T>... filters) {
        this.filters = filters;
    }

    @Override
    public boolean test(T t) {
        return Arrays.stream(filters).allMatch(f -> f.test(t));
    }

    /**
     * Get all the sub filters of this operation.
     * @return filters
     */
    public Stream<Filter> getFilters() {
        return Arrays.stream(filters);
    }
}
