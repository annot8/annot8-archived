package io.annot8.core.capabilities;

import io.annot8.core.bounds.Bounds;

import java.util.stream.Stream;

public interface ComponentCapabilities {

  Stream<Annotation> createsAnnotations();
  Stream<Annotation> processesAnnotations();
  Stream<Annotation> deletesAnnotations();

  Stream<Content> createsContent();
  Stream<Content> processesContent();
  Stream<Content> deletesContent();

  Stream<Group> createsGroups();
  Stream<Group> processesGroups();
  Stream<Group> deletesGroups();

  final class Annotation {
    private final String type;
    private final Class<? extends Bounds> bounds;

    public Annotation(String type, Class<? extends Bounds> bounds) {
      this.type = type;
      this.bounds = bounds;
    }

    public String getType() {
      return type;
    }

    public Class<? extends Bounds> getBounds() {
      return bounds;
    }
  }

  final class Content {
    private final Class<? extends io.annot8.core.data.Content> type;

    public Content(Class<? extends io.annot8.core.data.Content> type) {
      this.type = type;
    }

    public Class<? extends io.annot8.core.data.Content> getType() {
      return type;
    }
  }

  final class Group {
    private final String type;

    public Group(String type) {
      this.type = type;
    }

    public String getType() {
      return type;
    }
  }
}
