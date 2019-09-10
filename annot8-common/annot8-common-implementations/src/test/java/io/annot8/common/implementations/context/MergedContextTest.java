/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.implementations.context;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import io.annot8.core.components.Resource;
import io.annot8.core.settings.Settings;

@ExtendWith(MockitoExtension.class)
class MergedContextTest {

  @Mock Resource aResource;

  @Mock Resource bResource;

  private SimpleContext a;
  private SimpleContext b;
  private MergedContext m;

  @BeforeEach
  public void beforeEach() {
    a = new SimpleContext(Map.of("a", aResource));
    b = new SimpleContext(Map.of("b", bResource));
    m = new MergedContext(a, b);
  }

  @Test
  void getResource() {
    assertThat(m.getResource(Resource.class).get()).isEqualTo(aResource);
  }

  @Test
  void getResourceKeys() {
    assertThat(m.getResourceKeys(Resource.class)).containsExactly("a", "b");
  }

  @Test
  void getResourceKeys1() {
    assertThat(m.getResourceKeys()).containsExactly("a", "b");
  }

  @Test
  void getResource1() {
    assertThat(m.getResource("b", Resource.class).get()).isEqualTo(bResource);
  }

  @Test
  void getResources() {
    assertThat(m.getResources(Resource.class)).containsExactly(aResource, bResource);
  }
}
