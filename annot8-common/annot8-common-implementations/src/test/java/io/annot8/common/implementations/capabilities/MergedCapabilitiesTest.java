/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.implementations.capabilities;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import io.annot8.core.bounds.Bounds;
import io.annot8.core.capabilities.AnnotationCapability;
import io.annot8.core.capabilities.Capabilities;
import io.annot8.core.capabilities.ContentCapability;
import io.annot8.core.capabilities.GroupCapability;
import io.annot8.core.capabilities.ResourceCapability;
import io.annot8.core.components.Resource;
import io.annot8.core.data.Content;

public class MergedCapabilitiesTest {

  private final AnnotationCapability annCap =
      new AnnotationCapability("test", FakeBounds.class, true);
  private final AnnotationCapability annCap2 =
      new AnnotationCapability("test2", FakeBounds.class, true);
  private final ContentCapability conCap = new ContentCapability(FakeContent.class, true);
  private final ContentCapability conCap2 = new ContentCapability(FakeContent2.class, true);
  private final GroupCapability groupCap = new GroupCapability("group", true);
  private final GroupCapability groupCap2 = new GroupCapability("group2", true);
  private final ResourceCapability resCap = new ResourceCapability(FakeResource.class, true);
  private final ResourceCapability resCap2 = new ResourceCapability(FakeResource2.class, true);

  @Test
  public void testMergedCapabilities() {
    Capabilities capabilities1 = Mockito.mock(Capabilities.class);
    Capabilities capabilities2 = Mockito.mock(Capabilities.class);

    setupMockedCapabilities(capabilities1, annCap, conCap, groupCap, resCap);
    setupMockedCapabilities(capabilities2, annCap2, conCap2, groupCap2, resCap2);

    MergedCapabilities capabilities = new MergedCapabilities(capabilities1, capabilities2);
    assertThat(capabilities.getProcessedAnnotations()).containsExactlyInAnyOrder(annCap, annCap2);
    assertThat(capabilities.getCreatedAnnotations()).containsExactlyInAnyOrder(annCap, annCap2);
    assertThat(capabilities.getDeletedAnnotations()).containsExactlyInAnyOrder(annCap, annCap2);
    assertThat(capabilities.getProcessedContent()).containsExactlyInAnyOrder(conCap, conCap2);
    assertThat(capabilities.getCreatedContent()).containsExactlyInAnyOrder(conCap, conCap2);
    assertThat(capabilities.getDeletedContent()).containsExactlyInAnyOrder(conCap, conCap2);
    assertThat(capabilities.getProcessedGroups()).containsExactlyInAnyOrder(groupCap, groupCap2);
    assertThat(capabilities.getCreatedGroups()).containsExactlyInAnyOrder(groupCap, groupCap2);
    assertThat(capabilities.getDeletedGroups()).containsExactlyInAnyOrder(groupCap, groupCap2);
    assertThat(capabilities.getUsedResources()).containsExactlyInAnyOrder(resCap, resCap2);
  }

  @Test
  public void testDistinctCapabilities() {
    Capabilities capabilities1 = Mockito.mock(Capabilities.class);
    Capabilities capabilities2 = Mockito.mock(Capabilities.class);
    AnnotationCapability duplicateAnn = new AnnotationCapability("test", FakeBounds.class, true);
    ContentCapability duplicateCon = new ContentCapability(FakeContent.class, true);
    GroupCapability duplicateGro = new GroupCapability("group", true);
    ResourceCapability duplicateRes = new ResourceCapability(FakeResource.class, true);

    setupMockedCapabilities(capabilities1, annCap, conCap, groupCap, resCap);
    setupMockedCapabilities(capabilities2, duplicateAnn, duplicateCon, duplicateGro, duplicateRes);

    MergedCapabilities capabilities = new MergedCapabilities(capabilities1, capabilities2);
    assertThat(capabilities.getProcessedAnnotations()).containsExactlyInAnyOrder(annCap);
    assertThat(capabilities.getCreatedAnnotations()).containsExactlyInAnyOrder(annCap);
    assertThat(capabilities.getDeletedAnnotations()).containsExactlyInAnyOrder(annCap);
    assertThat(capabilities.getProcessedContent()).containsExactlyInAnyOrder(conCap);
    assertThat(capabilities.getCreatedContent()).containsExactlyInAnyOrder(conCap);
    assertThat(capabilities.getDeletedContent()).containsExactlyInAnyOrder(conCap);
    assertThat(capabilities.getProcessedGroups()).containsExactlyInAnyOrder(groupCap);
    assertThat(capabilities.getCreatedGroups()).containsExactlyInAnyOrder(groupCap);
    assertThat(capabilities.getDeletedGroups()).containsExactlyInAnyOrder(groupCap);
    assertThat(capabilities.getUsedResources()).containsExactlyInAnyOrder(resCap);
  }

  @Test
  public void testHandlesNullAndEmpty() {
    MergedCapabilities capabilities = new MergedCapabilities(null, null);
    MergedCapabilities capabilities2 = new MergedCapabilities();
    assertEmptyCapabilities(capabilities);
    assertEmptyCapabilities(capabilities2);
  }

  private void assertEmptyCapabilities(Capabilities capabilities) {
    assertThat(capabilities.getProcessedAnnotations()).isEmpty();
    assertThat(capabilities.getCreatedAnnotations()).isEmpty();
    assertThat(capabilities.getDeletedAnnotations()).isEmpty();
    assertThat(capabilities.getProcessedContent()).isEmpty();
    assertThat(capabilities.getCreatedContent()).isEmpty();
    assertThat(capabilities.getDeletedContent()).isEmpty();
    assertThat(capabilities.getProcessedGroups()).isEmpty();
    assertThat(capabilities.getCreatedGroups()).isEmpty();
    assertThat(capabilities.getDeletedGroups()).isEmpty();
    assertThat(capabilities.getUsedResources()).isEmpty();
  }

  private void setupMockedCapabilities(
      Capabilities capabilities,
      AnnotationCapability annotationCapability,
      ContentCapability contentCapability,
      GroupCapability groupCapabiltiy,
      ResourceCapability resourceCapability) {
    when(capabilities.getProcessedAnnotations()).thenAnswer(getStreamAnswer(annotationCapability));
    when(capabilities.getCreatedAnnotations()).thenAnswer(getStreamAnswer(annotationCapability));
    when(capabilities.getDeletedAnnotations()).thenAnswer(getStreamAnswer(annotationCapability));

    when(capabilities.getCreatedContent()).thenAnswer(getStreamAnswer(contentCapability));
    when(capabilities.getProcessedContent()).thenAnswer(getStreamAnswer(contentCapability));
    when(capabilities.getDeletedContent()).thenAnswer(getStreamAnswer(contentCapability));

    when(capabilities.getCreatedGroups()).thenAnswer(getStreamAnswer(groupCapabiltiy));
    when(capabilities.getProcessedGroups()).thenAnswer(getStreamAnswer(groupCapabiltiy));
    when(capabilities.getDeletedGroups()).thenAnswer(getStreamAnswer(groupCapabiltiy));

    when(capabilities.getUsedResources()).thenAnswer(getStreamAnswer(resourceCapability));
  }

  private <T> Answer<Stream<T>> getStreamAnswer(T content) {
    return invocation -> Stream.of(content);
  }

  private abstract class FakeBounds implements Bounds {}

  private abstract class FakeContent implements Content<String> {}

  private abstract class FakeContent2 implements Content<String> {}

  private abstract class FakeResource implements Resource {}

  private abstract class FakeResource2 implements Resource {}
}
