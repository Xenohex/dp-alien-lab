package environment;

import lifeform.LifeForm;

/**
 * A Cell that can hold a LifeForm.
 *
 */
public class Cell {
  LifeForm lifeForm = null;

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
  void removeLifeForm() {
    lifeForm = null;
  }
}
