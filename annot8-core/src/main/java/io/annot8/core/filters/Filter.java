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


}
