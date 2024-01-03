package com.example.sample.resource;

import java.util.Random;

public class RandomNameGenerator {

    private static final String[] FIRST_NAMES = {
        "James", "Mary", "John", "Patricia", "Robert", "Jennifer", "Michael", "Linda", 
        "William", "Elizabeth", "David", "Barbara", "Richard", "Susan", "Joseph", "Jessica"

    };

    private static final String[] LAST_NAMES = {
        "Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis", 
        "Rodriguez", "Martinez", "Hernandez", "Lopez", "Gonzalez", "Wilson", "Anderson", "Thomas"

    };

    private final Random random = new Random();

    public String generateFirstName() {
        int index = random.nextInt(FIRST_NAMES.length);
        return FIRST_NAMES[index];
    }

    public String generateLastName() {
        int index = random.nextInt(LAST_NAMES.length);
        return LAST_NAMES[index];
    }

    public String generateFullName() {
        return generateFirstName() + " " + generateLastName();
    }
}

