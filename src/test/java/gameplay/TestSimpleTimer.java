package gameplay;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * @author Jun
 *
 */
public class TestSimpleTimer {

  /**
   * this test ensures that SimpleTimer starts with round 0 and
   * no observers
   */
  @Test
  public void testInitialization() {
    SimpleTimer st = new SimpleTimer();
    assertTrue(st instanceof Timer);
    assertEquals(st.getRound(), 0);
    assertEquals(st.getNumObservers(),0);
  }
  
  /**
   * test the ability to add and delete time Observers
   */
  @Test
  public void testAddAndDeleteTimeObserver() {
    SimpleTimer st = new SimpleTimer();
    MockSimpleTimerObserver to = new MockSimpleTimerObserver();
    st.addTimeObserver(to);
    assertEquals(st.getNumObservers(),1);
    st.removeTimeObserver(to);
    assertEquals(st.getNumObservers(),0);
  }
  
  /**
   * test to ensure that the round has been updated
   */
  @Test
  public void testTimeChanged() {
    SimpleTimer st = new SimpleTimer();
    assertEquals(st.getRound(),0);
    st.timeChanged();
    assertEquals(st.getRound(),1);
  }
  
  /**
   * This tests that SimpleTimer will update time once
   * every second.
   * @throws InterruptedException when something goes wrong
   * with the thread
   */
  @Test
  public void testSimpletimerAsThread() throws
  InterruptedException{
    SimpleTimer st = new SimpleTimer(1000);
    st.start();
    Thread.sleep(250); // So we are 1/4th a second different
    for (int x = 0;x<5;x++) {
      assertEquals(x,st.getRound()); //assumes round starts at 0
      Thread.sleep(1000); // wait for the next time change
    }
  }

}
