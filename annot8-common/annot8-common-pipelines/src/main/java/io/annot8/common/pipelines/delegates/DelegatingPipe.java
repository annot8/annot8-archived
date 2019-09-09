/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.delegates;

import io.annot8.common.implementations.listeners.Deregister;
import io.annot8.common.pipelines.elements.Pipe;
import io.annot8.common.pipelines.listeners.PipeListener;
import io.annot8.core.capabilities.Capabilities.Builder;
import io.annot8.core.components.responses.ProcessorResponse;
import io.annot8.core.context.Context;
import io.annot8.core.data.Item;
import io.annot8.core.exceptions.Annot8Exception;
import io.annot8.core.exceptions.BadConfigurationException;
import io.annot8.core.exceptions.MissingResourceException;

public class DelegatingPipe implements Pipe {

  private final Pipe delegate;

  public DelegatingPipe(Pipe delegate) {
    this.delegate = delegate;
  }

  protected Pipe getDelegate() {
    return delegate;
  }

  @Override
  public Deregister register(PipeListener listener) {
    return delegate.register(listener);
  }

  @Override
  public void deregister(PipeListener listener) {
    delegate.deregister(listener);
  }

  @Override
  public String getName() {
    return delegate.getName();
  }

  @Override
  public ProcessorResponse process(Item item) throws Annot8Exception {
    return delegate.process(item);
  }

  @Override
  public void configure(Context context)
      throws BadConfigurationException, MissingResourceException {
    delegate.configure(context);
  }

  @Override
  public void close() {
    delegate.close();
  }

  @Override
  public void buildCapabilities(Builder builder) {
    delegate.buildCapabilities(builder);
  }
}
