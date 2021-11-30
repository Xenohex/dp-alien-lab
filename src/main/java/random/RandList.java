package random;

import java.util.ArrayList;
import java.util.List;

public class RandList<A> implements Random<List<A>> {
  
  Random<A> ra; int n;
  RandList(Random<A> r, int m) { ra = r; n = m; }
  public List<A> choose() {
    List<A> lst = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      lst.add(ra.choose());
    }
    return lst;
  }
 

}
