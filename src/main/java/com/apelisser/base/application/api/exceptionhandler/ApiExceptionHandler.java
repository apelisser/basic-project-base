package com.apelisser.base.application.api.exceptionhandler;

import static com.apelisser.base.core.i18n.MessageConstants.GENERIC_USER_MESSAGE;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.apelisser.base.application.api.exceptionhandler.model.Problem;
import com.apelisser.base.application.api.exceptionhandler.model.ProblemType;
import com.apelisser.base.core.context.Context;
import com.apelisser.base.core.i18n.MessageManager;

@RestControllerAdvice
public class ApiExceptionHandler extends ExceptionHandlingHelper {

    private static final Logger log = LoggerFactory.getLogger(ApiExceptionHandler.class);

    public ApiExceptionHandler(MessageManager messageManager, Context context) {
        super(log, context, messageManager);
    }

    /**
     * Handles an uncaught exception. This method is called when an exception is not
     * handled by any other exception handler. It is used to generate a
     * {@link ProblemDetail} object that contains information about the exception.
     *
     * @param e the exception to handle
     * @return a {@link ProblemDetail} object containing information about the
     *         exception
     */
    @ExceptionHandler(Exception.class)
    private ProblemDetail handleUncaughtException(Exception e) {
        HttpStatusCode status = HttpStatus.INTERNAL_SERVER_ERROR;
        ProblemType problemType = ProblemType.SYSTEM_ERROR;
        String genericMessage = getMessage(GENERIC_USER_MESSAGE);

        log.error(e.getMessage(), e);

        Problem problem = createProblemBuilder(status, problemType, genericMessage)
                .userMessage(genericMessage)
                .build();

        return problem.toProblemDetail();
    }

}
