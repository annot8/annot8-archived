/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.api.components.responses;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

/**
 * Class to hold the response from a source.
 *
 * <p>The response consists of a status.
 */
public interface SourceResponse {

  /**
   * Create source read items normally
   *
   * @return builder for continuation
   */
  static SourceResponseBuilder ok() {
    return new SourceResponseBuilder(Status.OK);
  }

  /**
   * Source is now out of items
   *
   * @return builder for continuation
   */
  static SourceResponseBuilder done() {
    return new SourceResponseBuilder(Status.DONE);
  }

  /**
   * There was an error reading from the Source
   *
   * @return builder for continuation
   */
  static SourceResponseBuilder sourceError() {
    return new SourceResponseBuilder(Status.SOURCE_ERROR);
  }

  /**
   * There was an error reading from the Source with a collection of exceptions
   *
   * @param exceptions exception for information
   * @return builder for continuation
   */
  static SourceResponseBuilder sourceError(Collection<Exception> exceptions) {
    return new SourceResponseBuilder(Status.SOURCE_ERROR, exceptions);
  }

  /**
   * There was an error reading from the Source with one or more exception
   *
   * @param exceptions exception for information
   * @return builder for continuation
   */
  static SourceResponseBuilder sourceError(Exception... exceptions) {
    return new SourceResponseBuilder(Status.SOURCE_ERROR, Arrays.asList(exceptions));
  }

  /**
   * The Source is currently empty
   *
   * @return builder for continuation
   */
  static SourceResponseBuilder empty() {
    return new SourceResponseBuilder(Status.EMPTY);
  }

  /**
   * Return the status associated with this response
   *
   * @return status
   */
  Status getStatus();

  /**
   * Return any exceptions associated with this response
   *
   * @return list of exceptions during read
   */
  Collection<Exception> getExceptions();

  /**
   * Does this response has exceptions associated with it?
   *
   * @return true is has exceptions
   */
  default boolean hasExceptions() {
    return !getExceptions().isEmpty();
  }

  /** Response status returned by the source */
  enum Status {
    /**
     * Indicates that the source found new items, and that the pipeline may ask the source for new
     * items again as soon as it is ready.
     */
    OK,

    /**
     * Indicates that an error occurred with the source. It is up to the pipeline to decide whether
     * to try again or not.
     */
    SOURCE_ERROR,

    /**
     * Indicates that the source has been exhausted, and will never return any new items. The
     * pipeline should stop asking for new items and terminate.
     */
    DONE,

    /**
     * Indicates that the source is temporarily empty, but that it may have new items in the future.
     */
    EMPTY
  }

  class SourceResponseBuilder implements SourceResponse {

    private final Status status;
    private final Collection<Exception> exceptions;

    protected SourceResponseBuilder(Status status) {
      this.status = status;
      this.exceptions = Collections.emptyList();
    }

    protected SourceResponseBuilder(Status status, Collection<Exception> exceptions) {
      this.status = status;
      this.exceptions = exceptions;
    }

    @Override
    public Status getStatus() {
      return status;
    }

    @Override
    public Collection<Exception> getExceptions() {
      return exceptions;
    }

    @Override
    public boolean hasExceptions() {
      return !exceptions.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      SourceResponseBuilder that = (SourceResponseBuilder) o;
      return status == that.status;
    }

    @Override
    public int hashCode() {
      return Objects.hash(status);
    }

    @Override
    public String toString() {
      return status.toString();
    }
  }
}
