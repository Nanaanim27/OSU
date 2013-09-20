package edu.osu.cse.misc.graph.plotting.impl.plotter.components.tabs;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

import edu.osu.cse.misc.graph.plotting.impl.plotter.EquationPlotter;

public class Overview extends JPanel {

	private DefaultListModel<String> model = new DefaultListModel<>();
	private EquationPlotter plotInstance;
	
	private JList<String> list = new JList<String>() {{
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JList<String> list = (JList<String>) e.getSource();
				if (e.getClickCount() == 2) {
					int index = list.getSelectedIndex();
					Overview.this.model.remove(index);
					Overview.this.plotInstance.getGraphPanel().removeFunction(index);
				}
			};
		});
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setModel(Overview.this.model);
	}};
	
	public Overview(EquationPlotter instance) {
		this.plotInstance = instance;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(this.list);
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
	
	public void addFunction(String function) {
		this.model.addElement(function);
	}
	
	public void removeFunction(String function) {
		for (int i = 0; i < this.model.size(); i++) {
			if (this.model.get(i).equals(function)) {
				this.model.remove(i);
				return;
			}
		}
	}
}
