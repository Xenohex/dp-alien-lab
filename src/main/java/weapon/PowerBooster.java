package weapon;

import exceptions.AttachmentException;
import exceptions.WeaponException;

/**
 * PowerBooster
 * @author Jun
 *
 */
public class PowerBooster extends Attachment {
  
  /**
  * Makes an new instance of PowerBooster that wraps a Weapon object
  * increase damage based on ammo left, more ammo = more damage
  * @param b weapon to be modified
  * @throws AttachmentException if an invalid attachment is given
  */
  public PowerBooster(Weapon b) throws AttachmentException {
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
    if (base.getShotsLeft() == 0) {
      return 0;
    }
    return Double.valueOf(Math.floor((1 
        + (base.getCurrentAmmo()) / Double.valueOf(base.getMaxAmmo())) 
        * base.fire(distance))).intValue();
  }
  
  /**
  * @return the weapon plus any attachments
  */
  public String toString() {
    return base.toString() + " +PowerBooster";
  }

}
