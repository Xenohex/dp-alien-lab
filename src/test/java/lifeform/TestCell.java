package lifeform;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import environment.Cell;
import weapon.Pistol;
import weapon.PlasmaCannon;
import weapon.ChainGun;
import weapon.Weapon;

/**
 * The test cases for the Cell class
 *
 */
public class TestCell {
  ///-------------lab5 tests------------
  @Test
  public void testInitializationWithSingleton() {
    Cell cell = new Cell();
    assertNull(cell.getLifeForm());
  }
  
  @Test
  public void testAddWeapon() {
    Cell cell = new Cell();
    Weapon pistol = new Pistol();
    Weapon chainGun = new ChainGun();
    Weapon plasmaCannon = new PlasmaCannon();
    assertTrue(cell.addWeapon(pistol));
    assertTrue(cell.addWeapon(chainGun));
    assertFalse(cell.addWeapon(plasmaCannon));
    
  }
  
  @Test
  public void testRemoveWeapon() {
    Cell cell = new Cell();
    Weapon pistol = new Pistol();
    Weapon chainGun = new ChainGun();
    Weapon plasmaCannon = new PlasmaCannon();
    cell.addWeapon(pistol);
    cell.addWeapon(chainGun);
    assertEquals(pistol,cell.removeWeapon(pistol));
    assertNull(cell.removeWeapon(plasmaCannon));
    assertTrue(cell.addWeapon(plasmaCannon));
  }
  
  @Test
  public void testCellFull() {
    Cell cell = new Cell();
    Weapon pistol = new Pistol();
    Weapon chainGun = new ChainGun();
    Weapon plasmaCannon = new PlasmaCannon();
    assertTrue(cell.addWeapon(pistol));
    assertTrue(cell.addWeapon(chainGun));
    assertFalse(cell.addWeapon(plasmaCannon));
  }
  
  @Test
  public void testWeaponCount() {
    Cell cell = new Cell();
    Weapon pistol = new Pistol();
    Weapon chainGun = new ChainGun();
    Weapon plasmaCannon = new PlasmaCannon();
    assertEquals(0,cell.getWeaponsCount());
    
    assertTrue(cell.addWeapon(pistol));
    assertEquals(1,cell.getWeaponsCount());
    
    assertTrue(cell.addWeapon(chainGun));
    assertEquals(2,cell.getWeaponsCount());
    
    assertFalse(cell.addWeapon(plasmaCannon));
    assertEquals(2,cell.getWeaponsCount());
    
  }
  
  
  
  //---------Decorator Tests and pre-lab5 tests------
  /**
   * At initialization, the Cell should be empty and not contain a LifeForm.
   */
  @Test
  public void testInitialization() {
    Cell cell = new Cell();
    assertNull(cell.getLifeForm());
  }

  /**
   * Checks to see if we change the LifeForm held by the Cell that getLifeForm
   * properly responds to this change.
   */
  @Test
  public void testAddLifeForm() {
    LifeForm bob = new MockLifeForm("Bob", 40);
    LifeForm fred = new MockLifeForm("Fred", 40);
    Cell cell = new Cell();
    // The cell is empty so this should work;
    boolean success = cell.addLifeForm(bob);
    assertTrue(success);
    assertEquals(bob, cell.getLifeForm());
    // The cell is not empty so this should fail.
    success = cell.addLifeForm(fred);
    assertFalse(success);
    assertEquals(bob, cell.getLifeForm());
  }

  /**
   * Checks to see if we can remove a LifeForm
   */
  @Test
  public void testRemoveLifeForm() {
    LifeForm bob = new MockLifeForm("Bob", 40);
    Cell cell = new Cell();
    assertTrue(cell.addLifeForm(bob));
    cell.removeLifeForm();
    assertNull(cell.getLifeForm());
  }
}
