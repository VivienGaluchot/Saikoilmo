package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyListener;
import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;

import words.WordGuess;

public class PartiePanel extends JPanel {
	private static final long serialVersionUID = -3523030016680704124L;

	private JLabel gameLabel;
	private JLabel infoLabel;
	private JLabel playerLabel;
	private JLabel scoreLabel;

	private ArrayList<String> failLetters;

	public PartiePanel() {
		failLetters = new ArrayList<>();

		setFocusable(true);
		setLayout(new GridBagLayout());

		JLabel title = new JLabel("Saikoilmo ?!");
		title.setFont(new Font("Tahoma", Font.PLAIN, 24));
		add(title, new GridBagConstraints(0, 0, 2, 1, 0, 0, GridBagConstraints.NORTH, GridBagConstraints.NONE,
				new Insets(10, 10, 10, 10), 0, 0));

		// Game Panel
		JPanel gamePanel = new JPanel();
		gamePanel.setLayout(new GridBagLayout());
		gamePanel.setBackground(Color.white);
		gameLabel = new JLabel();
		Map<TextAttribute, Object> attributes = new HashMap<TextAttribute, Object>();
		attributes.put(TextAttribute.TRACKING, 0.1);
		Font baseFont = new Font("Consolas", Font.PLAIN, 24);
		gameLabel.setFont(baseFont.deriveFont(attributes));
		gamePanel.add(gameLabel, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));

		infoLabel = new JLabel();
		infoLabel.setFont(new Font("Consolas", Font.PLAIN, 18));
		infoLabel.setForeground(Color.DARK_GRAY);
		gamePanel.add(infoLabel, new GridBagConstraints(0, 1, 1, 1, 1, 0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));

		add(gamePanel, new GridBagConstraints(0, 1, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 10, 0, 10), 0, 0));

		playerLabel = new JLabel();
		playerLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		add(playerLabel, new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE,
				new Insets(10, 10, 10, 10), 0, 0));

		scoreLabel = new JLabel();
		scoreLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		add(scoreLabel, new GridBagConstraints(1, 2, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE,
				new Insets(10, 10, 10, 10), 0, 0));

		// end GamePanel
	}

	public void setScore(int score, float accuracy) {
		String acc = String.format("%.1f", accuracy);
		scoreLabel.setText("Score : " + score + " | Précision : " + acc);
	}

	public void setPlayer(String player) {
		playerLabel.setText("Joueur : " + player);
	}

	public void setKeyListener(KeyListener list) {
		for (KeyListener k : getKeyListeners())
			removeKeyListener(k);

		addKeyListener(list);
	}

	public void displayWord(WordGuess word) {
		gameLabel.setText(word.toString());
	}

	public void displayInfo(String info) {
		infoLabel.setText(info);
	}

	public void addFailLetter(char letter) {
		String s = "" + letter;
		if (!failLetters.contains(s)) {
			failLetters.add(s);
			if (failLetters.size() > 15)
				failLetters.remove(0);
		} else {
			failLetters.remove(s);
			failLetters.add(s);
		}
		displayFailLetter();
	}

	public void resetFailLetters() {
		failLetters.clear();
		displayFailLetter();
	}

	private void displayFailLetter() {
		String str = "-";
		if (failLetters.size() > 0)
			str = failLetters.get(0);
		for (int i = 1; i < failLetters.size(); i++) {
			str += " " + failLetters.get(i);
		}
		infoLabel.setText(str);
	}
}
