package utilities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class WordStorage implements Serializable, Comparable<WordStorage>{

	private static final long serialVersionUID = -4109924332741632030L;
	private String word = "";
	private int freq = 0;
	private Map<String, ArrayList<Integer>> files = new LinkedHashMap<String, ArrayList<Integer>>();
	
	public WordStorage(String readWord, String fileName, int line) {
		this.word = readWord;
		ArrayList<Integer> lineAdd = new ArrayList<Integer>();
		lineAdd.add(line);
		files.put(fileName, lineAdd);
		freq+=1;
	}
	public WordStorage(String readWord) {
		this.word = readWord;
	}
	
	public String getWord() {
		return this.word;
	}
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
	public String displayFiles() {
		String toReturn = "Word: " + "==="+this.word+"=== found in file(s): " ;
		for (String file : files.keySet()) {
			  toReturn += file + ", ";

			}
		return toReturn;
	}
	public void addOccurence(String fileName, int lineNum) {
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

	@Override
	public int compareTo(WordStorage that) {
		return this.getWord().compareTo( that.getWord() );
	}

}
