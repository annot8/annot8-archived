/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.feeders;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import io.annot8.common.implementations.listeners.Deregister;
import io.annot8.common.pipelines.listeners.SourceListener;
import io.annot8.core.components.Source;
import io.annot8.core.data.ItemFactory;
import io.annot8.core.helpers.WithProcessItem;

public class MultiItemFeeder implements ItemFeeder {

  private final ItemFactory itemFactory;
  private final Collection<Source> sources;

  private final Set<SourceListener> listeners = new CopyOnWriteArraySet<>();

  public MultiItemFeeder(ItemFactory itemFactory, Source... sources) {
    this(itemFactory, Arrays.asList(sources));
  }

  public MultiItemFeeder(ItemFactory itemFactory, Collection<Source> sources) {
    this.itemFactory = itemFactory;
    this.sources = sources;
  }

  @Override
  public void feed(WithProcessItem processor) {
    for (Source source : sources) {
      SingleItemFeeder feeder = new SingleItemFeeder(itemFactory, source);
      listeners.forEach(feeder::register);
      feeder.feed(processor);
    }
  }

  @Override
  public void close() {
    sources.forEach(Source::close);
  }

  // NOTE: these wont act on the current feeder

  @Override
  public Deregister register(SourceListener listener) {
    listeners.add(listener);
    return () -> listeners.remove(listener);
  }

  @Override
  public void deregister(SourceListener listener) {
    listeners.remove(listener);
  }
}
