package com.kotak.cryptocurrency.exception;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public abstract class AbstractException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public AbstractException(String message) {
        super(message);
    }

	public AbstractException(String message, Throwable cause) {
        super(message, cause);
    }
    
    @Override
	public String getMessage() {
		final String message = super.getMessage();
		if (StringUtils.isBlank(message)) {
			return ClassUtils.getShortCanonicalName(getClass());
		}
		return message;
	}

}