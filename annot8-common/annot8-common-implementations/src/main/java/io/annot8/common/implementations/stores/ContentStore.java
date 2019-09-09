/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.implementations.stores;

import java.util.Optional;
import java.util.stream.Stream;

import io.annot8.core.data.Content;

public interface ContentStore {

  Stream<Content<?>> getContent();

  Optional<Content<?>> getContent(String contentName);

  Stream<String> getContentNames();

  void removeContent(String contentName);

  Content<?> save(Content<?> content);
}
