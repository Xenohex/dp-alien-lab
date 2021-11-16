package gui;

/**
 * 
 * @author Austin Pliska
 * Remote to set commands and execute them per the command pattern
 */
public class Remote {
  Command command;
  
  void setCommand(Command cc) {
    command = cc;
  }
  
  void buttonPressed() {
    command.execute();
  }
}
