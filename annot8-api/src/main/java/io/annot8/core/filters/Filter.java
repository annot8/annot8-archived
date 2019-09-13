/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.core.filters;

/** Filter to test to elements against. */
@FunctionalInterface
public interface Filter<T> {

  /**
   * Predicate test
   *
   * @param t the element test against
   * @return true if matches
   */
  boolean test(T t);

  /**
   * Negate this filter.
   *
   * @return negated filter
   */
  default Filter<T> negate() {
    return new NotFilter<>(this);
  }

  /**
   * Combine this filter with another using logical AND
   *
   * @param filter the other filter
   * @return filter which performs AND operation
   */
  default Filter<T> and(Filter<T> filter) {
    return new AndFilter<>(this, filter);
  }

  /**
   * Combine this filter with another using logical OR
   *
   * @param filter the other filter
   * @return filter which performs OR operation
   */
  default Filter<T> or(Filter<T> filter) {
    return new OrFilter<>(this, filter);
  }
}
