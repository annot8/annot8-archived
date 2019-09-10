/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.base;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import io.annot8.core.data.ItemFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;

import io.annot8.common.implementations.configuration.ComponentHolder;
import io.annot8.common.implementations.configuration.ResourcesHolder;
import io.annot8.common.pipelines.definitions.BranchDefinition;
import io.annot8.common.pipelines.definitions.MergeDefinition;
import io.annot8.common.pipelines.definitions.PipelineDefinition;
import io.annot8.common.pipelines.elements.Pipe;
import io.annot8.common.pipelines.elements.PipelineBuilder;
import io.annot8.common.pipelines.queues.ItemQueue;
import io.annot8.common.pipelines.queues.MemoryItemQueue;
import io.annot8.common.pipelines.simple.MultiPipe;
import io.annot8.core.components.Resource;
import io.annot8.core.components.Source;
import io.annot8.core.settings.Settings;

public abstract class AbstractPipelineBuilder implements PipelineBuilder {

  private static final Logger LOGGER = LoggerFactory.getLogger(AbstractPipelineBuilder.class);

  // Use a linked hash map so the addition order = configuration order
  private final ComponentHolder<Source> sourceHolder = new ComponentHolder<>();
  private final ResourcesHolder resourcesHolder = new ResourcesHolder();
  private final ListMultimap<String, Pipe> pipes = ArrayListMultimap.create();
  private final List<MergeDefinition> merges = new LinkedList<>();
  private final List<BranchDefinition> branches = new LinkedList<>();

  private ItemQueue queue = null;
  private String name = "anonymous";
  private ItemFactory itemFactory = null;

  @Override
  public PipelineBuilder withName(String name) {
    this.name = name;
    return this;
  }

  @Override
  public PipelineBuilder addResource(
      final String id, final Resource resource, final Collection<Settings> configuration) {
    resourcesHolder.addResource(id, resource, configuration);
    return this;
  }

  @Override
  public PipelineBuilder addSource(final Source source, final Collection<Settings> configuration) {
    sourceHolder.add(source, configuration);
    return this;
  }

  @Override
  public PipelineBuilder addPipe(String key, Pipe pipe) {
    pipes.put(key, pipe);
    return this;
  }

  @Override
  public PipelineBuilder addBranch(BranchDefinition branchDefinition) {
    branches.add(branchDefinition);
    return this;
  }

  @Override
  public PipelineBuilder addMerge(MergeDefinition mergeDefinition) {
    merges.add(mergeDefinition);
    return this;
  }

  public PipelineBuilder withQueue(final ItemQueue queue) {
    this.queue = queue;
    return this;
  }

  public PipelineBuilder withItemFactory(final ItemFactory itemFactory) {
    this.itemFactory = itemFactory;
    return this;
  }

  protected ResourcesHolder getResourcesHolder() {
    return resourcesHolder;
  }

  protected ComponentHolder<Source> getSourceHolder() {
    return sourceHolder;
  }

  protected ListMultimap<String, Pipe> getPipes() {
    return pipes;
  }

  protected Map<String, Pipe> getPipesAsMap() {
    return pipes
        .keySet()
        .stream()
        .collect(
            Collectors.toMap(
                k -> k,
                k -> {
                  String name = k;
                  List<Pipe> list = pipes.get(k);
                  return new MultiPipe(name, list);
                }));
  }

  protected String getName() {
    return name;
  }

  protected ItemFactory getItemFactory() {
    return itemFactory;
  }

  protected List<BranchDefinition> getBranches() {
    return branches;
  }

  protected List<MergeDefinition> getMerges() {
    return merges;
  }

  protected ItemQueue getQueue() {
    if (queue == null) {
      LOGGER.warn(
          "Queue requires for Source ingest, non specified so using the an in-memory queue");
      queue = new MemoryItemQueue();
    }
    return queue;
  }

  protected PipelineDefinition getDefinition() {

    Objects.requireNonNull(queue);
    Objects.requireNonNull(name);
    Objects.requireNonNull(itemFactory);

    return new PipelineDefinition(
        name,
        itemFactory,
        queue,
        getResourcesHolder(),
        getSourceHolder(),
        getPipesAsMap(),
        getBranches(),
        getMerges());
  }
}
