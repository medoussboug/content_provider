package com.example.crud1_content_provider.data.entities;

public class Person {
    private long id;
    private String name;
    private String major;

    public Person(long id, String name, String major) {
        this.id = id;
        this.name = name;
        this.major = major;
    }

    public Person() {}

    public Person(String name, String major) {
        this.name = name;
        this.major = major;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", major='" + major + '\'' +
                "}\n";
    }
}
