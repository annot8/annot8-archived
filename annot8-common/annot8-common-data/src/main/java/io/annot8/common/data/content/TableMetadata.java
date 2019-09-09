/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.data.content;

import java.util.List;

public class TableMetadata {

  private String name;
  private String type;
  private List<ColumnMetadata> columns;
  private int rowCount;

  public TableMetadata(String name, String type, List<ColumnMetadata> columns, int rowCount) {
    this.name = name;
    this.type = type;
    this.columns = columns;
    this.rowCount = rowCount;
  }

  public String getName() {
    return name;
  }

  public String getType() {
    return type;
  }

  public List<ColumnMetadata> getColumns() {
    return columns;
  }

  public int getRowCount() {
    return rowCount;
  }
}
