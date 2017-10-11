package com.playtika.fourth;

class Person {
    private String name;
    private int age;
    private String city;

    Person(String name, int age, String city) {
        this.name = name;
        this.age = age;
        this.city = city;
    }

    String getName() {
        return name;
    }

    int getAge() {
        return age;
    }

    String getCity() {
        return city;
    }

}
