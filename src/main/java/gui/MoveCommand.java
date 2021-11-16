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
        for (int i = speed; i >= 0; i--) {
          if ((row - i) < 0) { // if lifeform on border
            continue;
          } else if (e.getLifeForm((row - i), col) != null) { // if a lifeform is in target space
            continue;
          } else if (i == 0) {
            System.out.println(lifeform.getName() + " could not move");
            break;
          } else if (e.getLifeForm(row - i, col) == null) { // if no obstacles in target space
              lifeform.setLocation(row - i, col);
              break;
          } else {
            lifeform.setLocation(row - speed, col);
          }
        }
        
      } else if (direction == "South") {
          for (int i = speed; i >= 0; i--) {
            if ((row + i) >= e.getNumRows()) { // if lifeform on border
              continue;
            } else if (e.getLifeForm((row + i), col) != null) { // if a lifeform is in target space
              continue;
            } else if (i == 0) {
              System.out.println(lifeform.getName() + " could not move");
              break;
            } else if (e.getLifeForm(row + i, col) == null) {
                lifeform.setLocation(row + i, col);
                break;
            } else {
              lifeform.setLocation(row + speed, col);
            }
          }
        
      } else if (direction == "East") {
          for (int i = speed; i >= 0; i--) {
            if ((col + i) >= e.getNumCols()) { // if lifeform on border
              continue;
            } else if (e.getLifeForm(row, (col + i)) != null) { // if a lifeform is in target space
              continue;
            } else if (i == 0) {
              System.out.println(lifeform.getName() + " could not move");
              break;
            } else if (e.getLifeForm(row, col + i) == null) {
                lifeform.setLocation(row, col + i);
                break;
            } else {
              lifeform.setLocation(row, col + speed);
            }
          }
        
      } else if (direction == "West") {
          for (int i = speed; i >= 0; i--) {
            if ((col - i) < 0) { // if lifeform on border
              continue;
            } else if (e.getLifeForm(row, (col - i)) != null) { // if a lifeform is in target space
              continue;
            } else if (i == 0) {
              System.out.println(lifeform.getName() + " could not move");
              break;
            } else if (e.getLifeForm(row, col - i) == null) {
                lifeform.setLocation(row, col - i);
                break;
            } else {
              lifeform.setLocation(row, col - speed);
            }
          }
      }
      
      e.removeLifeForm(row, col);
      e.addLifeForm(lifeform, lifeform.getRow(), lifeform.getCol());
      
      System.out.println(lifeform.getName()
          + " moved to " + lifeform.getRow()
          + ", " + lifeform.getCol());
    
    } catch (EnvironmentException exception) {
      System.out.println("Error: EnvironmentException in move command");
    } catch (ArrayIndexOutOfBoundsException exception) {
      System.out.println("Error: ArrayIndexOutOfBoundsException in move command");
    }
    
    
    
  }

}
