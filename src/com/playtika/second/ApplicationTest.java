package com.playtika.second;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class ApplicationTest {

    private static Application application;

    @BeforeClass
    public static void executedBeforeEachTest() {
        application = new Application();
    }

    @Test
    public void textIsSplitByWhitespace() {
        int wordCount = application.countWords("I have 5 dollars");
        assertEquals(4, wordCount);
    }

    @Test
    public void emptyTextIsOneToken() throws Exception {
        int wordCount = application.countWords("");
        assertEquals(1,wordCount);
    }

    @Test
    public void oneWordTextIsSplit() throws Exception {
        int wordCount = application.countWords("Word");
        assertEquals(1, wordCount);
    }

    @Test
    public void textWithSpecialSymbolsIsSplit() {
        int wordCount = application.countWords("#@ ^!' ~");
        assertEquals(3,wordCount);
    }

    @Test
    public void textWithLineBreakIsSplit() {
        int wordCount = application.countWords("Line breaker\nwas here");
        assertEquals(3,wordCount);
    }

    @Test
    public void textWithSpacesOnlyIsSplit() throws Exception {
        int wordCount = application.countWords(" ");
        assertEquals(0,wordCount);
    }

    @Test
    public void textWithTabIsSplit() throws Exception {
        int wordCount = application.countWords("\t");
        assertEquals(1,wordCount);
    }

    @Test
    public void textWithFormFeedIsSplit() throws Exception {
        int wordCount = application.countWords("\f");
        assertEquals(1,wordCount);
    }
}