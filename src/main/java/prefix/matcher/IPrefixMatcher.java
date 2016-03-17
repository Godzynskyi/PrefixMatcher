package prefix.matcher;

public interface IPrefixMatcher {

    /**
     *  Формирует in-memory словарь слов. Метод принимает слово, строку, массив слов/строк. 
     *  Если приходит строка, то она разбивается на слова по пробелам.
     *  В словарь должны добавляться слова длиннее 2х символов.
     *  Предполагается что знаки пунктуации отсутствуют.
     *  
     * @param strings texts with words
     * @return number of added words
     */
    public int add(String... strings);

    /**
     * @param word
     * @return is trie consist in this word
     */
    public boolean contains(String word);

    /**
     *  удаляет слово из словаря
     *  
     * @param word
     * @return true if word was consisted in this word before deleting.
     */
    public boolean delete(String word);

    /**
     * к-во слов в словаре
     * 
     * @return size of dictionary;
     */
    public int size();

    /**
     *  если введенный pref длиннее или равен 2м символам, то возвращает набор слов k разных длин начиная с минимальной, и начинающихся с данного префикса pref.
     * Пример, даны слова следующей длины и pref='abc':
     * abc 3
     * abcd 4
     * abce 4
     * abcde 5
     * abcdef 6
     * - при k=1 возвращаются 'abc'
     * - при k=2 возвращаются 'abc', 'abcd', 'abce'
     * - при k=3 возвращаются 'abc', 'abcd', 'abce', 'abcde'
     * - при k=4 возвращаются 'abc', 'abcd', 'abce', 'abcde', 'abcdef'
     * 
     */
    public Iterable<String> wordsWithPrefix(String pref, int k);

    /**
     *  если введенный pref длиннее или равен 2м символам, то возвращает набор слов k=3 разных длин начиная с минимальной, и начинающихся с данного префикса pref.
     *  
     */
    public Iterable<String> wordsWithPrefix(String pref);

}
