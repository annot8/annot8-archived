/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.testing.tck.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import io.annot8.common.data.content.Text;
import io.annot8.core.data.BaseItem;
import io.annot8.core.data.Content;
import io.annot8.core.data.Item;
import io.annot8.core.exceptions.IncompleteException;
import io.annot8.core.exceptions.UnsupportedContentException;
import io.annot8.testing.testimpl.TestConstants;

/**
 * Abstract class providing tests for non default methods on provided implementations of {@link
 * Item}
 */
public abstract class AbstractItemTest {

  protected abstract BaseItem getItem();

  @Test
  public void testGetContent() {
    BaseItem item = getItem();
    try {
      item.create(Text.class)
          .withData(() -> "test")
          .withName(TestConstants.CONTENT_NAME)
          .withId(TestConstants.CONTENT_ID)
          .save();
    } catch (IncompleteException | UnsupportedContentException e) {
      fail("Test should not fail here", e);
    }

    Optional<Content<?>> optional = item.getContent(TestConstants.CONTENT_ID);
    assertTrue(optional.isPresent());
    Content<?> content = optional.get();
    assertEquals(TestConstants.CONTENT_ID, content.getId());
    assertEquals(TestConstants.CONTENT_NAME, content.getName());
    assertEquals("test", content.getData());
  }

  @Test
  public void testGetContents() {
    BaseItem item = getItem();

    try {
      item.create(Text.class).withData(() -> "test").withName(TestConstants.CONTENT_NAME).save();
      item.create(Text.class).withData(() -> "test2").withName("content2").save();
    } catch (UnsupportedContentException | IncompleteException e) {
      fail("Test should not error here", e);
    }

    assertThat(item.getContents().map(Content::getData).map(String.class::cast))
        .containsExactlyInAnyOrder("test", "test2");
    assertThat(item.getContents().map(Content::getName))
        .containsExactlyInAnyOrder(TestConstants.CONTENT_NAME, "content2");
  }

  @Test
  public void testCreate() {
    BaseItem item = getItem();

    Text test = null;
    try {
      test = item.create(Text.class).withName(TestConstants.CONTENT_NAME).withData("test").save();
    } catch (UnsupportedContentException | IncompleteException e) {
      fail("Test should not throw an exception.", e);
    }

    assertNotNull(test);
    assertEquals(TestConstants.CONTENT_NAME, test.getName());
    assertEquals("test", test.getData());
    assertNotNull(test.getId());
  }

  @Test
  public void testRemoveContent() {
    BaseItem item = getItem();
    try {
      item.create(Text.class)
          .withData(() -> "test")
          .withName(TestConstants.CONTENT_NAME)
          .withId(TestConstants.CONTENT_ID)
          .save();
    } catch (IncompleteException | UnsupportedContentException e) {
      fail("Test should not fail here", e);
    }

    assertThat(item.getContents()).isNotEmpty();

    item.removeContent(TestConstants.CONTENT_ID);

    assertThat(item.getContents()).isEmpty();
  }

  @Test
  public void testDiscard() {
    BaseItem item = getItem();
    assertFalse(item.isDiscarded());
    item.discard();
    assertTrue(item.isDiscarded());
  }
}
