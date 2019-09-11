package io.annot8.core.filters;

/**
 * Filter to test to elements against.
 */
@FunctionalInterface
public interface Filter<T> {

    /**
     * Predicate test
     *
     * @param t the element test against
     * @return true if matches
     */
    boolean test(T t);

    default Filter<T> negate() {
        return new NotFilter<>(this);
    }

    default Filter<T> and(Filter<T> filter) {
        return new AndFilter<>(this, filter);
    }

    default Filter<T> or(Filter<T> filter) {
        return new OrFilter<>(this, filter);
    }
}
