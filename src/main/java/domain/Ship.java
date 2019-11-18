package domain;

public class Ship {

  private Cell[] cells;

  public Ship(int size) {
    cells = new Cell[size];
  }

  public Cell[] getCells() {
    return cells;
  }

  public void add(int index, Cell cell) {
    if (index >= 0 && index < cells.length) {
      cells[index] = cell;
    } else {
      throw new IllegalArgumentException("Ship size is exceeded. " + cell);
    }
  }
}
