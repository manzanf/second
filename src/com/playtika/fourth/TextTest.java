package com.playtika.fourth;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

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
        HashMap<String, Long> expectedFrequencies = new HashMap<>();
        expectedFrequencies.put("one", 2L);
        expectedFrequencies.put("way", 2L);
        expectedFrequencies.put("ticket", 1L);
        expectedFrequencies.put("1", 1L);
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