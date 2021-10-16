package weapon;

abstract class Attachment implements Weapon{
  protected Weapon base;
  public Attachment() {
    
  }
  public abstract int fire(int distance) throws WeaponException;
  public int getBaseDamage() {
    return base.getBaseDamage();
  }
  public int getMaxRange() {
    return base.getMaxRange();
  }
  public int getRateOfFire() {
    return base.getRateOfFire();
  }
  public int getMaxAmmo() {
    return base.getMaxAmmo();
  }
  public int getCurrentAmmo() {
    return base.getCurrentAmmo();
  }
  public int getShotsLeft() {
    return base.getShotsLeft();
  }
  public int getNumAttachments() {
    return base.getNumAttachments() + 1;
  }
  public void reload() {
    base.reload();
  }
  public void updateTime(int time) {
    if (time >= 0) {
      base.updateTime(time);
    } else {
      System.out.println("bad");
    }
  }
}
