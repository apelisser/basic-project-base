package com.apelisser.base.core.context.impl;

import java.util.Map;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.apelisser.base.core.context.Context;
import com.apelisser.base.core.context.ContextKey;

@Component
public class ContextImpl implements Context {

    @Override
    public void add(ContextKey key, String value) {
        validateKey(key);
        MDC.put(key.getName(), value);
    }

    @Override
    public String get(ContextKey key) {
        validateKey(key);
        return MDC.get(key.getName());
    }

    @Override
    public Map<String, String> getAll() {
        return MDC.getCopyOfContextMap();
    }

    @Override
    public boolean exists(ContextKey key) {
        validateKey(key);
        return MDC.get(key.getName()) != null;
    }

    @Override
    public void remove(ContextKey key) {
        if (key != null) {
            MDC.remove(key.getName());
        }
    }

    @Override
    public void clear() {
        MDC.clear();
    }

    private void validateKey(ContextKey key) {
        Assert.notNull(key, "Key cannot be null.");
    }

}
