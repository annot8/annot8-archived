/**
 * This module contains common functionality for the Annot8 framework, including helper and utility
 * functions, abstract classes, factory objects, and common implementations of some components
 * (notably Bounds).
 *
 * <p>It does not contain default implementations of the majority of components, which are held in
 * the default-impl module.
 *
 * <p>The abstract classes in this module are there to provide correct implementations of functions
 * such as equals, hashCode and toString. They do not provide any logic beyond this, and should
 * generally be used by any implementations of the interfaces they are abstracting.
 */
open module io.annot8.common.implementations {
  requires java.json.bind;
  requires com.google.common;
  requires transitive io.annot8.core;
  requires org.slf4j;
  requires io.annot8.common.utils;

  exports io.annot8.common.implementations.annotations;
  exports io.annot8.common.implementations.factories;
  exports io.annot8.common.implementations.references;
  exports io.annot8.common.implementations.registries;
  exports io.annot8.common.implementations.stores;
  exports io.annot8.common.implementations.properties;
  exports io.annot8.common.implementations.content;
  exports io.annot8.common.implementations.delegates;
  exports io.annot8.common.implementations.listeners;
}
