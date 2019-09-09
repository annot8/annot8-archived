/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.annot8.common.implementations.listeners.Deregister;
import io.annot8.common.implementations.listeners.Listeners;
import io.annot8.common.pipelines.elements.Pipe;
import io.annot8.common.pipelines.events.PipeEvent;
import io.annot8.common.pipelines.listeners.PipeListener;

public abstract class AbstractPipe implements Pipe {

  private static final Logger LOGGER = LoggerFactory.getLogger(AbstractPipe.class);

  private final Listeners<PipeListener, PipeEvent> listeners =
      new Listeners<>(PipeListener::onPipeEvent);
  private final String name;

  public AbstractPipe(String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }

  public Deregister register(PipeListener listener) {
    return listeners.register(listener);
  }

  public void deregister(PipeListener listener) {
    listeners.deregister(listener);
  }

  protected void fire(PipeEvent event) {
    listeners.fire(event);
  }

  @Override
  public void close() {}
}
