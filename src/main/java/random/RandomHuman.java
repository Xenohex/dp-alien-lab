package random;

import java.util.List;

import lifeform.Human;

public class RandomHuman implements Random<Human> {
  
  List<String> names = List.of("Alice", "Bob", "Charlie","Denise");
  
  
  public Human choose() {
    return new Human(new FromList<>(names).choose(), 
            new RandInt(30,60).choose(),
            new RandInt(0,10).choose());
  }
}
