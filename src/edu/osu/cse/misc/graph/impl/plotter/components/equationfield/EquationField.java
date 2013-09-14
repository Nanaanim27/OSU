package edu.osu.cse.misc.graph.impl.plotter.components.equationfield;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Consists of a JLabel and a JTextField in such a format as:
 * <pre>f(x)=[        ]</pre>
 */
public class EquationField extends JPanel {

	private JLabel descripter;
	private JTextField functionField = new JTextField(15);
	
	/**
	 * 
	 * @param descripter The function evaluation to be made, i.e. "f(x)"
	 * @param variable The variable the function is in respect to. 
	 * Should match up with the descripter's variable. For example, "x"
	 */
	public EquationField(String descripter, String variable) {
		this.descripter = new JLabel((descripter + "=").replaceAll("[==]+", "="));
	}
	
	public JLabel getDescripter() {
		return this.descripter;
	}
	
	public JTextField getFunctionField() {
		return this.functionField;
	}
	
}
