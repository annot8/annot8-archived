/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.plumbing;

import java.util.List;

import io.annot8.common.pipelines.base.AbstractPipe;
import io.annot8.common.pipelines.elements.Branch;
import io.annot8.common.pipelines.elements.Merge;
import io.annot8.common.pipelines.elements.Pipe;
import io.annot8.core.components.responses.ProcessorResponse;
import io.annot8.core.components.responses.ProcessorResponse.Status;
import io.annot8.core.context.Context;
import io.annot8.core.data.Item;
import io.annot8.core.exceptions.Annot8Exception;
import io.annot8.core.exceptions.BadConfigurationException;
import io.annot8.core.exceptions.MissingResourceException;

public class ForwardingPipe extends AbstractPipe {

  private final Pipe pipe;
  private final List<Branch> nextBranches;
  private final List<Merge> nextMerges;

  public ForwardingPipe(String name, Pipe pipe, List<Branch> nextBranches, List<Merge> nextMerges) {
    super(name);
    this.pipe = pipe;
    this.nextBranches = nextBranches;
    this.nextMerges = nextMerges;
  }

  @Override
  public ProcessorResponse process(Item item) throws Annot8Exception {
    // Not firing events here, as they should be fired by the underling pipeline

    ProcessorResponse response = pipe.process(item);

    if (response.getStatus() == Status.OK && !item.isDiscarded()) {

      for (Merge m : nextMerges) {
        m.receive(item);
      }

      for (Branch b : nextBranches) {
        b.forward(item);
      }
    }

    return response;
  }

  @Override
  public void configure(Context context)
      throws BadConfigurationException, MissingResourceException {
    super.configure(context);
    // Do not configuration ourselves, as these should be configured already
  }

  @Override
  public void close() {
    super.close();
    // Do not configuration ourselves, as these should be close through other means
  }
}
