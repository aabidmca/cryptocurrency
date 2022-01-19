package com.kotak.cryptocurrency.exception;
/**
 * @author Aabid Husain
 */

public class ResourceNotFoundException extends AbstractException {

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String message) {
        super(message);
    }
	public ResourceNotFoundException(String message, Throwable e) {
        super(message, e);
    }
}