package field;

import domain.Cell;
import domain.Ship;
import java.util.Random;

public class RandomShipGenerator {

  private final int size;
  private static final Random RANDOM = new Random();

  public RandomShipGenerator(int size) {
    this.size = size;
  }

  public Ship generate(int shipSize) {
    boolean isHorizontal = RANDOM.nextBoolean();
    int randomVerticalPosition = isHorizontal ? RANDOM.nextInt(size) : RANDOM.nextInt(size - shipSize);
    int randomHorizontalPosition = isHorizontal ? RANDOM.nextInt(size - shipSize) : RANDOM.nextInt(size);
    return generateShip(shipSize, randomVerticalPosition, randomHorizontalPosition, isHorizontal);
  }

  private Ship generateShip(int shipSize, int verticalPosition, int horizontalPosition, boolean isHorizontal) {
    int horizontalIncrement = 0;
    int verticalIncrement = 0;

    if (isHorizontal) {
      horizontalIncrement = 1;
    } else {
      verticalIncrement = 1;
    }

    Ship ship = new Ship(shipSize);
    for (int i = 0; i < shipSize; i++) {
      ship.add(i, new Cell(horizontalPosition += horizontalIncrement, verticalPosition += verticalIncrement));
    }
    return ship;
  }
}
