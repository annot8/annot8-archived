package io.annot8.core.capabilities;

public class GroupCapability {
  public static final String ANY_TYPE = "__any__";
  
  private final String type;

  public GroupCapability(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }
}
