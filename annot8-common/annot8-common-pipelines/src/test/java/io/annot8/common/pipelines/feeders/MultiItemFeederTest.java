/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.feeders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import io.annot8.common.pipelines.events.source.SourceDoneEvent;
import io.annot8.common.pipelines.events.source.SourceReadEvent;
import io.annot8.common.pipelines.listeners.SourceListener;
import io.annot8.core.components.Source;
import io.annot8.core.components.responses.SourceResponse;
import io.annot8.core.data.ItemFactory;
import io.annot8.core.helpers.WithProcessItem;

@ExtendWith(MockitoExtension.class)
class MultiItemFeederTest {

  @Mock Source a;

  @Mock Source b;

  @Mock ItemFactory itemFactory;

  @Mock WithProcessItem processor;

  @Test
  void feed() {
    when(a.read(itemFactory)).thenReturn(SourceResponse.ok()).thenReturn(SourceResponse.done());
    when(b.read(itemFactory)).thenReturn(SourceResponse.done());

    MultiItemFeeder feeder = new MultiItemFeeder(itemFactory, a, b);
    feeder.feed(processor);

    verify(a, Mockito.times(2)).read(itemFactory);
    verify(b).read(itemFactory);

    feeder.close();
  }

  @Test
  void register() {
    when(a.read(itemFactory)).thenReturn(SourceResponse.ok()).thenReturn(SourceResponse.done());

    final SourceListener listener = mock(SourceListener.class);

    MultiItemFeeder feeder = new MultiItemFeeder(itemFactory, a);

    feeder.register(listener);

    feeder.feed(processor);

    verify(listener).onSourceEvent(any(SourceReadEvent.class));
    verify(listener).onSourceEvent(any(SourceDoneEvent.class));

    feeder.close();
  }
}
