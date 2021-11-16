package gui;

/**
 * 
 * @author Austin Pliska
 * Remote to set and execute commands in order to satisfy command pattern
 *
 */
public class Remote {
  Command c;
  public void setCommand(Command cc) {
    c = cc;
  }
  
  public void buttonPressed() {
    c.execute();
  }
}
