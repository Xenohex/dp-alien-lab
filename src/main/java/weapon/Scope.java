package weapon;

import exceptions.AttachmentException;
import exceptions.WeaponException;

/**
 * Scope
 * @author Jun
 *
 */
public class Scope extends Attachment {

  /**
  * Makes an new instance of scope that wraps a Weapon object
  * scope gives the weapon + 10 range
  * @param b weapon to be modified
  * @throws AttachmentException if an invalid attachment is given
  */
  public Scope(Weapon b) throws AttachmentException {
    if (b.getNumAttachments() >= 2) {
      throw new AttachmentException("You are trying to add too many Attachments!");
    }
    this.base = b;
  }
  
  /**
   * fire the weapon, reduce current ammo by 1
   * @param distance of the target
   * @return the adjusted damage with scope
   * @throws WeaponException if invalid distance is given
   */
  public int fire(int distance) throws WeaponException {
    if (base.getMaxRange() < distance && distance <= getMaxRange()) {
      return (5 + base.fire(base.getMaxRange()));
    }
    return Double.valueOf(Math.floor(base.fire(distance) 
        * (1 + (getMaxRange() - distance) 
            / Double.valueOf(getMaxRange())))).intValue();
  }
  
  /**
  * @return the weapon plus any attachments
  */
  public String toString() {
    return base.toString() + " +Scope";
  }
  
  /**
  * @return the maximum range + 10
  */
  public int getMaxRange() {
    return base.getMaxRange() + 10;
  }
}
