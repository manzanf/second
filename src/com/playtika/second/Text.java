package com.playtika.second;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        Set<String> uniqueWords = new TreeSet<>(words());
        return new ArrayList<>(uniqueWords).subList(0, Math.min(n, uniqueWords.size()));
    }

    public Map<String, Integer> getFrequencies() {
        Map<String, Integer> frequencies = new HashMap<>();
        for (String word : words()) {
            Integer count = frequencies.get(word);
            frequencies.put(word, count == null ? 1 : count + 1);
        }
        return frequencies;
    }

    public Integer getLengthInChars() {
        Integer sumOfChars = 0;
        for (String word : words()) {
            sumOfChars += word.length();
        }
        return sumOfChars;
    }

    private List<String> words() {
        //String[] textWithOnlyWords = text.trim().replaceAll("[^a-zA-Z0-9 ]", "").toLowerCase().split("\\s+");
        /*Pattern pattern = Pattern.compile("[a-zA-Z0-9]+");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String word = matcher.group();
        }*/

        String[] textWithOnlyWords = text.toLowerCase().split("[^a-zA-Z0-9]+");
        List<String> textList = Arrays.asList(textWithOnlyWords);
        if (textList.size() > 0 && textList.get(0).equals("")) {
            textList = textList.subList(1, textList.size());
        }
        return textList;
    }
}
