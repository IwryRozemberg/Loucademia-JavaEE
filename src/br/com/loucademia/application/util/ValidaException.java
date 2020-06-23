package br.com.loucademia.application.util;

import javax.ejb.ApplicationException;

@ApplicationException
public class ValidaException extends RuntimeException {

	public ValidaException() {
		// TODO Auto-generated constructor stub
	}

	public ValidaException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ValidaException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public ValidaException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ValidaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
