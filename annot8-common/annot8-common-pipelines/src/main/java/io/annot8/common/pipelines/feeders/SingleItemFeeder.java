/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.feeders;

import io.annot8.common.implementations.listeners.Deregister;
import io.annot8.common.implementations.listeners.Listeners;
import io.annot8.common.pipelines.events.SourceEvent;
import io.annot8.common.pipelines.events.source.SourceDoneEvent;
import io.annot8.common.pipelines.events.source.SourceEmptyEvent;
import io.annot8.common.pipelines.events.source.SourceErrorEvent;
import io.annot8.common.pipelines.events.source.SourceReadEvent;
import io.annot8.common.pipelines.listeners.SourceListener;
import io.annot8.core.components.Source;
import io.annot8.core.components.responses.SourceResponse;
import io.annot8.core.data.ItemFactory;
import io.annot8.core.helpers.WithProcessItem;

public class SingleItemFeeder implements ItemFeeder {

  private final ItemFactory itemFactory;
  private final Source source;

  private final Listeners<SourceListener, SourceEvent> listeners =
      new Listeners<>(SourceListener::onSourceEvent);

  public SingleItemFeeder(ItemFactory itemFactory, Source source) {
    this.itemFactory = itemFactory;
    this.source = source;
  }

  public void feed(WithProcessItem pipeline) {
    SourceResponse.Status status;
    do {
      final SourceResponse response = source.read(itemFactory);
      status = response.getStatus();

      switch (status) {
        case OK:
          listeners.fire(new SourceReadEvent(source));
          break;
        case EMPTY:
          listeners.fire(new SourceEmptyEvent(source));
          break;
        case DONE:
          listeners.fire(new SourceDoneEvent(source));
          break;
        case SOURCE_ERROR:
          listeners.fire(new SourceErrorEvent(source));
          break;
      }

    } while (status == SourceResponse.Status.OK || status == SourceResponse.Status.EMPTY);

    close();
  }

  public void close() {
    source.close();
    listeners.clear();
  }

  @Override
  public Deregister register(SourceListener listener) {
    return listeners.register(listener);
  }

  @Override
  public void deregister(SourceListener listener) {
    listeners.deregister(listener);
  }
}
