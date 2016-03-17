package prefix.matcher;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RWayTrie implements Trie {
	Node main = new Node();
	int size = 0;

	class Node {
		Node[] letters = new Node[26];
		boolean end;
	}

	/**
	 * returns index of current letter in array for all letters
	 * @param c
	 * @return index of current letter in array 
	 */
	private int getLetterNumber(char c) {
		int letter = 0;
		
		// ASCII of 'a' = 97, 'z' = 122
		// letters[0] -> 'a', letters[25] -> 'z'
		// So index of array is c - 97; 
		if (97 <= c && c <= 122) {
			letter = c - 97;
			
		// ASCII of 'A' = 65, 'Z' = 90
		// So index of array is c - 65;
		} else if (65 <= c && c <= 90) {
			letter = c - 65;
		}
		return letter;
	}

	private char getLetterByNumber(int number) {
		return (char) (number + 97);
	}

	@Override
	public void add(Tuple tuple) {
		String word = tuple.getTerm();

		// Test is word contains of only letters
		Pattern pattern = Pattern.compile("[a-zA-z]+");
		Matcher matcher = pattern.matcher(word);
		if (!matcher.matches()) return;

		Node current = main;
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			int letter = getLetterNumber(c);

			if (current.letters[letter] == null)
				current.letters[letter] = new Node();
			current = current.letters[letter];
		}
		if (current.end == false)
			size++;
		current.end = true;
	}

	@Override
	public boolean contains(String word) {
		
		
		// Test is word contains of only letters
		Pattern pattern = Pattern.compile("[a-zA-z]+");
		Matcher matcher = pattern.matcher(word);
		
		// We add only words by regex "[a-zA-z]+"
		// So all other strings not contains in this dictionary
		if (!matcher.matches()) return false;

		Node current = main;
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			int letter = getLetterNumber(c);

			if (current.letters[letter] == null) return false;
			current = current.letters[letter];
		}
		return current.end;
	}

	/**
	 * 
	 * @return is able to remove this node
	 */
	private boolean delete(Queue<Character> chars, Node node) {
		char c = chars.poll();
		int letter = getLetterNumber(c);
		Node current = node.letters[letter];
		if (chars.isEmpty()) {
			current.end = false;
			for (Node n : current.letters) {
				if (n != null) return false;
			}
			return true;
		}
		char next = chars.peek();
		int nextLetter = getLetterNumber(next);
		boolean isAbleToDelete = delete(chars, current);
		if (isAbleToDelete) {
			if (current == main) return false;
			current.letters[nextLetter] = null;
			if (current.end) return false;
			for (Node n : current.letters) {
				if (n != null) return false;
			}
			return true;
		} else
			return false;
	}

	@Override
	public boolean delete(String word) {
		if (!contains(word)) return false;
		Queue<Character> chars = new LinkedList<>();
		for (int i = 0; i < word.length(); i++) {
			chars.add(word.charAt(i));
		}
		delete(chars, main);
		size--;
		return true;
	}

	@Override
	public Iterable<String> words() {
		return wordsWithPrefix("");
	}

	
	@Override
	public Iterable<String> wordsWithPrefix(String pref) {
		return new Iterable<String>() {
			Queue<char[]> charsQueue = new LinkedList<>();
			Queue<Node> nodesQueue = new LinkedList<>();

			@Override
			public Iterator<String> iterator() {
				return new Iterator<String>() {
					Node currentNode = main;
					char[] currentChars = new char[pref.length()];

					{
						boolean containsOfPref = changeCurrentNodeToPref();
						if (containsOfPref && size != 0) {
							charsQueue.offer(currentChars);
							nodesQueue.offer(currentNode);
						}
					}

					@Override
					public boolean hasNext() {
						return !charsQueue.isEmpty();
					}

					@Override
					public String next() {
						do {
							currentNode = nodesQueue.poll();
							currentChars = charsQueue.poll();
							addAllChildsToQueues();
						} while (!currentNode.end);
						StringBuilder res = new StringBuilder();
						for (char c : currentChars)
							res.append(c);
						return res.toString();
					}

					/**
					 * Moves cursor to pref.
					 * 
					 * @return true if trie contains this pref
					 * 			or false otherwise
					 */
					private boolean changeCurrentNodeToPref() {
						for (int i = 0; i < pref.length(); i++) {
							char c = pref.charAt(i);
							int letter = getLetterNumber(c);
							if (currentNode.letters[letter] != null) {
								currentNode = currentNode.letters[letter];
								currentChars[i] = c;
							} else { 
								return false;
							}
						}
						return true;
					}
					
					/**
					 * Takes all children of currentNode and offer they to queues.
					 */
					private void addAllChildsToQueues() {
						for (int i = 0; i < 26; i++) {
							if (currentNode.letters[i] != null) {
								char[] chars = new char[currentChars.length + 1];
								System.arraycopy(currentChars, 0, chars, 0, currentChars.length);
								chars[chars.length - 1] = getLetterByNumber(i);
								nodesQueue.offer(currentNode.letters[i]);
								charsQueue.offer(chars);
							}
						}
					}
				};
			}
		};
	}

	@Override
	public int size() {
		return size;
	}

}
