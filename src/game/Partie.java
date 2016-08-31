package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import gui.PartiePanel;
import words.Lexic;
import words.WordGuess;

public class Partie {
	private String player;
	private int score;

	private int tryNumber;
	private int failNumber;

	private PartiePanel gui;
	private Lexic lexic;

	private WordGuess currentWord;

	public Partie(String player) {
		this.player = player;
		tryNumber = 0;
		failNumber = 0;
	}

	public void playWith(PartiePanel gui, Lexic lexic) {
		this.gui = gui;
		this.lexic = lexic;
		gui.setKeyListener(new Listener());
		gui.setPlayer(player);
		gui.setScore(score, getAccuracy());

		showNewWord();
	}

	private void showNewWord() {
		currentWord = lexic.getRandomWordGuess();
		gui.resetFailLetters();
		gui.displayWord(currentWord);
	}

	private void tryLetter(char letter) {
		tryNumber++;
		if (currentWord.tryLetter(letter)) {
			score++;
			gui.displayWord(currentWord);
			gui.resetFailLetters();
			if (currentWord.isComplete()) {
				score += 5;
				gui.displayInfo("Entrer pour continuer...");
			}
		} else {
			failNumber++;
			gui.addFailLetter(letter);
		}
		gui.setScore(score, getAccuracy());
	}

	private float getAccuracy() {
		if (tryNumber == 0)
			return 100;
		else
			return 100 * (tryNumber - failNumber) / (float) tryNumber;
	}

	public synchronized void waitEnd() {
		try {
			this.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private synchronized void end() {
		this.notifyAll();
	}

	class Listener implements KeyListener {
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
				showNewWord();
			else if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
				end();
		}

		@Override
		public void keyReleased(KeyEvent e) {

		}

		@Override
		public void keyTyped(KeyEvent e) {
			if (!currentWord.isComplete() && Character.isLetter(e.getKeyChar()))
				tryLetter(e.getKeyChar());
		}
	}
}
