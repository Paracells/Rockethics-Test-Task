package ru.paracells.rockethics.model;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Cat {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private String name;
    private Integer age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Cat(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Cat() {
    }
}
