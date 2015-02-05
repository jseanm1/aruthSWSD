package com.aruth.swsd.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import net.sf.extjwnl.JWNLException;
import net.sf.extjwnl.data.IndexWord;
import net.sf.extjwnl.data.POS;
import net.sf.extjwnl.dictionary.Dictionary;

public class WordNetReader {

	private final static String WORDNETPATH = "conf/file_properties.xml";

	/**
	 * @param String noun : the noun to be retrieved from the Sinhala WordNet. unicode character encoding
	 * @return IndexWord word
	 */
	public static IndexWord getNounAsIndexWord (String noun) {
		Dictionary dictionary = getDictionary();
		IndexWord word = null;
		
		try {
			word = dictionary.getIndexWord(POS.NOUN, noun);	
		} catch (JWNLException e) {
			System.out.println(e.getLocalizedMessage());
		}
		
		return word;
	}
	
	/**
	 * 
	 * @return Dictionary
	 */
	private static Dictionary getDictionary() {
		FileInputStream inputStream;
		Dictionary dictionary = null;
		
		try {
			inputStream = new FileInputStream(WORDNETPATH);
			dictionary = Dictionary.getInstance(inputStream);
		} catch (FileNotFoundException e) {
			System.out.println(e.getLocalizedMessage());
		} catch (JWNLException e) {
			System.out.println(e.getLocalizedMessage());
		}
		
		return dictionary;
	}
}
