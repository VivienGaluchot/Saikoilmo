package gui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameFrame extends JFrame {
	private static final long serialVersionUID = 169794085155753673L;

	public void launch() {
		setTitle("Saikoilmo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// TODO
		// frame.setMenuBar(mb);

		setMinimumSize(new Dimension(600, 300));
		setVisible(true);
		pack();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);
	}

	public void displayPannel(JPanel panel) {
		setContentPane(panel);
		panel.requestFocusInWindow();
	}
}
