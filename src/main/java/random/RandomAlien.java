package random;

import java.util.List;

import exceptions.RecoveryRateException;
import lifeform.Alien;

public class RandomAlien implements Random<Alien> {
  
  List<String> names = List.of("A1", "A2", "A3", "A4");
  
  public Alien choose() {
    
    try {
      return new Alien(new FromList<>(names).choose(), 
              new RandInt(30,60).choose(),
              new RandomRecovery().choose());
    } catch (RecoveryRateException e) {
      e.printStackTrace();
    }
    return null;
  }
  
}
