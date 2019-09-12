/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.serialization;

import java.util.stream.Stream;

import io.annot8.core.bounds.Bounds;
import io.annot8.core.capabilities.AnnotationCapability;
import io.annot8.core.capabilities.ComponentCapabilities;
import io.annot8.core.components.ProcessorDescriptor;

public class TestDescriptor implements ProcessorDescriptor<TestProcessor, TestSettings> {

  private String name;
  private TestSettings settings;

  public TestDescriptor() {
    // Do nothing
  }

  public TestDescriptor(String name, String host, int port) {
    this.name = name;
    this.settings = new TestSettings(host, port);
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void setSettings(TestSettings settings) {
    this.settings = settings;
  }

  @Override
  public TestSettings getSettings() {
    return settings;
  }

  @Override
  public ComponentCapabilities capabilities() {
    return new ComponentCapabilities() {
      @Override
      public Stream<AnnotationCapability> processesAnnotations() {
        return Stream.of(new AnnotationCapability(AnnotationCapability.ANY_TYPE, Bounds.class));
      }
    };
  }

  @Override
  public TestProcessor create() {
    return new TestProcessor(getSettings());
  }
}
