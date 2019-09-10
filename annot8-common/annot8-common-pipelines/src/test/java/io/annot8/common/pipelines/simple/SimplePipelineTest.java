/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.simple;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.clearInvocations;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import io.annot8.common.implementations.context.SimpleContext;
import io.annot8.common.pipelines.elements.Pipe;
import io.annot8.common.pipelines.elements.Pipeline;
import io.annot8.common.pipelines.queues.MemoryItemQueue;
import io.annot8.core.components.Source;
import io.annot8.core.components.responses.SourceResponse;
import io.annot8.core.context.Context;
import io.annot8.core.data.Item;
import io.annot8.core.data.ItemFactory;
import io.annot8.core.exceptions.Annot8Exception;

@ExtendWith(MockitoExtension.class)
class SimplePipelineTest {

  @Mock BaseItemFactory itemFactory;

  @Mock Source source;

  @Mock Item item;

  @Mock Pipe pipe;

  @Test
  void perform() throws Annot8Exception {

    when(source.read(any())).thenReturn(SourceResponse.ok(), SourceResponse.done());

    final Pipeline pipeline =
        new SimplePipelineBuilder()
            .withName("test")
            .withQueue(new MemoryItemQueue())
            .withItemFactory(itemFactory)
            .addSource(source)
            .addPipe(pipe)
            .build();

    assertThat(pipeline.getName()).isEqualTo("test");

    Context context = new SimpleContext();
    pipeline.configure(context);

    verify(source).configure(any(Context.class));
    verify(pipe).configure(any(Context.class));

    clearInvocations(source, pipe);

    pipeline.run();
    verify(source, times(2)).read(any(ItemFactory.class));
    // TODO no way of checking this: verify(pipe).process(item);

    clearInvocations(source, pipe);

    pipeline.close();
    verify(source).close();
    verify(pipe).close();
  }
}
