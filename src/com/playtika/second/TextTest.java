package com.playtika.second;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.*;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;

public class TextTest {
    Text sentences = new Text("  one way,1 way -oNe*  ticket '");
    Text emptyText = new Text(",  ^\\ @\n %\t ");

    @Test
    public void sortedSetOfUniqueWordsIsReturned() {
        assertThat(sentences.getTopWords(4), contains("1", "one", "ticket", "way"));
    }

    @Test
    public void wordsWithDifferentCaseAreTheSame() {
        assertThat(new Text("One wAy onE Way").getTopWords(4), contains("one", "way"));
    }

    @Test
    public void cutSetIsReturnedIfNLessThanSetSize() {
        assertThat(sentences.getTopWords(2), hasSize(2));
    }

    @Test
    public void fullSetIsReturnedIfNMoreThanSetSize() {
        assertThat(sentences.getTopWords(5), hasSize(4));
    }

    @Test(expected = IllegalArgumentException.class)
    public void topCannotBeZero() throws Exception {
        sentences.getTopWords(0);
    }

    @Test
    public void topWordsIsEmptyForTextWithoutWords() {
        assertThat(emptyText.getTopWords(3), empty());
    }

    @Test
    public void wordFrequenciesAreReturnedForTextWithBothCases() {
        HashMap<String, Integer> expectedFrequencies = new HashMap<>();
        expectedFrequencies.put("one", 2);
        expectedFrequencies.put("way", 2);
        expectedFrequencies.put("ticket", 1);
        expectedFrequencies.put("1", 1);
        assertThat(sentences.getFrequencies(), is(equalTo(expectedFrequencies)));
    }

    @Test
    public void wordFrequenciesIsEmptyForTextWithoutWords() {
        assertThat(emptyText.getFrequencies().keySet(), hasSize(0));
    }

    @Test
    public void lengthInCharsIsReturnedForText() {
        assertThat(sentences.getLengthInChars(), CoreMatchers.is(19));
    }

    @Test
    public void lengthIsZeroForTextWithoutWords() {
        assertThat(emptyText.getLengthInChars(), equalTo(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullTextShouldNotBeProcessed() {
        new Text(null);
    }
}