package com.apelisser.base.core.context;

import java.util.Map;

public interface Context {

    void add(ContextKey key, String value);

    String get(ContextKey key);

    Map<String, String> getAll();

    boolean exists(ContextKey key);

    void remove(ContextKey key);

    void clear();

}
