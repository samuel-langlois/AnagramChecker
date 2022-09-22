package assign04;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 * @author Samuel Langlois and Noah Garff
 **/
public class TimeAnagramChecker {
	private static Random rand;
	//private static Comparator<? super Integer> cmp = new IntegerComparator();

	public static void main(String[] args) {
		rand = new Random();
		rand.setSeed(System.currentTimeMillis());

		// Do 10000 lookups and use the average running time.
		int timesToLoop = 100000;
		String sortWord = "abcde";
		String[] anagramGroup;
		for (int n = 1000; n <= 20000; n += 1000) {
//			sortWord = randomN(n);
			anagramGroup = randomNArray(n);

			long startTime, midpointTime, stopTime;

			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000) {
			}

			startTime = System.nanoTime();

			for (int i = 0; i < timesToLoop; i++) {

//			AnagramChecker.getLargestAnagramGroup(anagramGroup);
//			AnagramChecker.areAnagrams(sortWord, sortWord);
			}

			midpointTime = System.nanoTime();

			for (int i = 0; i < timesToLoop; i++) {
			Arrays.sort(anagramGroup, new WordComparator());
//			AnagramChecker.sort(sortWord);
			}

			stopTime = System.nanoTime();

			double getLAGTime = (midpointTime - startTime) / (double) timesToLoop;
			double sortTime = (stopTime - midpointTime) / (double) timesToLoop;
//			double totalTime = getLAGTime - sortTime;

			System.out.println(n + "\t" + "   " +  sortTime);
		}
	}

	public static Integer randomInt() {
		return rand.nextInt();
	}

	public static String randomN(int n) {
		String nString = "";
		for(int i = 0; i < n; i++) {
			nString += "a";
		}
		return nString;
	}
	public static String[] randomNArray(int n) {
		String[] nString = new String[n];
		for(int i = 0; i < n; i++) {
			nString[i] = "a";
		}
		return nString;
	}
	protected static class WordComparator implements Comparator<String> {
		@Override
		public int compare(String o1, String o2) {
//			o1 = o1.toLowerCase();
//			o2 = o2.toLowerCase();
			return o1.compareTo(o2);
		}
	}
}
