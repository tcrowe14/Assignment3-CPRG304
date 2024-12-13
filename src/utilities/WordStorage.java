package utilities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Class that stores a word, every ocurrence of that word, and files its found in as well as every line its found on.
 * 
 * @version 1.0
 * @author Abduallah Shaklaoon
 * @author Kris Senger
 * @author Taylor Crowe
 * @author Dallas Huppie
 */
public class WordStorage implements Serializable, Comparable<WordStorage>{

	/**
	 * Serialization ID
	 */
	private static final long serialVersionUID = -4109924332741632030L;
	/**
	 * The word being stored
	 */
	private String word = "";
	/**
	 * How many times the word is recorded
	 */
	private int freq = 0;
	/**
	 * A hashmap containing the name of the files and lines the word is found in
	 */
	private Map<String, ArrayList<Integer>> files = new LinkedHashMap<String, ArrayList<Integer>>();

	/**
	 * Constructor for the class
	 * @param readWord the main word of the class
	 * @param fileName the name of the file the word is found in
	 * @param line the line in the file the word is found in
	 */
	public WordStorage(String readWord, String fileName, int line) {
		this.word = readWord;
		ArrayList<Integer> lineAdd = new ArrayList<Integer>();
		lineAdd.add(line);
		files.put(fileName, lineAdd);
		freq+=1;
	}
	/**
	 * Constructor that only uses the word, used for comparisons
	 * @param readWord the word to compare
	 */
	public WordStorage(String readWord) {
		this.word = readWord;
	}
	/**
	 * Returns the word of the class
	 * @return word
	 */
	public String getWord() {
		return this.word;
	}
	/**
	 * Returns a string representation of the word according to the formatter it reads
	 * @param fmt the format to be used
	 * @return a string representation of the word matching the specified format
	 */
	public String displayType(String fmt) {
		if(fmt.equals("-pf")) {
			return this.displayFiles();
		}
		else if(fmt.equals("-pl")) {
			return this.displayFilesLines();
		}
		else {
			return this.displayAll();
		}
	}
	/**
	 * Returns the class  formatted to display the file names and lines for those files the word is found in
	 * @return the string representation of the class with lines, files
	 */
	public String displayFilesLines() {
		String toReturn = "Word: " + "==="+this.word+"=== found in file(s): " ;
		for (String file : files.keySet()) {
			  toReturn +=   file + " on lines: ";
			  for(int line : files.get(file)) {
				  toReturn += line +", ";
			  }

			}
		return toReturn;
	}
	/**
	 * Returns the class formatted to display the file names and lines for those files, and how many time the word is recorded
	 * @return the string representation of the class with lines, files and frequency
	 */
	public String displayAll() {
		String toReturn = "Word: " + "==="+this.word+"=== number of entries: "+ this.freq + ", found in file(s): " ;
		for (String file : files.keySet()) {
			  toReturn +=   file + " on lines: ";
			  for(int line : files.get(file)) {
				  toReturn += line +", ";
			  }

			}
		return toReturn;
	}
	/**
	 * Returns the class formatted to show the word and files it is in
	 * @return the string representation of the class with the word and files
	 */
	public String displayFiles() {
		String toReturn = "Word: " + "==="+this.word+"=== found in file(s): " ;
		for (String file : files.keySet()) {
			  toReturn += file + ", ";

			}
		return toReturn;
	}
	/**
	 * Adds an occurrence of a word to be stored in the hash map
	 * @param fileName the name of the file the word is found in
	 * @param lineNum the line number its on
	 */
	public void addOccurrence(String fileName, int lineNum) {
		if(this.files.containsKey(fileName)) {
			ArrayList<Integer> dataChange = files.get(fileName);
			dataChange.add(lineNum);
			files.put(fileName, dataChange);
			}
		else {
			ArrayList<Integer> toAdd = new ArrayList<Integer>();
			toAdd.add(lineNum);
			files.put(fileName, toAdd);
		}
		freq+=1;
	}
	
	/**
	 * Makes the class comparable by comparing the word being stored
	 */
	@Override
	public int compareTo(WordStorage that) {
		return this.getWord().compareTo( that.getWord() );
	}

}
