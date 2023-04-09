package org.ppbang.spring.test.bean;

public class UserService {

    private String name;

    public UserService(String name) {
        this.name = name;
    }

    public void queryUserName() {
        System.out.println("hello " + name);
    }

    @Override
    public String toString() {
        return "UserService{" +
                "name='" + name + '\'' +
                '}';
    }
}
