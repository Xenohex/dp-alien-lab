package random;

/**
 * @author chris
 *
 */
public class RandInt implements Random<Integer> {
  int lo;
  int hi; 
  
  RandInt(int l, int h) {
    lo = l; 
    hi = h; 
  }
  
  public Integer choose() {
    return new java.util.Random().nextInt(hi - lo) + lo;
  }
}
