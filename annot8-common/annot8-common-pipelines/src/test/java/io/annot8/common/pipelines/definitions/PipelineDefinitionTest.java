/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.definitions;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import io.annot8.common.implementations.configuration.ComponentHolder;
import io.annot8.common.implementations.configuration.ResourcesHolder;
import io.annot8.common.pipelines.elements.Pipe;
import io.annot8.common.pipelines.queues.BaseItemQueue;
import io.annot8.core.components.Source;

@ExtendWith(MockitoExtension.class)
class PipelineDefinitionTest {

  String name = "test";

  @Mock BaseItemFactory baseItemFactory;

  @Mock BaseItemQueue queue;

  @Mock ResourcesHolder resourcesHolder;

  @Mock ComponentHolder<Source> sourceHolder;

  @Mock Map<String, Pipe> pipes;

  @Mock List<BranchDefinition> branches;

  @Mock List<MergeDefinition> merge;

  @Test
  void constructor() {
    PipelineDefinition defn =
        new PipelineDefinition(
            name, baseItemFactory, queue, resourcesHolder, sourceHolder, pipes, branches, merge);

    assertThat(defn.getMerges()).isEqualTo(merge);
    assertThat(defn.getName()).isEqualTo(name);
    assertThat(defn.getBaseItemFactory()).isEqualTo(baseItemFactory);
    assertThat(defn.getBranches()).isEqualTo(branches);
    assertThat(defn.getPipes()).isEqualTo(pipes);
    assertThat(defn.getResourcesHolder()).isEqualTo(resourcesHolder);
    assertThat(defn.getSourceHolder()).isEqualTo(sourceHolder);
    assertThat(defn.getQueue()).isEqualTo(queue);
  }
}
