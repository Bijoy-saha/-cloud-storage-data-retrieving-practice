package com.example.mp;

public class model {
    String name,id,email,description;
    public model(){}
    public model(String id,String name,String email,String description)
    {
        this.id=id;
        this.name=name;
        this.email=email;
        this.description=description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
