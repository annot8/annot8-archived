/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.implementations.delegates;

import java.util.Optional;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import io.annot8.core.components.Resource;
import io.annot8.core.context.Context;
import io.annot8.core.settings.Settings;

@ExtendWith(MockitoExtension.class)
class DelegateContextTest {

  @Mock Context delegate;

  private DelegateContext context;

  @BeforeEach
  public void beforeEach() {
    context = new DelegateContext(delegate);
  }

  @Test
  void getSettings() {
    final Stream<Settings> settings = context.getSettings();
    Mockito.verify(delegate, Mockito.times(1)).getSettings();
  }

  @Test
  void getSettings1() {
    final Optional<Settings> result = context.getSettings(Settings.class);
    Mockito.verify(delegate, Mockito.times(1)).getSettings(Settings.class);
  }

  @Test
  void getResource() {
    context.getResource(Resource.class);
    Mockito.verify(delegate, Mockito.times(1)).getResource(Resource.class);
  }

  @Test
  void getResourceKeys() {
    context.getResourceKeys();
    Mockito.verify(delegate, Mockito.times(1)).getResourceKeys();
  }

  @Test
  void getResourceKeys1() {
    context.getResourceKeys(Resource.class);
    Mockito.verify(delegate, Mockito.times(1)).getResourceKeys(Resource.class);
  }

  @Test
  void getResource1() {
    context.getResource("name", Resource.class);
    Mockito.verify(delegate, Mockito.times(1)).getResource("name", Resource.class);
  }

  @Test
  void getResources() {
    context.getResources(Resource.class);
    Mockito.verify(delegate, Mockito.times(1)).getResources(Resource.class);
  }
}
