package edu.osu.cse.misc.graph.impl.plotter.components;

import java.awt.FlowLayout;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import edu.osu.cse.misc.graph.impl.plotter.components.equationform.AbstractEquationForm;
import edu.osu.cse.misc.graph.wrappers.function._2d.AbstractFunction2D;

public class EquationSelecter extends JPanel {

	private JList<String> list = new JList<>(Equation.getNames());
	private AbstractEquationForm<? extends AbstractFunction2D> equationForm;
	
	public EquationSelecter() {
		this.setLayout(new FlowLayout(FlowLayout.LEFT, 3, 3));
		
		this.list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting()) {
					setEquationForm(EquationFormFactory.createEquationForm(EquationSelecter.this.getSelectedEquation()));
				}
			}
		});
		this.add(this.list);
	}
	
	public Equation getSelectedEquation() {
		return Equation.valueOf(list.getSelectedValue().toUpperCase());
	}

	private void setEquationForm(AbstractEquationForm<? extends AbstractFunction2D> form) {
		if (this.equationForm != null) {
			this.remove(this.equationForm);
		}
		this.equationForm = form;
		this.add(this.equationForm);
		this.revalidate();
		this.repaint();
	}
	
}
