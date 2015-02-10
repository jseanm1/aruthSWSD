package com.aruth.swsd;

import java.util.List;

import junit.framework.TestCase;

import com.aruth.swsd.data.StopWordReader;

import com.aruth.swsd.exceptions.AruthSWSDException;

public class StopWordReaderTestCases extends TestCase {

	/*
	 * Test if StopWordReader.getStopWords method is working properly
	 */

	public void testGetStopWords() {

		try {
			System.out
					.println("Executing StopWOrdReaderTestCases.testGetStopWords");

			// should not throw an exception
			List<String> stopWords = new StopWordReader().getStopWords();

			// should not be null
			assertTrue(stopWords != null);

			// should not be empty
			assertTrue(stopWords.size() > 0);
			System.out
					.println("Succesfully executed StopWOrdReaderTestCases.testGetStopWords");

		} catch (AruthSWSDException e) {
			System.err.println("Test failed: " + e.getErrorCode() + " "
					+ e.getMessage());
			assertFalse(true);
		}
	}
}
