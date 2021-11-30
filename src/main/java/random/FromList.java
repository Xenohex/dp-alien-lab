package random;

import java.util.List;

public class FromList<A> implements Random<A> {
  List<A> choices;
  FromList(List<A> cs) { choices = cs; }
  public A choose() {
    return choices.get(new RandInt(0, choices.size()).choose());
  }
}
