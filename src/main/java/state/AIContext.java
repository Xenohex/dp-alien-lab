package state;

import environment.Environment;
import gameplay.TimerObserver;
import lifeform.LifeForm;

public class AIContext implements TimerObserver {

  protected LifeForm lifeForm;
  protected Environment e;
  
  ActionState outOfAmmoState = new OutOfAmmoState(this);
  ActionState hasWeaponState = new HasWeaponState(this);
  ActionState noWeaponState = new NoWeaponState(this);
  ActionState deadState = new DeadState(this);
  
  ActionState current = noWeaponState;
  
  protected int myTime;
  
  
  
  AIContext(LifeForm lf, Environment e) {
    lifeForm = lf;
    this.e = e;
  }
  
  /**
   * calls the current ActionState's own executeAction
   */
  public void execute() {
    current.executeAction();
  }
  
  
  /**
   * @return the OutOfAmmoState
   */
  public OutOfAmmoState getOutOfAmmoState() {
    return (OutOfAmmoState) outOfAmmoState;
  }
  
  
  /**
   * @return the HasWeaponState
   */
  public HasWeaponState getHasWeaponState() {
    return (HasWeaponState) hasWeaponState;
  }
  
  
  /**
   * @return the NoWeaponState
   */
  public NoWeaponState getNoWeaponState() {
    return (NoWeaponState) noWeaponState;
  }
  
  
  /**
   * @return the DeadState
   */
  public DeadState getDeadState() {
    return (DeadState) deadState;
    
  }
  
  
  /**
   * sets the current state to the new state 
   * (will be set by concrete states)
   * @param newState
   */
  public void setCurrentState(ActionState newState) {
    current = newState;
  }
  
  
  /**
   * @return the current state
   */
  public ActionState getCurrentState() {
    return current;
  }
  
  
  /**
   * @return the lifeForm
   */
  public LifeForm getLifeForm() {
    return lifeForm;
  }
  
  
  /**
   * @return the environment
   */
  public Environment getEnvironment() {
    return e;
  }
  
  
  /**
   * sets the time to the new time
   */
  @Override
  public void updateTime(int time) {
    execute();
    // e.updateCell()
    myTime = time;
    
  }

}
