package gui;

import exceptions.EnvironmentException;
import environment.*;
import lifeform.LifeForm;

public class moveCommand implements Command {
  
  LifeForm lifeform;
  String direction;
  int speed;
  Environment e;
  
  public moveCommand(LifeForm lf, Environment ee) {
    lifeform = lf;
    direction = lifeform.getCurrentDirection();
    speed = lifeform.getMaxSpeed();
    e = ee;
  }
  
  public void execute() {
    
    int row = lifeform.getRow();
    int col = lifeform.getCol();
    
    if (lifeform == null) {
      System.out.println("Error: no lifeform selected");
    } else {
      try {
        if (direction == "North") {
          if ((row - speed) <= 0) {
            lifeform.setLocation(0, col);
          } else if (e.getLifeForm((row - speed), col) != null) {
            lifeform.setLocation(row - (speed - 1), col);
          } else {
            lifeform.setLocation(row - speed, col);
          }
        } else if (direction == "South") {
          if ((row + speed) >= e.getNumRows()) {
            lifeform.setLocation(e.getNumRows() - 1, col);
          } else if (e.getLifeForm((row + speed), col) != null) {
            lifeform.setLocation(row + (speed - 1), col);
          } else {
            lifeform.setLocation(row + speed, col);
          }
          
        } else if (direction == "East") {
          if ((col + speed) >= e.getNumCols() - 1) {
            lifeform.setLocation(row, e.getNumCols() - 1);
          } else if (e.getLifeForm(row, (col + speed)) != null) {
            lifeform.setLocation(row, col + (speed - 1));
          } else {
            lifeform.setLocation(row, col + speed);
          }
          
        } else if (direction == "West") {
          if ((col - speed) <= 0) {
            lifeform.setLocation(row, 0);
          } else if (e.getLifeForm(row, (col - speed)) != null) {
            lifeform.setLocation(row, col - (speed - 1));
          } else {
            lifeform.setLocation(row, col - speed);
          }
        }
      
      } catch (EnvironmentException exception) {
        System.out.println("Error: EnvironmentException in move command");
      }
    }
    
  }
  
  /*
   * move lifeform no matter what
   * THEN check if lifeform is out of bounds and if they are, move them back in
   * by taking the difference between the lifeform's new position and the 
   * edge of the environment and move it back that many positions
   */

}
