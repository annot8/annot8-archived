/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.implementations.factories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import io.annot8.core.data.Item;

@ExtendWith(MockitoExtension.class)
class SimpleItemFactoryTest {

  @Mock BaseItemFactory baseItemFactory;

  @Mock WrappingBaseItemToItem converter;

  @Mock BaseItem baseItem;

  @Mock Item item;

  @Test
  void create() {

    when(baseItemFactory.create()).thenReturn(baseItem);
    when(converter.convert(baseItem)).thenReturn(item);

    final SimpleItemFactory factory = new SimpleItemFactory(baseItemFactory, converter);

    Item created = factory.create();

    assertThat(created).isEqualTo(item);
  }

  @Test
  void createWithDefault() {

    when(baseItemFactory.create()).thenReturn(baseItem);

    final SimpleItemFactory factory = new SimpleItemFactory(baseItemFactory);

    Item created = factory.create();

    assertThat(created).isNotNull();
  }
}
