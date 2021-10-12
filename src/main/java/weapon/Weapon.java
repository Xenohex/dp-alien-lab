package weapon;

public interface Weapon {
  
  public int fire(int distance);
  public int getBaseDamage();
  public int getMaxRange();
  public int getRateOfFire();
  public int getMaxAmmo();
  public int getCurrentAmmo();
  public int getShotsLeft();
  public int getNumAttachments();
  public void reload();
  public String toString();
}
