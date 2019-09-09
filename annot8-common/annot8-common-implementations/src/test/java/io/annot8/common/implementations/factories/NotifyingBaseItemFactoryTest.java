/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.implementations.factories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import io.annot8.common.implementations.data.BaseItemFactory;
import io.annot8.common.implementations.listeners.Deregister;
import io.annot8.core.data.BaseItem;

@ExtendWith(MockitoExtension.class)
class NotifyingBaseItemFactoryTest {

  @Mock BaseItemFactory delegateItemFactory;

  @Mock BaseItem itemWithoutParent;

  @Mock BaseItem itemWithParent;

  private NotifyingBaseItemFactory itemFactory;

  @BeforeEach
  public void beforeEach() {

    itemFactory = new NotifyingBaseItemFactory(delegateItemFactory);
  }

  @Test
  void create() {

    when(delegateItemFactory.create()).thenReturn(itemWithoutParent);

    List<BaseItem> items = new LinkedList<>();
    itemFactory.register(items::add);
    final BaseItem item = itemFactory.create();

    assertThat(item).isEqualTo(itemWithoutParent);
    assertThat(items).containsExactly(itemWithoutParent);
  }

  @Test
  void createWithParent() {
    when(delegateItemFactory.create(Mockito.any(BaseItem.class))).thenReturn(itemWithParent);

    List<BaseItem> items = new LinkedList<>();
    itemFactory.register(items::add);
    final BaseItem item = itemFactory.create(itemWithoutParent);

    assertThat(item).isEqualTo(itemWithParent);
    assertThat(items).containsExactly(itemWithParent);
  }

  @Test
  void deregister() {
    when(delegateItemFactory.create(Mockito.any(BaseItem.class))).thenReturn(itemWithParent);

    List<BaseItem> items = new LinkedList<>();

    Consumer<BaseItem> consumer = items::add;
    Deregister deregister = itemFactory.register(consumer);

    // This would fire as above

    deregister.deregister();

    final BaseItem item = itemFactory.create(itemWithoutParent);

    assertThat(item).isEqualTo(itemWithParent);
    assertThat(items).isEmpty();
  }
}
