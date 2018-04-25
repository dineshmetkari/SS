package com.stackroute.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import org.springframework.stereotype.Service;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.CoreAnnotations.LemmaAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.PartOfSpeechAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TextAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

@Service
public class PosTaggingService {

	protected StanfordCoreNLP pipeline;

	public PosTaggingService() {
		Properties props;
		props = new Properties();
		props.put("annotators", "tokenize, ssplit, pos");

		/*
		 * This is a pipeline that takes in a string and returns various analyzed linguistic forms. 
		 * The String is tokenized via a tokenizer (such as PTBTokenizerAnnotator), 
		 * and then other sequence model style annotation can be used to add things like lemmas, 
		 * POS tags, and named entities. These are returned as a list of CoreLabels. 
		 * Other analysis components build and store parse trees, dependency graphs, etc. 
		 * 
		 * This class is designed to apply multiple Annotators to an Annotation. 
		 * The idea is that you first build up the pipeline by adding Annotators, 
		 * and then you take the objects you wish to annotate and pass them in and 
		 * get in return a fully annotated object.
		 * 
		 *  StanfordCoreNLP loads a lot of models, so you probably
		 *  only want to do this once per execution
		 */
		this.pipeline = new StanfordCoreNLP(props);
	}

	public List<String> posTagging(String documentText)
	{
		//List<String> lemmas = new LinkedList<String>();
		// Create an empty Annotation just with the given text
		Annotation document = new Annotation(documentText);
		// run all Annotators on this text
		this.pipeline.annotate(document);
		// Iterate over all of the sentences found
		List<CoreMap> sentences = document.get(SentencesAnnotation.class);
		String queryWithPos = "";
		for(CoreMap sentence: sentences) {
			// Iterate over all tokens in a sentence
			for (CoreLabel token: sentence.get(TokensAnnotation.class)) {
				
				String word = token.get(TextAnnotation.class);
				// this is the POS tag of the token
				String pos = token.get(PartOfSpeechAnnotation.class);
				queryWithPos = queryWithPos + word + "/" + pos + " ";
			}
		}
		String[] queryPos = queryWithPos.split("\\s+");
		List<String> wordList = new ArrayList<String>(); 

		String words = "";
		for(int i=0;i<queryPos.length;i++){
			String word = queryPos[i];
			if(i==queryPos.length-1 && word.contains("NN")){
				words += word + " ";
				wordList.add(words);
			}

			else if(word.contains("NN")){
				words += word + " ";
			}

			else{
				wordList.add(words.trim());
				words = "";
				wordList.add(word);
			}
		}
		wordList.removeAll(Arrays.asList("", null));
		return wordList;
	}

//	public static void main(String[] args) {
//		System.out.println("Starting Stanford Lemmatizer");
//		String text = "defne how spring awt is working";		
//		PosTaggingService slem = new PosTaggingService();
//		System.out.println(slem.posTagging(text).toString());
//	}



}