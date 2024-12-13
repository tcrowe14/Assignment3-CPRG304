package implementations;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;

import utilities.WordStorage;

public class WordTracker implements Serializable{
	private static final long serialVersionUID = 4568400013214735429L;
	public BSTree<WordStorage> wordTree = new BSTree<WordStorage>();
	public WordTracker() {
		if((new File("res/repository.ser")).exists()) {
			this.deserializeTree();
		}
	}
	public BSTree<WordStorage> getTree(){
		return wordTree;
	}
	
	public String[] lineBreaker(String line) {
		line = line.replaceAll("[^A-Za-z ]+", "").toLowerCase();
		String[] toReturn = line.split(" ");
		return toReturn;
	}
	public void addWord(String readWord, String fileName, int lineNum) {
		WordStorage temp = new WordStorage(readWord);
		if(wordTree.contains(temp)) {
			((wordTree.search(temp)).getElement()).addOccurence(fileName, lineNum);
		}
		else {
			wordTree.add(new WordStorage(readWord, fileName, lineNum));
		}
	}
	public void displayEntries(String fmt) {
		utilities.Iterator<WordStorage> itTest = wordTree.inorderIterator();
		while(itTest.hasNext()) {
			WordStorage thing = itTest.next();
			System.out.println(thing.displayType(fmt));
		}
	}
	public void serializeTree()
	{
		try
		{
			ObjectOutputStream oos = new ObjectOutputStream(
					new FileOutputStream("res/repository.ser"));
			
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
	
	@SuppressWarnings( "unchecked" )
	public void deserializeTree()
	{
		try
		{
			ObjectInputStream ois = new ObjectInputStream(
						new FileInputStream("res/repository.ser"));
			
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
	public void TextParse(String file) {
		File textFile = new File("res/"+file);
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
	public static void main(String[] args) {
		WordTracker newTracker = new WordTracker();
		//newTracker.TextParse("test2.txt");
		newTracker.displayEntries("-pf");
		System.out.println("===================");
		newTracker.displayEntries("-pl");
		System.out.println("===================");
		newTracker.displayEntries("-po");
		newTracker.serializeTree();
	}
}
