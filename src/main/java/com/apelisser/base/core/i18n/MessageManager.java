package com.apelisser.base.core.i18n;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * This class is responsible for managing messages in the application.
 * It uses the {@link MessageSource} to retrieve messages based on the
 * provided code and optional arguments.
 */
@Component
public class MessageManager {

    private final MessageSource messageSource;

    public MessageManager(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * Retrieves the message associated with the given code.
     *
     * @param code the code of the message to retrieve
     * @return the message associated with the given code
     */
    public String getMessage(String code) {
        return this.getMessage(code, (Object) null);
    }

    /**
     * Retrieves the message associated with the given code.
     *
     * @param code the code of the message to retrieve
     * @param args optional arguments to be interpolated into the message
     * @return the message associated with the given code
     */
    public String getMessage(String code, Object... args) {
        Locale contextLocale = this.getContextLocale();
        return messageSource.getMessage(code, args, contextLocale);
    }

    /**
     * Retrieves the message corresponding to the given MessageSourceResolvable in
     * the current context locale.
     *
     * @param resolvable the MessageSourceResolvable object representing the message
     *                   to retrieve
     * @return the message string corresponding to the given MessageSourceResolvable
     *         in the current context locale
     */
    public String getMessage(MessageSourceResolvable resolvable) {
        Locale contextLocale = this.getContextLocale();
        return messageSource.getMessage(resolvable, contextLocale);
    }

    /**
     * Retrieves the locale from the current context.
     *
     * @return the locale from the current context
     */
    public Locale getContextLocale() {
        return LocaleContextHolder.getLocale();
    }

}
