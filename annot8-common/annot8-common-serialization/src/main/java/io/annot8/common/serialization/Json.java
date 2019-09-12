package io.annot8.common.serialization;

import io.annot8.core.components.Annot8ComponentDescriptor;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Json implements Serializer<String> {
  private Jsonb jb = JsonbBuilder.create();

  private static final Pattern clazzPattern = Pattern.compile("\\{\"(.*?)\":(\\{.*})}");

  @Override
  public Annot8ComponentDescriptor deserialize(String serialized) throws IOException, ClassNotFoundException {
    Matcher m = clazzPattern.matcher(serialized);
    if (!m.find()) {
      throw new IOException("JSON is not correct format");
    }

    Class<? extends Annot8ComponentDescriptor> clazz = Class.forName(m.group(1)).asSubclass(Annot8ComponentDescriptor.class);
    return jb.fromJson(m.group(2), clazz);
  }

  @Override
  public String serialize(Annot8ComponentDescriptor descriptor) {
    String descriptorJson = jb.toJson(descriptor);
    return "{\""+descriptor.getClass().getName()+"\":"+descriptorJson+"}";
  }
}
