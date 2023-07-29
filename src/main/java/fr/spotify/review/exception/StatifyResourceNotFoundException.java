package fr.spotify.review.exception;

public class StatifyResourceNotFoundException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public StatifyResourceNotFoundException(String msg) {
    super(msg);
  }
}
