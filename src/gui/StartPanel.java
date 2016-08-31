package gui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class StartPanel extends JPanel {
	private static final long serialVersionUID = 6164822529801511069L;

	private JButton start;
	private JTextField pseudo;

	public StartPanel(int entriesNumber) {
		setLayout(new GridBagLayout());

		JLabel label = new JLabel("Saikoilmo ?!");
		label.setFont(new Font("Tahoma", Font.PLAIN, 24));
		add(label, new GridBagConstraints(0, 0, 2, 1, 0, 0, GridBagConstraints.NORTH, GridBagConstraints.NONE,
				new Insets(10, 10, 10, 10), 0, 0));

		label = new JLabel(entriesNumber + " mots chargés");
		label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(label, new GridBagConstraints(0, 1, 2, 1, 1, 1, GridBagConstraints.SOUTH, GridBagConstraints.NONE,
				new Insets(10, 10, 10, 10), 0, 0));

		label = new JLabel("Pseudo");
		label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(label, new GridBagConstraints(0, 2, 1, 1, 1, 0, GridBagConstraints.EAST, GridBagConstraints.NONE,
				new Insets(10, 10, 0, 0), 0, 0));

		pseudo = new JTextField(10);
		pseudo.setText("Anon");
		pseudo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(pseudo, new GridBagConstraints(1, 2, 1, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.NONE,
				new Insets(10, 10, 0, 10), 0, 0));

		start = new JButton("Nouvelle partie");
		start.setFont(new Font("Tahoma", Font.PLAIN, 16));
		start.setFocusPainted(false);
		start.addActionListener(new Listener());
		add(start, new GridBagConstraints(0, 3, 2, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.NONE,
				new Insets(10, 10, 10, 10), 0, 0));
	}

	public synchronized void waitStart() {
		try {
			this.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String getPseudo() {
		return pseudo.getText();
	}

	class Listener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			synchronized (StartPanel.this) {
				StartPanel.this.notifyAll();
			}
		}
	}
}
