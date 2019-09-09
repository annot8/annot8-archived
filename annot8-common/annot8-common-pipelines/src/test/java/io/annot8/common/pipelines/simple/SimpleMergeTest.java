/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.simple;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import io.annot8.core.components.responses.ProcessorResponse;
import io.annot8.core.data.Item;
import io.annot8.core.exceptions.Annot8Exception;

@ExtendWith(MockitoExtension.class)
class SimpleMergeTest {

  @Mock Item item;

  @Mock Item ignoreItem;

  @Test
  void mergeWithoutPredicate() throws Annot8Exception {

    List<Item> output = new LinkedList<>();

    final SimpleMerge merge = new SimpleMerge();

    merge.setOutput(
        p -> {
          output.add(p);
          return ProcessorResponse.ok();
        });

    merge.receive(item);
    merge.receive(ignoreItem);

    assertThat(output).containsExactly(item, ignoreItem);

    merge.close();
  }

  @Test
  void mergeWithPredicate() throws Annot8Exception {

    List<Item> output = new LinkedList<>();

    final SimpleMerge merge = new SimpleMerge(i -> !i.equals(ignoreItem));

    merge.setOutput(
        p -> {
          output.add(p);
          return ProcessorResponse.ok();
        });

    merge.receive(item);
    merge.receive(ignoreItem);

    assertThat(output).containsExactly(item);

    merge.close();
  }
}
