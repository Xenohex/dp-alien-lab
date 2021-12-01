package state;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import environment.Environment;
import exceptions.AttachmentException;
import exceptions.EnvironmentException;
import gameplay.SimpleTimer;
import lifeform.Human;
import lifeform.LifeForm;

public class testSimulator {


  @Before 
  public void resetEnvironment() {
    Environment e = Environment.getEnvironment(1, 2);
    e.clearBoard();
  } 
  
  /**
   * 
   */
  @Test
  public void test() {
    
    int row = 1;
    int col = 2;
    int lfCount = 0;
    int weaponCount = 0;
    int human = 1;
    int alien = 1;
    
    Environment e = Environment.getEnvironment(row,col);
    SimpleTimer t = new SimpleTimer(1000);
    
    try {
      Simulator s = new Simulator(e, t, human, alien);
    } catch (EnvironmentException e1) {
      e1.printStackTrace();
    } catch (AttachmentException e1) {
      e1.printStackTrace();
    }
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        if (e.getLifeForm(i, j) != null) {
          lfCount++;
        }
        weaponCount += e.getCell(i, j).getWeaponsCount();
      }
    }
    assertEquals(human+alien,lfCount);
    assertEquals(human+alien,weaponCount);
  } 
  
 

  
  /**
   * @throws AttachmentException 
   * @throws EnvironmentException 
   * 
   */
  
  @Test
  public void testRun() throws EnvironmentException, AttachmentException {
    Environment e = Environment.getEnvironment(1,2);
    SimpleTimer t = new SimpleTimer();
    LifeForm h = null;
    
    Simulator s = new Simulator(e, t, 1, 0);
    
    for (int i = 0; i < 1; i++) {
      for (int j = 0; j < 2; j++) {
        if (e.getLifeForm(i, j) != null) {
          h = e.getLifeForm(i, j);
        }
      }
    }
    assertFalse(h.hasWeapon());
    
    for(int i = 0; i < 30; i++) {
      t.timeChanged();
    }   
    assertTrue(h.hasWeapon());
  } 
  

}
