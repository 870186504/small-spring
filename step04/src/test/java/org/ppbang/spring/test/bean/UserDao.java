package org.ppbang.spring.test.bean;

import java.util.HashMap;
import java.util.Map;

public class UserDao {
    private static Map<String, String> hashMap = new HashMap<>();

    static {
        hashMap.put("10001", "彭于晏");
        hashMap.put("10002", "张学友");
        hashMap.put("10003", "刘德华");
    }

    public String queryUserName(String uId) {
        return hashMap.get(uId);
    }
}
