package com.playtika.second;

import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;

import static org.junit.Assert.*;

public class TextTest {
    Text sentences = new Text("  one way,1 way -oNe*  ticket '");
    Text nullText = new Text(null);
    Text emptyText = new Text(",  ^\\ @\n %\t ");

    @Test
    public void sortedSetOfUniqueWordsIsReturned() {
        TreeSet<String> expectedSet = new TreeSet<>();
        expectedSet.add("1");
        expectedSet.add("one");
        expectedSet.add("ticket");
        expectedSet.add("way");

        assertEquals(expectedSet.size(),sentences.getTopWords(4).size());
        Iterator<String> iteratorExpected = expectedSet.iterator();
        for (String nextActual : sentences.getTopWords(4)) {
            String nextExpected = iteratorExpected.next();
            assertEquals(nextExpected, nextActual);
        }
    }

    @Test
    public void wordsWithDifferentCaseAreTheSame() {
        Text words = new Text("One wAy onE Way");
        TreeSet<String> expectedSet = new TreeSet<>();
        expectedSet.add("one");
        expectedSet.add("way");
        assertEquals(expectedSet.size(), words.getTopWords(4).size());
        Iterator<String> iteratorExpected = expectedSet.iterator();
        for (String nextActual : words.getTopWords(3)) {
            String nextExpected = iteratorExpected.next();
            assertEquals(nextExpected, nextActual);
        }
    }

    @Test
    public void cutSetIsReturnedIfNLessThanSetSize() {
        assertEquals(2,sentences.getTopWords(2).size());
    }

    @Test
    public void fullSetIsReturnedIfNMoreThanSetSize() {
        assertEquals(4,sentences.getTopWords(5).size());
    }

    @Test
    public void topCannotBeZero() throws Exception {
        try {
            sentences.getTopWords(0);
            fail("IAE expected");
        } catch (IllegalArgumentException e) {
            assertEquals("N should be positive", e.getMessage());
        }
    }

    @Test
    public void topWordsIsEmptyForTextWithoutWords() {
        assertEquals(0, emptyText.getTopWords(3).size());
    }

    @Test
    public void topWordsIsNullForNullText() {
        assertEquals(null, nullText.getTopWords(3));
    }

    @Test
    public void wordFrequenciesAreReturnedForTextWithBothCases() {
        HashMap<String, Integer> expectedFrequencies = new HashMap<>();
        expectedFrequencies.put("one", 2);
        expectedFrequencies.put("way", 2);
        expectedFrequencies.put("ticket",1);
        expectedFrequencies.put("1", 1);
        assertEquals(expectedFrequencies, sentences.getFrequencies());
    }

    @Test
    public void wordFrequenciesIsEmptyForTextWithoutWords() {
        HashMap<String, Integer> expectedFrequencies = new HashMap<>();
        assertEquals(expectedFrequencies, emptyText.getFrequencies());
    }

    @Test
    public void wordFrequenciesReturnsNullForNullText() {
        assertEquals(null, nullText.getFrequencies());
    }

    @Test
    public void lengthInCharsIsReturnedForText() {
        assertEquals(new Integer(19), sentences.getLengthInChars());
    }

    @Test
    public void lengthIsZeroForTextWithoutWords() {
        assertEquals(new Integer(0), emptyText.getLengthInChars());
    }

    @Test
    public void lengthIsNullForNullText() {
        assertEquals(null, nullText.getLengthInChars());
    }
}