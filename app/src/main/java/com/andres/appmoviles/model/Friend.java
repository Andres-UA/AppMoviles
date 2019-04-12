package com.andres.appmoviles.model;

import java.lang.reflect.Field;

public class Friend {
    private String id;
    private String name;
    private String age; // String porque no voy a realizar operaciones con este dato
    private String phone;
    private String email;
    private String userId;

    // Serializar
    public Friend() {
    }

    public Friend(String id, String name, String age, String phone, String email, String userId) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.email = email;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Friend) {
            return this.id.equals(((Friend) obj).id);
        }
        return false;
    }

}
