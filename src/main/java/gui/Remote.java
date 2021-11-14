package gui;

public class Remote {
  Command c;
  void setCommand(Command cc) { c = cc; }
  void buttonPressed() { c.execute(); }
}
