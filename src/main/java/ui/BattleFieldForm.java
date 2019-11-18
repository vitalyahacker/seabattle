package ui;

import domain.Ship;
import field.SeaBattle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class BattleFieldForm extends Application {

  private static final int size = 500;

  private static final List<Line> cells = new ArrayList<>();
  private static final List<Rectangle> ships = new ArrayList<>();

  public static void main(String[] args) {
    SeaBattle seaBattle = new SeaBattle(10, 1);

    Set<Ship> ships = seaBattle.getShips();

    int battleFieldSize = seaBattle.getSize();

    createCells(battleFieldSize);
    createShips(ships, battleFieldSize);

    launch();
  }

  private static void createShips(Set<Ship> ships, int battleFieldSize) {
    int singleCellSize = BattleFieldForm.size / battleFieldSize;
    BattleFieldForm.ships.addAll(
        ships.stream()
            .flatMap(it -> Arrays.asList(it.getCells()).stream())
            .map(cell -> {
              Rectangle rectangle = new Rectangle(cell.getHorizontalPosition(), cell.getVerticalPosition());
              rectangle.setHeight(singleCellSize);
              rectangle.setWidth(singleCellSize);
              rectangle.setX(cell.getHorizontalPosition() * singleCellSize);
              rectangle.setY(cell.getVerticalPosition() * singleCellSize);
              return rectangle;
            })
            .collect(Collectors.toList())
    );
  }

  private static void createCells(int battleFieldSize) {
    int singleCellSize = BattleFieldForm.size / battleFieldSize;
    for (int i = 0; i < battleFieldSize; i++) {
      cells.add(new Line(0, i * singleCellSize, size, i * singleCellSize));
      cells.add(new Line(i * singleCellSize, size, i * singleCellSize, 0));
    }
  }

  @Override
  public void start(Stage stage) {
    stage.setTitle("Sea battle!");
    stage.setScene(createScene());
    stage.show();
  }

  private Scene createScene() {
    Group group = new Group();
    group.getChildren().addAll(ships);
    group.getChildren().addAll(cells);
    return new Scene(group, size, size);
  }
}
