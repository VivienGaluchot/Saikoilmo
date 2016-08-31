package game;

import gui.GameFrame;
import gui.PartiePanel;
import gui.StartPanel;
import words.Lexic;

public class MainGame {
	public static void main(String[] args) {
		Lexic lexic = new Lexic("liste_francais.txt");

		GameFrame frame = new GameFrame();
		StartPanel startPanel = new StartPanel(lexic.size());
		frame.displayPannel(startPanel);
		frame.launch();

		while (frame.isVisible()) {
			startPanel.waitStart();

			PartiePanel partiePanel = new PartiePanel();
			frame.displayPannel(partiePanel);

			Partie partie = new Partie(startPanel.getPseudo());
			partie.playWith(partiePanel, lexic);
			partie.waitEnd();

			frame.displayPannel(startPanel);
		}
	}
}
