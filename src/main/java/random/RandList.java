package random;

import java.util.ArrayList;
import java.util.List;

public class RandList<A> implements Random<List<A>> {
  
  Random<A> ra; 
  int num;
  
  RandList(Random<A> r, int m) { 
    ra = r; 
    num = m; 
  }
  
  /**
   * Chooses from list
   */
  public List<A> choose() {
    List<A> lst = new ArrayList<>();
    for (int i = 0; i < num; i++) {
      lst.add(ra.choose());
    }
    return lst;
  }
 

}
