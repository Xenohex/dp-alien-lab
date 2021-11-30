package state;

import java.util.Random;

import environment.Environment;
import exceptions.EnvironmentException;
import lifeform.LifeForm;
import weapon.Weapon;

public class NoWeaponState extends ActionState {

  NoWeaponState(AIContext context) {
    super(context);
    // TODO Auto-generated constructor stub
  }

  @Override
  void executeAction() {
    LifeForm lf = context.getLifeForm();
    Environment e = context.getEnvironment();
    // TODO Auto-generated method stub
    //search();
    if (lf.getCurrentLifePoints() <= 0) {
      context.setCurrentState(context.getDeadState());
    } else if (e.getCell(lf.getRow(), lf.getCol()).getWeapon1() == null) {
      search();
      
    } else {
      acquireWeapon();
      
    }
    //search();
    e.updateCell(lf.getRow(), lf.getCol());
    // Evaluation
  }
  
  public void acquireWeapon() {
    LifeForm lifeform = context.getLifeForm();
    Environment env = context.getEnvironment();
    if (env.getCell(lifeform.getRow(), lifeform.getCol()).getWeapon2() != null) {
      Weapon w = lifeform.dropWeapon();
      lifeform.pickUpWeapon(env.getCell(lifeform.getRow(), lifeform.getCol()).getWeapon1());
      env.removeWeapon(lifeform.getWeapon(), lifeform.getRow(), lifeform.getCol());
      env.addWeapon(w, lifeform.getRow(), lifeform.getCol());
      context.setCurrentState(context.hasWeaponState);
      env.updateCell(lifeform.getRow(), lifeform.getCol());
      System.out.println("Weapon switched");
    } else if (env.getCell(lifeform.getRow(), lifeform.getCol()).getWeapon1() != null) {
      Weapon w = lifeform.dropWeapon();
      lifeform.pickUpWeapon(env.getCell(lifeform.getRow(), lifeform.getCol()).getWeapon1());
      env.removeWeapon(lifeform.getWeapon(), lifeform.getRow(), lifeform.getCol());
      env.addWeapon(w, lifeform.getRow(), lifeform.getCol());
      context.setCurrentState(context.hasWeaponState);
      env.updateCell(lifeform.getRow(), lifeform.getCol());
      System.out.println("Weapon switched");
    }
    /**
    if (e.getCell(lf.getRow(), lf.getCol()).getWeapon1() != null) {
      e.removeWeapon(null, 0, 0);
      lf.pickUpWeapon(e.getCell(lf.getRow(), lf.getCol()).);
      
      
    } else {
      lf.pickUpWeapon(e.getCell(lf.getRow(), lf.getCol()).getWeapon1());
    }
    context.setCurrentState(context.hasWeaponState); **/
  }
  
  public void search() {
    LifeForm lf = context.getLifeForm();
    Environment e = context.getEnvironment();
    
    int row = lf.getRow();
    int col = lf.getCol();
    int speed = lf.getMaxSpeed();
    
    Random random = new Random();
    int d = random.nextInt(4) + 1;
    switch (d) {
    case 1:
      lf.changeDirection("North");
      System.out.println("North");
      e.updateCell(lf.getRow(), lf.getCol());
      break;
    case 2:
      lf.changeDirection("South");
      System.out.println("south");
      e.updateCell(lf.getRow(), lf.getCol());
      break;
    case 3:
      lf.changeDirection("East");
      System.out.println("east");
      e.updateCell(lf.getRow(), lf.getCol());
      break;
    case 4:
      lf.changeDirection("West");
      System.out.println("west");
      e.updateCell(lf.getRow(), lf.getCol());
    }
    
    String direction = lf.getCurrentDirection();
    
    try {
      if (direction == "North") {
        for (int i = speed; i >= 0; i--) {
          if ((row - i) < 0) {
            continue;
          } else if (e.getLifeForm((row - i), col) != null) {
            continue;
          } else if (i == 0) {
            break;
          } else if (e.getLifeForm(row - i, col) == null) {
            lf.setLocation(row - i, col);
            e.updateCell(row - i, col);
            break;
          } else {
            lf.setLocation(row - speed, col);
            e.updateCell(row - speed, col);
          }
          
        }System.out.println(lf.getName() + "moved");
        
      } else if (direction == "South") {
        for (int i = speed; i >= 0; i--) {
          if ((row + i) >= e.getNumRows()) { // if lf on border
            continue;
          } else if (e.getLifeForm((row + i), col) != null) { // if a lf is in target space
            continue;
          } else if (i == 0) {
            break;
          } else if (e.getLifeForm(row + i, col) == null) {
            lf.setLocation(row + i, col);
            e.updateCell(row + i, col);
            break;
          } else {
            lf.setLocation(row + speed, col);
            e.updateCell(row + speed, col);
          }
          
        }System.out.println(lf.getName() + "moved");
        
      } else if (direction == "East") {
        for (int i = speed; i >= 0; i--) {
          if ((col + i) >= e.getNumCols()) { // if lf on border
            continue;
          } else if (e.getLifeForm(row, (col + i)) != null) { // if a lf is in target space
            continue;
          } else if (i == 0) {
            break;
          } else if (e.getLifeForm(row, col + i) == null) {
            lf.setLocation(row, col + i);
            e.updateCell(row, col + i);
            break;
          } else {
            lf.setLocation(row, col + speed);
            e.updateCell(row, col + speed);
          }
          
        }
        System.out.println(lf.getName() + "moved");
      } else if (direction == "West") {
        for (int i = speed; i >= 0; i--) {
          if ((col - i) < 0) { // if lf on border
            continue;
          } else if (e.getLifeForm(row, (col - i)) != null) { // if a lf is in target space
            continue;
          } else if (i == 0) {
            break;
          } else if (e.getLifeForm(row, col - i) == null) {
            lf.setLocation(row, col - i);
            e.updateCell(row, col - i);
            break;
          } else {
            lf.setLocation(row, col - speed);
            e.updateCell(row, col - i);
          }
          
        }System.out.println(lf.getName() + "moved");
      }
      
      e.removeLifeForm(row, col);
      e.addLifeForm(lf, lf.getRow(), lf.getCol());
      e.updateCell(row, col);
    } catch (EnvironmentException exception) {
      System.out.println("Error: EnvironmentException in move command");
    } catch (ArrayIndexOutOfBoundsException exception) {
      System.out.println("Error: ArrayIndexOutOfBoundsException in move command");
    }
    
  }

}
