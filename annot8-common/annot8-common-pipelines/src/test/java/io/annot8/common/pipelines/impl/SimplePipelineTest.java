/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import io.annot8.common.pipelines.Pipeline;
import io.annot8.core.components.Processor;
import io.annot8.core.components.Source;
import io.annot8.core.exceptions.IncompleteException;

@ExtendWith(MockitoExtension.class)
public class SimplePipelineTest {

  private final String PIPELINE_NAME = "Test pipeline";
  private final String PIPELINE_DESCRIPTION = "Simple test pipeline";

  @Mock private Source testSource;
  @Mock private Processor testProcessor1;
  @Mock private Processor testProcessor2;

  @Test
  public void test() {
    Pipeline p =
        new SimplePipeline.Builder()
            .withName(PIPELINE_NAME)
            .withDescription(PIPELINE_DESCRIPTION)
            .withSource(testSource)
            .withProcessor(testProcessor1)
            .withProcessor(testProcessor2)
            .build();

    assertEquals(PIPELINE_NAME, p.getName());
    assertEquals(PIPELINE_DESCRIPTION, p.getDescription());
    assertEquals(1, p.getSources().size());
    assertEquals(2, p.getProcessors().size());
  }

  @Test
  public void testNoName() {
    Pipeline.Builder pb =
        new SimplePipeline.Builder()
            .withDescription(PIPELINE_DESCRIPTION)
            .withSource(testSource)
            .withProcessor(testProcessor1);

    assertThrows(IncompleteException.class, pb::build);
  }

  @Test
  public void testNoSource() {
    Pipeline.Builder pb =
        new SimplePipeline.Builder()
            .withName(PIPELINE_NAME)
            .withDescription(PIPELINE_DESCRIPTION)
            .withProcessor(testProcessor1);

    assertThrows(IncompleteException.class, pb::build);
  }

  @Test
  public void testNoProcessor() {
    Pipeline.Builder pb =
        new SimplePipeline.Builder()
            .withName(PIPELINE_NAME)
            .withDescription(PIPELINE_DESCRIPTION)
            .withSource(testSource);

    assertThrows(IncompleteException.class, pb::build);
  }

  @Test
  public void testFrom() {
    Pipeline p =
        new SimplePipeline.Builder()
            .withName(PIPELINE_NAME)
            .withDescription(PIPELINE_DESCRIPTION)
            .withSource(testSource)
            .withProcessor(testProcessor1)
            .build();

    Pipeline p2 = new SimplePipeline.Builder().from(p).withProcessor(testProcessor2).build();

    assertEquals(PIPELINE_NAME, p2.getName());
    assertEquals(PIPELINE_DESCRIPTION, p2.getDescription());
    assertEquals(1, p2.getSources().size());
    assertEquals(2, p2.getProcessors().size());
  }

  @Test
  public void testProcessorsArgs() {
    Pipeline p =
        new SimplePipeline.Builder()
            .withName(PIPELINE_NAME)
            .withDescription(PIPELINE_DESCRIPTION)
            .withSource(testSource)
            .withProcessors(testProcessor1, testProcessor2)
            .build();

    assertEquals(PIPELINE_NAME, p.getName());
    assertEquals(PIPELINE_DESCRIPTION, p.getDescription());
    assertEquals(1, p.getSources().size());
    assertEquals(2, p.getProcessors().size());
  }

  @Test
  public void testProcessorsCollection() {
    Pipeline p =
        new SimplePipeline.Builder()
            .withName(PIPELINE_NAME)
            .withDescription(PIPELINE_DESCRIPTION)
            .withSource(testSource)
            .withProcessors(Arrays.asList(testProcessor1, testProcessor2))
            .build();

    assertEquals(PIPELINE_NAME, p.getName());
    assertEquals(PIPELINE_DESCRIPTION, p.getDescription());
    assertEquals(1, p.getSources().size());
    assertEquals(2, p.getProcessors().size());
  }
}
