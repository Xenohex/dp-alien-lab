package gui;

public class Remote {
  Command c;
  public void setCommand(Command cc) { c = cc; }
  public void buttonPressed() { c.execute(); }
}
