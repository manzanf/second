package com.playtika.second;

import java.util.ArrayList;
import java.util.Arrays;

class WordsCounter {

    public int countWords(String text) {
        if (text == null || text.isEmpty()) {
            return 0;
        }

       //String[] textWithOnlyWords = text.trim().replaceAll("[^a-zA-Z0-9 ]+", "").toLowerCase().split("\\s+");
        String[] textWithOnlyWords = text.toLowerCase().split("[^a-zA-Z0-9]+");

        int count = textWithOnlyWords.length;
        if (textWithOnlyWords.length>0 && textWithOnlyWords[0].equals("")) {
            count = count - 1;
        }
        System.out.println(Arrays.toString(textWithOnlyWords));
        return count;
    }
}
