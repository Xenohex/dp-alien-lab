package gameplay;

/**
 * This is made and use to mimic what Timer Observer can do
 * @author Jun
 *
 */
public class MockSimpleTimerObserver implements TimerObserver {

  public int myTime = 0;
  
  public void updateTime(int time) {
    myTime = time;
  }

}
