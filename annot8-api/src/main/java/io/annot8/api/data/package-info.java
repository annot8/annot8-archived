/**
 * This package contains the api data objects used by Annot8
 *
 * <p>Content objects contain the actual data, or more specifically a view on the data. For example,
 * you might have a Content object containing French text, and a Content object containing a
 * translated version of that text. Annotations are stored within the Content objects.
 *
 * <p>Content objects are stored within Item objects, which are the object that is passed around
 * between components. Groups are stored within the Item object, as a group may contain annotations
 * from more than one content object.
 */
package io.annot8.api.data;
