package com.aruth.swsd;

import static org.junit.Assert.*;

import java.util.List;



import junit.framework.TestCase;


import  com.aruth.swsd.exceptions.AruthSWSDException;


import com.aruth.swsd.utilities.Preprocessor;

public class LeskPreprocessorTestCases  extends TestCase{

	private final String context1 = "අත් අඩංගුවට ගත් මුව මස් කිලෝ තිහක් විසි කරන්නද කියන්නේ. " +
										"උයාගෙන කාපුවාම මොකද වෙන්නේ. පණ පිටින් ඉන්න මුවෝ ටික රැක ගන්න " +
										"බැරි උනා නම් මස්වලට මොනවා උනාම මොකද හිතන්න ඇති. ";

	private final String context2 = "අත් අඩංගුවට ගත් මුව මස් කිලෝ තිහක් වි[සි කරන්නද කියන්නේ. " +
										"උයාගෙන කාපුවාම මොකද වෙන්නේ. පණ පිටින් ඉන්න මුවෝ ටික රැක ගන්න " +
										"බැරි උනා නම් මස්වලට මොනවා උනාම මොකද හිතන්න ඇති. ";

	/*
	 * Test if LeskPreprocessor.preprocessContext is working properly
	 */

	public void testPreprocessContext() {
		System.out.println("Executing LeskPreprocessorTestCases.testPreprocessContext");

		test(context1);
		test(context2);

		System.out.println("Succesfully executed LeskPreprocessorTestCases.testPreprocessContext");
	}

	public void test (String context) {
		try {
			// should not throw an exception
            List<String> pContext = Preprocessor.preprocessContext(context);


			// all words in the list should be from the original text
			for (String s : pContext) {
				assertTrue(context.contains(s));
			}

			// all unnecessary symbols should be removed
			for (String s : pContext) {
                assertFalse(s.contains("["));
				assertFalse(s.contains("]"));
				assertFalse(s.contains("\""));
				assertFalse(s.contains(","));
				assertFalse(s.contains("|"));
			}

		} catch (AruthSWSDException e) {
			System.out.println("Test failed: " + e.getErrorCode() + " " + e.getMessage());
			assertFalse(true);
		}
	}

}
