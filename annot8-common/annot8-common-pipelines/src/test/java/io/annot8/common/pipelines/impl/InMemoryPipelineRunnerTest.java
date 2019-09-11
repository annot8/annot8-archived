package io.annot8.common.pipelines.impl;

import io.annot8.common.pipelines.Pipeline;
import io.annot8.core.components.Processor;
import io.annot8.core.components.Source;
import io.annot8.core.components.responses.ProcessorResponse;
import io.annot8.core.components.responses.SourceResponse;
import io.annot8.core.data.Item;
import io.annot8.core.data.ItemFactory;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.Answer;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class InMemoryPipelineRunnerTest {
  @Test
  public void test(){

    ItemFactory itemFactory = mock(ItemFactory.class);
    when(itemFactory.create()).thenReturn(mock(Item.class));

    //In total, creates 5 items
    Source source1 = mock(Source.class);
    Source source2 = mock(Source.class);
    when(source1.read(any())).thenAnswer((Answer<SourceResponse>) invocationOnMock -> {
      invocationOnMock.getArgument(0, ItemFactory.class).create();
      return SourceResponse.ok();
    }).thenAnswer((Answer<SourceResponse>) invocationOnMock -> {
      invocationOnMock.getArgument(0, ItemFactory.class).create();
      return SourceResponse.ok();
    }).thenAnswer((Answer<SourceResponse>) invocationOnMock -> {
      invocationOnMock.getArgument(0, ItemFactory.class).create();
      return SourceResponse.ok();
    }).thenAnswer((Answer<SourceResponse>) invocationOnMock -> {
      invocationOnMock.getArgument(0, ItemFactory.class).create();
      return SourceResponse.done();
    });
    when(source2.read(any()))
    .thenReturn(SourceResponse.empty())
    .thenAnswer((Answer<SourceResponse>) invocationOnMock -> {
      invocationOnMock.getArgument(0, ItemFactory.class).create();
      return SourceResponse.ok();
    })
    .thenReturn(SourceResponse.sourceError());

    Processor processor1 = mock(Processor.class);
    Processor processor2 = mock(Processor.class);
    when(processor1.process(any())).thenReturn(ProcessorResponse.ok(), ProcessorResponse.itemError(), ProcessorResponse.ok(), ProcessorResponse.ok(), ProcessorResponse.ok());
    when(processor2.process(any())).thenReturn(ProcessorResponse.ok(), ProcessorResponse.ok(), ProcessorResponse.processingError());

    Pipeline pipeline = mock(Pipeline.class);
    when(pipeline.getSources()).thenReturn(Arrays.asList(source1, source2));
    when((pipeline.getProcessors())).thenReturn(Arrays.asList(processor1, processor2));

    InMemoryPipelineRunner runner = new InMemoryPipelineRunner(pipeline, itemFactory);
    runner.run();

    verify(itemFactory, times(5)).create();
    verify(source1, times(4)).read(any());
    verify(source2, times(3)).read(any());
    verify(processor1, times(5)).process(any());
    verify(processor2, times(3)).process(any());
  }

}