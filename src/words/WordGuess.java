package words;

public class WordGuess {
	private String word;
	private int length;
	private Lexic lexic;

	public WordGuess(String word, int length, Lexic lexic) {
		this.word = word;
		this.length = length;
		this.lexic = lexic;
	}

	@Override
	public String toString() {
		String res = word;
		while (res.length() < length)
			res += "_";
		return res;
	}

	public boolean tryLetter(char letter) {
		String toTry = word + letter;
		if (lexic.contains(toTry, length)) {
			word = toTry;
			return true;
		} else
			return false;
	}

	public boolean isComplete() {
		return word.length() == length;
	}
}
