package prefix.matcher;

import static org.junit.Assert.*;

import org.junit.Test;

public class TrieTest {
	
	private Trie getTestingTrie() {
		return new RWayTrie();
	}
	
	@Test
	public void sizeAfterSimpleAdd() {
		Trie trie = getTestingTrie();
		trie.add(new Tuple("First", 5));
		int actual = trie.size();
		int expected = 1;
		assertEquals(expected, actual);
	}
	
	@Test
	public void sizeOneAfterAddingOfTwoSameStrings() {
		Trie trie = getTestingTrie();
		trie.add(new Tuple("First", 5));
		trie.add(new Tuple("First", 5));
		int actual = trie.size();
		int expected = 1;
		assertEquals(expected, actual);
	}
	
	@Test
	public void wordsWithPrefix() {
		Trie trie = getTestingTrie();
		trie.add(new Tuple("Fi", 2));
		trie.add(new Tuple("Fi", 2));
		trie.add(new Tuple("Fir", 3));
		trie.add(new Tuple("Fis", 3));
		trie.add(new Tuple("Fiesta", 6));
		trie.add(new Tuple("Fas", 3));
		trie.add(new Tuple("Fj", 2));
		trie.add(new Tuple("Fja", 3));
		
		Iterable<String> iter = trie.wordsWithPrefix("fi");
		
		int actual = 0;
		for (String s: iter) {
			actual++;
		}
		int expected = 4;
		assertEquals(expected, actual);
	}
	
	@Test
	public void words() {
		Trie trie = getTestingTrie();
		trie.add(new Tuple("Fi", 2));
		trie.add(new Tuple("Fi", 2));
		trie.add(new Tuple("Fir", 3));
		trie.add(new Tuple("Fis", 3));
		trie.add(new Tuple("Fiesta", 6));
		trie.add(new Tuple("Fas", 3));
		trie.add(new Tuple("Fj", 2));
		trie.add(new Tuple("Fja", 3));
		
		Iterable<String> i = trie.words();
		
		int actual = 0;
		for (String s: i) {
			actual++;
		}
		int expected = 7;
		assertEquals(expected, actual);
	}
	
	@Test
	public void removeWord() {
		Trie trie = getTestingTrie();
		trie.add(new Tuple("Fi", 2));
		trie.add(new Tuple("Fi", 2));
		trie.add(new Tuple("Fir", 2));
		trie.add(new Tuple("Fis", 2));
		trie.add(new Tuple("Fiesta", 6));
		trie.add(new Tuple("Fas", 4));
		trie.add(new Tuple("Fj", 2));
		trie.add(new Tuple("Fja", 3));
		
		trie.delete("Fj");
		
		Iterable<String> i = trie.words();
		
		int actual = 0;
		for (String s: i) {
			actual++;
		}
		int expected = 6;
		assertEquals(expected, actual);
	}
	
	@Test
	public void removeAllWords() {
		Trie trie = getTestingTrie();
		trie.add(new Tuple("Fi", 2));
		trie.add(new Tuple("Fi", 2));
		trie.add(new Tuple("Fir", 2));
		trie.add(new Tuple("Fis", 2));
		trie.add(new Tuple("Fiesta", 6));
		trie.add(new Tuple("Fas", 4));
		trie.add(new Tuple("Fj", 2));
		trie.add(new Tuple("Fja", 3));
		
		trie.delete("Fi");
		trie.delete("Fir");
		trie.delete("Fis");
		trie.delete("Fiesta");
		trie.delete("Fas");
		trie.delete("Fj");
		trie.delete("Fja");
		
		Iterable<String> i = trie.words();
		
		int actual = 0;
		for (String s: i) {
			actual++;
		}
		int expected = 0;
		assertEquals(expected, actual);
	}
	
	@Test
	public void wordsIfTrieIsEmpty() {
		Trie trie = getTestingTrie();
		Iterable<String> i = trie.words();
		int actual = 0;
		for (String s: i) {
			actual++;
		}
		int expected = 0;
		assertEquals(expected, actual);
	}
	
	@Test
	public void containsWord() {
		Trie trie = getTestingTrie();
		trie.add(new Tuple("Fi", 2));
		trie.add(new Tuple("Fi", 2));
		trie.add(new Tuple("Fir", 2));
		trie.add(new Tuple("Fis", 2));
		trie.add(new Tuple("Fiesta", 6));
		trie.add(new Tuple("Fas", 4));
		trie.add(new Tuple("Fj", 2));
		trie.add(new Tuple("Fja", 3));
		
		assertTrue(trie.contains("Fja"));
	}
	
	@Test
	public void containsWordFromEmptyTrie() {
		Trie trie = getTestingTrie();
		
		assertFalse(trie.contains("Fja"));
	}
	
}
