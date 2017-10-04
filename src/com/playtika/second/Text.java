package com.playtika.second;

import java.util.*;

class Text {
    private final String text;

    Text(String text) {
        if (text == null) {
            throw new IllegalArgumentException("Text cannot be null");
        } else {
            this.text = text;
        }
    }

    public List<String> getTopWords(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("N should be positive");
        }
        if (text.isEmpty()) {
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
        for (String s : words()) {
            sumOfChars += s.length();
        }
        return sumOfChars;
    }

    private List<String> words() {
        //String[] textWithOnlyWords = text.trim().replaceAll("[^a-zA-Z0-9 ]", "").toLowerCase().split("\\s+");
        String[] textWithOnlyWords = text.toLowerCase().split("[^a-zA-Z0-9]+");
        List<String> textList = new ArrayList<>(Arrays.asList(textWithOnlyWords));
        if (textList.size() > 0 && textList.get(0).equals("")) {
            textList.remove(0);
        }
        return textList;
    }
}
