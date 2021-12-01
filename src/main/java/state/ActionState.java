package state;

import environment.Environment;
import lifeform.LifeForm;

public abstract class ActionState {
  
  protected AiContext context;
  protected Environment env;
  protected LifeForm lifeForm;
  
  /**
   * @param context
   */
  public ActionState(AiContext context) {
    this.context = context;
    env = context.getEnvironment();
    lifeForm = context.getLifeForm();
  }
  
  abstract void executeAction();
}
