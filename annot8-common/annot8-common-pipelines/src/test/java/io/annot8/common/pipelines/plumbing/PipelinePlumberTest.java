/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.plumbing;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import io.annot8.core.data.ItemFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import io.annot8.common.implementations.context.SimpleContext;
import io.annot8.common.pipelines.base.AbstractPipelineBuilder;
import io.annot8.common.pipelines.elements.Pipeline;
import io.annot8.common.pipelines.elements.PipelineBuilder;
import io.annot8.common.pipelines.queues.ItemQueue;
import io.annot8.common.pipelines.simple.SimpleBranch;
import io.annot8.common.pipelines.simple.SimpleBranchBuilder;
import io.annot8.common.pipelines.simple.SimpleMerge;
import io.annot8.common.pipelines.simple.SimpleMergeBuilder;
import io.annot8.common.pipelines.simple.SimplePipeBuilder;
import io.annot8.common.pipelines.simple.SimplePipelineBuilder;
import io.annot8.core.components.Processor;
import io.annot8.core.components.Source;
import io.annot8.core.components.responses.ProcessorResponse;
import io.annot8.core.components.responses.SourceResponse;
import io.annot8.core.context.Context;
import io.annot8.core.data.Item;
import io.annot8.core.exceptions.Annot8Exception;
import io.annot8.core.exceptions.BadConfigurationException;
import io.annot8.core.exceptions.IncompleteException;
import io.annot8.core.exceptions.MissingResourceException;

/** This is not really a unit test, since it uses many other classes. */
@ExtendWith(MockitoExtension.class)
class PipelinePlumberTest {

  // TODO: This should be a lot more comphrensive

  @Mock
  ItemFactory itemFactory;

  @Mock
  ItemQueue itemQueue;

  @Mock Item item;

  @Mock Source source;

  @Mock Processor defaultProcessor;

  @Mock Processor branchProcessor;

  @Mock Processor offBranchProcessor;

  @Mock Processor mergeProcessor;

  @BeforeEach
  void beforeEach() {
    when(itemQueue.hasItems()).thenReturn(true, false);
    when(itemQueue.next()).thenReturn(item, null);

    when(source.read(any())).thenReturn(SourceResponse.ok(), SourceResponse.done());
  }

  @Test
  void empty() throws IncompleteException, BadConfigurationException, MissingResourceException {
    Pipeline pipeline =
        newPipelineBuilder()
            // We need a processor for the default
            .addPipe(new SimplePipeBuilder().build())
            .build();

    run(pipeline);
  }

  @Test
  void simple() throws Annot8Exception {
    when(defaultProcessor.process(any(Item.class))).thenReturn(ProcessorResponse.ok());

    Pipeline pipeline =
        newPipelineBuilder()
            .addPipe(new SimplePipeBuilder().addProcessor(defaultProcessor).build())
            .build();

    run(pipeline);

    verify(defaultProcessor).process(any(Item.class));
    verify(defaultProcessor).close();
  }

  @Test
  void branching() throws Annot8Exception {
    when(defaultProcessor.process(any(Item.class))).thenReturn(ProcessorResponse.ok());
    when(branchProcessor.process(any(Item.class))).thenReturn(ProcessorResponse.ok());

    Pipeline pipeline =
        newPipelineBuilder()
            .addPipe(new SimplePipeBuilder().addProcessor(defaultProcessor).build())
            .addPipe("branched", new SimplePipeBuilder().addProcessor(branchProcessor).build())
            .addPipe(
                "offBranched", new SimplePipeBuilder().addProcessor(offBranchProcessor).build())
            .addBranch(
                new SimpleBranchBuilder()
                    .withInput(AbstractPipelineBuilder.DEFAULT_PIPE)
                    .withOutput("branched")
                    .use(new SimpleBranch(i -> "branched"))
                    .build())
            .build();

    run(pipeline);

    verify(defaultProcessor).configure(any(Context.class));
    verify(defaultProcessor).process(any(Item.class));
    verify(defaultProcessor).close();

    verify(branchProcessor).configure(any(Context.class));
    verify(branchProcessor).process(any(Item.class));
    verify(branchProcessor).close();

    verify(offBranchProcessor).configure(any(Context.class));
    verify(offBranchProcessor, never()).process(any(Item.class));
    verify(offBranchProcessor).close();
  }

  @Test
  void branchingAndMerging() throws Annot8Exception {

    when(defaultProcessor.process(any(Item.class))).thenReturn(ProcessorResponse.ok());
    when(branchProcessor.process(any(Item.class))).thenReturn(ProcessorResponse.ok());
    when(mergeProcessor.process(any(Item.class))).thenReturn(ProcessorResponse.ok());

    Pipeline pipeline =
        newPipelineBuilder()
            .addPipe(new SimplePipeBuilder().addProcessor(defaultProcessor).build())
            .addPipe("branched", new SimplePipeBuilder().addProcessor(branchProcessor).build())
            .addPipe(
                "offBranched", new SimplePipeBuilder().addProcessor(offBranchProcessor).build())
            .addPipe("merge", new SimplePipeBuilder().addProcessor(mergeProcessor).build())
            .addBranch(
                new SimpleBranchBuilder()
                    .withInput(AbstractPipelineBuilder.DEFAULT_PIPE)
                    .withOutput("branched")
                    .use(new SimpleBranch(i -> "branched"))
                    .build())
            .addMerge(
                new SimpleMergeBuilder()
                    .withInput("branched")
                    .withInput("offBranched")
                    .withOutput("merge")
                    .use(new SimpleMerge())
                    .build())
            .build();

    run(pipeline);

    verify(defaultProcessor).configure(any(Context.class));
    verify(defaultProcessor).process(any(Item.class));
    verify(defaultProcessor).close();

    verify(branchProcessor).configure(any(Context.class));
    verify(branchProcessor).process(any(Item.class));
    verify(branchProcessor).close();

    verify(offBranchProcessor).configure(any(Context.class));
    verify(offBranchProcessor, never()).process(any(Item.class));
    verify(offBranchProcessor).close();

    verify(mergeProcessor).configure(any(Context.class));
    verify(mergeProcessor).process(any(Item.class));
    verify(mergeProcessor).close();
  }

  private PipelineBuilder newPipelineBuilder() {
    return new SimplePipelineBuilder()
        .withName("test")
        .withItemFactory(itemFactory)
        .withQueue(itemQueue)
        .addSource(source);
  }

  private void run(Pipeline pipeline) throws BadConfigurationException, MissingResourceException {
    assertThat(pipeline.getName()).isEqualTo("test");
    assertThat(pipeline.getId()).isNotBlank();

    Context context = new SimpleContext();
    pipeline.configure(context);
    pipeline.run();
    pipeline.close();
  }
}
