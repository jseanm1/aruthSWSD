package com.aruth.swsd;

import com.aruth.swsd.exceptions.AruthSWSDException;

public class Main {

	/**
	 * @param args
	 * General tests
	 * Not to be confused with unit tests and integration tests 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Disambiguator disambiguator = new Disambiguator();
		
		String context = "අත් අඩංගුවට ගත් මුව මස් කිලෝ තිහක් විසි කරන්නද කියන්නේ. " +
							"උයාගෙන කාපුවාම මොකද වෙන්නේ. පණ පිටින් ඉන්න මුවෝ ටික රැක ගන්න බැරි උනා නම් " +
							"මස්වලට මොනවා උනාම මොකද හිතන්න ඇති.";
		
		String target = "මුව";
		
		try {
			System.out.println(disambiguator.getNounSenseUsingSimplifiedLesk(context, target));
			
		} catch (AruthSWSDException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			System.out.println(disambiguator.getNounSensesUsingOptimizedLesk(context, target));
		} catch (AruthSWSDException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
