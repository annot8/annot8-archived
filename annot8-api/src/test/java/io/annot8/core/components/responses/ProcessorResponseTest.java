/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.core.components.responses;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Test;

import io.annot8.core.components.responses.ProcessorResponse.Status;

public class ProcessorResponseTest {

  @Test
  public void testOk() {
    ProcessorResponse pr = ProcessorResponse.ok();

    assertEquals(ProcessorResponse.Status.OK, pr.getStatus());
    assertFalse(pr.hasExceptions());
    assertEquals(0, pr.getExceptions().size());
  }

  @Test
  public void testItemError() {
    ProcessorResponse pr = ProcessorResponse.itemError();

    assertEquals(Status.ITEM_ERROR, pr.getStatus());
    assertFalse(pr.hasExceptions());
    assertEquals(0, pr.getExceptions().size());
  }

  @Test
  public void testItemErrorExceptionCollection() {
    Collection<Exception> ex = new ArrayList<>();
    ex.add(new IOException("Test exception"));
    ex.add(new IllegalArgumentException("Another test exception"));

    ProcessorResponse pr = ProcessorResponse.itemError(ex);

    assertEquals(Status.ITEM_ERROR, pr.getStatus());
    assertTrue(pr.hasExceptions());
    assertEquals(2, pr.getExceptions().size());
    assertEquals(ex, pr.getExceptions());
  }

  @Test
  public void testItemErrorExceptionArgs() {
    ProcessorResponse pr =
        ProcessorResponse.itemError(
            new IOException("Test exception"),
            new IllegalArgumentException("Another test exception"));

    assertEquals(Status.ITEM_ERROR, pr.getStatus());
    assertTrue(pr.hasExceptions());
    assertEquals(2, pr.getExceptions().size());
  }

  @Test
  public void testProcessingError() {
    ProcessorResponse pr = ProcessorResponse.processingError();

    assertEquals(Status.PROCESSOR_ERROR, pr.getStatus());
    assertFalse(pr.hasExceptions());
    assertEquals(0, pr.getExceptions().size());
  }

  @Test
  public void testProcessingErrorExceptionCollection() {
    Collection<Exception> ex = new ArrayList<>();
    ex.add(new IOException("Test exception"));
    ex.add(new IllegalArgumentException("Another test exception"));

    ProcessorResponse pr = ProcessorResponse.processingError(ex);

    assertEquals(Status.PROCESSOR_ERROR, pr.getStatus());
    assertTrue(pr.hasExceptions());
    assertEquals(2, pr.getExceptions().size());
    assertEquals(ex, pr.getExceptions());
  }

  @Test
  public void testProcessorErrorExceptionArgs() {
    ProcessorResponse pr =
        ProcessorResponse.processingError(
            new IOException("Test exception"),
            new IllegalArgumentException("Another test exception"));

    assertEquals(Status.PROCESSOR_ERROR, pr.getStatus());
    assertTrue(pr.hasExceptions());
    assertEquals(2, pr.getExceptions().size());
  }

  @Test
  public void testEqualsAndHashCode() {

    assertEquals(ProcessorResponse.ok(), ProcessorResponse.ok());
    assertEquals(ProcessorResponse.ok().hashCode(), ProcessorResponse.ok().hashCode());

    assertEquals(ProcessorResponse.itemError(), ProcessorResponse.itemError());
    assertEquals(
        ProcessorResponse.itemError().hashCode(), ProcessorResponse.itemError().hashCode());

    assertEquals(ProcessorResponse.processingError(), ProcessorResponse.processingError());
    assertEquals(
        ProcessorResponse.processingError().hashCode(),
        ProcessorResponse.processingError().hashCode());
  }
}
