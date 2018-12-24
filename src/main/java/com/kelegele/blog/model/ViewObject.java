package com.kelegele.blog.model;

import org.apache.commons.collections4.map.HashedMap;

import java.util.Map;

/**
 * @program: blog-4
 * @description: 显示对象
 * @author: FelixHuang
 * @create: 2018-11-26 11:24
 **/
public class ViewObject {
    private Map<String, Object> objects = new HashedMap();

    public void set(String key, Object value) {
        objects.put(key, value);
    }

    public Object get(String key) {
        return objects.get(key);
    }
}
