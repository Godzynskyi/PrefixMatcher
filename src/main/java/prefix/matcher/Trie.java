package prefix.matcher;

public interface Trie {
	/**
	 *  Добавляет в Trie пару слово - term, и его вес - weight.
	 *  В качестве веса используйте длину слова
	 */
    public void add(Tuple tuple);

    /**
     * @param word
     * @return true if word contains in trie.
     */
    public boolean contains(String word);

    /**
     *  удаляет слово из Trie
     * @param word
     * @return true if word was deleted.
     */
    public boolean delete(String word);

    /**
     *  итератор по всем словам, обход в ширину
     */
    public Iterable<String> words();

    /**
     *  итератор по всем словам, начинающимся с pref, обход в ширину
     */
    public Iterable<String> wordsWithPrefix(String pref);

    /**
     *  к-во слов в Trie
     */
    public int size();
}
