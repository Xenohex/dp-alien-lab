package gameplay;

interface Timer {
  /**
   * subscribes a TimerObserver to observe this Timer
   * @param observer - the observer that wants to observe this Timer
   */
  void addTimeObserver(TimerObserver observer);
  
  /**
   * unsubscribes a TimerObserver from this Timer
   * @param observer
   */
  void removeTimeObserver(TimerObserver observer);
  
  /**
   * updates the current round by one round
   */
  void timeChanged();
}
