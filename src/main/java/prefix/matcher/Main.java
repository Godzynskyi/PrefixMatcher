package prefix.matcher;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;

import prefix.matcher.prefix.matcher.IPrefixMatcher;
import prefix.matcher.prefix.matcher.PrefixMatcher;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		IPrefixMatcher pm = new PrefixMatcher();
		
		File f = new File(Main.class.getClassLoader().getResource("words.txt").getFile());
		BufferedReader br = new BufferedReader(new FileReader(f));
		
		Iterator<String> iterator = br.lines().iterator();
		
		while (iterator.hasNext()) {
			pm.add(iterator.next());
		}
		
		System.out.println("Size = " + pm.size());
		for (String s: pm.wordsWithPrefix("ara", 3)) {
			System.out.println(s);
		}
	}
}
