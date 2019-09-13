package io.annot8.common.serialization;

import org.junit.jupiter.api.Test;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Annot8ComponentDescriptorSerializerTest {
  private static final TestDescriptor descriptor = new TestDescriptor("Test", "localhost", 8080);
  private static final String serialized =
      "{\""
          + TestDescriptor.class.getName()
          + "\":{\"name\":\"Test\",\"settings\":{\"host\":\"localhost\",\"port\":8080}}}";
  @Test
  public void test(){
    JsonbConfig config = new JsonbConfig().withSerializers(new Annot8ComponentDescriptorSerializer());
    Jsonb jb = JsonbBuilder.create(config);

    String json = jb.toJson(descriptor);
    assertEquals(serialized, json);
  }
}
