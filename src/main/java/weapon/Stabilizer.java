package weapon;

import exceptions.AttachmentException;
import exceptions.WeaponException;

/**
 * Stabilizer
 * @author Jun
 *
 */
public class Stabilizer extends Attachment {
  
  /**
  * Makes an new instance of Stabilizer that wraps a Weapon object
  * Stabilizer makes the weapon be able to auto reload
  * It also increase the damage of the weapon by 25%
  * @param b weapon to be modified
  * @throws AttachmentException if an invalid attachment is given
  */
  public Stabilizer(Weapon b) throws AttachmentException {
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
    int damage = base.fire(distance);
    if (base.getCurrentAmmo() == 0) {
      base.reload();
    }
    return Double.valueOf(Math.floor(Math.floor(damage * 0.25)
        + damage)).intValue();
  }
  
  /**
  * @return the weapon plus any attachments
  */
  public String toString() {
    return base.toString() + " +Stabilizer";
  }
}
