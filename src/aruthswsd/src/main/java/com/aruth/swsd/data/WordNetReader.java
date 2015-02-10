package com.aruth.swsd.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.aruth.swsd.exceptions.AruthSWSDException;
import com.aruth.swsd.exceptions.ErrorCodes;

import net.sf.extjwnl.JWNLException;
import net.sf.extjwnl.data.IndexWord;
import net.sf.extjwnl.data.POS;
import net.sf.extjwnl.dictionary.Dictionary;

public class WordNetReader {

	private final static String WORDNETPATH = "conf/file_properties.xml";

	/**
	 * @param String
	 *            noun : the noun to be retrieved from the Sinhala WordNet.
	 *            unicode character encoding
	 * @return IndexWord word
	 * @throws AruthSWSDException
	 * @throws JWNLException 
	 */
	public static IndexWord getNounAsIndexWord(String noun)
			throws AruthSWSDException {
		
		Dictionary dictionary = getDictionary();
		IndexWord word = null;

		try {
			word = dictionary.getIndexWord(POS.NOUN, noun);
			
		} catch (JWNLException e) {
			String errorMessage = "Exception occured trying to read WordNet :" + e.getLocalizedMessage();
			System.err.println(errorMessage);
			
			throw new AruthSWSDException(ErrorCodes.CANNOT_READ_DICTIONARY, errorMessage, null);
		}
		
		if (word == null) {
			String errorMessage = "Target word " + noun + "not found in WordNet";
			System.err.println(errorMessage);
			
			throw new AruthSWSDException(ErrorCodes.WORD_NOT_FOUND, errorMessage, null);
		}
		
		return word;
	}

	/**
	 * 
	 * @return Dictionary
	 * @throws AruthSWSDException
	 */
	private static Dictionary getDictionary() throws AruthSWSDException {
		FileInputStream inputStream;
		Dictionary dictionary = null;

		try {
			inputStream = new FileInputStream(WORDNETPATH);
			dictionary = Dictionary.getInstance(inputStream);

		} catch (FileNotFoundException e) {
			String errorMessage = "Cannot read files at " + WORDNETPATH;
			System.err.println(errorMessage);

			throw new AruthSWSDException(ErrorCodes.CANNOT_OPEN_FILE,
					errorMessage, null);

		} catch (JWNLException e) {
			String errorMessage = "Cannot create dictionary instance";
			System.err.println(errorMessage);

			throw new AruthSWSDException(ErrorCodes.CANNOT_CREATE_DICTIONARY,
					errorMessage, null);

		}

		return dictionary;
	}
}
