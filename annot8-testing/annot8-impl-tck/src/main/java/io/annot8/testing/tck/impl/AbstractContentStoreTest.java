/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.testing.tck.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.annot8.common.implementations.properties.MapImmutableProperties;
import io.annot8.common.implementations.properties.MapMutableProperties;
import io.annot8.common.implementations.stores.ContentStore;
import io.annot8.core.data.Content;
import io.annot8.core.data.Item;
import io.annot8.core.exceptions.IncompleteException;
import io.annot8.core.properties.ImmutableProperties;
import io.annot8.testing.testimpl.TestItem;
import io.annot8.testing.testimpl.content.TestStringContent;

public abstract class AbstractContentStoreTest {

  private static final String TEST_ID = "TEST_ID";
  private static final String TEST_NAME = "TEST_NAME";
  private static final String TEST_DATA = "TEST_DATA";
  private static final String TEST_PROPERTY_KEY = "TEST_PROPERTY_KEY";
  private static final String TEST_PROPERTY_VALUE = "TEST_PROPERTY_VALUE";
  private final TestItem item = new TestItem();

  protected abstract ContentStore getContentStore(Item item);

  @Test
  public void testGetContent() {
    TestStringContent testContent = createDefaultTestContent();
    ContentStore contentStore = getContentStore(item);
    contentStore.save(testContent);

    List<Content<?>> content = contentStore.getContent().collect(Collectors.toList());
    Assertions.assertEquals(1, content.size());
    testDefaultContent(content.get(0));

    contentStore.save(
        createTestContent("TEST_CONTENT_2", createTestProperties(), "TEST_DATA", "TEST_NAME_2"));
    Assertions.assertEquals(2, contentStore.getContent().count());
  }

  @Test
  public void testGetContentWithName() {
    ContentStore contentStore = getContentStore(item);
    contentStore.save(createDefaultTestContent());
    String contentName2 = "TEST_NAME_2";
    String testId2 = "TEST_CONTENT_2";
    contentStore.save(createTestContent(testId2, createTestProperties(), TEST_DATA, contentName2));

    Optional<Content<?>> content = contentStore.getContent(TEST_NAME);
    Assertions.assertTrue(content.isPresent());
    testDefaultContent(content.get());

    Optional<Content<?>> content2 = contentStore.getContent(contentName2);
    Assertions.assertTrue(content.isPresent());
    testContentFields(
        testId2, contentName2, TEST_DATA, TEST_PROPERTY_KEY, TEST_PROPERTY_VALUE, content2.get());
  }

  @Test
  public void testGetContentNames() {
    ContentStore contentStore = getContentStore(item);
    contentStore.save(createDefaultTestContent());
    String contentName2 = "contentName2";
    String contentId2 = "contentId2";
    contentStore.save(
        createTestContent(contentId2, createTestProperties(), TEST_DATA, contentName2));

    List<String> contentNames = contentStore.getContentNames().collect(Collectors.toList());
    Assertions.assertEquals(2, contentNames.size());
    Assertions.assertTrue(contentNames.contains(TEST_NAME));
    Assertions.assertTrue(contentNames.contains(contentName2));
  }

  @Test
  public void testRemoveContent() {
    ContentStore contentStore = getContentStore(item);
    contentStore.save(createDefaultTestContent());
    Assertions.assertEquals(1, contentStore.getContent().count());
    contentStore.removeContent(TEST_ID);
    Assertions.assertEquals(1, contentStore.getContent().count());
  }

  @Test
  public void testSave() {
    ContentStore contentStore = getContentStore(item);
    Content<?> savedContent = contentStore.save(createDefaultTestContent());
    testDefaultContent(savedContent);
  }

  private void testContentFields(
      String expectedId,
      String expectedName,
      String expectedData,
      String expectedPropertyKey,
      String expectedPropertyValue,
      Content content) {
    Assertions.assertEquals(expectedId, content.getId());
    Assertions.assertEquals(expectedName, content.getName());
    Assertions.assertEquals(expectedData, content.getData());
    Assertions.assertTrue(content.getProperties().has(expectedPropertyKey));
    Assertions.assertEquals(
        expectedPropertyValue, content.getProperties().get(expectedPropertyKey).get());
  }

  private void testDefaultContent(Content content) {
    testContentFields(
        TEST_ID, TEST_NAME, TEST_DATA, TEST_PROPERTY_KEY, TEST_PROPERTY_VALUE, content);
  }

  private ImmutableProperties createTestProperties() {
    Map<String, Object> props = new HashMap<>();
    props.put(TEST_PROPERTY_KEY, TEST_PROPERTY_VALUE);
    MapMutableProperties simpleMutableProperties = new MapMutableProperties(props);
    ImmutableProperties.Builder propsBuilder = new MapImmutableProperties.Builder();
    ImmutableProperties properties = null;
    try {
      properties = propsBuilder.from(simpleMutableProperties).save();
    } catch (IncompleteException e) {
      Assertions.fail("Failed to build properties", e);
    }
    return properties;
  }

  private TestStringContent createTestContent(
      String id, ImmutableProperties properties, String data, String name) {
    return new TestStringContent(id, name, properties, () -> data);
  }

  private TestStringContent createDefaultTestContent() {
    return new TestStringContent(TEST_ID, TEST_NAME, createTestProperties(), () -> TEST_DATA);
  }
}
