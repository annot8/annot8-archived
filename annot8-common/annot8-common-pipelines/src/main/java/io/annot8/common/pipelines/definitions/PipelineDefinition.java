/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.definitions;

import java.util.List;
import java.util.Map;

import io.annot8.common.implementations.configuration.ComponentHolder;
import io.annot8.common.implementations.configuration.ResourcesHolder;
import io.annot8.common.implementations.data.BaseItemFactory;
import io.annot8.common.pipelines.elements.Pipe;
import io.annot8.common.pipelines.queues.BaseItemQueue;
import io.annot8.core.components.Source;

public class PipelineDefinition {

  private final String name;
  private final BaseItemFactory baseItemFactory;

  private final BaseItemQueue queue;
  private final ResourcesHolder resourcesHolder;
  private final ComponentHolder<Source> sourceHolder;
  private final Map<String, Pipe> pipes;
  private final java.util.List<BranchDefinition> branches;
  private final List<MergeDefinition> merges;

  public PipelineDefinition(
      String name,
      BaseItemFactory baseItemFactory,
      BaseItemQueue queue,
      ResourcesHolder resourcesHolder,
      ComponentHolder<Source> sourceHolder,
      Map<String, Pipe> pipes,
      List<BranchDefinition> branches,
      List<MergeDefinition> merges) {
    this.name = name;
    this.baseItemFactory = baseItemFactory;
    this.queue = queue;
    this.resourcesHolder = resourcesHolder;
    this.sourceHolder = sourceHolder;
    this.pipes = pipes;
    this.branches = branches;
    this.merges = merges;
  }

  public String getName() {
    return name;
  }

  public BaseItemFactory getBaseItemFactory() {
    return baseItemFactory;
  }

  public BaseItemQueue getQueue() {
    return queue;
  }

  public ResourcesHolder getResourcesHolder() {
    return resourcesHolder;
  }

  public ComponentHolder<Source> getSourceHolder() {
    return sourceHolder;
  }

  public Map<String, Pipe> getPipes() {
    return pipes;
  }

  public List<BranchDefinition> getBranches() {
    return branches;
  }

  public List<MergeDefinition> getMerges() {
    return merges;
  }
}
