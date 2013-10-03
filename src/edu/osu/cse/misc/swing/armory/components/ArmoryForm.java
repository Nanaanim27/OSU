package edu.osu.cse.misc.swing.armory.components;

import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ArmoryForm extends JPanel {

	private final JLabel realmNameLabel = new JLabel("Realm: "), characterNameLabel = new JLabel("Name: ");
	private final JTextField realmNameField = new JTextField(10), characterNameField = new JTextField(15);

	private JButton go = new JButton("Search");

	public ArmoryForm() {
		this.setLayout(new FlowLayout(FlowLayout.LEFT, 4, 0));

		this.add(this.realmNameLabel);
		this.add(this.realmNameField);

		this.add(this.characterNameLabel);
		this.add(this.characterNameField);

		this.realmNameField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == '\n')
					ArmoryForm.this.characterNameField.requestFocus();
			}
		});

		this.characterNameField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == '\n') {
					browse(ArmoryForm.this);
					ArmoryForm.this.characterNameField.setText("");
				}
			}
		});

		this.go.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				browse(ArmoryForm.this);
			}
		});
		this.add(this.go);
	}

	private static void browse(ArmoryForm form) {
		try {
			Desktop.getDesktop().browse(new URL("http://us.battle.net/wow/en/character/" + form.getRealm() + "/" + form.getCharacterName() + "/advanced").toURI());
		} catch (Exception err) {
			err.printStackTrace();
		}
	}

	public String getRealm() {
		return this.realmNameField.getText();
	}

	public String getCharacterName() {
		return this.characterNameField.getText();
	}

}
