package gui;

/**
 * 
 * @author Austin Pliska
 * Remote to set and execute commands in order to satisfy command pattern
 *
 */
public class Remote {
  Command command;
  
  public void setCommand(Command cc) {
    command = cc;
  }
  
  public void buttonPressed() {
    command.execute();
  }
}
