/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.implementations.capabilities;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import io.annot8.common.implementations.capabilities.AnnotationBasedCapabilitiesTest.FakeBounds;
import io.annot8.common.implementations.capabilities.AnnotationBasedCapabilitiesTest.FakeContent;
import io.annot8.core.capabilities.AnnotationCapability;
import io.annot8.core.capabilities.Capabilities;
import io.annot8.core.capabilities.Capabilities.Builder;
import io.annot8.core.capabilities.ContentCapability;
import io.annot8.core.capabilities.CreatesAnnotation;
import io.annot8.core.capabilities.CreatesContent;
import io.annot8.core.capabilities.CreatesGroup;
import io.annot8.core.capabilities.DeletesAnnotation;
import io.annot8.core.capabilities.DeletesContent;
import io.annot8.core.capabilities.DeletesGroup;
import io.annot8.core.capabilities.GroupCapability;
import io.annot8.core.capabilities.ProcessesAnnotation;
import io.annot8.core.capabilities.ProcessesContent;
import io.annot8.core.capabilities.ProcessesGroup;
import io.annot8.core.capabilities.ResourceCapability;
import io.annot8.core.capabilities.UsesResource;
import io.annot8.core.components.Annot8Component;
import io.annot8.core.components.Resource;
import io.annot8.core.data.Content;

public class CapabilitiesCompilerTest {

  @Test
  public void testCompile() {
    CapabilitiesCompiler compiler = new CapabilitiesCompiler(TestCapabilitiesBuilder::new);
    Capabilities capabilities = compiler.compile(new FakeComponent());

    assertThat(capabilities.getCreatedAnnotations().map(AnnotationCapability::getType))
        .containsExactly("a1");
    assertThat(capabilities.getProcessedAnnotations().map(AnnotationCapability::getType))
        .containsExactly("a2");
    assertThat(capabilities.getDeletedAnnotations().map(AnnotationCapability::getType))
        .containsExactly("a3");

    assertThat(capabilities.getCreatedGroups().map(GroupCapability::getType)).containsExactly("g1");
    assertThat(capabilities.getProcessedGroups().map(GroupCapability::getType))
        .containsExactly("g2");
    assertThat(capabilities.getDeletedGroups().map(GroupCapability::getType)).containsExactly("g3");

    Stream<Class<? extends Content<?>>> classes =
        capabilities.getCreatedContent().map(ContentCapability::getType);
    assertThat(classes).containsExactly(FakeContent.class);
    Stream<Class<? extends Content<?>>> processedClasses =
        capabilities.getProcessedContent().map(ContentCapability::getType);
    assertThat(processedClasses).containsExactly(FakeContent.class);
    Stream<Class<? extends Content<?>>> deletesClasses =
        capabilities.getDeletedContent().map(ContentCapability::getType);
    assertThat(deletesClasses).containsExactly(FakeContent.class);
  }

  @Test
  public void testParentCapabilities() {
    CapabilitiesCompiler compiler = new CapabilitiesCompiler(TestCapabilitiesBuilder::new);
    Capabilities capabilities = compiler.compile(new FakeComponentChild());

    assertThat(capabilities.getCreatedGroups().map(GroupCapability::getType))
        .containsExactlyInAnyOrder("g1", "g2");
  }

  @CreatesAnnotation(value = "a1", bounds = FakeBounds.class)
  @ProcessesAnnotation(value = "a2")
  @DeletesAnnotation(value = "a3", bounds = FakeBounds.class)
  @CreatesContent(FakeContent.class)
  @ProcessesContent(FakeContent.class)
  @DeletesContent(FakeContent.class)
  @CreatesGroup("g1")
  @ProcessesGroup(value = "g2")
  @DeletesGroup(value = "g3")
  @UsesResource(Resource.class)
  private class FakeComponent implements Annot8Component {}

  @CreatesGroup("g2")
  private class FakeComponentChild extends FakeComponent {}

  private class TestCapabilitiesBuilder implements Capabilities.Builder {

    private final List<AnnotationCapability> processesAnnotations = new ArrayList<>();
    private final List<AnnotationCapability> createsAnnotations = new ArrayList<>();
    private final List<AnnotationCapability> deletesAnnotations = new ArrayList<>();
    private final List<GroupCapability> processesGroup = new ArrayList<>();
    private final List<GroupCapability> createsGroup = new ArrayList<>();
    private final List<GroupCapability> deletesGroup = new ArrayList<>();
    private final List<ContentCapability> processesContent = new ArrayList<>();
    private final List<ContentCapability> createsContent = new ArrayList<>();
    private final List<ContentCapability> deletesContent = new ArrayList<>();
    private final List<ResourceCapability> resources = new ArrayList<>();

    @Override
    public Builder processesAnnotation(AnnotationCapability capability) {
      processesAnnotations.add(capability);
      return this;
    }

    @Override
    public Builder createsAnnotation(AnnotationCapability capability) {
      createsAnnotations.add(capability);
      return this;
    }

    @Override
    public Builder deletesAnnotation(AnnotationCapability capability) {
      deletesAnnotations.add(capability);
      return this;
    }

    @Override
    public Builder processesGroup(GroupCapability capability) {
      processesGroup.add(capability);
      return this;
    }

    @Override
    public Builder createsGroup(GroupCapability capability) {
      createsGroup.add(capability);
      return this;
    }

    @Override
    public Builder deletesGroup(GroupCapability capability) {
      deletesGroup.add(capability);
      return this;
    }

    @Override
    public Builder processesContent(ContentCapability capability) {
      processesContent.add(capability);
      return this;
    }

    @Override
    public Builder createsContent(ContentCapability capability) {
      createsContent.add(capability);
      return this;
    }

    @Override
    public Builder deletesContent(ContentCapability capability) {
      deletesContent.add(capability);
      return this;
    }

    @Override
    public Builder usesResource(ResourceCapability capability) {
      resources.add(capability);
      return this;
    }

    @Override
    public Capabilities save() {
      return new Capabilities() {

        @Override
        public Stream<AnnotationCapability> getProcessedAnnotations() {
          return processesAnnotations.stream();
        }

        @Override
        public Stream<AnnotationCapability> getCreatedAnnotations() {
          return createsAnnotations.stream();
        }

        @Override
        public Stream<AnnotationCapability> getDeletedAnnotations() {
          return deletesAnnotations.stream();
        }

        @Override
        public Stream<GroupCapability> getProcessedGroups() {
          return processesGroup.stream();
        }

        @Override
        public Stream<GroupCapability> getCreatedGroups() {
          return createsGroup.stream();
        }

        @Override
        public Stream<GroupCapability> getDeletedGroups() {
          return deletesGroup.stream();
        }

        @Override
        public Stream<ContentCapability> getCreatedContent() {
          return createsContent.stream();
        }

        @Override
        public Stream<ContentCapability> getDeletedContent() {
          return deletesContent.stream();
        }

        @Override
        public Stream<ContentCapability> getProcessedContent() {
          return processesContent.stream();
        }

        @Override
        public Stream<ResourceCapability> getUsedResources() {
          return resources.stream();
        }
      };
    }
  }
}
