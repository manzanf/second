package com.playtika.second;

import java.util.*;

class Text {
    private final String text;
    private final ArrayList<String> textList;

    Text(String text) {
        this.text = text;
        if (text == null) {
            textList = null;
            return;
        }
        //String[] textWithOnlyWords = text.trim().replaceAll("[^a-zA-Z0-9 ]", "").toLowerCase().split("\\s+");
        String[] textWithOnlyWords = text.toLowerCase().split("[^a-zA-Z0-9]+");
        this.textList = new ArrayList<> (Arrays.asList(textWithOnlyWords));
        if (this.textList.size()>0 && this.textList.get(0).equals("")) {
            this.textList.remove(0);
        }
    }

    public Set<String> getTopWords (int N) {
        if (N < 1) {
            throw new IllegalArgumentException("N should be positive");
        }
        if (text == null) {
            return null;
        }
        if (text.isEmpty()) {
            return new TreeSet<>();
        }
        TreeSet<String> wordsSet = new TreeSet<>(textList);
        if (N < wordsSet.size()) {
            List<String> topWordsList = (new LinkedList<>(wordsSet)).subList(0, N);
            return new TreeSet<>(topWordsList);
        } else {
            return wordsSet;
        }
    }

    public Map<String,Integer> getFrequencies() {
        if (text == null) {
            return null;
        }
        HashMap<String,Integer> wordsFrequenciesMap = new HashMap<>();
        for (String t : textList) {
            if (wordsFrequenciesMap.containsKey(t)) {
                Integer value = wordsFrequenciesMap.get(t);
                wordsFrequenciesMap.put(t, ++value);
            } else {
                wordsFrequenciesMap.put(t, 1);
            }
        }
        return wordsFrequenciesMap;
    }

    public Integer getLengthInChars() {
        if (text == null) {
            return null;
        }
        Integer sumOfChars = 0;
        for (String s : textList) {
            sumOfChars = sumOfChars + s.length();
        }
        return sumOfChars;
    }

}
