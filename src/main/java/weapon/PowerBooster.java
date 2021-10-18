package weapon;

import exceptions.AttachmentException;
import exceptions.WeaponException;


public class PowerBooster extends Attachment {
  
  
  public PowerBooster(Weapon b) throws AttachmentException {
    if (b.getNumAttachments() >= 2) {
      throw new AttachmentException("You are trying to add too many Attachments!");
    }
    this.base = b;
  }
  
  /**
   * @param distance
   * @return the damage from firing the weapon with booster
   * attached. 
   */
  public int fire(int distance) throws WeaponException {
    if (base.getShotsLeft() == 0) {
      return 0;
    }
    return Double.valueOf(Math.floor((1 
        + (base.getCurrentAmmo()) / Double.valueOf(base.getMaxAmmo())) 
        * base.fire(distance))).intValue();
  }
  
  public String toString() {
    return base.toString() + " +PowerBooster";
  }

}
