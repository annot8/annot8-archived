/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.implementations.factories;

import java.util.function.Consumer;

import io.annot8.core.data.Item;

@FunctionalInterface
public interface ItemFactoryListener extends Consumer<Item> {}
