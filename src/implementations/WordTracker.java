package implementations;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;

import utilities.WordStorage;
/**
 * Program that reads through a file, stores every word from the text file, and outputs according to a formatter
 * The output may also optionally be entered into a specified output text file
 * 
 * @version 1.1
 * @author Abduallah Shaklaoon
 * @author Kris Senger
 * @author Taylor Crowe
 * @author Dallas Huppie
 */
public class WordTracker implements Serializable{
	/**
	 * serialization ID
	 */
	private static final long serialVersionUID = 4568400013214735429L;
	/**
	 * The BSTree that stores the words read from a text file
	 */
	private BSTree<WordStorage> wordTree = new BSTree<WordStorage>();
    /**
     * Constructor for WordTracker
     * 
     * Checks if a file repository exists to load a previous occurrence of the BSTree from it
     */
	public WordTracker() {
		if((new File("repository.ser")).exists()) {
			this.deserializeTree();
		}
	}
	/**
	 * Getter for the BSTree
	 */
	public BSTree<WordStorage> getTree(){
		return wordTree;
	}
	/**
	 * Takes a string, and breaks it up by removing punctuation and returns each word in a string array
	 * 
	 * @param line the string (line) to be broken up and returned
	 * @return an array of strings containing every word from the input string
	 */
	public String[] lineBreaker(String line) {
		line = line.replaceAll("[^A-Za-z ]+", "").toLowerCase();
		String[] toReturn = line.split(" ");
		return toReturn;
	}
	/**
	 * Takes a 3 parameters and checks if the word is already in the BSTree. 
	 * If it is it adds it to the occurrence that word in the tree, otherwise it adds a new entry with that word 
	 * 
	 * @param readWord is the word that is being checked
	 * @param fileName is the name of the file 
	 * @param lineNum is the current line
	 */
	public void addWord(String readWord, String fileName, int lineNum) {
		WordStorage temp = new WordStorage(readWord);
		if(wordTree.contains(temp)) {
			((wordTree.search(temp)).getElement()).addOccurrence(fileName, lineNum);
		}
		else {
			wordTree.add(new WordStorage(readWord, fileName, lineNum));
		}
	}
	/**
	 * Takes a parameter to do determine what format to display every word stored in the BSTree.
	 * Then prints out every word stored in the BSTree on their own line.
	 * 
	 * @param fmt is the format specifier
	 */
	public void displayEntries(String fmt) {
		System.out.println("Displaying with the: " + fmt + " format!");
		utilities.Iterator<WordStorage> itTest = wordTree.inorderIterator();
		while(itTest.hasNext()) {
			WordStorage thing = itTest.next();
			System.out.println(thing.displayType(fmt));
		}
		
	}
	/**
	 * Takes 2 parameter to do determine what format to write every word stored in the BSTree.
	 * Then prints out every word stored in the BSTree on their own line in a file that is a parameter.
	 * 
	 * @param fmt is the format specifier, 
	 * @param fileName is the name of the text file being written to
	 */
	public void writeToFile(String fileName, String fmt) {
	    try {
	        FileWriter myWriter = new FileWriter(fileName);
			utilities.Iterator<WordStorage> itTest = wordTree.inorderIterator();
			while(itTest.hasNext()) {
				WordStorage thing = itTest.next();
				myWriter.write(thing.displayType(fmt)+"\n");
			}
	        myWriter.close();
	      } catch (IOException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	      }
	}
	/**
	 *  Serializes the BSTree to a file named repository.ser
	 */
	public void serializeTree()
	{
		try
		{
			ObjectOutputStream oos = new ObjectOutputStream(
					new FileOutputStream("repository.ser"));
			
				oos.writeObject(wordTree);
				oos.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 *  the BSTree from a file named repository.ser 
	 */
	@SuppressWarnings( "unchecked" )
	public void deserializeTree()
	{
		try
		{
			ObjectInputStream ois = new ObjectInputStream(
						new FileInputStream("repository.ser"));
			
			wordTree = (BSTree<WordStorage>) ois.readObject();
			ois.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Main function that reads through a file and stores the occurrence of each word in the BSTree 
	 * @param file is the name of the file being read
	 */
	public void TextParse(String file) {
		File textFile = new File(file);
		Scanner scanFile = null;
		int currentLine = 0;
		try 
		{
			scanFile = new Scanner( textFile );
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		while(scanFile.hasNext()) 
		{
			currentLine++;
			String textLine = scanFile.nextLine();
			for(String word : this.lineBreaker(textLine)) {
				this.addWord(word, file, currentLine);
			}
		}
	}
	
	/**
	 * Main driver for the program
	 * @param args
	 */
	public static void main(String[] args) {
		String input;
		String fmt;
		String output;
		WordTracker newTracker = new WordTracker();
		if(args.length == 3) {
			if(args[2].length() > 2 && args[2].substring(0, 2).equals("-f")) {
				input = args[0];
				fmt = args[1].toLowerCase();
				output = args[2].substring(2);
				if(fmt.equals("-pf") || fmt.equals("-po") || fmt.equals("-pl")) {
					newTracker.TextParse(input);
					newTracker.writeToFile(output, fmt);
					System.out.println("All done! ouput sent to: " +output);
				}else {
					System.out.print("Invalid print formatter!");
				}
			}else {
				System.out.print("Output file specifier error!");
			}

		}
		else if(args.length == 2){
			input = args[0];
			fmt = args[1].toLowerCase();
			if(fmt.equals("-pf") || fmt.equals("-po") || fmt.equals("-pl")) {
				newTracker.TextParse(input);
				newTracker.displayEntries(fmt);
				System.out.println("All done!");
			}else {
				System.out.print("Invalid print formatter!");
			}

		}
		else {
			System.out.print("Not enough arguments passed, nothing will run!");
		}
		newTracker.serializeTree();
	}
}
