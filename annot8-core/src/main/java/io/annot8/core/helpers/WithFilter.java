package io.annot8.core.helpers;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * General interface for filtering elements to find matches.
 *
 * @param <T> element type to find
 * @param <F> filter function
 */
public interface WithFilter<T,F extends Predicate<T>> {

    /**
     * Filter to many matching elements
     * @param filter the test to filter with
     * @return stream of matching annotations
     */
    Stream<T> filter(F filter);

    /**
     * Find a single matching element
     * @param filter the test to filter with
     * @return single of matching annotation (or empty)
     */
    default Optional<T> find(F filter) {
        return filter(filter).findAny();
    }

}
