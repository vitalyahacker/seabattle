package field;

import domain.BattleField;
import domain.Cell;
import domain.Ship;
import java.util.Map;
import java.util.Set;

public class SeaBattle {

  private final int size;
  private final int shipsSizeMultiplier;
  private final RandomShipGenerator randomShipGenerator;
  private final BattleField battleField;
  private final Map<Integer, Integer> shipSizeToShipsNumber = Map.of(
      4, 1,
      3, 2,
      2, 3,
      1, 4
  );

  public SeaBattle(int size, int shipsSizeMultiplier) {
    this.size = size;
    this.shipsSizeMultiplier = shipsSizeMultiplier;
    battleField = new BattleField(size);
    randomShipGenerator = new RandomShipGenerator(size);
    initialize();
  }

  private void initialize() {
    for (Map.Entry<Integer, Integer> entry : shipSizeToShipsNumber.entrySet()) {
      generateShips(entry.getKey(), entry.getValue());
    }
  }

  private void generateShips(int shipSize, int shipsNumber) {
    int numberOfGeneratedShips = 0;

    while (numberOfGeneratedShips < shipsNumber) {
      Ship randomShip = randomShipGenerator.generate(shipSize * this.shipsSizeMultiplier);
      if (shipCanBePlacedHere(randomShip)) {
        battleField.addShip(randomShip);
        numberOfGeneratedShips++;
      }
    }
  }

  public boolean shipCanBePlacedHere(Ship ship) {
    for (Cell cell : ship.getCells()) {
      if (battleField.isOccupied(cell)) {
        return false;
      }
    }
    return true;
  }

  public Set<Ship> getShips() {
    return battleField.getShips();
  }

  public int getSize() {
    return size;
  }
}
