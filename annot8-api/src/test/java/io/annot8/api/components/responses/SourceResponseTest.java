/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.api.components.responses;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import org.junit.jupiter.api.Test;

public class SourceResponseTest {

  @Test
  public void testOk() {
    SourceResponse sr = SourceResponse.ok();

    assertEquals(SourceResponse.Status.OK, sr.getStatus());
    assertFalse(sr.hasExceptions());
    assertEquals(0, sr.getExceptions().size());
  }

  @Test
  public void testDone() {
    SourceResponse sr = SourceResponse.done();

    assertEquals(SourceResponse.Status.DONE, sr.getStatus());
    assertFalse(sr.hasExceptions());
    assertEquals(0, sr.getExceptions().size());
  }

  @Test
  public void testSourceError() {
    SourceResponse sr = SourceResponse.sourceError();

    assertEquals(SourceResponse.Status.SOURCE_ERROR, sr.getStatus());
    assertFalse(sr.hasExceptions());
    assertEquals(0, sr.getExceptions().size());
  }

  @Test
  public void testSourceErrorExceptionCollection() {
    Collection<Exception> ex = new ArrayList<>();
    ex.add(new IOException("Test exception"));
    ex.add(new IllegalArgumentException("Another test exception"));

    SourceResponse sr = SourceResponse.sourceError(ex);

    assertEquals(SourceResponse.Status.SOURCE_ERROR, sr.getStatus());
    assertTrue(sr.hasExceptions());
    assertEquals(2, sr.getExceptions().size());
    assertEquals(ex, sr.getExceptions());
  }

  @Test
  public void testSourceErrorExceptionArgs() {
    SourceResponse sr =
        SourceResponse.sourceError(
            new IOException("Test exception"),
            new IllegalArgumentException("Another test exception"));

    assertEquals(SourceResponse.Status.SOURCE_ERROR, sr.getStatus());
    assertTrue(sr.hasExceptions());
    assertEquals(2, sr.getExceptions().size());
  }

  @Test
  public void testEmpty() {
    SourceResponse sr = SourceResponse.empty();

    assertEquals(SourceResponse.Status.EMPTY, sr.getStatus());
    assertFalse(sr.hasExceptions());
    assertEquals(0, sr.getExceptions().size());
  }

  @Test
  public void testEqualsAndHashCode() {

    assertEquals(SourceResponse.ok(), SourceResponse.ok());
    assertEquals(SourceResponse.ok().hashCode(), SourceResponse.ok().hashCode());

    assertEquals(SourceResponse.sourceError(), SourceResponse.sourceError());
    assertEquals(SourceResponse.sourceError().hashCode(), SourceResponse.sourceError().hashCode());

    assertEquals(SourceResponse.done(), SourceResponse.done());
    assertEquals(SourceResponse.done().hashCode(), SourceResponse.done().hashCode());

    assertEquals(SourceResponse.empty(), SourceResponse.empty());
    assertEquals(SourceResponse.empty().hashCode(), SourceResponse.empty().hashCode());
  }
}
