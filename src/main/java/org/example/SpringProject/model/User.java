package org.example.SpringProject.model;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String name;
    private String lastname;
    private byte age;

    public User() {
    }

    public User(Long id, String name, String lastname, byte age) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.age = age;
    }
}
