package assign04;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * @author Samuel Langlois and Noah Garff.
 * @version September 23, 2021
 */
class AnagramCheckerTest {

	/******* sort tests *******/

	@Test
	void testSmallSort() {
		String testStart = "Reacts";
		String test = "Racest";

		assertEquals(test, AnagramChecker.sort(testStart));
	}

	@Test
	void testSortSmallNumbers() {
		String testStart = "54321";
		String test = "12345";

		assertEquals(test, AnagramChecker.sort(testStart));
	}

	@Test
	void testSortOneLetter() {
		String testStart = "a";
		String test = "a";

		assertEquals(test, AnagramChecker.sort(testStart));
	}

	@Test
	void testSortLettersAndNonLetters() {
		String testStart = "cba@%&12JK";
		String test = "%&12@JKabc";

		assertEquals(test, AnagramChecker.sort(testStart));
	}

	@Test
	void testSortNullArray() {
		String testStart = null;
		assertThrows(NullPointerException.class, () -> AnagramChecker.sort(testStart));
	}

	/******* areAnagram tests *******/

	@Test
	void testAreAnagrams() {
		String testStart = "Bob";
		String test = "bob";

		assertTrue(AnagramChecker.areAnagrams(testStart, test));
	}

	/******* getLargestAnagramGroup tests *******/

	@Test
	void testSortEmptyArray() {
		String[] testStart = { "" };
		String[] anagramGroup = AnagramChecker.getLargestAnagramGroup(testStart);

		assertTrue(anagramGroup.length == 0);
	}

	@Test
	void testGetLargestAnagramGroupNullArray() {
		String[] testStart = null;
		assertThrows(NullPointerException.class, () -> AnagramChecker.getLargestAnagramGroup(testStart));
	}

	@Test
	void testFileSort() {
		String[] test = { "Caters", "Reacts", "carets", "caster", "crates", "recast", "traces" };
		String[] anagramGroup = AnagramChecker.getLargestAnagramGroup("src/assign04/sample_word_list.txt");
		for (int i = 0; test.length > i; i++)
			assertEquals(test[i], anagramGroup[i]);
	}

	@Test
	void testFileEmpty() {

		assertTrue(AnagramChecker.getLargestAnagramGroup("").length == 0);

	}

	@Test
	void testSortNumbersGetLargest() {
		String[] testStart = { "54321", "21345", "4567", "95843" };
		String[] test = { "21345", "54321" };
		String[] anagramGroup = AnagramChecker.getLargestAnagramGroup(testStart);

		for (int i = 0; i < test.length; i++)
			assertEquals(test[i], anagramGroup[i]);
	}

	@Test
	void testGetLargestAnagramGroupNonLetters() {
		String[] testStart = { "aA@1#$", "$a#1@A", "#$@1Aa", "NJDF", "Reacts", "traces", "Caters" };
		String[] test = { "#$@1Aa", "$a#1@A", "aA@1#$" };
		String[] anagramGroup = AnagramChecker.getLargestAnagramGroup(testStart);

		for (int i = 0; i < test.length; i++)
			assertEquals(test[i], anagramGroup[i]);

	}

	@Test
	void testGetLargestAnagramGroupDifferentCases() {
		String[] testStart = { "Beginning", "beginning" };
		String[] test = { "Beginning", "beginning" };
		String[] anagramGroup = AnagramChecker.getLargestAnagramGroup(testStart);

		for (int i = 0; i < test.length; i++)
			assertEquals(test[i], anagramGroup[i]);

	}

}