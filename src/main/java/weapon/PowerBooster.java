package weapon;

import exceptions.AttachmentException;

public class PowerBooster extends Attachment{
  public PowerBooster(Weapon b) throws AttachmentException {
    if (b.getNumAttachments() >= 2) {
      throw new AttachmentException("You are trying to add too many Attachments!");
    }
    this.base = b;
  }
  public int fire(int distance) {
    return Double.valueOf(Math.floor((1 + 
        (base.getCurrentAmmo()) / base.getMaxAmmo()) * 
        base.fire(distance))).intValue();
  }
  public String toString() {
    return base.toString() + " +PowerBooster";
  }

}
