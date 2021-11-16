package gui;

import environment.Environment;
import exceptions.EnvironmentException;
import lifeform.LifeForm;

public class MoveCommand implements Command {
  
  LifeForm lifeform;
  String direction;
  int speed;
  Environment env;
  Board board;
  
  /**
   * 
   * @param lf  lifeform
   * @param ee  environment
   * @param bb  board
   * Uses lifeform to determine which lifeform to move
   * Uses environment to obtain which cell the lifeform is in and which cell to move it to
   * Uses board to actually move the lifeform on the board
   */
  public MoveCommand(LifeForm lf, Environment ee, Board bb) {
    lifeform = lf;
    direction = lifeform.getCurrentDirection();
    speed = lifeform.getMaxSpeed();
    env = ee;
    board = bb;
  }
  
  /**
   * Executes the move command, which uses loops to decide whether the lifeform can
   * move to where it is trying to, if the command can compromise and at least
   * move it as far as possible, or not move the lifeform at all.
   * Once this happens, the lifeform is removed from its old spot on the board
   * and re-placed in its new spot.
   */
  public void execute() {
    
    int row = lifeform.getRow();
    int col = lifeform.getCol();
    
    try {
      if (direction == "North") {
        for (int i = speed; i >= 0; i--) {
          if ((row - i) < 0) {
            continue;
          } else if (env.getLifeForm((row - i), col) != null) {
            continue;
          } else if (i == 0) {
            System.out.println(lifeform.getName() + " could not move");
            break;
          } else if (env.getLifeForm(row - i, col) == null) {
            lifeform.setLocation(row - i, col);
            break;
          } else {
            lifeform.setLocation(row - speed, col);
          }
        }
        
      } else if (direction == "South") {
        for (int i = speed; i >= 0; i--) {
          if ((row + i) >= env.getNumRows()) { // if lifeform on border
            continue;
          } else if (env.getLifeForm((row + i), col) != null) { // if a lifeform is in target space
            continue;
          } else if (i == 0) {
            System.out.println(lifeform.getName() + " could not move");
            break;
          } else if (env.getLifeForm(row + i, col) == null) {
            lifeform.setLocation(row + i, col);
            break;
          } else {
            lifeform.setLocation(row + speed, col);
          }
        }
        
      } else if (direction == "East") {
        for (int i = speed; i >= 0; i--) {
          if ((col + i) >= env.getNumCols()) { // if lifeform on border
            continue;
          } else if (env.getLifeForm(row, (col + i)) != null) { // if a lifeform is in target space
            continue;
          } else if (i == 0) {
            System.out.println(lifeform.getName() + " could not move");
            break;
          } else if (env.getLifeForm(row, col + i) == null) {
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
          } else if (env.getLifeForm(row, (col - i)) != null) { // if a lifeform is in target space
            continue;
          } else if (i == 0) {
            System.out.println(lifeform.getName() + " could not move");
            break;
          } else if (env.getLifeForm(row, col - i) == null) {
            lifeform.setLocation(row, col - i);
            break;
          } else {
            lifeform.setLocation(row, col - speed);
          }
        }
      }
      
      env.removeLifeForm(row, col);
      env.addLifeForm(lifeform, lifeform.getRow(), lifeform.getCol());
      
      System.out.println(lifeform.getName() + " moved to " 
          + lifeform.getRow() + ", " + lifeform.getCol());
    
    } catch (EnvironmentException exception) {
      System.out.println("Error: EnvironmentException in move command");
    } catch (ArrayIndexOutOfBoundsException exception) {
      System.out.println("Error: ArrayIndexOutOfBoundsException in move command");
    }
    
    
    
  }

}
