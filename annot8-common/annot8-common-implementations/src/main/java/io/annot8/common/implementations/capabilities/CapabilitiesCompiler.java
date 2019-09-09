/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.implementations.capabilities;

import java.util.function.Supplier;

import io.annot8.core.capabilities.Capabilities;
import io.annot8.core.capabilities.Capabilities.Builder;
import io.annot8.core.components.Annot8Component;

/**
 * Merges capabilities for components from their annotations and buildCapabilities.
 *
 * <p>The annotations are read first, and then buildComponents (though ordering should not matter).
 *
 * <p>Since a component parent classes have annotations on them, for each component its entired
 * class hierarchy is processed. For the buildCapabilitites it is up the class itself to call
 * super.buildCapabilities in order to add the 'dynamic' capabilitties of its parents.
 *
 * <p>The result of the compilation is a single Capabilities object which can be used.
 *
 * <p>As buildCapabiltiites are configuration dependent if you udpate the configuration (eg rerun
 * Annot8Component.configure) you should recompile the capabilities.
 */
public class CapabilitiesCompiler {

  private final Supplier<Builder> builderSupplier;

  /**
   * New instance
   *
   * @param builderSupplier a supplier which will provide a new Capabilities builder each call
   */
  public CapabilitiesCompiler(Supplier<Builder> builderSupplier) {
    this.builderSupplier = builderSupplier;
  }

  /**
   * Merge the capabilities for a component into a single {@link Capabilities} object
   *
   * @param component the component to consider
   * @return the capabilities
   */
  public Capabilities compile(Annot8Component component) {
    Builder builder = builderSupplier.get();

    addAnnotatedCapabilities(builder, component.getClass());

    addGetCapabilities(builder, component);

    return builder.save();
  }

  protected void addGetCapabilities(Builder builder, Annot8Component component) {
    component.buildCapabilities(builder);
  }

  protected void addAnnotatedCapabilities(Builder builder, Class<?> clazz) {
    // Recurse through parents
    Class<?> superclass = clazz.getSuperclass();
    if (superclass != null) {
      addAnnotatedCapabilities(builder, superclass);
    }

    AnnotationBasedCapabilities capabilities = new AnnotationBasedCapabilities(clazz);
    builder.merge(capabilities);
  }
}
