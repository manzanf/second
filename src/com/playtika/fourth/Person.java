package com.playtika.fourth;

class Person {
    private final String name;
    private final int age;
    private final String city;

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
