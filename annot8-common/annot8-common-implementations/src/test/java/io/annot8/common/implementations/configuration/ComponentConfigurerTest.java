/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.implementations.configuration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import io.annot8.common.implementations.context.SimpleContext;
import io.annot8.core.components.Processor;
import io.annot8.core.components.Resource;
import io.annot8.core.context.Context;
import io.annot8.core.exceptions.BadConfigurationException;
import io.annot8.core.exceptions.MissingResourceException;
import io.annot8.core.settings.Settings;

@ExtendWith(MockitoExtension.class)
class ComponentConfigurerTest {

  @Mock Resource globalResource;

  ComponentConfigurer configurer;

  @BeforeEach
  public void beforeEach() {
    Context context = new SimpleContext(Map.of("global", globalResource));
    configurer = new ComponentConfigurer(context);
  }

  @Test
  public void globalResourcesAvailable() {
    final Map<String, Resource> gotResources = configurer.getResources();
    assertThat(gotResources).containsEntry("global", globalResource);
  }

  @Test
  void configureEmptyResources() {

    ResourcesHolder holder = new ResourcesHolder();

    final Map<String, Resource> resources = configurer.configureResources(holder);
    final Map<String, Resource> gotResources = configurer.getResources();

    assertThat(gotResources).isEqualTo(resources);
    // Confirm that our global resource is still there
    assertThat(gotResources).containsEntry("global", globalResource);

    assertThat(gotResources).hasSize(1);
  }

  @Test
  void configureResources() throws BadConfigurationException, MissingResourceException {

    ResourcesHolder holder = new ResourcesHolder();

    Resource r = mock(Resource.class);
    Collection<Settings> settings = new ArrayList<>();
    holder.addResource("local", r, settings);

    final Map<String, Resource> resources = configurer.configureResources(holder);
    final Map<String, Resource> gotResources = configurer.getResources();

    assertThat(gotResources).isEqualTo(resources);
    // Confirm that our global resource is still there
    assertThat(gotResources).containsEntry("global", globalResource);

    // Check rest of resources

    // Ensure configure has been called
    verify(r).configure(any(Context.class));

    assertThat(gotResources).containsEntry("local", r);
  }

  @Test
  void configureComponents() throws BadConfigurationException, MissingResourceException {
    ComponentHolder<Processor> holder = new ComponentHolder<>();

    Processor p = mock(Processor.class);
    Collection<Settings> settings = new ArrayList<>();
    holder.add(p, settings);

    Processor q = mock(Processor.class);
    Collection<Settings> qSettings = new ArrayList<>();
    holder.add(q, qSettings);

    final List<Processor> processors = configurer.configureComponents(holder);

    assertThat(processors).containsExactly(p, q);
    verify(p).configure(any(Context.class));
  }

  @Test
  void configureComponent() throws BadConfigurationException, MissingResourceException {
    Processor p = mock(Processor.class);
    Collection<Settings> settings = new ArrayList<>();

    configurer.configureComponent(p, settings);

    verify(p).configure(any(Context.class));
  }
}
