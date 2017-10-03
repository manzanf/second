package com.playtika.second;

import java.util.*;

class Text {
    private final String text;
    // private final ArrayList<String> textList;

    Text(String text) {
        if (text.equals(null)) {
            throw new IllegalArgumentException("Text cannot be null");
        } else {
            this.text = text;
        }
        /*//String[] textWithOnlyWords = text.trim().replaceAll("[^a-zA-Z0-9 ]", "").toLowerCase().split("\\s+");
        String[] textWithOnlyWords = text.toLowerCase().split("[^a-zA-Z0-9]+");
        this.textList = new ArrayList<>(Arrays.asList(textWithOnlyWords));
        if (this.textList.size() > 0 && this.textList.get(0).equals("")) {
            this.textList.remove(0);
        }*/
    }

    public Set<String> getTopWords(int n) {

        if (n < 1) {
            throw new IllegalArgumentException("N should be positive");
        }
        if (text.isEmpty()) {
            return new TreeSet<>();
        }
        TreeSet<String> wordsSet = new TreeSet<>(words());
       /* if (n < wordsSet.size()) {
            List<String> topWordsList = (new LinkedList<>(wordsSet)).subList(0, n);
            return new TreeSet<>(topWordsList);
        } else {
            return wordsSet;
        } */
        //wordsList.subList(0, Math.min(n, wordsList.size()));
        List<String> topWordsList = (new LinkedList<>(wordsSet)).subList(0, Math.min(n, wordsSet.size()));
        return new TreeSet<>(topWordsList);
    }

    public Map<String, Integer> getFrequencies() {
        HashMap<String, Integer> frequencies = new HashMap<>();
        //Integer count = frequencies.get(word); frequencies.put(word, count == null ? 1 : count + 1);
        for (String word : words()) {
            Integer count = frequencies.get(word);
            frequencies.put(word, count == null ? 1 : count + 1);
            /*if (frequencies.containsKey(word)) {
                Integer count = frequencies.get(word);
                frequencies.put(word, ++count);
            } else {
                frequencies.put(word, 1);
            }*/
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
        ArrayList<String> textList = new ArrayList<>(Arrays.asList(textWithOnlyWords));
        if (textList.size() > 0 && textList.get(0).equals("")) {
            textList.remove(0);
        }
        return textList;
    }
}
