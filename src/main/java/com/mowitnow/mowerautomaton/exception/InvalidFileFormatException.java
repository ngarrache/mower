package com.mowitnow.mowerautomaton.exception;

/**
 * @author Nizar Garrache
 * 
 * @since 7 juil. 2011
 */
@SuppressWarnings("serial")
public class InvalidFileFormatException extends Exception {

	public InvalidFileFormatException(String message) {
		super(message);
	}

	public InvalidFileFormatException(String message, Throwable cause) {
		super(message, cause);
	}

}
