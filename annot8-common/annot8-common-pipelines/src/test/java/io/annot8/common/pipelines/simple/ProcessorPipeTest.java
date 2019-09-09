/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.simple;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import io.annot8.common.implementations.context.SimpleContext;
import io.annot8.core.components.Processor;
import io.annot8.core.data.Item;
import io.annot8.core.exceptions.Annot8Exception;
import io.annot8.core.exceptions.BadConfigurationException;
import io.annot8.core.exceptions.MissingResourceException;

@ExtendWith(MockitoExtension.class)
class ProcessorPipeTest {

  @Mock Processor processor;
  private ProcessorPipe pipe;

  @BeforeEach
  void beforeEach() {
    pipe = new ProcessorPipe(processor);
  }

  @Test
  void configure() throws BadConfigurationException, MissingResourceException {
    SimpleContext context = new SimpleContext();
    pipe.configure(context);
    verify(processor).configure(context);
  }

  @Test
  void process() throws Annot8Exception {
    Item item = mock(Item.class);
    pipe.process(item);
    verify(processor).process(item);
  }

  @Test
  void close() {
    pipe.close();
    verify(processor).close();
  }
}
