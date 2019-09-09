/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.serialisation.jackson;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ScanResult;

public class Annot8ObjectMapperFactory {

  private static final Logger LOGGER = LoggerFactory.getLogger(Annot8ObjectMapperFactory.class);

  private final Set<AbstractAnnot8Serializer> serializers = new HashSet<>();

  private final Set<AbstractAnnot8Deserializer> deserializers = new HashSet<>();

  public Annot8ObjectMapperFactory() {
    // Do nothing
  }

  public Annot8ObjectMapperFactory(
      Collection<AbstractAnnot8Serializer<?>> serializers,
      Collection<AbstractAnnot8Deserializer<?>> deserializers) {
    if (deserializers != null) {
      deserializers.forEach(this::register);
    }

    if (serializers != null) {
      serializers.forEach(this::register);
    }
  }

  public void scan() {

    ScanResult scan = new ClassGraph().enableClassInfo().enableMethodInfo().scan();

    scan.getSubclasses(AbstractAnnot8Deserializer.class.getName())
        .filter(this::hasPublicNoArgumentsConstructor)
        .forEach(ci -> registerDeserialiser((Class<AbstractAnnot8Deserializer<?>>) ci.loadClass()));

    scan.getSubclasses(AbstractAnnot8Serializer.class.getName())
        .filter(this::hasPublicNoArgumentsConstructor)
        .forEach(ci -> registerSerialiser((Class<AbstractAnnot8Serializer<?>>) ci.loadClass()));
  }

  private boolean hasPublicNoArgumentsConstructor(ClassInfo ci) {
    return !ci.isAbstract()
        && ci.getConstructorInfo()
            .stream()
            .anyMatch(mi -> mi.isPublic() && mi.getParameterInfo().length == 0);
  }

  protected void registerSerialiser(Class<AbstractAnnot8Serializer<?>> clazz) {
    try {
      register(clazz.getConstructor().newInstance());
    } catch (NoSuchMethodException
        | InstantiationException
        | IllegalAccessException
        | InvocationTargetException e) {
      // Do nothing
    }
  }

  protected void registerDeserialiser(Class<AbstractAnnot8Deserializer<?>> clazz) {
    try {
      register(clazz.getConstructor().newInstance());
    } catch (NoSuchMethodException
        | InstantiationException
        | IllegalAccessException
        | InvocationTargetException e) {
      // Do nothing
    }
  }

  public void register(AbstractAnnot8Deserializer<?> deserializer) {
    if (deserializer != null) {
      deserializers.add(deserializer);
    }
  }

  public void register(AbstractAnnot8Serializer<?> serializer) {
    if (serializer != null) {
      serializers.add(serializer);
    }
  }

  public com.fasterxml.jackson.databind.Module getModule() {
    SimpleModule module = new SimpleModule();
    serializers.forEach(s -> module.addSerializer(s.getSerializableClass(), s));
    deserializers.forEach(d -> module.addDeserializer(d.getDeserializableClass(), d));
    return module;
  }

  public ObjectMapper configure(ObjectMapper mapper) {
    mapper.registerModule(getModule());
    return mapper;
  }
}
