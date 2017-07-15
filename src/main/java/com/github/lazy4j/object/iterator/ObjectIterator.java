package com.github.lazy4j.object.iterator;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Core api for object-iterator. Constructed
 */
public class ObjectIterator implements Iterator<FieldValue> {

    private Object o;

    private Class c;

    private boolean deep = false;

    private Iterator<Field> fieldIterator;

    private LinkedList<FieldValue> subs;

    public ObjectIterator(Object o, boolean deep) {
        if (o == null) {
            throw new IllegalArgumentException("init ObjectIterator fail: object is null");
        }

        this.o = o;
        this.deep = deep;

        c = o.getClass();
        fieldIterator = Arrays.asList(c.getDeclaredFields()).iterator();

        if (deep) {
            subs = new LinkedList<>();
        }
    }

    @Override
    public boolean hasNext() {
        if (fieldIterator.hasNext())
            return true;
        else if (subs.size() > 0) {
            FieldValue fieldValue = subs.pop();
            Object sub = fieldValue.getObject();
            if (sub != null) {
                fieldIterator = new ObjectIterator(sub, deep).fieldIterator;
                o = sub;
            }
            return hasNext();
        } else {
            return false;
        }
    }

    @Override
    public FieldValue next() {
        FieldValue fieldValue = new FieldValue(fieldIterator.next(), o);
        if (deep && !isJavaClass(fieldValue.getField().getType())) {
            subs.add(fieldValue);
        }
        return fieldValue;
    }

    public boolean isJavaClass(Class<?> c) {
        return c != null && c.getClassLoader() == null;
    }
}
