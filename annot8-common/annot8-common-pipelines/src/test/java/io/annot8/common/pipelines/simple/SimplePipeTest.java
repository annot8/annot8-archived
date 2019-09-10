/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.simple;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import io.annot8.common.implementations.context.SimpleContext;
import io.annot8.common.pipelines.elements.Pipe;
import io.annot8.core.components.Processor;
import io.annot8.core.components.responses.ProcessorResponse;
import io.annot8.core.context.Context;
import io.annot8.core.data.Item;
import io.annot8.core.exceptions.Annot8Exception;

@ExtendWith(MockitoExtension.class)
class SimplePipeTest {

  @Mock private Processor processor;

  @BeforeEach
  void beforeEach() throws Annot8Exception {
    when(processor.process(any())).thenReturn(ProcessorResponse.ok());
  }

  @Test
  void process() throws Annot8Exception {
    final Pipe pipe = new SimplePipeBuilder().withName("test").addProcessor(processor).build();

    assertThat(pipe.getName()).isEqualTo("test");

    SimpleContext context = new SimpleContext();
    pipe.configure(context);

    verify(processor).configure(any(Context.class));

    final Item item = mock(Item.class);

    pipe.process(item);

    verify(processor).process(item);

    pipe.close();
  }
}
