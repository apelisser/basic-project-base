package com.apelisser.base.api.exceptionhandler.model;

import org.springframework.http.ProblemDetail;

public interface ConvertibleProblemDetail {

    /**
     * When a class that represents an error implements this interface,
     * it must be able to generate a ProblemDetail object.
     *
     * @return a new {@link ProblemDetail} object
     */
    ProblemDetail toProblemDetail();

}
