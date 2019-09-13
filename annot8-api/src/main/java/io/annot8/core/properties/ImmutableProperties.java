/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.core.properties;

import io.annot8.core.helpers.builders.WithFromBuilder;
import io.annot8.core.helpers.builders.WithPropertiesBuilder;
import io.annot8.core.helpers.builders.WithSave;

/** Base immutable properties interface from which all other immutable properties extend. */
public interface ImmutableProperties extends Properties {

  /** Builder interface to createContent (immutable) properties classes */
  interface Builder
      extends WithFromBuilder<Builder, Properties>,
          WithPropertiesBuilder<Builder>,
          WithSave<ImmutableProperties> {}
}
