package uk.gov.justice.laa.crime.courtmessageprocessor.exception;

/**
 * The exception thrown when item not found.
 */
public class ItemNotFoundException extends RuntimeException {

  /**
   * Constructor for ItemNotFoundException.
   *
   * @param message the error message
   */
  public ItemNotFoundException(String message) {
    super(message);
  }
}
