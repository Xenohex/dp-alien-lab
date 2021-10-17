package lifeform;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import exceptions.WeaponException;
import weapon.Pistol;
import weapon.PlasmaCannon;
import weapon.Weapon;

/**
 * Tests the functionality provided by the LifeForm class
 *
 */
public class TestLifeForm {

  /**
   * When a LifeForm is created, it should know its name and how many life points
   * it has.
   */
  @Test
  public void testInitialization() {
    LifeForm entity;
    entity = new MockLifeForm("Bob", 40);
    assertEquals("Bob", entity.getName());
    assertEquals(40, entity.getCurrentLifePoints());
  }

  /**
   * Test that the LifeForm can take hits and it's health will not go below 0
   */
  @Test
  public void testTakeHits() {
    LifeForm entity;
    entity = new MockLifeForm("Bob", 40);
    entity.takeHit(20);
    assertEquals(20, entity.getCurrentLifePoints());
    entity.takeHit(30);
    assertEquals(0, entity.getCurrentLifePoints());
  }
  
  /**
   * test to make sure attack strength is stored correctly and one LifeForm
   * can attack the other
   * @throws WeaponException 
   */
  @Test
  public void attackAnotherLifeForm() throws WeaponException {
    LifeForm a,b;
    a = new MockLifeForm("Bob", 40, 5);
    b = new MockLifeForm("Poo", 40, 10);
    assertEquals(a.getAttackStrength(),5);
    assertEquals(b.getAttackStrength(),10);
    a.attack(b, 1);
    assertEquals(b.getCurrentLifePoints(),35);
  }
  
  /**
   * test to make sure a dead LifeForm cannot attack another LifeForm
   * @throws WeaponException 
   */
  @Test
  public void cannotAttackWhenDead() throws WeaponException {
    LifeForm a,b;
    a = new MockLifeForm("Bob", 0, 5);
    b = new MockLifeForm("Poo", 40, 10);
    a.attack(b, 1);
    assertEquals(b.getCurrentLifePoints(),40);
    b.attack(a, 1);
    assertEquals(a.getCurrentLifePoints(),0);
  }
  

  @Test
  public void pickUpWeapon() {
    LifeForm a = new MockLifeForm("Bob", 0, 5);
    Weapon w = new Pistol();
    assertEquals(true,a.pickUpWeapon(w));
    assertEquals(w,a.weapon);
  }
  
  @Test
  public void noPickUpWeapon() {
    LifeForm a = new MockLifeForm("bob", 0, 5);
    Weapon w = new Pistol();
    Weapon x = new Pistol();
    a.pickUpWeapon(x);
    assertEquals(false,a.pickUpWeapon(w));
  }
  
  @Test
  public void dropWeapon() {
    LifeForm a = new MockLifeForm("Bob", 0, 5);
    Weapon w = new Pistol();
    a.pickUpWeapon(w);
    assertEquals(w,a.dropWeapon());
  }
  
  @Test
  public void useWeapon() throws WeaponException {
    LifeForm a = new MockLifeForm("Bob", 10, 3);
    LifeForm c = new MockLifeForm("Chris", 11, 3);
    Weapon w = new Pistol();
    a.pickUpWeapon(w);
    a.attack(c, 10);
    assertEquals(11-(10*(50-10+10)/50), c.getCurrentLifePoints());
  }
  
  @Test
  public void attackWithWeapon() throws WeaponException {
    LifeForm a = new MockLifeForm("Bob", 10, 3);
    LifeForm c = new MockLifeForm("Chris", 11, 3);
    Weapon w = new PlasmaCannon();
    a.pickUpWeapon(w);
    a.attack(c,45);
    a.attack(c,40);
    assertEquals(2,w.getCurrentAmmo());
    
  }
  
  @Test
  public void useMeleeNoAmmo() throws WeaponException {
    LifeForm a = new MockLifeForm("Bob", 10, 3);
    LifeForm c = new MockLifeForm("Chris", 11, 3);
    Weapon w = new Pistol();
    a.pickUpWeapon(w);
    for(int i = 0; i < 10; i++) {
      w.fire(10);
    }
    assertEquals(0, w.getCurrentAmmo());
    a.attack(c, 2);
    assertEquals(8, c.getCurrentLifePoints());
  }
  
  @Test
  public void meleeNoDamage() throws WeaponException {
    LifeForm a = new MockLifeForm("Bob", 10, 3);
    LifeForm c = new MockLifeForm("Chris", 11, 3);
    Weapon w = new Pistol();
    a.pickUpWeapon(w);
    for(int i = 0; i < 10; i++) {
      w.fire(10);
    }
    a.attack(c,6);
    assertEquals(11,c.getCurrentLifePoints());
  }
  
  /**
  @Test public void canReload() {
    LifeForm a = new MockLifeForm("Bob", 10, 3);
    LifeForm c = new MockLifeForm("Chris", 11, 3);
    Weapon w = new Pistol();
    a.pickUpWeapon(w);
    for(int i = 0; i < 10; i++) {
      w.fire(10);
    }
    w.reload();
    
  }
  **/
}
