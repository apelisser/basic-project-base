package com.apelisser.base.api.v1.controller;

import static com.apelisser.base.core.i18n.MessageConstants.APPLICATION_TEST;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apelisser.base.core.i18n.MessageManager;


/**
 * This controller was created only to test i18n.
 */
@RestController
@RequestMapping("/i18n")
public class I18NController {

    private static final Logger log = LoggerFactory.getLogger(I18NController.class);

    private final MessageManager message;

    public I18NController(MessageManager message) {
        this.message = message;
    }

    @GetMapping("/test")
    public String test(@PathVariable String code) {
        String retrievedMessage = message.getMessage(APPLICATION_TEST);
        log.info("Message: '{}'", retrievedMessage);
        return retrievedMessage;
    }

}
