package com.aruth.swsd.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.aruth.swsd.exceptions.AruthSWSDException;
import com.aruth.swsd.exceptions.ErrorCodes;

public class StopWordReader {
	
	private static final String STOPWORD_FILE = "data/stopwords.wsd";
	/**
	 * @return List<String> the stopwords specified at "data/stopwords.wsd" in the library
	 * @throws AruthSWSDException 
	 * @throws IOException
	 */
	public List<String> getStopWords () throws AruthSWSDException {
		FileInputStream fis;
		try {
			fis = new FileInputStream(new File(STOPWORD_FILE));
			
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			
			List<String> stopwords = new ArrayList<String>();
			
			String s = br.readLine();
			
			while (s != null) {
				stopwords.add(s);
				s = br.readLine();
			}
			
			return stopwords;
			
		} catch (FileNotFoundException e) {
			String errorMessage = "Cannot open file at " + STOPWORD_FILE;
			System.out.println(errorMessage);
			
			throw new AruthSWSDException(ErrorCodes.CANNOT_OPEN_FILE, errorMessage, null);
			
		} catch (IOException e) {
			String errorMessage = "Cannot read file at " +STOPWORD_FILE;
			System.err.println(errorMessage);
			
			throw new AruthSWSDException(ErrorCodes.CANNOT_READ_FILE, errorMessage, null);
		}		
	}
}
