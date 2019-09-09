/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.simple;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import io.annot8.common.pipelines.elements.Pipe;
import io.annot8.core.data.Item;
import io.annot8.core.exceptions.Annot8Exception;

@ExtendWith(MockitoExtension.class)
class SimpleBranchTest {

  @Mock Item item;

  @Test
  void forwardWhenNotExists() throws Annot8Exception {
    final SimpleBranch branch = new SimpleBranch(i -> "na");

    branch.forward(item);
  }

  @Test
  void forwardWhenExists() throws Annot8Exception {
    final SimpleBranch branch = new SimpleBranch(i -> "yes");

    final Pipe yesPipe = mock(Pipe.class);
    final Pipe noPipe = mock(Pipe.class);

    branch.addOutput("yes", yesPipe::process);
    branch.addOutput("no", noPipe::process);

    branch.forward(item);

    verify(yesPipe).process(item);
    verify(noPipe, never()).process(item);
  }
}
