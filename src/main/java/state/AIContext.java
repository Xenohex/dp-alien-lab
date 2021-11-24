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
  
  
  
  AIContext(LifeForm lf, Environment e) {
    lifeForm = lf;
    this.e = e;
  }
  
  public void execute() {
    current.executeAction();
  }
  
  public OutOfAmmoState getOutOfAmmoState() {
    return (OutOfAmmoState) outOfAmmoState;
  }
  
  public HasWeaponState getHasWeaponState() {
    return (HasWeaponState) hasWeaponState;
  }
  
  public NoWeaponState getNoWeaponState() {
    return (NoWeaponState) noWeaponState;
  }
  
  public DeadState getDeadState() {
    return (DeadState) deadState;
    
  }
  
  public void setCurrentState(ActionState newState) {
    current = newState;
  }
  
  public ActionState getCurrentState() {
    return current;
  }
  
  public LifeForm getLifeForm() {
    return lifeForm;
  }
  
  public Environment getEnvironment() {
    return e;
  }
  
  
  
  
  
  @Override
  public void updateTime(int time) {
    // TODO Auto-generated method stub
    
  }

}
