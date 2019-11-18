package domain;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BattleField {

  private final int size;
  private final Set<Cell> occupiedCells;
  private final Set<Ship> ships = new HashSet<>();

  public BattleField(int size) {
    this.size = size;
    this.occupiedCells = new HashSet<>(size * size);
  }

  public boolean isOccupied(Cell cell) {
    return occupiedCells.contains(cell);
  }

  public void addShip(Ship ship) {
    ships.add(ship);
    for (Cell cell : ship.getCells()) {
      occupiedCells.add(cell);
      occupySurroundingCells(cell);
    }
  }

  private void occupySurroundingCells(Cell cell) {
    int horizontalPosition = cell.getHorizontalPosition();
    int verticalPosition = cell.getVerticalPosition();

    List<Cell> surroundingCells = Arrays.asList(
        new Cell(horizontalPosition - 1, verticalPosition - 1),
        new Cell(horizontalPosition - 1, verticalPosition),
        new Cell(horizontalPosition - 1, verticalPosition + 1),

        new Cell(horizontalPosition + 1, verticalPosition - 1),
        new Cell(horizontalPosition + 1, verticalPosition),
        new Cell(horizontalPosition + 1, verticalPosition + 1),

        new Cell(horizontalPosition, verticalPosition + 1),
        new Cell(horizontalPosition, verticalPosition - 1)
    );

    for (Cell surroundingCell : surroundingCells) {
      if (canBeOccupied(surroundingCell)) {
        occupiedCells.add(surroundingCell);
      }
    }
  }

  public boolean canBeOccupied(Cell cell) {
    return cell.getVerticalPosition() >= 0 &&
        cell.getVerticalPosition() < size &&
        cell.getHorizontalPosition() >= 0 &&
        cell.getHorizontalPosition() < size;
  }

  public Set<Ship> getShips() {
    return new HashSet<>(ships);
  }
}
