/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.implementations.capabilities;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.annot8.core.components.Annot8Component;
import io.annot8.core.settings.Settings;
import io.annot8.core.settings.SettingsClass;

public class SettingsCompilerTest {

  private SettingsCompiler compiler;

  @BeforeEach
  public void beforeEach() {
    compiler = new SettingsCompiler();
  }

  @Test
  public void compileWithNoSettings() {
    Map<Class<?>, Collection<Class<? extends Settings>>> map = compiler.compile(NoSettings.class);
    assertThat(map).isEmpty();
  }

  @Test
  public void compileAsSetWithNoSettings() {
    Set<Class<? extends Settings>> set = compiler.compileAsSet(NoSettings.class);
    assertThat(set).isEmpty();
  }

  @Test
  public void compileWithSingleSettings() {
    Map<Class<?>, Collection<Class<? extends Settings>>> map =
        compiler.compile(SingleSettings.class);

    assertThat(map.keySet()).containsExactly(SingleSettings.class);
    assertThat(map.get(SingleSettings.class)).containsExactly(SettingsA.class);
  }

  @Test
  public void compileAsSetWithSingleSettings() {
    Set<Class<? extends Settings>> set = compiler.compileAsSet(SingleSettings.class);
    assertThat(set).containsExactly(SettingsA.class);
  }

  @Test
  public void compileWithMultiSettings() {
    Map<Class<?>, Collection<Class<? extends Settings>>> map =
        compiler.compile(MultipleSettings.class);

    assertThat(map.keySet()).containsExactly(MultipleSettings.class);
    assertThat(map.get(MultipleSettings.class))
        .containsExactlyInAnyOrder(SettingsA.class, SettingsB.class);
  }

  @Test
  public void compileAsSetWithMultiSettings() {
    Set<Class<? extends Settings>> set = compiler.compileAsSet(MultipleSettings.class);
    assertThat(set).containsExactlyInAnyOrder(SettingsA.class, SettingsB.class);
  }

  @Test
  public void compileWithInheritedSettings() {
    Map<Class<?>, Collection<Class<? extends Settings>>> map =
        compiler.compile(InheritedSettings.class);

    assertThat(map.keySet())
        .containsExactlyInAnyOrder(MultipleSettings.class, InheritedSettings.class);
    assertThat(map.get(MultipleSettings.class))
        .containsExactlyInAnyOrder(SettingsA.class, SettingsB.class);
    assertThat(map.get(InheritedSettings.class)).containsExactlyInAnyOrder(SettingsB.class);
  }

  @Test
  public void compileAsSetWithInheritedSettings() {
    Set<Class<? extends Settings>> set = compiler.compileAsSet(InheritedSettings.class);
    assertThat(set).containsExactlyInAnyOrder(SettingsA.class, SettingsB.class);
  }

  // Class examples to test

  public static class SettingsA implements Settings {

    @Override
    public boolean validate() {
      return true;
    }
  }

  public static class SettingsB implements Settings {

    @Override
    public boolean validate() {
      return true;
    }
  }

  public static class NoSettings implements Settings {

    @Override
    public boolean validate() {
      return true;
    }
  }

  @SettingsClass(SettingsA.class)
  public static class SingleSettings implements Settings {

    @Override
    public boolean validate() {
      return true;
    }
  }

  @SettingsClass(SettingsA.class)
  @SettingsClass(SettingsB.class)
  public static class MultipleSettings implements Annot8Component {}

  @SettingsClass(SettingsB.class)
  public static class InheritedSettings extends MultipleSettings {}
}
