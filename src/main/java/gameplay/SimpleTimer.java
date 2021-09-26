package gameplay;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jun
 *
 */
public class SimpleTimer extends Thread implements Timer {
  private List<TimerObserver> theObservers = new ArrayList<>();
  int round;
  int sleep;
  boolean start = false;
  
  /**
   * sets up a SimpleTimer to start at Round 0 with no observers
   */
  public SimpleTimer() {
    this(0);
    round = 0;
  }
  
  /**
   * a Simple Timer with sleep time in milliseconds 
   * @param sleep
   */
  public SimpleTimer(int sleep) {
    this.sleep = sleep;
  }
  
  /**
   * subscribes a TimerObserver to observe this Timer
   * @param observer - the observer that wants to observer this Timer
   */
  public void addTimeObserver(TimerObserver observer) {
    theObservers.add(observer);
  }
  
  /**
   * unsubscribes a TimerObserver from this Timer
   * @param observer
   */
  public void removeTimeObserver(TimerObserver observer) {
    theObservers.remove(observer);
  }

  /**
   * updates the current round by one round
   */
  public void timeChanged() {
    round++;
    theObservers.forEach(o -> o.updateTime(round));
  }
  
  /**
   * updates the round at a fixed interval determined by the number of 
   * Milliseconds used in instantiation
   */
  public void run() {
    try {
      for (int i = 0; i < 50; i++) {
        Thread.sleep(sleep);
        timeChanged();
      }
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  /**
   * @return the number of observers this Timer has
   */
  public int getNumObservers() {
    return theObservers.size();
  }
  
  /**
   * @return the current round number 
   */
  public int getRound() {
    return round;
  }
}
