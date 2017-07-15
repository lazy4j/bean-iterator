package com.github.lazy4j.object.iterator;

import java.lang.reflect.Field;

public class FieldValue {

    private Field field;

    private Object owner;

    public FieldValue(Field field, Object owner) {
        this.field = field;
        this.owner = owner;
    }

    public Object getObject()  {
        if (!field.isAccessible()) {
            field.setAccessible(true);
        }
        try {
            return field.get(owner);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public Object getOwner() {
        return owner;
    }

    public void setOwner(Object owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "FieldValue{" +
                "field=" + field +
                ", owner=" + owner +
                '}';
    }
}
