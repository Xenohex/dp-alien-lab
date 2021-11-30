package state;

import java.util.Random;

import environment.Environment;
import exceptions.EnvironmentException;
import exceptions.WeaponException;
import lifeform.LifeForm;

public class HasWeaponState extends ActionState {

boolean shouldMove = false;
  
  HasWeaponState(AIContext context) {
    super(context);
  }

  @Override
  void executeAction() {
    LifeForm lifeForm = context.getLifeForm();
    Environment e = context.getEnvironment();
    
    if (lifeForm.getWeapon().getCurrentAmmo() <= 0) {
      context.setCurrentState(context.getOutOfAmmoState());
    } else if (lifeForm.getCurrentLifePoints() <= 0) {
      context.setCurrentState(context.getDeadState());
    } else {
      attackTarget();
    }
  }
  
  public void attackTarget() { 
    LifeForm lifeForm = context.getLifeForm();
    Environment e = context.getEnvironment();
    
  String direction = lifeForm.getCurrentDirection();
  int row = lifeForm.getRow();
  int col = lifeForm.getCol();
  int distance = 0;
  boolean hitLifeForm = false;
      
      try {
        if (direction == "North") {
          for (int i = row - 1; i >= -1; i--) {
            distance += 5;
            if ((i == 0 && e.getLifeForm(i, col) == null) || i < 0) {
                lifeForm.getWeapon().fire(distance);
              break;
            } 
            if (e.getLifeForm(i, col) != null) {
              if (lifeForm.getClass() == e.getLifeForm(i, col).getClass()) { 
                break; 
              }
              lifeForm.attack(e.getLifeForm(i, col), distance);
              System.out.println(lifeForm.getName() + " attacked " 
                  + e.getLifeForm(i, col).getName());
              hitLifeForm = true;
              break;
            }
          }
        } else if (direction == "South") {
          for (int i = row + 1; i <= e.getNumRows(); i++) {
            distance += 5;
            if ((i == (e.getNumRows() - 1) && e.getLifeForm(i, col) == null)
                || i == e.getNumRows()) {
                lifeForm.getWeapon().fire(distance);
              break;
            } 
            if (e.getLifeForm(i, col) != null) {
              if (lifeForm.getClass() == e.getLifeForm(i, col).getClass()) { 
                break; 
              }
              lifeForm.attack(e.getLifeForm(i, col), distance);
              System.out.println(lifeForm.getName() + " attacked " 
                  + e.getLifeForm(i, col).getName());
              hitLifeForm = true;
              break;
            }
          }
        } else if (direction == "East") {
          for (int i = col + 1; i <= e.getNumCols(); i++) {
            distance += 5;
            if ((i == (e.getNumCols() - 1) && e.getLifeForm(row, i) == null)
                || i == e.getNumCols()) {
                lifeForm.getWeapon().fire(distance);
              break;
            } 
            if (e.getLifeForm(row, i) != null) {
              if (lifeForm.getClass() == e.getLifeForm(i, col).getClass()) { 
                break; 
              }
              lifeForm.attack(e.getLifeForm(row, i), distance);
              System.out.println(lifeForm.getName() + " attacked " 
                  + e.getLifeForm(row, i).getName());
              hitLifeForm = true;
              break;
            }
          }
        } else if (direction == "West") {
          for (int i = col - 1; i >= -1; i--) {
            distance += 5;
            if ((i == 0 && e.getLifeForm(row, i) == null) || (i < 0)) {
                lifeForm.getWeapon().fire(distance);
              break;
            } 
            if (e.getLifeForm(row, i) != null) {
              if (lifeForm.getClass() == e.getLifeForm(i, col).getClass()) { 
                break; 
              }
              lifeForm.attack(e.getLifeForm(row, i), distance);
              System.out.println(lifeForm.getName() + " attacked " 
                  + e.getLifeForm(row, i).getName());
              hitLifeForm = true;
              break;
            }
            
          }
        }
      } catch (WeaponException exception) {
        System.out.println("Error: WeaponException thrown in HasWeaponState");
      }  
      
      if (!hitLifeForm) {
        search();
      }
  }

  public void search() {
    LifeForm lifeForm = context.getLifeForm();
    Environment e = context.getEnvironment();
    Random random = new Random();
    int d = random.nextInt(4) + 1;
    switch (d) {
    case 1:
      lifeForm.changeDirection("North");
      break;
    case 2:
      lifeForm.changeDirection("South");
      break;
    case 3:
      lifeForm.changeDirection("East");
      break;
    case 4:
      lifeForm.changeDirection("West");
    }
    e.updateCell(lifeForm.getRow(), lifeForm.getCol());
    
    if(shouldMove) {
      move();
    }
    shouldMove = true;
  }
  
  public void move() {
    LifeForm lifeForm = context.getLifeForm();
    Environment e = context.getEnvironment();
    int row = lifeForm.getRow();
    int col = lifeForm.getCol();
    int speed = lifeForm.getMaxSpeed();
    String direction = lifeForm.getCurrentDirection();
    
    try {
      if (direction == "North") {
        for (int i = speed; i >= 0; i--) {
          if ((row - i) < 0) {
            continue;
          } else if (e.getLifeForm((row - i), col) != null) {
            continue;
          } else if (i == 0) {
            System.out.println(lifeForm.getName() + " could not move");
            break;
          } else if (e.getLifeForm(row - i, col) == null) {
            lifeForm.setLocation(row - i, col);
            e.updateCell(row - i, col);
            break;
          } else {
            lifeForm.setLocation(row - speed, col);
            e.updateCell(row - speed, col);
          }
        }
        
      } else if (direction == "South") {
        for (int i = speed; i >= 0; i--) {
          if ((row + i) >= e.getNumRows()) { // if lifeform on border
            continue;
          } else if (e.getLifeForm((row + i), col) != null) { // if a lifeform is in target space
            continue;
          } else if (i == 0) {
            System.out.println(lifeForm.getName() + " could not move");
            break;
          } else if (e.getLifeForm(row + i, col) == null) {
            lifeForm.setLocation(row + i, col);
            e.updateCell(row + i, col);
            break;
          } else {
            lifeForm.setLocation(row + speed, col);
            e.updateCell(row + speed, col);
          }
        }
        
      } else if (direction == "East") {
        for (int i = speed; i >= 0; i--) {
          if ((col + i) >= e.getNumCols()) { // if lifeform on border
            continue;
          } else if (e.getLifeForm(row, (col + i)) != null) { // if a lifeform is in target space
            continue;
          } else if (i == 0) {
            System.out.println(lifeForm.getName() + " could not move");
            break;
          } else if (e.getLifeForm(row, col + i) == null) {
            lifeForm.setLocation(row, col + i);
            e.updateCell(row, col + i);
            break;
          } else {
            lifeForm.setLocation(row, col + speed);
            e.updateCell(row, col + speed);
          }
        }
        
      } else if (direction == "West") {
        for (int i = speed; i >= 0; i--) {
          if ((col - i) < 0) { // if lifeform on border
            continue;
          } else if (e.getLifeForm(row, (col - i)) != null) { // if a lifeform is in target space
            continue;
          } else if (i == 0) {
            System.out.println(lifeForm.getName() + " could not move");
            break;
          } else if (e.getLifeForm(row, col - i) == null) {
            lifeForm.setLocation(row, col - i);
            e.updateCell(row,  col - i);
            break;
          } else {
            lifeForm.setLocation(row, col - speed);
            e.updateCell(row,  col - speed);
          }
        }
      }
      
      e.removeLifeForm(row, col);
      e.addLifeForm(lifeForm, lifeForm.getRow(), lifeForm.getCol());
      e.updateCell(row, col);
      shouldMove = false;
      
      System.out.println(lifeForm.getName() + " moved to " 
          + lifeForm.getRow() + ", " + lifeForm.getCol());
    
    } catch (EnvironmentException exception) {
      System.out.println("Error: EnvironmentException in move command");
    } catch (ArrayIndexOutOfBoundsException exception) {
      System.out.println("Error: ArrayIndexOutOfBoundsException in move command");
    }
    
  }

}
