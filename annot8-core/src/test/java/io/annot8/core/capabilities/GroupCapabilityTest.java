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

@ExtendWith(MockitoExtension.class)
public class GroupCapabilityTest {

  private static final String GROUP_TYPE = "testGroup";

  @Mock private CreatesGroup createsGroup;

  @Mock private DeletesGroup deletesGroup;

  @Mock private ProcessesGroup processesGroup;

  @Mock private CreatesGroup empty;

  @Test
  public void testCreatesGroupConstructor() {
    doReturn(GROUP_TYPE).when(createsGroup).value();
    GroupCapability capability = new GroupCapability(createsGroup);
    assertEquals(GROUP_TYPE, capability.getType());
    assertTrue(capability.isOptional());
  }

  @Test
  public void testDeletesGroupConstructor() {
    doReturn(GROUP_TYPE).when(deletesGroup).value();
    GroupCapability capability = new GroupCapability(deletesGroup);
    assertEquals(GROUP_TYPE, capability.getType());
    assertTrue(capability.isOptional());
  }

  @Test
  public void testProcessesGroup() {
    doReturn(GROUP_TYPE).when(processesGroup).value();
    doReturn(true).when(processesGroup).optional();
    GroupCapability capability = new GroupCapability(processesGroup);
    assertEquals(GROUP_TYPE, capability.getType());
    assertTrue(capability.isOptional());
  }

  @Test
  public void testEquals() {
    doReturn(GROUP_TYPE).when(createsGroup).value();
    GroupCapability capability = new GroupCapability(createsGroup);
    GroupCapability capability2 = new GroupCapability(createsGroup);
    assertEquals(capability, capability2);
    assertEquals(capability, capability);
  }

  @Test
  public void testNotEquals() {
    doReturn(GROUP_TYPE).when(createsGroup).value();
    GroupCapability capability = new GroupCapability(createsGroup);
    GroupCapability capability2 = new GroupCapability(empty);
    assertNotEquals(capability, capability2);
    assertNotEquals(null, capability);
  }
}
