/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.implementations.capabilities;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.annot8.core.bounds.Bounds;
import io.annot8.core.capabilities.AnnotationCapability;
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
import io.annot8.core.properties.ImmutableProperties;
import io.annot8.core.stores.AnnotationStore;

public class AnnotationBasedCapabilitiesTest {

  private AnnotationBasedCapabilities capabilities;

  @BeforeEach
  void beforeEach() {
    capabilities = new AnnotationBasedCapabilities(AnnotatedComponent.class);
  }

  @Test
  void getRequiredInputAnnotations() {
    assertThat(
            capabilities
                .getProcessedAnnotations()
                .filter(c -> !c.isOptional())
                .map(AnnotationCapability::getType))
        .containsExactlyInAnyOrder("ar1", "ar2");
  }

  @Test
  void getOptionalInputAnnotations() {
    assertThat(
            capabilities
                .getProcessedAnnotations()
                .filter(AnnotationCapability::isOptional)
                .map(AnnotationCapability::getType))
        .containsExactlyInAnyOrder("ao1");
  }

  @Test
  void getOutputAnnotations() {
    assertThat(capabilities.getCreatedAnnotations().map(AnnotationCapability::getType))
        .containsExactlyInAnyOrder("a1", "a2");
  }

  @Test
  public void testGetDeletesAnnotations() {
    assertThat(capabilities.getDeletedAnnotations().map(AnnotationCapability::getType))
        .containsExactly("a3");
    Stream<Class<? extends Bounds>> bounds =
        capabilities.getDeletedAnnotations().map(AnnotationCapability::getBounds);
    assertThat(bounds).containsExactly(FakeBounds.class);
  }

  @Test
  void getRequiredInputGroups() {
    assertThat(
            capabilities
                .getProcessedGroups()
                .filter(c -> !c.isOptional())
                .map(GroupCapability::getType))
        .containsExactlyInAnyOrder("gr1", "gr2");
  }

  @Test
  void getOptionalInputGroups() {
    assertThat(
            capabilities
                .getProcessedGroups()
                .filter(c -> c.isOptional())
                .map(GroupCapability::getType))
        .containsExactlyInAnyOrder("go1");
  }

  @Test
  void getOutputGroups() {
    assertThat(capabilities.getCreatedGroups().map(GroupCapability::getType))
        .containsExactlyInAnyOrder("g");
  }

  @Test
  public void testGetDeletedGroups() {
    assertThat(capabilities.getDeletedGroups().map(GroupCapability::getType))
        .containsExactly("go2");
  }

  @Test
  void getCreatedContent() {
    Stream<Class<? extends Content<?>>> classes =
        capabilities.getCreatedContent().map(ContentCapability::getType);
    assertThat(classes).containsExactlyInAnyOrder(FakeContent.class);
  }

  @Test
  void getRequiredContent() {
    Stream<Class<? extends Content<?>>> classes =
        capabilities
            .getProcessedContent()
            .filter(c -> !c.isOptional())
            .map(ContentCapability::getType);
    assertThat(classes).containsExactlyInAnyOrder(FakeContent.class);
  }

  @Test
  public void testGetDeletedContent() {
    Stream<Class<? extends Content<?>>> classes =
        capabilities.getDeletedContent().map(ContentCapability::getType);
    assertThat(classes).containsExactly(FakeContent.class);
  }

  @Test
  void getRequiredResources() {
    Stream<Class<? extends Resource>> classes =
        capabilities.getUsedResources().map(ResourceCapability::getType);
    assertThat(classes).containsExactlyInAnyOrder(Resource.class);
  }

  @Test
  void getOutputBounds() {
    Stream<Class<? extends Bounds>> classes =
        capabilities.getCreatedAnnotations().map(AnnotationCapability::getBounds);
    assertThat(classes.distinct()).containsExactlyInAnyOrder(FakeBounds.class);
  }

  @Test
  void getOutputGroupsForChildComponent() {
    AnnotationBasedCapabilities child =
        new AnnotationBasedCapabilities(ChildAnnotatedComponent.class);
    assertThat(child.getCreatedGroups().map(GroupCapability::getType))
        .containsExactlyInAnyOrder("sg");
  }

  @CreatesAnnotation(value = "a1", bounds = FakeBounds.class)
  @CreatesAnnotation(value = "a2", bounds = FakeBounds.class)
  @CreatesContent(FakeContent.class)
  @CreatesGroup("g")
  @ProcessesAnnotation(value = "ar1")
  @ProcessesAnnotation(value = "ar2")
  @ProcessesAnnotation(value = "ao1", optional = true)
  @ProcessesContent(FakeContent.class)
  @ProcessesGroup(value = "gr1")
  @ProcessesGroup(value = "gr2")
  @ProcessesGroup(value = "go1", optional = true)
  @UsesResource(Resource.class)
  @DeletesAnnotation(value = "a3", bounds = FakeBounds.class)
  @DeletesGroup(value = "go2")
  @DeletesContent(FakeContent.class)
  public static class AnnotatedComponent implements Annot8Component {}

  @CreatesGroup("sg")
  public static class ChildAnnotatedComponent extends AnnotatedComponent {}

  public static class FakeContent implements Content<String> {

    @Override
    public String getData() {
      return null;
    }

    @Override
    public Class<String> getDataClass() {
      return null;
    }

    @Override
    public Class<? extends Content<String>> getContentClass() {
      return null;
    }

    @Override
    public AnnotationStore getAnnotations() {
      return null;
    }

    @Override
    public String getId() {
      return null;
    }

    @Override
    public String getDescription() {
      return null;
    }

    @Override
    public ImmutableProperties getProperties() {
      return null;
    }
  }

  public static class FakeBounds implements Bounds {

    @Override
    public <D, C extends Content<D>, R> Optional<R> getData(C content, Class<R> requiredClass) {
      return Optional.empty();
    }

    @Override
    public <D, C extends Content<D>> boolean isValid(C content) {
      return false;
    }
  }
}
