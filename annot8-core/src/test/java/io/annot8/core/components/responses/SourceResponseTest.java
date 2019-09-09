/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.core.components.responses;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SourceResponseTest {

  @Test
  public void testOk() {
    SourceResponse sr = SourceResponse.ok();

    assertEquals(SourceResponse.Status.OK, sr.getStatus());
  }

  @Test
  public void testDone() {
    SourceResponse sr = SourceResponse.done();

    assertEquals(SourceResponse.Status.DONE, sr.getStatus());
  }

  @Test
  public void testSourceError() {
    SourceResponse sr = SourceResponse.sourceError();

    assertEquals(SourceResponse.Status.SOURCE_ERROR, sr.getStatus());
  }

  @Test
  public void testEmpty() {
    SourceResponse sr = SourceResponse.empty();

    assertEquals(SourceResponse.Status.EMPTY, sr.getStatus());
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
