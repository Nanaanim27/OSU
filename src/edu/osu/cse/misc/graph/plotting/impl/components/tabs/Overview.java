package edu.osu.cse.misc.graph.plotting.impl.components.tabs;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

import edu.osu.cse.misc.graph.plotting.impl.EquationPlotter;

public class Overview extends JPanel {

	private EquationPlotter plotInstance;
	
	public Overview(EquationPlotter instance) {
		this.plotInstance = instance;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(new JLabel("<html><pre>" +
				"\nAcception functions are as such:" +
				"\nabs: absolute value" +
				"\nacos: arc cosine" +
				"\nasin: arc sine" +
				"\natan: arc tangent" +
				"\ncbrt: cubic root" +
				"\nceil: nearest upper integer" +
				"\ncos: cosine" +
				"\ncosh: hyperbolic cosine" +
				"\nexp: e^x" +
				"\nfloor: nearest lower integer" +
				"\nlog: natural log (base e)" +
				"\nsin: sine" +
				"\nsinh: hyperbolic sine" +
				"\nsqrt: square root" +
				"\ntan: tangent" +
				"\ntanh: hyperbolic tangent" +
				"\n" +
				"\nFunction library courtesy of <i>exp4j*</i>" +
				"\n*http://www.objecthunter.net/exp4j/" +
				"</pre></html>"));
	}
	
}
