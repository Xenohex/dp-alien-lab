package gui;

import environment.Environment;
import exceptions.WeaponException;
import lifeform.LifeForm;

public class AttackCommand implements Command {
  
  LifeForm lifeform;
  String direction;
  Environment env;
  
  /**
   * 
   * @param lf selected lifeform
   * @param ee singleton environment
   * Constructor for the attack command.
   * Lifeform is used to determine which lifeform is attacking
   * Environment is used to determine what the lifeform is attacking
   */
  public AttackCommand(LifeForm lf, Environment ee) {
    lifeform = lf;
    direction = lifeform.getCurrentDirection();
    env = ee;
  }
  
  /**
   * Loops through the environment based on the selected lifeform's current
   * direction to determine if the lifeform is attacking another lifeform, if true
   * the lifeform then attacks according to the weapon they are holding, if they
   * are holding one
   */
  public void execute() {
    
    int row = lifeform.getRow();
    int col = lifeform.getCol();
    int distance = 0;
    
    try {
      if (direction == "North") {
        for (int i = row - 1; i >= -1; i--) {
          distance += 5;
          if (i < 0) {
            if (lifeform.hasWeapon()) {
              lifeform.getWeapon().fire(distance);
            }
            break;
          }
          if (i == 0 && env.getLifeForm(i, col) == null) {
            if (lifeform.hasWeapon()) {
              lifeform.getWeapon().fire(distance);
            }
            break;
          } 
          if (env.getLifeForm(i, col) != null) {
            lifeform.attack(env.getLifeForm(i, col), distance);
            System.out.println(lifeform.getName() + " attacked " 
                + env.getLifeForm(i, col).getName());
            break;
          }
        }
      } else if (direction == "South") {
        for (int i = row + 1; i <= env.getNumRows(); i++) {
          distance += 5;
          if (i == env.getNumRows()) {
            if (lifeform.hasWeapon()) {
              lifeform.getWeapon().fire(distance);
            }
            break;
          }
          if (i == (env.getNumRows() - 1) && env.getLifeForm(i, col) == null) {
            if (lifeform.hasWeapon()) {
              lifeform.getWeapon().fire(distance);
            }
            break;
          } 
          if (env.getLifeForm(i, col) != null) {
            lifeform.attack(env.getLifeForm(i, col), distance);
            System.out.println(lifeform.getName() + " attacked " 
                + env.getLifeForm(i, col).getName());
            break;
          }
        }
      } else if (direction == "East") {
        for (int i = col + 1; i <= env.getNumCols(); i++) {
          distance += 5;
          if (i == env.getNumCols()) {
            if (lifeform.hasWeapon()) {
              lifeform.getWeapon().fire(distance);
            }
            break;
          }
          if (i == (env.getNumCols() - 1) && env.getLifeForm(row, i) == null) {
            if (lifeform.hasWeapon()) {
              lifeform.getWeapon().fire(distance);
            }
            break;
          } 
          if (env.getLifeForm(row, i) != null) {
            lifeform.attack(env.getLifeForm(row, i), distance);
            System.out.println(lifeform.getName() + " attacked " 
                + env.getLifeForm(row, i).getName());
            break;
          }
        }
      } else if (direction == "West") {
        for (int i = col - 1; i >= -1; i--) {
          distance += 5;
          if (i < 0) {
            if (lifeform.hasWeapon()) {
              lifeform.getWeapon().fire(distance);
            }
            break;
          }
          if (i == 0 && env.getLifeForm(row, i) == null) {
            if (lifeform.hasWeapon()) {
              lifeform.getWeapon().fire(distance);
            }
            break;
          } 
          if (env.getLifeForm(row, i) != null) {
            lifeform.attack(env.getLifeForm(row, i), distance);
            System.out.println(lifeform.getName() + " attacked " 
                + env.getLifeForm(row, i).getName());
            break;
          }
        }
      }
      
      if (lifeform.hasWeapon() == false || distance <= 5) {
        System.out.println(lifeform.getName() + " attacks with melee");
      } else {
        System.out.println(lifeform.getName() + " Ammo: " 
            + lifeform.getWeapon().getCurrentAmmo());
      }
    
    } catch (WeaponException exception) {
      System.out.println("Error: WeaponException thrown in attack command");
    }
    
  }
}
    
