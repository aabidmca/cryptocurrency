package com.kotak.cryptocurrency.exception;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.springframework.context.i18n.LocaleContextHolder;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionMappingUtils {

    private static final String PREFIX = "exception.";
    private static final String MESSAGE_SUFFIX = ".message";
    private static final String CODE_SUFFIX = ".code";
    private static final String DEFAULT_CODE = "500";

    /**
     *
     */
    public static String getMessage(final Throwable exception) {
        try {
            ResourceBundle BUNDLE = ResourceBundle.getBundle("exception", LocaleContextHolder.getLocale());
            return BUNDLE.getString(PREFIX + exception.getClass().getSimpleName() + MESSAGE_SUFFIX);
          } catch (MissingResourceException e) {
            return exception.getClass().getSimpleName() + " : " + exception.getMessage();
        }
    }

    /**
     * @return
     */
    public static String getErrorCode(final Throwable exception) {
        try {
            ResourceBundle BUNDLE = ResourceBundle.getBundle("exception", LocaleContextHolder.getLocale());
            return BUNDLE.getString(PREFIX + exception.getClass().getSimpleName() + CODE_SUFFIX);
        } catch (MissingResourceException e) {
            return DEFAULT_CODE;
        }
    }

    /**
     * @return
     */
    public static Map<String, Map<String, String>> getAllErrors() {
        ResourceBundle BUNDLE = ResourceBundle.getBundle("exception", LocaleContextHolder.getLocale());

        Map<String, Map<String, String>> errors = new HashMap<>();

        Enumeration<String> iterator = BUNDLE.getKeys();

        while (iterator.hasMoreElements()) {
            String key = iterator.nextElement();
            String value = BUNDLE.getString(key);

            String[] fragments = key.split("\\.");

            errors.putIfAbsent(fragments[1], new HashMap<>());
            errors.get(fragments[1]).putIfAbsent(fragments[2], value);

        }

        return errors;
    }
}