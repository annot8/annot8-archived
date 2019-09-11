package io.annot8.core.capabilities;

import java.util.stream.Stream;

public abstract class AbstractComponentCapabilities implements ComponentCapabilities{
  @Override
  public Stream<Annotation> createsAnnotations() {
    return Stream.empty();
  }

  @Override
  public Stream<Annotation> processesAnnotations() {
    return Stream.empty();
  }

  @Override
  public Stream<Annotation> deletesAnnotations() {
    return Stream.empty();
  }

  @Override
  public Stream<Content> createsContent() {
    return Stream.empty();
  }

  @Override
  public Stream<Content> processesContent() {
    return Stream.empty();
  }

  @Override
  public Stream<Content> deletesContent() {
    return Stream.empty();
  }

  @Override
  public Stream<Group> createsGroups() {
    return Stream.empty();
  }

  @Override
  public Stream<Group> processesGroups() {
    return Stream.empty();
  }

  @Override
  public Stream<Group> deletesGroups() {
    return Stream.empty();
  }
}
