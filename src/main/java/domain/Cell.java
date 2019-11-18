package domain;

import java.util.Objects;

public class Cell {

  private int horizontalPosition;
  private int verticalPosition;

  public Cell(int horizontalPosition, int verticalPosition) {
    this.horizontalPosition = horizontalPosition;
    this.verticalPosition = verticalPosition;
  }

  public int getHorizontalPosition() {
    return horizontalPosition;
  }

  public int getVerticalPosition() {
    return verticalPosition;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Cell cell = (Cell) o;
    return horizontalPosition == cell.horizontalPosition &&
        verticalPosition == cell.verticalPosition;
  }

  @Override
  public int hashCode() {
    return Objects.hash(horizontalPosition, verticalPosition);
  }

  @Override
  public String toString() {
    return "Cell{" +
        "horizontalPosition=" + horizontalPosition +
        ", verticalPosition=" + verticalPosition +
        '}';
  }
}
