package com.trickl.oanda.model.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ConversionFailureException extends RuntimeException {
  private static final long serialVersionUID = 2610477518423283258L;

  public ConversionFailureException(String message) {
    super(message);
  }

  public ConversionFailureException(String message, Throwable cause) {
    super(message, cause);
  }

  public ConversionFailureException(Throwable cause) {
    super(cause);
  }
}
