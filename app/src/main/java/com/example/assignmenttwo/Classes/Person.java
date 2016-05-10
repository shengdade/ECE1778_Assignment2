package com.example.assignmenttwo.Classes;

import java.io.Serializable;

public class Person implements Serializable {
    public String name;
    public int age;
    public String movie;

    public Person(){
        super();
    }

    public Person(String Name, int Age, String Movie) {
        super();
        this.name = Name;
        this.age = Age;
        this.movie = Movie;
    }
}
