package gui;

import exceptions.EnvironmentException;
import environment.*;
import lifeform.LifeForm;

public class moveCommand implements Command {
  
  LifeForm lifeform;
  String direction;
  int speed;
  Environment e;
  Board b;
  
  public moveCommand(LifeForm lf, Environment ee, Board bb) {
    lifeform = lf;
    direction = lifeform.getCurrentDirection();
    speed = lifeform.getMaxSpeed();
    e = ee;
    b = bb;
  }
  
  public void execute() {
    
    int row = lifeform.getRow();
    int col = lifeform.getCol();
    
    try {
      if (direction == "North") {
        if ((row - speed) <= 0) { // if lifeform on border
          lifeform.setLocation(0, col);
        } else if (e.getLifeForm((row - speed), col) != null) { // if a lifeform is in target space
          for (int i = speed; i >= 0; i--) {
            if (i == 0) {
              System.out.println(lifeform.getName() + " could not move");
              break;
            } else if (e.getLifeForm(i, col) == null) { // if no obstacles in target space
                lifeform.setLocation(row - i, col);
                break;
            }
          }
        } else {
          lifeform.setLocation(row - speed, col);
        }
        
      } else if (direction == "South") {
        if ((row + speed) >= e.getNumRows()) {
          lifeform.setLocation(e.getNumRows() - 1, col);
        } else if (e.getLifeForm((row + speed), col) != null) {
          for (int i = speed; i >= 0; i--) {
            if (i == 0) {
              System.out.println(lifeform.getName() + " could not move");
              break;
            } else if (e.getLifeForm(i, col) == null) {
                lifeform.setLocation(row + i, col);
                break;
            }
          }
        } else {
          lifeform.setLocation(row + speed, col);
        }
        
      } else if (direction == "East") {
        if ((col + speed) >= e.getNumCols() - 1) {
          lifeform.setLocation(row, e.getNumCols() - 1);
        } else if (e.getLifeForm(row, (col + speed)) != null) {
          for (int i = speed; i >= 0; i--) {
            if (i == 0) {
              System.out.println(lifeform.getName() + " could not move");
              break;
            } else if (e.getLifeForm(col, i) == null) {
                lifeform.setLocation(row, col + i);
                break;
            }
          }
        } else {
          lifeform.setLocation(row, col + speed);
        }
        
      } else if (direction == "West") {
        if ((col - speed) <= 0) {
          lifeform.setLocation(row, 0);
        } else if (e.getLifeForm(row, (col - speed)) != null) {
          for (int i = speed; i >= 0; i--) {
            if (i == 0) {
              System.out.println(lifeform.getName() + " could not move");
              break;
            } else if (e.getLifeForm(col, i) == null) {
                lifeform.setLocation(row, col - i);
                break;
            }
          }
        } else {
          lifeform.setLocation(row, col - speed);
        }
      }
      
      e.removeLifeForm(row, col);
      e.addLifeForm(lifeform, lifeform.getRow(), lifeform.getCol());
      // b.setSelectedCell(lifeform.getRow(), lifeform.getCol()); ??
      
      System.out.println(lifeform.getName()
          + " moved to " + lifeform.getRow()
          + ", " + lifeform.getCol());
    
    } catch (EnvironmentException exception) {
      System.out.println("Error: EnvironmentException in move command");
    }
    
    
    
  }

}
