/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.core.capabilities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import io.annot8.core.bounds.Bounds;
import io.annot8.core.capabilities.Capabilities.Builder;
import io.annot8.core.components.Resource;
import io.annot8.core.data.Content;

/** Unit tests for the default implementations of the {@link Capabilities} interface */
public class CapabilitiesBuilderTest {

  private static final String TEST_TYPE = "TEST_TYPE";
  private static final String TEST_TYPE2 = "TEST_TYPE2";

  @Test
  public void testProcessesAnnotation() {
    Capabilities.Builder builder = new TestCapabilitiesBuilder();
    Capabilities capabilities =
        builder.processesAnnotation(TEST_TYPE, TestBounds.class, true).save();

    List<AnnotationCapability> result =
        capabilities.getProcessedAnnotations().collect(Collectors.toList());
    assertDefaultAnnotationCapabilities(result);
  }

  @Test
  public void testCreatesAnnotation() {
    Capabilities.Builder builder = new TestCapabilitiesBuilder();
    Capabilities capabilities = builder.createsAnnotation(TEST_TYPE, TestBounds.class).save();

    List<AnnotationCapability> result =
        capabilities.getCreatedAnnotations().collect(Collectors.toList());
    assertDefaultAnnotationCapabilities(result);
  }

  @Test
  public void testDeletesAnnotation() {
    Capabilities.Builder builder = new TestCapabilitiesBuilder();
    Capabilities capabilities = builder.deletesAnnotation(TEST_TYPE, TestBounds.class).save();

    List<AnnotationCapability> results =
        capabilities.getDeletedAnnotations().collect(Collectors.toList());
    assertDefaultAnnotationCapabilities(results);
  }

  @Test
  public void testProcessesGroup() {
    Capabilities.Builder builder = new TestCapabilitiesBuilder();
    Capabilities capabilities = builder.processesGroup(TEST_TYPE, true).save();

    List<GroupCapability> results = capabilities.getProcessedGroups().collect(Collectors.toList());
    assertDefaultGroupCapabilities(results);
  }

  @Test
  public void testCreatesGroup() {
    Capabilities.Builder builder = new TestCapabilitiesBuilder();
    Capabilities capabilities = builder.createsGroup(TEST_TYPE).save();

    List<GroupCapability> results = capabilities.getCreatedGroups().collect(Collectors.toList());
    assertDefaultGroupCapabilities(results);
  }

  @Test
  public void testDeletesGroup() {
    Capabilities.Builder builder = new TestCapabilitiesBuilder();
    Capabilities capabilities = builder.deletesGroup(TEST_TYPE).save();

    List<GroupCapability> results = capabilities.getDeletedGroups().collect(Collectors.toList());
    assertDefaultGroupCapabilities(results);
  }

  @Test
  public void testProcessesContent() {
    Capabilities.Builder builder = new TestCapabilitiesBuilder();
    Capabilities capabilities = builder.processesContent(TestContent.class, true).save();

    List<ContentCapability> results =
        capabilities.getProcessedContent().collect(Collectors.toList());
    assertDefaultContentCapabilities(results);
  }

  @Test
  public void testCreatesContent() {
    Capabilities.Builder builder = new TestCapabilitiesBuilder();
    Capabilities capabilities = builder.createsContent(TestContent.class).save();

    List<ContentCapability> results = capabilities.getCreatedContent().collect(Collectors.toList());
    assertDefaultContentCapabilities(results);
  }

  @Test
  public void testDeletesContent() {
    Capabilities.Builder builder = new TestCapabilitiesBuilder();
    Capabilities capabilities = builder.deletesContent(TestContent.class).save();

    List<ContentCapability> results = capabilities.getDeletedContent().collect(Collectors.toList());
    assertDefaultContentCapabilities(results);
  }

  @Test
  public void testUsesResource() {
    Capabilities.Builder builder = new TestCapabilitiesBuilder();
    Capabilities capabilities = builder.usesResource(TestResource.class, false).save();

    List<ResourceCapability> results = capabilities.getUsedResources().collect(Collectors.toList());
    assertEquals(1, results.size());
    assertEquals(TestResource.class, results.get(0).getType());
    assertTrue(results.get(0).isOptional());
  }

  @Test
  public void testMerge() {
    Capabilities saved =
        new TestCapabilitiesBuilder()
            .processesAnnotation(TEST_TYPE, TestBounds.class, true)
            .createsAnnotation(TEST_TYPE, TestBounds.class)
            .deletesAnnotation(TEST_TYPE, TestBounds.class)
            .save();

    Capabilities merged =
        new TestCapabilitiesBuilder()
            .processesAnnotation(TEST_TYPE2, TestBounds.class, true)
            .createsAnnotation(TEST_TYPE2, TestBounds.class)
            .deletesAnnotation(TEST_TYPE2, TestBounds.class)
            .merge(saved)
            .save();

    assertEquals(2, merged.getProcessedAnnotations().count());
    assertEquals(2, merged.getCreatedAnnotations().count());
    assertEquals(2, merged.getDeletedAnnotations().count());
  }

  private void assertDefaultGroupCapabilities(List<GroupCapability> groupCapabilities) {
    assertEquals(1, groupCapabilities.size());
    assertEquals(TEST_TYPE, groupCapabilities.get(0).getType());
    assertTrue(groupCapabilities.get(0).isOptional());
  }

  private void assertDefaultAnnotationCapabilities(
      List<AnnotationCapability> annotationCapabilities) {
    assertEquals(1, annotationCapabilities.size());
    assertEquals(TEST_TYPE, annotationCapabilities.get(0).getType());
    assertEquals(TestBounds.class, annotationCapabilities.get(0).getBounds());
    assertTrue(annotationCapabilities.get(0).isOptional());
  }

  private void assertDefaultContentCapabilities(List<ContentCapability> contentCapabilities) {
    assertEquals(1, contentCapabilities.size());
    assertEquals(TestContent.class, contentCapabilities.get(0).getType());
    assertTrue(contentCapabilities.get(0).isOptional());
  }

  private abstract class TestResource implements Resource {}

  private abstract class TestBounds implements Bounds {}

  private abstract class TestContent implements Content<String> {}

  class TestCapabilities implements Capabilities {

    private final Set<AnnotationCapability> processedAnnotations;
    private final Set<AnnotationCapability> createdAnnotations;
    private final Set<AnnotationCapability> deletedAnnotations;
    private final Set<GroupCapability> processedGroups;
    private final Set<GroupCapability> createdGroups;
    private final Set<GroupCapability> deletedGroups;
    private final Set<ContentCapability> processedContent;
    private final Set<ContentCapability> createdContent;
    private final Set<ContentCapability> deletedContent;
    private final Set<ResourceCapability> usedResources;

    public TestCapabilities(
        Set<AnnotationCapability> processedAnnotations,
        Set<AnnotationCapability> createdAnnotations,
        Set<AnnotationCapability> deletedAnnotations,
        Set<GroupCapability> processedGroups,
        Set<GroupCapability> createdGroups,
        Set<GroupCapability> deletedGroups,
        Set<ContentCapability> processedContent,
        Set<ContentCapability> createdContent,
        Set<ContentCapability> deletedContent,
        Set<ResourceCapability> usedResources) {
      this.processedAnnotations = processedAnnotations;
      this.createdAnnotations = createdAnnotations;
      this.deletedAnnotations = deletedAnnotations;
      this.processedGroups = processedGroups;
      this.createdGroups = createdGroups;
      this.deletedGroups = deletedGroups;
      this.processedContent = processedContent;
      this.createdContent = createdContent;
      this.deletedContent = deletedContent;
      this.usedResources = usedResources;
    }

    @Override
    public Stream<AnnotationCapability> getProcessedAnnotations() {
      return processedAnnotations.stream();
    }

    @Override
    public Stream<AnnotationCapability> getCreatedAnnotations() {
      return createdAnnotations.stream();
    }

    @Override
    public Stream<AnnotationCapability> getDeletedAnnotations() {
      return deletedAnnotations.stream();
    }

    @Override
    public Stream<GroupCapability> getProcessedGroups() {
      return processedGroups.stream();
    }

    @Override
    public Stream<GroupCapability> getCreatedGroups() {
      return createdGroups.stream();
    }

    @Override
    public Stream<GroupCapability> getDeletedGroups() {
      return deletedGroups.stream();
    }

    @Override
    public Stream<ContentCapability> getCreatedContent() {
      return createdContent.stream();
    }

    @Override
    public Stream<ContentCapability> getDeletedContent() {
      return deletedContent.stream();
    }

    @Override
    public Stream<ContentCapability> getProcessedContent() {
      return processedContent.stream();
    }

    @Override
    public Stream<ResourceCapability> getUsedResources() {
      return usedResources.stream();
    }
  }

  class TestCapabilitiesBuilder implements Capabilities.Builder {

    private final Set<AnnotationCapability> processedAnnotations = new HashSet<>();
    private final Set<AnnotationCapability> createdAnnotations = new HashSet<>();
    private final Set<AnnotationCapability> deletedAnnotations = new HashSet<>();
    private final Set<GroupCapability> processedGroups = new HashSet<>();
    private final Set<GroupCapability> createdGroups = new HashSet<>();
    private final Set<GroupCapability> deletedGroups = new HashSet<>();
    private final Set<ContentCapability> processedContent = new HashSet<>();
    private final Set<ContentCapability> createdContent = new HashSet<>();
    private final Set<ContentCapability> deletedContent = new HashSet<>();
    private final Set<ResourceCapability> usedResources = new HashSet<>();

    @Override
    public Builder processesAnnotation(AnnotationCapability capability) {
      processedAnnotations.add(capability);
      return this;
    }

    @Override
    public Builder createsAnnotation(AnnotationCapability capability) {
      createdAnnotations.add(capability);
      return this;
    }

    @Override
    public Builder deletesAnnotation(AnnotationCapability capability) {
      deletedAnnotations.add(capability);
      return this;
    }

    @Override
    public Builder processesGroup(GroupCapability capability) {
      processedGroups.add(capability);
      return this;
    }

    @Override
    public Builder createsGroup(GroupCapability capability) {
      createdGroups.add(capability);
      return this;
    }

    @Override
    public Builder deletesGroup(GroupCapability capability) {
      deletedGroups.add(capability);
      return this;
    }

    @Override
    public Builder processesContent(ContentCapability capability) {
      processedContent.add(capability);
      return this;
    }

    @Override
    public Builder createsContent(ContentCapability capability) {
      createdContent.add(capability);
      return this;
    }

    @Override
    public Builder deletesContent(ContentCapability capability) {
      deletedContent.add(capability);
      return this;
    }

    @Override
    public Builder usesResource(ResourceCapability capability) {
      usedResources.add(capability);
      return this;
    }

    @Override
    public Capabilities save() {
      return new TestCapabilities(
          processedAnnotations,
          createdAnnotations,
          deletedAnnotations,
          processedGroups,
          createdGroups,
          deletedGroups,
          processedContent,
          createdContent,
          deletedContent,
          usedResources);
    }
  }
}
