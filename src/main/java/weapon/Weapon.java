package weapon;

import gameplay.TimerObserver;
import exceptions.WeaponException;

/**
 * @author Christian Spitler
 * 
 */
public interface Weapon extends TimerObserver {
  
  /**
   * @param distance
   * @return
   * @throws WeaponException
   */
  public int fire(int distance) throws WeaponException;
  
  /**
   * @return the base damage of this weapon
   */
  public int getBaseDamage();
  
  /**
   * @return the maximum range of the weapon
   */
  public int getMaxRange();
  
  /**
   * @return the number of times this weapon may fire during a round
   */
  public int getRateOfFire();
  
  /**
   * @return the clip size of this weapon
   */
  public int getMaxAmmo();
  
  /**
   * @return the current number of bullets in the clip
   */
  public int getCurrentAmmo();
  
  /**
   * @return number of shots left in this round
   */
  public int getShotsLeft();
  
  /**
   * @return the number of attachments on this weapon
   */
  public int getNumAttachments();
  
  /**
   * reload clip to full capacity. 
   */
  public void reload();
  
  /**
   * 
   * @return Displays weapon and any attachments
   */
  public String toString();
}
