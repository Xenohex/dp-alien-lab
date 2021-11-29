package state;

import java.util.Random;

import exceptions.EnvironmentException;

public class NoWeaponState extends ActionState {

  NoWeaponState(AIContext context) {
    super(context);
    // TODO Auto-generated constructor stub
  }

  @Override
  void executeAction() {
    // TODO Auto-generated method stub
    if (lifeForm.getCurrentLifePoints() <= 0) {
      context.setCurrentState(context.getDeadState());
    } else if (e.getWeapons(lifeForm.getRow(), lifeForm.getCol()) != null) {
      acquireWeapon();
    } else {
      search();
    }
    // Evaluation
  }
  
  public void acquireWeapon() {
    if (e.getCell(lifeForm.getRow(), lifeForm.getCol()).getWeapon1() == null) {
      lifeForm.pickUpWeapon(e.getCell(lifeForm.getRow(), lifeForm.getCol()).getWeapon2());
    } else {
      lifeForm.pickUpWeapon(e.getCell(lifeForm.getRow(), lifeForm.getCol()).getWeapon1());
    }
    context.setCurrentState(context.hasWeaponState);
  }
  
  public void search() {
    
    int row = lifeForm.getRow();
    int col = lifeForm.getCol();
    int speed = lifeForm.getMaxSpeed();
    
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
            break;
          } else {
            lifeForm.setLocation(row - speed, col);
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
            break;
          } else {
            lifeForm.setLocation(row + speed, col);
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
            break;
          } else {
            lifeForm.setLocation(row, col + speed);
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
            break;
          } else {
            lifeForm.setLocation(row, col - speed);
          }
        }
      }
      
      e.removeLifeForm(row, col);
      e.addLifeForm(lifeForm, lifeForm.getRow(), lifeForm.getCol());
      
      System.out.println(lifeForm.getName() + " moved to " 
          + lifeForm.getRow() + ", " + lifeForm.getCol());
    
    } catch (EnvironmentException exception) {
      System.out.println("Error: EnvironmentException in move command");
    } catch (ArrayIndexOutOfBoundsException exception) {
      System.out.println("Error: ArrayIndexOutOfBoundsException in move command");
    }
    
  }

}
