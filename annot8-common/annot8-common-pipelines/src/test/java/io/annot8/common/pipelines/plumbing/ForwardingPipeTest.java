/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.plumbing;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import io.annot8.common.implementations.context.SimpleContext;
import io.annot8.common.pipelines.elements.Branch;
import io.annot8.common.pipelines.elements.Merge;
import io.annot8.common.pipelines.elements.Pipe;
import io.annot8.core.components.responses.ProcessorResponse;
import io.annot8.core.context.Context;
import io.annot8.core.data.Item;
import io.annot8.core.exceptions.Annot8Exception;

@ExtendWith(MockitoExtension.class)
class ForwardingPipeTest {

  @Mock Item item;

  @Mock Branch branch;

  @Mock Merge merge;

  @Mock Pipe innerPipe;

  @Test
  void process() throws Annot8Exception {
    when(innerPipe.process(item)).thenReturn(ProcessorResponse.ok());

    ForwardingPipe pipe =
        new ForwardingPipe(
            "test", innerPipe, Collections.singletonList(branch), Collections.singletonList(merge));

    assertThat(pipe.getName()).isEqualTo("test");

    Context context = new SimpleContext();
    pipe.configure(context);

    verify(innerPipe, never()).configure(any(Context.class));
    verify(branch, never()).configure(any(Context.class));
    verify(merge, never()).configure(any(Context.class));

    Mockito.clearInvocations(branch, merge);

    pipe.process(item);

    verify(innerPipe).process(item);
    verify(branch).forward(item);
    verify(merge).receive(item);

    Mockito.clearInvocations(branch, merge);

    pipe.close();

    verify(innerPipe, never()).close();
    verify(branch, never()).close();
    verify(merge, never()).close();
  }
}
