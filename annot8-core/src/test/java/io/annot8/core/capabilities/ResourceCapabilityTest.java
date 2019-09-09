/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.core.capabilities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import io.annot8.core.components.Resource;

@ExtendWith(MockitoExtension.class)
public class ResourceCapabilityTest {

  @Mock private UsesResource usesResource;

  @Mock private UsesResource empty;

  @Test
  public void testUsesResourceConstructor() {
    doReturn(TestResource.class).when(usesResource).value();
    doReturn(true).when(usesResource).optional();

    ResourceCapability capability = new ResourceCapability(usesResource);
    assertEquals(TestResource.class, capability.getType());
    assertTrue(capability.isOptional());
  }

  @Test
  public void testEquals() {
    doReturn(TestResource.class).when(usesResource).value();
    doReturn(true).when(usesResource).optional();
    ResourceCapability capability = new ResourceCapability(usesResource);
    ResourceCapability capability2 = new ResourceCapability(usesResource);

    assertEquals(capability, capability2);
    assertEquals(capability, capability);
  }

  @Test
  public void testNotEquals() {
    doReturn(TestResource.class).when(usesResource).value();
    doReturn(true).when(usesResource).optional();
    ResourceCapability capability = new ResourceCapability(usesResource);
    ResourceCapability capability2 = new ResourceCapability(empty);
    assertNotEquals(capability, capability2);
    assertNotEquals(null, capability);
  }

  private abstract class TestResource implements Resource {}
}
