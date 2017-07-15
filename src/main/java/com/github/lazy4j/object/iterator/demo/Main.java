package com.github.lazy4j.object.iterator.demo;

import com.github.lazy4j.object.iterator.FieldValue;
import com.github.lazy4j.object.iterator.ObjectIterator;

public class Main {
    public static void main(String[] args) {
        Owner owner = new Owner();
        owner.setByteValue((byte) 1);
        owner.setCharValue((char) 10);
        owner.setShortValue((short) 100);
        owner.setIntValue(1000);
        owner.setLongValue(10000);
        owner.setFloatValue(100000);
        owner.setDoubleValue(1000000);
        owner.setStringValue("hello java");

        Sub sub = new Sub();
        sub.setByteValue((byte) 2);
        sub.setCharValue((char) 20);
        sub.setShortValue((short) 200);
        sub.setIntValue(2000);
        sub.setLongValue(20000);
        sub.setFloatValue(200000);
        sub.setDoubleValue(2000000);
        sub.setStringValue("hello lazy");

        owner.setSub(sub);

        ObjectIterator oi = new ObjectIterator(owner, true);
        while (oi.hasNext()) {
            FieldValue next = oi.next();
            System.out.println(next);
        }
    }
}
