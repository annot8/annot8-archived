/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.data.bounds;

import java.util.Optional;

import io.annot8.common.data.content.Row;
import io.annot8.common.data.content.Table;
import io.annot8.common.data.content.TableContent;
import io.annot8.core.data.Content;
import io.annot8.core.exceptions.Annot8RuntimeException;

public class CellBounds extends AbstractCellBounds {

  private final int row;
  private final int column;

  public CellBounds(int row, int column) {
    this.row = row;
    this.column = column;
  }

  @Override
  public <D, C extends Content<D>, R> Optional<R> getData(C content, Class<R> requiredClass) {
    if (isValid(content)) {
      Table table = (Table) content.getData();
      Optional<Row> optional = table.getRow(row);
      if (!optional.isPresent()) {
        throw new Annot8RuntimeException("Failed to find row");
      }
      Optional<Object> value = optional.get().getValueAt(column);
      return value.map(requiredClass::cast);
    }
    return Optional.empty();
  }

  @Override
  public <D, C extends Content<D>> boolean isValid(C content) {
    return super.isValid(content) && isCellReferenceValid((TableContent) content, row, column);
  }

  public int getRow() {
    return row;
  }

  public int getColumn() {
    return column;
  }
}
