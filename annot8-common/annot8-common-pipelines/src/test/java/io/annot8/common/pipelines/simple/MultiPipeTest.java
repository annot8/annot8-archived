/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.simple;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import io.annot8.common.implementations.context.SimpleContext;
import io.annot8.common.pipelines.elements.Pipe;
import io.annot8.common.pipelines.listeners.PipeListener;
import io.annot8.core.components.responses.ProcessorResponse;
import io.annot8.core.context.Context;
import io.annot8.core.data.Item;
import io.annot8.core.exceptions.Annot8Exception;
import io.annot8.core.exceptions.BadConfigurationException;
import io.annot8.core.exceptions.MissingResourceException;

@ExtendWith(MockitoExtension.class)
class MultiPipeTest {

  @Mock private Pipe p1;

  @Mock private Pipe p2;

  @Mock private Item item;

  @Test
  void configure() throws BadConfigurationException, MissingResourceException {
    MultiPipe mp = new MultiPipe("test", Arrays.asList(p1, p2));

    assertThat(mp.getName()).isEqualTo("test");

    final Context context = new SimpleContext();
    mp.configure(context);

    verify(p1).configure(context);
    verify(p2).configure(context);

    mp.close();
  }

  @Test
  void process() throws Annot8Exception {
    when(p1.process(item)).thenReturn(ProcessorResponse.ok());
    when(p2.process(item)).thenReturn(ProcessorResponse.itemError());

    MultiPipe mp = new MultiPipe("test", Arrays.asList(p1, p2));

    assertThat(mp.process(item)).isEqualTo(ProcessorResponse.itemError());

    verify(p1).process(item);
    verify(p2).process(item);
  }

  @Test
  void close() {
    MultiPipe mp = new MultiPipe("test", Arrays.asList(p1, p2));

    mp.close();

    verify(p1).close();
    verify(p2).close();
  }

  @Test
  void register() throws Annot8Exception {
    when(p1.process(item)).thenReturn(ProcessorResponse.ok());
    when(p2.process(item)).thenReturn(ProcessorResponse.itemError());
    MultiPipe mp = new MultiPipe("test", Arrays.asList(p1, p2));

    final PipeListener listener = mock(PipeListener.class);
    mp.register(listener);

    mp.process(item);

    verify(p1).register(listener);
    verify(p2).register(listener);
  }
}
