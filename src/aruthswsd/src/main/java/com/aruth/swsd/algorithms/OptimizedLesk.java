package com.aruth.swsd.algorithms;

import java.io.IOException;
import java.util.List;

import com.aruth.swsd.exceptions.AruthSWSDException;
import com.aruth.swsd.utilities.Preprocessor;

public class OptimizedLesk {
	
	private List<String> pContext;
	private List<String> pGlosses;
	private List<String> parentGlosses;
	private List<String> childGlosses;
	
	private int size;
	
	private float A;
	
	public OptimizedLesk () {
		this.A = 0.5f;
	}
	
	public OptimizedLesk (float optimizedParam) {
		this.A = optimizedParam;
	}
	
	/**
	 * @param String context : the context for which the disambiguation is done
	 * @param List<String> glosses : glosses of the senses of the target polysemous word
	 * @return int location of the glosses list where the implied sense is stored
	 * This is actually the simplified lesk algorithm
	 * @throws IOException 
	 * @throws AruthSWSDException 
	 */
	public int getNounSense (String context, List<String> glosses) throws AruthSWSDException {
		// preprocess context
		pContext = Preprocessor.preprocessContext(context);
				
		// preprocess glosses
		pGlosses = Preprocessor.preprocessGlosses(glosses);
		
		int size = pGlosses.size();
		float overLapCount[] = new float[size];
		int maxIndex = 0;
		
		overLapCount = getPrimaryCount();
		
		maxIndex = getMaxIndex(overLapCount);
		
		return maxIndex;
	}

	/**
	 * @param String context : the context for which the disambiguation is done
	 * @param List<String> glosses : glosses of the senses of the target polysemous word
	 * @param List<String> parentGlosses : glosses of the immediate hypernyms of the senses
	 * of the target polysemous word
	 * @param List<String> childGlosses : glosses of the immediate hyponyms of the senses 
	 * of the target polysemous word
	 * @return int location of the glosses list where the implied sense is stored
	 * This is the optimized lesk algorithm
	 * @throws IOException 
	 * @throws AruthSWSDException 
	 */
	public int getNounSense (	String context, 
								List<String> glosses, 
								List<String> parentGlosses, 
								List<String> childGlosses) throws AruthSWSDException {
		int maxIndex = 0;
		
		// preprocess context
		this.pContext = Preprocessor.preprocessContext(context);

		// preprocess glosses
		this.pGlosses = Preprocessor.preprocessGlosses(glosses);
		
		//preprocess parent glosses
		this.parentGlosses = Preprocessor.preprocessGlosses(parentGlosses);
		
		//preprocess child glosses
		this.childGlosses = Preprocessor.preprocessGlosses(childGlosses);

		size = pGlosses.size();

		if (size != parentGlosses.size() || size != childGlosses.size()) {
			System.out.println("error! size mismatch");
			return 0;
		}
		// same as Simplified Lesk V1.0
		float primaryCount[] = getPrimaryCount();
		
		// new overlap count (secondary) for optimization
		float secondaryCount[] = getSecondaryCount();	
		
		float finalCount[] = new float[size];
		
		// Optimization happens here
		for (int i=0; i<size; i++) {
			finalCount[i] = primaryCount[i] + A * secondaryCount[i];
		}
		
		// find the optimized solution
		maxIndex = getMaxIndex(finalCount);
		
		return maxIndex;
	}
	
	private float[] getPrimaryCount () {
		// Same implementation from SimplifiedLeskV1		
		float primaryCount[] = new float[size];
		
		for (int i = 0; i < size; i++) {
			primaryCount[i] = 0;
		}

		for (int i = 0; i < size; i++) {
			for (String word : pGlosses.get(i).split(" ")) {
				if (pContext.contains(word)) {
					primaryCount[i]++;
				}
			}
		}
		
		return primaryCount;
	}
	
	private float[] getSecondaryCount() {
		// get parent and children senses and get the secondary overlapping count		
		float secondaryCount[] = new float[size];
		
		for (int i = 0; i < size; i++) {
			secondaryCount[i] = 0;
		}

		for (int i = 0; i < size; i++) {
			for (String word : parentGlosses.get(i).split(" ")) {
				if (pContext.contains(word)) {
					secondaryCount[i]++;
				}
			}
			
			for (String word : childGlosses.get(i).split(" ")) {
				if (pContext.contains(word)) {
					secondaryCount[i]++;
				}
			}
		}
		
		return secondaryCount;
	}
	
	private int getMaxIndex (float countArray[]) {
		// find the optimized solution
		float maxValue = 0;
		int maxIndex = 0;
		
		for (int i=0; i<size; i++) {
			if (countArray[i]>maxValue) {
				maxValue = countArray[i];
				maxIndex = i;
			}
		}

		return maxIndex;
	}
}
