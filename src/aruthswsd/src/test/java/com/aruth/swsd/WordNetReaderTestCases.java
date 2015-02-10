package com.aruth.swsd;

import static org.junit.Assert.*;

import junit.framework.TestCase;
import net.sf.extjwnl.data.IndexWord;

import org.junit.Test;

import com.aruth.swsd.data.WordNetReader;
import  com.aruth.swsd.exceptions.AruthSWSDException;
import  com.aruth.swsd.exceptions.ErrorCodes;

public class WordNetReaderTestCases extends TestCase{
	
	private final String SINHALA_WORD_CORRECT = "මුව";
	private final String SINHALA_WORD_WRONG = "මුවමුව";
	private final String ENGLISH_WORD = "glass";
	private final String NONSE	= "24TDSFVමුවEV";
	
	/*
	 * Test if WordNetReader.getNounAsIndexWord method is working properly
	 */

	public void testGetNounAsIndexWord1 () {
		try {
			System.out.println("Executing WordNetReaderTestCases.testGetNounAsIndexWord1");
			
			// should not throw an exception
			IndexWord indexWord = WordNetReader.getNounAsIndexWord(SINHALA_WORD_CORRECT);
			
			//should be not null
			assertTrue(indexWord != null);
			System.out.println("Succesfully executed WordNetReaderTestCases.testGetNounAsIndexWord");
			
		} catch (AruthSWSDException e) {
			System.err.println("Test failed: " + e.getErrorCode() + " " + e.getMessage());
			assertFalse(true);
		}
	}
	

	public void testGetNounAsIndexWord2 () {
		try {
			System.out.println("Executing WordNetReaderTestCases.testGetNounAsIndexWord2");
			
			// should throw an exception
			@SuppressWarnings(value = { "unused" })
			IndexWord indexWord = WordNetReader.getNounAsIndexWord(SINHALA_WORD_WRONG);
			
			System.err.println("Test failed");
			assertFalse(true);
			
		} catch (AruthSWSDException e) {
			assertTrue(e.getErrorCode() == ErrorCodes.WORD_NOT_FOUND);
			System.out.println("Succesfully executed WordNetReaderTestCases.testGetNounAsIndexWord");
		}
	}
	

	public void testGetNounAsIndexWord3 () {
		try {
			System.out.println("Executing WordNetReaderTestCases.testGetNounAsIndexWord3");
			
			// should throw an exception
			@SuppressWarnings(value = { "unused" })
			IndexWord indexWord = WordNetReader.getNounAsIndexWord(ENGLISH_WORD);
			
			System.err.println("Test failed");
			assertFalse(true);
			
		} catch (AruthSWSDException e) {
			assertTrue(e.getErrorCode() == ErrorCodes.WORD_NOT_FOUND);
			System.out.println("Succesfully executed WordNetReaderTestCases.testGetNounAsIndexWord");
		}
	}
	

	public void testGetNounAsIndexWord4 () {
		try {
			System.out.println("Executing WordNetReaderTestCases.testGetNounAsIndexWord3");
			
			// should throw an exception
			@SuppressWarnings(value = { "unused" })
			IndexWord indexWord = WordNetReader.getNounAsIndexWord(NONSE);
			
			System.err.println("Test failed");
			assertFalse(true);
			
		} catch (AruthSWSDException e) {
			assertTrue(e.getErrorCode() == ErrorCodes.WORD_NOT_FOUND);
			System.out.println("Succesfully executed WordNetReaderTestCases.testGetNounAsIndexWord");
		}
	}

}
