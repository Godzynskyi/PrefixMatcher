package prefix.matcher;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PrefixMatcher implements IPrefixMatcher {
	Trie trie;
	
	public PrefixMatcher() {
		trie = new RWayTrie();
	}

	public PrefixMatcher(Trie trie) {
		this.trie = trie;
	}
	
	@Override
	public int add(String... strings) {
		int firstSize = size();
		for (String s: strings) {
			Pattern pattern = Pattern.compile("[a-zA-Z]+");
			Matcher matcher = pattern.matcher(s);
			List<String> splitStrings = new LinkedList<>();
			while (matcher.find()) {
				splitStrings.add(matcher.group());
			}
			
			for (String word: splitStrings) {
				if (word.length() > 2) trie.add(new Tuple(word, word.length()));
			}
		}
		return size() - firstSize;
	}

	@Override
	public boolean contains(String word) {
		return trie.contains(word);
	}

	@Override
	public boolean delete(String word) {
		return trie.delete(word);
	}

	@Override
	public int size() {
		return trie.size();
	}

	@Override
	public Iterable<String> wordsWithPrefix(final String pref, final int k) {
		return new Iterable<String>() {
			Iterator<String> trieIterator = trie.wordsWithPrefix(pref).iterator();
			String next;
			
			@Override
			public Iterator<String> iterator() {
				
				return new Iterator<String>() {
					int count = k;
					int currentLength = 0;
					
					@Override
					public boolean hasNext() {
						if (!trieIterator.hasNext()) return false;
						next = trieIterator.next();
						int length = next.length();
						if (length > currentLength) {
							count--;
							if (count == -1) return false;
							currentLength = length;
						}
						return true;
					}

					@Override
					public String next() {
						return next;
					}
					
				};
			}
			
		};
	}

	@Override
	public Iterable<String> wordsWithPrefix(String pref) {
		if (pref.length() < 2) return new TreeSet<String>();
		return wordsWithPrefix(pref,3);
	}

}
