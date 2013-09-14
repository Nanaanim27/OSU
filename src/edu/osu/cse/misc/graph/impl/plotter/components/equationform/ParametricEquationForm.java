package edu.osu.cse.misc.graph.impl.plotter.components.equationform;

import javax.swing.JLabel;

import edu.osu.cse.misc.graph.wrappers.function._2d.ParametricFunction2D;
import edu.osu.cse.misc.graph.wrappers.graph.GraphPanel2D;

public class ParametricEquationForm extends AbstractEquationForm<ParametricFunction2D> {

	public ParametricEquationForm(GraphPanel2D graph) {
		super(graph);
	}

	@Override
	protected JLabel getVariableDescriptionLabel() {
		return new JLabel("Evaluate with respect to: t");
	}

}
