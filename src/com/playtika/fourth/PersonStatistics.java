package com.playtika.fourth;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.Comparator.comparing;
import static java.util.function.Function.identity;
import static java.util.function.Predicate.isEqual;
import static java.util.stream.Collectors.*;

public class PersonStatistics {
    public static void main(String[] args) {
        List<Person> persons = Arrays.asList(
                new Person("Ivan", 31, "Kyiv"),
                new Person("Sidor", 14, "Kyiv"),
                new Person("Mark", 31, "Odessa"),
                new Person("Dave", 53, "NY"),
                new Person("Nick", 27, "Odessa"),
                new Person("Petr", 40, "Odessa"));
        System.out.println("Average age is " +
                persons.stream()
                        .mapToInt(Person::getAge)
                        .average()
                        .orElseThrow(() -> new IllegalArgumentException("Empty list of persons")));
        Person oldestPerson = persons.stream()
                .reduce((prevPerson, nextPerson) -> prevPerson.getAge() > nextPerson.getAge() ? prevPerson : nextPerson)
                .orElseThrow(() -> new IllegalArgumentException("Empty list of persons"));
        System.out.println("The oldest person is " + oldestPerson.getName());
        System.out.println("The number of people with name Dave is " +
                persons.stream()
                        .map(Person::getName)
                        .filter(isEqual("Dave"))
                        .count());
        System.out.println("The statistics about age to number of people with this age:\n " +
                persons.stream()
                        .collect(groupingBy(Person::getAge, counting())));
        System.out.println("Top city by population is " +
                persons.stream()
                        .map(Person::getCity)
                        .collect(groupingBy(identity(), counting()))
                        .entrySet().stream()
                        .max(comparing(Map.Entry::getValue))
                        .map(Map.Entry::getKey)
                        .orElseThrow(() -> new IllegalArgumentException("Empty list of persons")));
        System.out.println("Average adults age per city is:\n" +
                persons.stream()
                        .filter(person -> person.getAge() > 17)
                        .collect(groupingBy(Person::getCity, averagingInt(Person::getAge))));
    }
}
