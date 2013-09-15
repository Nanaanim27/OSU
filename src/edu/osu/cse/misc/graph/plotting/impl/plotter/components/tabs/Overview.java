package edu.osu.cse.misc.graph.plotting.impl.plotter.components.tabs;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

import edu.osu.cse.misc.graph.plotting.impl.plotter.EquationPlotter;

public class Overview extends JPanel {

	private DefaultListModel<String> model = new DefaultListModel<>();
	private EquationPlotter plotInstance;
	
	private JList<String> list = new JList<String>() {{
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setModel(model);
	}};
	
	public Overview(EquationPlotter instance) {
		this.plotInstance = instance;
		addFunction("f(x)=sin(x)");
		addFunction("x(t)=3t");
		addFunction("y(t)=t");
		this.add(list);
	}
	
	public void addFunction(String function) {
		model.addElement(function);
	}
	
	public void removeFunction(String function) {
		for (int i = 0; i < model.size(); i++) {
			if (model.get(i).equals(function)) {
				model.remove(i);
				return;
			}
		}
	}
	
}
