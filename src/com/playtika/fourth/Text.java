package com.playtika.fourth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.function.Function.*;
import static java.util.stream.Collectors.*;

class Text {
    private final String text;

    Text(String text) {
        if (text == null) {
            throw new IllegalArgumentException("Text cannot be null");
        }
        this.text = text;
    }

    public List<String> getTopWords(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("n should be positive");
        }
        if (words().isEmpty()) {
            return new ArrayList<>();
        }
        return words().stream()
                .distinct()
                .sorted()
                .limit(n)
                .collect(toList());
    }

    public Map<String, Long> getFrequencies() {
        return words().stream()
                 .collect(groupingBy(identity(), counting()));
    }

    public Integer getLengthInChars() {
        return words().stream()
                .mapToInt(String::length)
                .sum();
    }

    private List<String> words() {
        String[] textWithOnlyWords = text.toLowerCase().split("[^a-zA-Z0-9]+");
        return Arrays.stream(textWithOnlyWords)
                .filter(word -> !word.isEmpty())
                .collect(toList());
    }
}
