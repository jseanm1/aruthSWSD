package com.aruth.swsd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.sf.extjwnl.data.IndexWord;
import net.sf.extjwnl.data.PointerUtils;
import net.sf.extjwnl.data.Synset;
import net.sf.extjwnl.data.list.PointerTargetNodeList;

import com.aruth.swsd.algorithms.OptimizedLesk;
import com.aruth.swsd.data.WordNetReader;

public class Disambiguator {

	/**
	 * Find the sense of the polysemous target word as implied in the context using the
	 * simplified lesk algorithm
	 * @param String context : the context
	 * @param String target : the polysemous word to be disambiguated
	 * @return String the sense of the polysemous word implied in the context
	 */
	public String getNounSenseUsingSimplifiedLesk (String context, String target) {
		IndexWord word = WordNetReader.getNounAsIndexWord(target);
		List <String> glosses;
		String sense;
		String gloss;
		int senseIndex;
		
		if (word == null) {
			String error = "no match found for noun " + target;
			System.out.println(error);
			return error;
		} 
		
		glosses = getGlosses(word);
		
		try {
			senseIndex = new OptimizedLesk().getNounSense(context, glosses);
			gloss = glosses.get(senseIndex);
			sense = getSenseOfAGloss(gloss);
			return sense;
		} catch (IOException e) {
			System.out.println(e.getLocalizedMessage());
			return null;
		}		
	}
	
	/**
	 * Find the sense of the polysemous target word as implied in the context using the
	 * the optimized lesk algorithm
	 * @param String context : the context
	 * @param String target : the polysemous word to be disambiguated
	 * @return String the sense of the polysemous word implied in the context
	 */
	public String getNounSensesUsingOptimizedLesk (String context, String target) {
		IndexWord word = WordNetReader.getNounAsIndexWord(target);
		List <String> glosses, parentGlosses, childGlosses;
		String sense;
		String gloss;
		int senseIndex;
 
		if (word == null) {
			String error = "no match found for noun " + target;
			System.out.println(error);
			return error;
		} 
		
		glosses = getGlosses(word);
		parentGlosses = getParentGlosses(word);
		childGlosses = getChildGosses(word);
		
		try {
			senseIndex = new OptimizedLesk().getNounSense(context, glosses, parentGlosses, childGlosses);
			gloss = glosses.get(senseIndex);
			sense = getSenseOfAGloss(gloss);
			return sense;
		} catch (IOException e) {
			System.out.println(e.getLocalizedMessage());
			return null;
		}
	}
	
	/*
	 * returns a List of glosses for the senses of the IndexWord word
	 */
	private List<String> getGlosses(IndexWord word) {
		List <Synset> synset = word.getSenses();
		List <String> glosses = new ArrayList<>();
		
		for (Synset syn : synset) {
			glosses.add(syn.getGloss());
		}
		
		return glosses;
	}
	
	/*
	 * returns a List of glosses of the senses of the parent of the Indexed word
	 */
	private List<String> getParentGlosses(IndexWord word) {
		List <Synset> synset = word.getSenses();
		List <String> parentGlosses = new ArrayList<>();
		PointerTargetNodeList hypernym;
		
		for (Synset s : synset) {
			hypernym = PointerUtils.getDirectHypernyms(s);
			
			if (hypernym.size() == 0) {
				System.out.println("No hypernyms for sense " + s.getGloss() + " in Sinhala WordNet");
				parentGlosses.add("");
			} else {
				// only consider the immidiate synset
				parentGlosses.add(hypernym.get(0).getSynset().getGloss());
			}
		}
		
		return parentGlosses;		
	}
	
	/*
	 * returns a List of glosses of the senses of the child of the Indexed word
	 * note : glosses are converted into the lower case to be compared 
	 */
	private List<String> getChildGosses(IndexWord word) {
		List <Synset> synset = word.getSenses();
		List <String> childGlosses = new ArrayList<>();
		PointerTargetNodeList hyponym;
		
		for (Synset s : synset) {
			hyponym = PointerUtils.getDirectHyponyms(s);
			
			if (hyponym.size() == 0) {
				System.out.println("No hyponyms for sense " + s.getGloss() + " in Sinhala WordNet");
				childGlosses.add("");
			} else {
				// only consider the immidiate synset
				childGlosses.add(hyponym.get(0).getSynset().getGloss());
			}
		}
		
		return childGlosses;		
	}
	
	private String[] devideGloss(String gloss)
	{
		String[] string=gloss.split("\\|");
		return string;
		
	}
	
	private String getSenseOfAGloss(String givenGloss)
	{
		String[] gloss=devideGloss(givenGloss);
		String sense=gloss[0];
		return sense;
		
	}
}
