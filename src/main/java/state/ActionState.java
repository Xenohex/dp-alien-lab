package state;

import environment.Environment;
import lifeform.LifeForm;

public abstract class ActionState {
  
  protected AIContext context;
  protected Environment e;
  protected LifeForm lifeForm;
  
  ActionState(AIContext context) {
    this.context = context;
    e = context.getEnvironment();
    lifeForm = context.getLifeForm();
  }
  
  abstract void executeAction();
}
