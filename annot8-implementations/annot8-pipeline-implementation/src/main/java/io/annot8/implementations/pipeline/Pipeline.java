package io.annot8.implementations.pipeline;

import io.annot8.api.components.Processor;
import io.annot8.api.components.Source;
import io.annot8.api.components.responses.ProcessorResponse;
import io.annot8.api.components.responses.SourceResponse;
import io.annot8.api.data.Item;

public interface Pipeline {

  SourceResponse read();

  ProcessorResponse process(Item item);

  ProcessorResponse process();

  interface Builder {

    Builder withName(String name);

    Builder withDescription(String description) ;

    Builder withSource(Source source);

    Builder withProcessor(Processor processor);

    Pipeline build();

  }
}
