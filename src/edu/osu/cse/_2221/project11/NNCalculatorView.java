package edu.osu.cse._2221.project11;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class NNCalculatorView extends JFrame implements ActionListener {

	public final JPanel container;
	public final JTextArea top, bottom;
	
	private JButton[] buttons = new JButton[19];
	
	private JButton zero, one, two, three, four, five, six, seven, eight, nine;
	private JButton plus, minus, multiply, divide, power, root;
	private JButton clear, swap, enter;
	
	private GridBagConstraints gbc = new GridBagConstraints();
	
	NNCalculatorView() {
		super("Natural Number Calculator");
		this.container = new JPanel(new GridBagLayout());
		
		this.top = new JTextArea() {{
			setEditable(false);
			setLineWrap(true);
		}};
		
		this.bottom = new JTextArea() {{
			setEditable(false);
			setLineWrap(true);
		}};
		
		this.zero = this.buttons[0] = new JButton("0");
		this.one = this.buttons[1] = new JButton("1");
		this.two = this.buttons[2] = new JButton("2");
		this.three = this.buttons[3] = new JButton("3");
		this.four = this.buttons[4] = new JButton("4");
		this.five = this.buttons[5] = new JButton("5");
		this.six = this.buttons[6] = new JButton("6");
		this.seven = this.buttons[7] = new JButton("7");
		this.eight = this.buttons[8] = new JButton("8");
		this.nine = this.buttons[9] = new JButton("9");
		
		this.plus = this.buttons[10] = new JButton("+");
		this.minus = this.buttons[11] = new JButton("-");
		this.multiply = this.buttons[12] = new JButton("*");
		this.divide = this.buttons[13] = new JButton("/");
		this.power = this.buttons[14] = new JButton("Power");
		this.root = this.buttons[15] = new JButton("Root");
		
		this.clear = this.buttons[16] = new JButton("Clear");
		this.swap = this.buttons[17] = new JButton("Swap");
		this.enter = this.buttons[18] = new JButton("Enter");
		
	}
	
	public void addComponent(Component c, int x, int y) {
		this.gbc.gridx = x;
		this.gbc.gridy = y;
		this.container.add(c, this.gbc);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int index = getButtonIndex(e.getSource());
		
		//Button pressed is a number
		if (index >= 0 && index <= 9) {
			
		}
	}
	
	private int getButtonIndex(Object source) {
		for (int i = 0; i < this.buttons.length; i++) {
			if (this.buttons[i] == source) {
				return i;
			}
		}
		return -1;
	}
	
}
