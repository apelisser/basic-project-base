package com.apelisser.base.core.context.impl;

import com.apelisser.base.core.context.ContextKey;

public enum ContextProperty implements ContextKey {

    REQUEST_ID("requestId");

    private final String key;

    private ContextProperty(String key) {
        this.key = key;
    }

    @Override
    public String getName() {
        return key;
    }

}
