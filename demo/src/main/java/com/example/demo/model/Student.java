package com.example.demo.model;

import net.minidev.json.annotate.JsonIgnore;

import java.util.UUID;

public class Student {
    private UUID id;
    private String name;

    public Student(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
