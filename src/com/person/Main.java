package com.person;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        List<Person> personList = generatePersons();
        personList = personList.stream()
                .sorted(Comparator.comparing(Person::getAge))
                .collect(Collectors.toList());

        Map<Gender, LinkedHashMap<Integer, List<Person>>> totalHashmap = personList.stream()
                .collect(Collectors.groupingBy(Person::getGender,
                        Collectors.groupingBy(Person::getAge,
                                LinkedHashMap::new, Collectors.toList())));

        System.out.println(totalHashmap);
    }

    public static String generateString() {

        Stream<Long> generateNumber = Stream.generate(() ->
                Math.round(Math.random() * 25 + 97))
                .limit(13);
        StringBuffer stringBuffer = new StringBuffer(13);
        generateNumber.map(each -> stringBuffer
                .append((char) each.intValue()))
                .count();

        String generatedString = stringBuffer.toString();
        String fullName = generatedString.substring(0, 1).toUpperCase()
                + generatedString.substring(1, 5) + " "
                + generatedString.substring(5, 6).toUpperCase()
                + generatedString.substring(6);

        return fullName;
    }

    public static List<Person> generatePersons() {
        List<Person> personStream6 = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            String fullName = generateString();
            int age = (int) Math.round(Math.random() * 100);
            Gender gender = Gender.values()[new Random().nextInt(Gender.values().length)];

            Person person = new Person(fullName, age, gender);
            personStream6.add(person);
        }
        return personStream6;
    }

}
