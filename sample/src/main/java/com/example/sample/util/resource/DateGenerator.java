package com.example.sample.util.resource;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

// this class is used to generate random dates
public class DateGenerator {

    // this function is used to generate a list of random dates
    public List<LocalDateTime> generateRandomDates(int numberOfDates) {
        LocalDateTime threeMonthsAgo = LocalDateTime.now().minusMonths(3);
        LocalDateTime now = LocalDateTime.now();

        List<LocalDateTime> randomDates = new ArrayList<>();

        for (int i = 0; i < numberOfDates; i++) {
            randomDates.add(getRandomDateBetween(threeMonthsAgo, now));
        }

        return randomDates;
    }

    // this function is used to generate random dates between a period of time. Current demand is 3 months.
    private LocalDateTime getRandomDateBetween(LocalDateTime start, LocalDateTime end) {
        long startSeconds = start.toEpochSecond(ZoneOffset.UTC);
        long endSeconds = end.toEpochSecond(ZoneOffset.UTC);
        long randomSeconds = ThreadLocalRandom.current().nextLong(startSeconds, endSeconds);

        return LocalDateTime.ofEpochSecond(randomSeconds, 0, ZoneOffset.UTC);
    }
}
