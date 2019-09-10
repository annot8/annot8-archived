/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.implementations.delegates;

import io.annot8.core.data.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import io.annot8.core.data.Content;
import io.annot8.core.exceptions.UnsupportedContentException;

@ExtendWith(MockitoExtension.class)
class DelegateItemTest {

  @Mock
  Item delegate;

  private DelegateItem item;

  @BeforeEach
  public void beforeEach() {
    item = new DelegateItem(delegate);
  }

  @Test
  void getParent() {
    item.getParent();
    Mockito.verify(delegate, Mockito.times(1)).getParent();
  }

  @Test
  void hasParent() {
    item.hasParent();
    Mockito.verify(delegate, Mockito.times(1)).hasParent();
  }

  @Test
  void getContent() {
    item.getContent("id");
    Mockito.verify(delegate, Mockito.times(1)).getContent("id");
  }

  @Test
  void getContents() {
    item.getContents();
    Mockito.verify(delegate, Mockito.times(1)).getContents();
  }

  @Test
  void getContents1() {
    item.getContents(Content.class);
    Mockito.verify(delegate, Mockito.times(1)).getContents(Content.class);
  }

  @Test
  void create() throws UnsupportedContentException {
    item.create(Content.class);
    Mockito.verify(delegate, Mockito.times(1)).create(Content.class);
  }

  @Test
  void removeContent() {
    item.removeContent("id");
    Mockito.verify(delegate, Mockito.times(1)).removeContent("id");
  }

  @Test
  void discard() {
    item.discard();
    Mockito.verify(delegate, Mockito.times(1)).discard();
  }

  @Test
  void isDiscarded() {
    item.isDiscarded();
    Mockito.verify(delegate, Mockito.times(1)).isDiscarded();
  }

  @Test
  void getGroups() {
    item.getGroups();
    Mockito.verify(delegate, Mockito.times(1)).getGroups();
  }

  @Test
  void getId() {
    item.getId();
    Mockito.verify(delegate, Mockito.times(1)).getId();
  }

  @Test
  void getProperties() {
    item.getProperties();
    Mockito.verify(delegate, Mockito.times(1)).getProperties();
  }

  @Test
  void createSubItem() {
    item.create();
    Mockito.verify(delegate, Mockito.times(1)).create();
  }
}
