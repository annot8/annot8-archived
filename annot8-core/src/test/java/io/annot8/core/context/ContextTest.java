/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.core.context;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doReturn;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import io.annot8.core.components.Resource;
import io.annot8.core.settings.Settings;

public class ContextTest {

  @Test
  public void testGetSettings() {
    Context context = Mockito.mock(Context.class);
    Context context2 = Mockito.mock(Context.class);
    TestSettings settings = new TestSettings();
    Settings settings2 = Mockito.mock(Settings.class);
    doReturn(Stream.of(settings)).when(context).getSettings();
    doReturn(Stream.of(settings2)).when(context2).getSettings();
    doCallRealMethod().when(context).getSettings(Mockito.any());
    doCallRealMethod().when(context2).getSettings(Mockito.any());

    assertEquals(settings, context.getSettings(TestSettings.class).get());
    assertFalse(context2.getSettings(TestSettings.class).isPresent());
  }

  @Test
  public void testGetSettingsWithDefault() {
    Context context = Mockito.mock(Context.class);
    TestSettings settings = new TestSettings();
    doReturn(Optional.of(settings)).when(context).getSettings(TestSettings.class);
    doReturn(Optional.empty()).when(context).getSettings(UnusedSettings.class);

    doCallRealMethod().when(context).getSettings(Mockito.any(), Mockito.any());

    // if it exists we get it (null or otherwise)
    assertEquals(settings, context.getSettings(TestSettings.class, null));
    assertEquals(settings, context.getSettings(TestSettings.class, new TestSettings()));

    // If we don't have it, then if null we get a new instance
    assertNotNull(context.getSettings(UnusedSettings.class, null));
    // Or the default
    UnusedSettings unusedSettings = new UnusedSettings();
    assertEquals(unusedSettings, context.getSettings(UnusedSettings.class, unusedSettings));
  }

  @Test
  public void testGetResourceKeys() {
    String res1 = "key1";
    String res2 = "key2";
    TestResource1 resource1 = Mockito.mock(TestResource1.class);
    TestResource2 resource2 = Mockito.mock(TestResource2.class);
    Context context = Mockito.mock(Context.class);
    doReturn(Optional.of(resource1))
        .when(context)
        .getResource(Mockito.eq(res1), Mockito.eq(TestResource1.class));
    doReturn(Optional.of(resource2))
        .when(context)
        .getResource(Mockito.eq(res2), Mockito.eq(TestResource2.class));

    doAnswer(
            new Answer<Stream<String>>() {
              @Override
              public Stream<String> answer(InvocationOnMock invocation) {
                return Stream.of(res1, res2);
              }
            })
        .when(context)
        .getResourceKeys();

    doCallRealMethod().when(context).getResourceKeys(Mockito.any());

    List<String> result1 =
        context.getResourceKeys(TestResource1.class).collect(Collectors.toList());
    assertEquals(1, result1.size());
    assertEquals(res1, result1.get(0));

    List<String> result2 =
        context.getResourceKeys(TestResource2.class).collect(Collectors.toList());
    assertEquals(1, result2.size());
    assertEquals(res2, result2.get(0));

    assertEquals(0, context.getResourceKeys(TestResource3.class).count());
  }

  @Test
  public void testGetResource() {
    Context context = Mockito.mock(Context.class);
    Resource res1 = Mockito.mock(TestResource1.class);
    doAnswer(
            new Answer<Stream<Resource>>() {
              @Override
              public Stream<Resource> answer(InvocationOnMock invocation) {
                return Stream.of(res1);
              }
            })
        .when(context)
        .getResources(Mockito.eq(TestResource1.class));
    doAnswer(
            new Answer<Stream<Resource>>() {
              @Override
              public Stream<Resource> answer(InvocationOnMock invocation) {
                return Stream.empty();
              }
            })
        .when(context)
        .getResources(Mockito.eq(TestResource2.class));
    doCallRealMethod().when(context).getResource(Mockito.any());

    Optional<TestResource1> resource = context.getResource(TestResource1.class);
    assertTrue(resource.isPresent());
    assertEquals(res1, resource.get());

    Optional<TestResource2> resource2 = context.getResource(TestResource2.class);
    assertFalse(resource2.isPresent());
  }

  public static class TestSettings implements Settings {

    @Override
    public boolean validate() {
      return true;
    }
  }

  public static class UnusedSettings implements Settings {

    @Override
    public boolean validate() {
      return true;
    }
  }

  public abstract class TestResource1 implements Resource {}

  public abstract class TestResource2 implements Resource {}

  public abstract class TestResource3 implements Resource {}
}
