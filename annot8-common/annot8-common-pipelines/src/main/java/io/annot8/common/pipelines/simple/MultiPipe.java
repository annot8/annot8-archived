/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.simple;

import java.util.List;

import io.annot8.common.implementations.listeners.Deregister;
import io.annot8.common.pipelines.elements.Pipe;
import io.annot8.common.pipelines.listeners.PipeListener;
import io.annot8.core.components.responses.ProcessorResponse;
import io.annot8.core.components.responses.ProcessorResponse.Status;
import io.annot8.core.context.Context;
import io.annot8.core.data.Item;
import io.annot8.core.exceptions.Annot8Exception;
import io.annot8.core.exceptions.BadConfigurationException;
import io.annot8.core.exceptions.MissingResourceException;

public class MultiPipe implements Pipe {

  private final String name;

  private final List<Pipe> pipes;

  public MultiPipe(String name, List<Pipe> pipes) {
    this.name = name;
    this.pipes = pipes;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void configure(Context context)
      throws BadConfigurationException, MissingResourceException {
    for (Pipe p : pipes) {
      p.configure(context);
    }
  }

  @Override
  public ProcessorResponse process(Item item) throws Annot8Exception {
    ProcessorResponse response = ProcessorResponse.ok();

    for (Pipe p : pipes) {
      response = p.process(item);
      Status status = response.getStatus();

      if (status == Status.ITEM_ERROR || status == Status.PROCESSOR_ERROR || item.isDiscarded()) {
        return response;
      }
    }

    return response;
  }

  @Override
  public void close() {
    pipes.forEach(Pipe::close);
  }

  @Override
  public Deregister register(PipeListener listener) {
    pipes.forEach(p -> p.register(listener));
    return () -> deregister(listener);
  }

  @Override
  public void deregister(PipeListener listener) {
    pipes.forEach(p -> p.deregister(listener));
  }
}
