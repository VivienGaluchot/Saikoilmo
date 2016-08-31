package words;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

public class Lexic {
	ArrayList<String> words;

	public Lexic(String path) {
		words = new ArrayList<>();
		addWords(path);
	}

	public WordGuess getRandomWordGuess() {
		int n = ThreadLocalRandom.current().nextInt(0, words.size());
		String fullword = words.get(n);
		int l = fullword.length() / 2;
		return new WordGuess(fullword.substring(0, l), fullword.length(), this);
	}

	protected boolean contains(String toTry, int length) {
		for (String word : words)
			if (word.startsWith(toTry) && word.length() == length)
				return true;
		return false;
	}

	// TODO loadbar
	private void addWords(String path) {
		try (Stream<String> stream = Files.lines(Paths.get(path))) {
			stream.forEach(this::processLine);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void processLine(String line) {
		if (!line.contains(" ") && !line.contains("-"))
			words.add(line);
	}

	public int size() {
		return words.size();
	}
}
