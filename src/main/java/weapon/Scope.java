package weapon;

import exceptions.AttachmentException;

public class Scope extends Attachment{

  public Scope(Weapon b) throws AttachmentException {
    if (b.getNumAttachments() >= 2) {
      throw new AttachmentException("You are trying to add too many Attachments!");
    }
    this.base = b;
  }
  public int fire(int distance) throws WeaponException {
    if (base.getMaxRange() < distance && distance <= getMaxRange()) {
      return (5 + base.fire(base.getMaxRange()));
    }
    return Double.valueOf(Math.floor(base.fire(distance) *
        (1 + (getMaxRange() - distance) / 
            Double.valueOf(getMaxRange())))).intValue();
  }
  public String toString() {
    return base.toString() + " +Scope";
  }
  public int getMaxRange() {
    return base.getMaxRange() + 10;
  }
}
