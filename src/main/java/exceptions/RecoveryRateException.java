package exceptions;

/**
 * RecoveryRateException happens when RecoveryRate is negative
 * 
 * @author jp4174
 *
 */
public class RecoveryRateException extends Exception {
  public RecoveryRateException(String message) {
    super(message);
  }
}
