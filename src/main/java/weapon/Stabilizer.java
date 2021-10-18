package weapon;

import exceptions.AttachmentException;
import exceptions.WeaponException;

public class Stabilizer extends Attachment{
  
  public Stabilizer(Weapon b) throws AttachmentException {
    if (b.getNumAttachments() >= 2) {
      throw new AttachmentException("You are trying to add too many Attachments!");
    }
    this.base = b;
  }
  public int fire(int distance) throws WeaponException {
    int damage = base.fire(distance);
    if(base.getCurrentAmmo() == 0) {
      base.reload();
    }
    return Double.valueOf(Math.floor(Math.floor(damage * 0.25)
        + damage)).intValue();
  }
  public String toString() {
    return base.toString() + " +Stabilizer";
  }
}
