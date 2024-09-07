package com.apelisser.base.core.context.filter;

import java.io.IOException;
import java.util.UUID;

import com.apelisser.base.core.context.Context;
import com.apelisser.base.core.context.impl.ContextProperty;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;

public class ContextFilter implements Filter {

    private final Context context;

    public ContextFilter(Context context) {
        this.context = context;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
            this.addRequestIdInContext();
            this.addRequestIdInResponseHeader(response);
            chain.doFilter(request, response);
        } finally {
            context.clear();
        }
    }

    private void addRequestIdInContext() {
        String uuid = UUID.randomUUID().toString();
        context.add(ContextProperty.REQUEST_ID, uuid);
    }

    private void addRequestIdInResponseHeader(ServletResponse response) {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setHeader("x-request-id", context.get(ContextProperty.REQUEST_ID));
    }

}
