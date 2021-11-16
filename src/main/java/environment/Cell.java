package environment;

import lifeform.LifeForm;
import weapon.Weapon;

/**
 * A Cell that can hold a LifeForm.
 *
 */
public class Cell {
  LifeForm lifeForm = null;
  Weapon weapon1 = null;
  Weapon weapon2 = null;

  /**
   * @return the LifeForm in this Cell.
   */
  public LifeForm getLifeForm() {
    return lifeForm;
  }

  /**
   * Tries to add the LifeForm to the Cell. Will not add if a LifeForm is already
   * present.
   * 
   * @param entity the LifeForm held in the cell
   * @return true if the LifeForm was added to the Cell, false otherwise.
   */
  public boolean addLifeForm(LifeForm entity) {
    if (lifeForm == null) {
      lifeForm = entity;
      return true;
    }
    return false;
  }

  /**
   * removes the LifeForm in the Cell
   */
  public void removeLifeForm() {
    lifeForm = null;
  }
  
  /**
   * @param weapon
   * @return true if the weapon is successfully placed 
   * in the cell. false otherwise(when full).
   */
  public boolean addWeapon(Weapon weapon) {
    
    if (weapon1 == weapon || weapon2 == weapon) {
      return false;
    } else if (weapon1 == null) {
      weapon1 = weapon;
      return true;
    } else if (weapon2 == null) {
      weapon2 = weapon;
      return true;
    } else {
      return false;
    } 
  }
  
  /**
   * @param weapon
   * @return the weapon removed from the cell.
   * null if no such weapon is found.
   */
  public Weapon removeWeapon(Weapon weapon) {
    Weapon val = null;
    if (weapon1 == weapon) {
      val = weapon1;
      weapon1 = weapon2;
      weapon2 = null;
    } else if (weapon2 == weapon) {
      val = weapon2;
      weapon2 = null;
    }
    return val;
  }
  
  
  /**
   * @return the weapon in the first slot
   */
  public Weapon getWeapon1() {
    return weapon1;
  }
  
  /**
   * @return the weapon in the second slot
   */
  public Weapon getWeapon2() {
    return weapon2;
  }
  
  /**
   * 
   * @return number of weapons in a cell
   */
  public int getWeaponsCount() {
    int count = 0;
    if (weapon1 != null) {
      count++;
    } 
    if (weapon2 != null) {
      count++;
    }
    return count;
  }
  
  public String getName() {
    if (lifeForm != null) {
      return lifeForm.getName();
    } else {
      return "";
    }
  }
}
