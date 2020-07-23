package com.xinghaol.io.json.model;

import com.xinghaol.io.json.exception.JsonTypeException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author: lixinghao
 * @date: 2020/7/22 10:02 下午
 * @Description:
 */
public class JsonArray implements Serializable, Iterable {
    private List list = new ArrayList();

    public void add(Object object) {
        list.add(object);
    }

    public Object get(int index) {
        return list.get(index);
    }

    public int size() {
        return list.size();
    }

    public JsonArray getJsonArray(int index) {
        Object object = list.get(index);
        if (!(object instanceof JsonArray)) {
            throw new JsonTypeException("Type of value is not JsonArray");
        }

        return (JsonArray) object;
    }

    public JsonObject getJsonObject(int index) {
        Object obj = list.get(index);
        if (!(obj instanceof JsonObject)) {
            throw new JsonTypeException("Type of value is not JsonObject");
        }

        return (JsonObject) obj;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator iterator() {
        return list.iterator();
    }
}
