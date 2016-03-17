package prefix.matcher;

public class Tuple {
	String term;
	int weight;
	
	public Tuple(String term, int weight) {
		this.term = term;
		this.weight = weight;
	}

	public String getTerm() {
		return term;
	}

	public int getWeight() {
		return weight;
	}

}
