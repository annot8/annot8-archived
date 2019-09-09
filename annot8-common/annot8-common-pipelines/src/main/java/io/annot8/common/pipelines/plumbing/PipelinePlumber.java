/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.plumbing;

import static java.util.stream.Collectors.toMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import io.annot8.common.pipelines.definitions.BranchDefinition;
import io.annot8.common.pipelines.definitions.MergeDefinition;
import io.annot8.common.pipelines.elements.Branch;
import io.annot8.common.pipelines.elements.Merge;
import io.annot8.common.pipelines.elements.Pipe;
import io.annot8.core.components.Annot8Component;
import io.annot8.core.context.Context;
import io.annot8.core.exceptions.Annot8RuntimeException;
import io.annot8.core.exceptions.BadConfigurationException;
import io.annot8.core.exceptions.MissingResourceException;

/**
 * Joins pipe, branch and merge into a single.
 *
 * <p>This is annot8component so that we have the ability to close and configure the components.
 */
public class PipelinePlumber implements Annot8Component {

  private final Map<String, Pipe> namedPipes;
  private final List<BranchDefinition> branchDefinitions;
  private final List<MergeDefinition> mergeDefinitions;

  private Map<BranchDefinition, Branch> branches;
  private Map<MergeDefinition, Merge> merges;

  private Map<String, ForwardingPipe> forwardingPipes = new HashMap<>();
  private ForwardingPipe configuredPipe;

  public PipelinePlumber(
      Map<String, Pipe> namedPipes,
      List<BranchDefinition> branchDefinitions,
      List<MergeDefinition> mergeDefinitions) {
    this.namedPipes = namedPipes;
    this.branchDefinitions = branchDefinitions;
    this.mergeDefinitions = mergeDefinitions;
  }

  @Override
  public void configure(Context context)
      throws BadConfigurationException, MissingResourceException {
    // This is a 'has been plumbed'
    if (forwardingPipes == null) {
      throw new Annot8RuntimeException("Must call plumb() before configure()");
    }

    for (Pipe c : namedPipes.values()) {
      c.configure(context);
    }

    for (Branch c : branches.values()) {
      c.configure(context);
    }

    for (Merge c : merges.values()) {
      c.configure(context);
    }
  }

  @Override
  public void close() {
    branches.values().forEach(Branch::close);
    merges.values().forEach(Merge::close);
    namedPipes.values().forEach(Pipe::close);
    forwardingPipes.values().forEach(Pipe::close);
  }

  public void plumb(String startingPipe) throws BadConfigurationException {

    if (namedPipes.get(startingPipe) == null) {
      throw new BadConfigurationException("Starting pipeline is missing");
    }

    // Create instances of merge and branches
    branches = branchDefinitions.stream().collect(toMap(e -> e, BranchDefinition::create));
    merges = mergeDefinitions.stream().collect(toMap(e -> e, MergeDefinition::create));
    forwardingPipes = new HashMap<>();

    // Hook up merge and branch to the end of the pipelines

    forwardingPipes =
        namedPipes
            .entrySet()
            .stream()
            .collect(toMap(Entry::getKey, e -> configurePipe(e.getKey(), e.getValue())));

    // Now hook up the merges to next pipeline

    merges.forEach(
        (d, m) -> {
          Pipe output = forwardingPipes.get(d.getOutput());
          m.setOutput(output);
        });

    // Now hook up the branch to the next pipeline

    branches.forEach(
        (d, b) ->
            d.getOutputs()
                .forEach(
                    o -> {
                      Pipe output = forwardingPipes.get(o);
                      b.addOutput(o, output);
                    }));

    // TODO: Would be nice to know if this pipeline was actually hooked up!

    // Start with the default pipeline

    configuredPipe = forwardingPipes.get(startingPipe);
  }

  private ForwardingPipe configurePipe(String key, Pipe pipe) {

    List<Branch> nextBranches =
        findBranchesWithInput(key).map(d -> branches.get(d)).collect(Collectors.toList());

    List<Merge> nextMerges =
        findMergeWithInput(key).map(d -> merges.get(d)).collect(Collectors.toList());

    return new ForwardingPipe(key, pipe, nextBranches, nextMerges);
  }

  private Stream<BranchDefinition> findBranchesWithInput(String key) {
    return branchDefinitions.stream().filter(b -> b.getInput().equals(key));
  }

  private Stream<MergeDefinition> findMergeWithInput(String key) {
    return mergeDefinitions.stream().filter(b -> b.getInputs().contains(key));
  }

  public Pipe getPipe() {
    return configuredPipe;
  }
}
