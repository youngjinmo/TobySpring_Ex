package com.bomstart.tobyspring.user.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    @JsonProperty
    String id;
    @JsonProperty
    String name;
    @JsonProperty
    String password;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
