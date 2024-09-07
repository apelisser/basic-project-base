package com.apelisser.base.core.util;

import java.util.Optional;

public final class ExceptionUtil {

    private ExceptionUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Finds the root cause of a given {@link Throwable} or returns an empty
     * {@link Optional} if the given exception is null.
     *
     * @param ex the given exception
     * @return an optional containing the root cause of the given exception
     */
    public static Optional<Throwable> findRootCause(Throwable ex) {
        if (ex == null) {
            return Optional.empty();
        }

        Throwable rootCause = ex;
        while (rootCause.getCause() != null && rootCause.getCause() != rootCause) {
            rootCause = rootCause.getCause();
        }
        return Optional.of(rootCause);
    }

}