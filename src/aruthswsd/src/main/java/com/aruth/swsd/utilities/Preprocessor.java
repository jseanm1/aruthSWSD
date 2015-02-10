package com.aruth.swsd.utilities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.aruth.swsd.data.StopWordReader;
import com.aruth.swsd.exceptions.AruthSWSDException;

public class Preprocessor {

	/**
	 * 
	 * @param String context : the context as a String
	 * @return List<String> the preprocessed context as a list of words
	 * @throws IOException
	 * @throws AruthSWSDException 
	 */
	public static List<String> preprocessContext (String context) throws AruthSWSDException {
		// get stop words
		List<String> stopWords = new StopWordReader().getStopWords();
		
		// replace other characters and get the user context as a list of Strings
		List<String> pContextTemp = Arrays.asList(context.replaceAll("[\\[\\]|\".,]", " ").split(" "));
		List<String> pContext = new ArrayList<String>();
			
		for (String word : pContextTemp) {
			if (!stopWords.contains(word)) {
				pContext.add(word);
			}
		}
				
		return pContext;
	}
	
	/**
	 * 
	 * @param List<String> glosses : the gloss list
	 * @return List<String> preprocessed gloss list
	 */
	public static List<String> preprocessGlosses (List<String> glosses) {
		// replace other characters and get the glosses as a list of Strings
		List<String> pGlosses = new ArrayList<String>();
			
		for (String gloss : glosses) {
			pGlosses.add(gloss.trim().replaceAll(" +", " ").replaceAll("[\\[\\]|\".,]", " ").trim().replaceAll(" +", " "));
		}
		
		return pGlosses;
	}
}
