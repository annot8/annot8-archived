/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.events.pipe;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.lang.reflect.InvocationTargetException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import io.annot8.common.pipelines.elements.Pipe;
import io.annot8.common.pipelines.events.PipeEvent;
import io.annot8.core.components.Processor;
import io.annot8.core.components.responses.ProcessorResponse;
import io.annot8.core.data.Item;

class PipeEventsTest {

  @ParameterizedTest
  @ValueSource(
    classes = {
      ItemDiscardedPipeEvent.class,
      ItemEnteredPipeEvent.class,
      ItemErrorPipeEvent.class,
      ItemExitedPipeEvent.class
    }
  )
  void testNew(Class<PipeEvent> clazz)
      throws NoSuchMethodException, IllegalAccessException, InvocationTargetException,
          InstantiationException {
    final Item item = mock(Item.class);
    final Pipe pipe = mock(Pipe.class);

    final AbstractItemPipeEvent event =
        (AbstractItemPipeEvent)
            clazz.getConstructor(Pipe.class, Item.class).newInstance(pipe, item);
    assertThat(event.getItem()).isEqualTo(item);
    assertThat(event.getPipe()).isEqualTo(pipe);
  }

  @Test
  void testNewBeforeItem() {
    final Item item = mock(Item.class);
    final Pipe pipe = mock(Pipe.class);
    final Processor processor = mock(Processor.class);

    final BeforeItemProcessedPipeEvent event =
        new BeforeItemProcessedPipeEvent(pipe, item, processor);
    assertThat(event.getItem()).isEqualTo(item);
    assertThat(event.getPipe()).isEqualTo(pipe);
    assertThat(event.getProcessor()).isEqualTo(processor);
  }

  @Test
  void testNewAfterItem() {
    final Item item = mock(Item.class);
    final Pipe pipe = mock(Pipe.class);
    final Processor processor = mock(Processor.class);
    final ProcessorResponse response = ProcessorResponse.ok();

    final AfterItemProcessedPipeEvent event =
        new AfterItemProcessedPipeEvent(pipe, item, processor, response);
    assertThat(event.getItem()).isEqualTo(item);
    assertThat(event.getPipe()).isEqualTo(pipe);
    assertThat(event.getProcessor()).isEqualTo(processor);
    assertThat(event.getResponse()).isEqualTo(response);
  }
}
