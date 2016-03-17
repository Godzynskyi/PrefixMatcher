package prefix.matcher;

import static org.junit.Assert.*;

import org.junit.Test;
import static org.mockito.Mockito.*;

import java.util.LinkedList;

public class PrefixMatcherTest {

	@Test
	public void addTest() {
		PrefixMatcher pm = new PrefixMatcher();
		Trie trie = mock(Trie.class);
		pm.trie = trie;
		
		pm.add("asdf  asdfe as 1 easdf asdfasdf", "asdfasd asdfwqef asdf");
		pm.add("asdf asdfe asdf easdf asdfasdf", "asdfasd asdfwqef asdf asdf");
		
		verify(trie, times(16)).add(anyObject());
	}
	
	@Test
	public void addTestWithoutStrings() {
		PrefixMatcher pm = new PrefixMatcher();
		Trie trie = mock(Trie.class);
		pm.trie = trie;
		
		pm.add();
		pm.add();
		
		verify(trie, never()).add(anyObject());
	}
	
	@Test
	public void wordsWithPrefix_NoWords() {
		PrefixMatcher pm = new PrefixMatcher();
		LinkedList<String> linkedListOfWordsByPrefixMethod = new LinkedList<>();
		//linkedListOfWordsByPrefixMethod.add("");
		
		Trie trie = mock(Trie.class);
		pm.trie = trie;
		
		doReturn(linkedListOfWordsByPrefixMethod).when(trie).wordsWithPrefix("ab");
		Iterable<String> iterable = pm.wordsWithPrefix("ab");
		
		int actual = 0;
		for (String s: iterable) {
			actual++;
		}
		
		int expected = 0;
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void wordsWithPrefix_MoreWordsLengthsThenK() {
		PrefixMatcher pm = new PrefixMatcher();
		LinkedList<String> linkedListOfWordsByPrefixMethod = new LinkedList<>();
		linkedListOfWordsByPrefixMethod.add("ab");
		linkedListOfWordsByPrefixMethod.add("abc");
		linkedListOfWordsByPrefixMethod.add("abd");
		linkedListOfWordsByPrefixMethod.add("abt");
		linkedListOfWordsByPrefixMethod.add("abas");
		linkedListOfWordsByPrefixMethod.add("abdr");
		linkedListOfWordsByPrefixMethod.add("abtr");
		linkedListOfWordsByPrefixMethod.add("aberuy");
		linkedListOfWordsByPrefixMethod.add("abervy");
		linkedListOfWordsByPrefixMethod.add("aberwy");
		linkedListOfWordsByPrefixMethod.add("aberxy");
		linkedListOfWordsByPrefixMethod.add("aberyy");
		
		
		Trie trie = mock(Trie.class);
		pm.trie = trie;
		
		doReturn(linkedListOfWordsByPrefixMethod).when(trie).wordsWithPrefix("ab");
		Iterable<String> iterable = pm.wordsWithPrefix("ab", 3);
		
		int actual = 0;
		for (String s: iterable) {
			actual++;
		}
		
		int expected = 7;
		
		assertEquals(expected, actual);
	}

	@Test
	public void wordsWithPrefix_LessWordsLengthsThenK() {
		PrefixMatcher pm = new PrefixMatcher();
		LinkedList<String> linkedListOfWordsByPrefixMethod = new LinkedList<>();
		linkedListOfWordsByPrefixMethod.add("ab");
		linkedListOfWordsByPrefixMethod.add("abc");
		linkedListOfWordsByPrefixMethod.add("abd");
		linkedListOfWordsByPrefixMethod.add("abt");
		
		Trie trie = mock(Trie.class);
		pm.trie = trie;
		
		doReturn(linkedListOfWordsByPrefixMethod).when(trie).wordsWithPrefix("ab");
		Iterable<String> iterable = pm.wordsWithPrefix("ab", 3);
		
		int actual = 0;
		for (String s: iterable) {
			actual++;
		}
		
		int expected = 4;
		
		assertEquals(expected, actual);
	}
	
}
