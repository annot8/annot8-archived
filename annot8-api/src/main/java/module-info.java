/**
 * This module contains the api interfaces for use within Annot8. It does not contain any
 * implementations of the interfaces described.
 *
 * <p>The basic premise of how Annot8 works is as follows:
 *
 * <p>You have an Item, which is your container and holds information about the data you wish to
 * process. This Item contains one or more Content objects, which are essentially views on the data
 * - for instance, if the data you wished to process was a HTML page, you might have a plain text
 * 'view' of the data as well as a formatted view.
 *
 * <p>On to these Content objects, you createContent Annotations. Annotations indicate that a
 * certain part of the Content has some special meaning (e.g. it is a mention of a person). These
 * Annotations are stored in an AnnotationStore, which is held by each Content object. An Annotation
 * has Bounds associated with it, which indicate the extent of the Annotation. The type of Bounds
 * will vary depending on the Content.
 *
 * <p>You can link Annotations together with Groups. When adding an Annotation to a Group, you must
 * assign it a role. This allows Groups to be used for a wide range of use cases, such as creating
 * relationships or event, and indicating co-reference between Annotations. Groups are stored in a
 * GroupStore, which is held on the Item object so that a Group may contain Annotations from
 * multiple Content objects.
 *
 * <p>Most of the objects above processors Properties, which allow you to attach additional
 * information to an object. Many objects also have a type and an ID.
 *
 * <p>There are three types of 'component' which are used to createContent and manipulate the above
 * objects. A Source will createContent new Items from some external source, for example a file
 * system or a database. The Items created by a Source will be passed to one or more Processors,
 * which will manipulate the Items by adding new Content objects to it, manipulating the Annotations
 * within these Content objects, and creating Groups of Annotations.
 *
 * <p>Both the Sources and Processors can use the third type of component - a Resource - to access
 * common or external resources (e.g. a database connection, or reference dataset).
 *
 * <p>It is envisaged that Sources, Processors and Resources will generally be corralled together to
 * form a processing pipeline which will read data in from one or more sources and do some
 * processing on it. However, the pipeline concept is not defined in this module so as to leave
 * users free to combine the components in any manner they wish.
 */
open module io.annot8.api {
  exports io.annot8.api.annotations;
  exports io.annot8.api.bounds;
  exports io.annot8.api.capabilities;
  exports io.annot8.api.components;
  exports io.annot8.api.components.annotations;
  exports io.annot8.api.components.responses;
  exports io.annot8.api.data;
  exports io.annot8.api.exceptions;
  exports io.annot8.api.helpers;
  exports io.annot8.api.helpers.builders;
  exports io.annot8.api.properties;
  exports io.annot8.api.references;
  exports io.annot8.api.settings;
  exports io.annot8.api.stores;
  exports io.annot8.api.filters;
  exports io.annot8.api.context;
  exports io.annot8.api.pipelines;
}
