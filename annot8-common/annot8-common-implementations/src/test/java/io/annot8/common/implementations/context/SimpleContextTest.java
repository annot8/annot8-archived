/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.implementations.context;

import io.annot8.core.components.Resource;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class SimpleContextTest {

  @Test
  void getResources() {
    Resource r1 = mock(Resource.class);
    Resource r2 = mock(Resource.class);

    SimpleContext context = new SimpleContext(r1, r2);

    assertThat(context.getResources()).containsExactly(r1, r2);
  }
}
