package com.aruth.swsd.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class StopWordReader {
	
	/**
	 * @return List<String> the stopwords specified at "data/stopwords.wsd" in the library
	 * @throws IOException
	 */
	public List<String> getStopWords () throws IOException {
		FileInputStream fis = new FileInputStream(new File("data/stopwords.wsd"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		
		List<String> stopwords = new ArrayList<String>();
		
		String s = br.readLine();
		
		while (s != null) {
			stopwords.add(s);
			s = br.readLine();
		}
		
		return stopwords;
	}

}
