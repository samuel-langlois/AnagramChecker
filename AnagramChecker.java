package assign04;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

/**
 * 	This class creates a program that determines if two words are anagrams
 *  and finds the largest group of anagrams in a list of words. Two
 *  words are anagrams if they contain the same letters in the same frequency.
 *  
 * @author Samuel Langlois and Noah Garff. 
 * @version September 23, 2021
 */
public class AnagramChecker {
	/**
	 * Calls the insertionSort method with a comparator to sort the input string.
	 * 
	 * @param the string being sorted
	 * @return a string, that is sorted
	 */
	public static String sort(String word) {
		String finalWord = "";
		Character[] sortedWord = new Character[word.length()];
		for (int i = 0; i < word.length(); i++) {
			sortedWord[i] = word.charAt(i);
		}
		insertionSort(sortedWord, new CharComparator());

		for (int i = 0; i < word.length(); i++) {
			finalWord += sortedWord[i];
		}
		return finalWord;
	}

	/**
	 * Uses insertion sort to sort an array list of type T. Also takes in a
	 * comparator used to compare each item.
	 * 
	 * @param the array list and comparator.
	 */
	public static <T> void insertionSort(T[] arrayGroup, Comparator<? super T> cmp) {

		for (int i = 1; i < arrayGroup.length; i++) {
			for (int j = i; j > 0; j--) {
				if ((cmp.compare(arrayGroup[j], arrayGroup[j - 1]) < 0)) {
					T tempVal = arrayGroup[j];
					arrayGroup[j] = arrayGroup[j - 1];
					arrayGroup[j - 1] = tempVal;
				}
			}
		}

	}

	/**
	 * Returns true if the two input Strings are anagrams, false if not. Converts
	 * the two words to lowercase to do the comparison. Uses the sort method to do
	 * the comparison.
	 * 
	 * @param the two Strings being compared
	 * @return a boolean, true if the words are anagrams
	 */
	public static boolean areAnagrams(String word1, String word2) {
		String copyWord1 = word1.toLowerCase();
		String copyWord2 = word2.toLowerCase();

		return sort(copyWord1).equals(sort(copyWord2));

	}

	/**
	 * Returns the largest group of anagrams in the input string array.
	 * 
	 * 
	 * @param the string array being checked for anagrams.
	 * @return a new string array with the group of the largest amount of anagrams.
	 */
	public static String[] getLargestAnagramGroup(String[] anagramGroup) {
		String[] anagramSortedGroup = {};
		String[] copyAnagramGroup = anagramGroup;

		int length = 0;
		ArrayList<String[]> listOfGroupedAnagrams = new ArrayList<String[]>();

		insertionSort(anagramGroup, new WordComparator());
		for (int i = 0; i < anagramGroup.length; i++) {

			if (anagramGroup[i] != "") {
				String[] groupedAnagrams = { "" };

				length = 0;
				groupedAnagrams[length++] = copyAnagramGroup[i];
				
				for (int j = i + 1; j < anagramGroup.length; j++) {
					if (copyAnagramGroup[i] != "" || copyAnagramGroup[j] != "")
						if (areAnagrams(copyAnagramGroup[i], copyAnagramGroup[j])) {
							if (length == groupedAnagrams.length) {
								String[] tempWords = groupedAnagrams;
								groupedAnagrams = new String[groupedAnagrams.length + 1];
								for (int k = 0; k < tempWords.length; k++) {
									groupedAnagrams[k] = tempWords[k];
								}
							}
							groupedAnagrams[length++] = copyAnagramGroup[j];
							copyAnagramGroup[j] = "";
						}
				}
				listOfGroupedAnagrams.add(groupedAnagrams);
				copyAnagramGroup[i] = "";
			}
		}
		int biggestArraySize = 1;
		for (int i = 0; listOfGroupedAnagrams.size() > i; i++) {
			if (listOfGroupedAnagrams.get(i).length > biggestArraySize) {

				anagramSortedGroup = listOfGroupedAnagrams.get(i);
				biggestArraySize = listOfGroupedAnagrams.get(i).length;
			}
		}
		return anagramSortedGroup;

	}

	/**
	 * Returns the largest group of anagrams in the input String file. Receives
	 * information from readFile.
	 * 
	 * 
	 * @param the String (file) being checked for anagrams.
	 * @return a new string array with the group of the largest amount of anagrams.
	 */
	public static String[] getLargestAnagramGroup(String filename) {

		if (new File(filename).exists() || !(new File(filename).length() == 0)) {
			File file = new File(filename);

			String[] words = null;
			try {
				words = readFile(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			return getLargestAnagramGroup(words);
		}

		return new String[0];
	}

	/**
	 * 
	 * Reads a given file from getLargestAnagramGroup.
	 * 
	 * @param the File being checked for anagrams.
	 * @return a new string array filled with the strings in the given file.
	 */
	private static String[] readFile(File file) throws FileNotFoundException {
		Scanner s = new Scanner(file);
		String[] words = new String[10];
		int wordIndex = 0;

		while (s.hasNext()) {
			words[wordIndex] = s.next();
			wordIndex++;
			if (wordIndex == words.length - 1) {
				String[] tempWords = words;
				words = new String[words.length * 2];
				for (int i = 0; i < tempWords.length; i++) {
					words[i] = tempWords[i];
				}
			}
		}

		String[] tempCopy = new String[wordIndex];
		for (int i = 0; i <= wordIndex - 1; i++)
			tempCopy[i] = words[i];
		return words = tempCopy;

	}

	/**
	 * Comparator class that compares words.
	 * 
	 */
	protected static class WordComparator implements Comparator<String> {
		@Override
		public int compare(String o1, String o2) {

			return o1.compareTo(o2);
		}
	}

	/**
	 * Comparator class that compares characters.
	 * 
	 */
	protected static class CharComparator implements Comparator<Character> {
		@Override
		public int compare(Character o1, Character o2) {

			return o1.compareTo(o2);
		}
	}
}