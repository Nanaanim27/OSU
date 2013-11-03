package edu.osu.cse.misc.graph.plotting.impl.components.tabs;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ListSelectionModel;

import edu.osu.cse.misc.graph.plotting.impl.EquationPlotter;
import edu.osu.cse.misc.graph.plotting.impl.components.equationform._3d.AbstractEquationForm3D;
import edu.osu.cse.misc.graph.plotting.impl.components.equationform._3d.Equation3D;
import edu.osu.cse.misc.graph.plotting.wrappers.function._3d.AbstractFunction3D;

public class AddFunction3D extends JPanel {

	private AbstractEquationForm3D<? extends AbstractFunction3D> functionForm;
	private GridBagConstraints gbc = new GridBagConstraints();
	private EquationPlotter plotInstance;
	
	private JRadioButton polynomial = new JRadioButton("Polynomial") {{
		setSelected(true);
		setFocusPainted(false);
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (isSelected()) {
					AddFunction3D.this.setForm(Equation3D.POLYNOMIAL.getForm());
				}
			}
		});
	}};
	
	private JRadioButton parametric = new JRadioButton("Parametric") {{
		setSelected(false);
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (isSelected()) {
					AddFunction3D.this.setForm(Equation3D.PARAMETRIC.getForm());
				}
			}
		});
	}};
	
	private ButtonGroup buttons = new ButtonGroup() {{
		add(AddFunction3D.this.polynomial);
		add(AddFunction3D.this.parametric);
	}};
	
	
	private DefaultListModel<String> model = new DefaultListModel<>();
	private JList<String> list = new JList<String>() {{
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JList<String> list = (JList<String>) e.getSource();
				if (e.getClickCount() == 2) {
					int index = list.getSelectedIndex();
					AddFunction3D.this.model.remove(index);
					AddFunction3D.this.plotInstance.getGraphPanel2D().removeFunction(index);
				}
			};
		});
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setModel(AddFunction3D.this.model);
	}};
	
	
	/**
	 * Constructs the AddFunction tab
	 */
	public AddFunction3D(EquationPlotter instance) {
		this.plotInstance = instance;
		this.setLayout(new GridBagLayout());

		this.gbc = new GridBagConstraints();
		this.gbc.fill = GridBagConstraints.NONE;
		this.gbc.anchor = GridBagConstraints.NORTHWEST;

		this.gbc.weighty = 0D;
		this.gbc.weightx = 1D;
		this.gbc.gridy = 0;
		this.gbc.gridx = 0;
		this.add(buildButtonContainer(), this.gbc);
		
		this.setForm(Equation3D.POLYNOMIAL.getForm());
	}
	
	private JPanel buildButtonContainer() {
		JPanel buttonContainer = new JPanel(new GridBagLayout());

		GridBagConstraints gbc2 = new GridBagConstraints();
		gbc2.fill = GridBagConstraints.NONE;
		gbc2.anchor = GridBagConstraints.NORTHWEST;
		
		gbc2.weightx = 0D;
		gbc2.weighty = 0D;
		gbc2.gridy = 0;
		buttonContainer.add(this.polynomial, gbc2);
		
		gbc2.gridy++;
		buttonContainer.add(this.parametric, gbc2);
		
		return buttonContainer;
	}

	private void setForm(AbstractEquationForm3D<? extends AbstractFunction3D> form) {
		if (this.functionForm != null) {
			this.remove(this.functionForm);
		}
		
		this.gbc.gridy = 1;
		this.gbc.gridx = 0;
		this.gbc.gridy++;
		this.gbc.weighty = 1D;
		this.gbc.weightx = 1D;
		this.functionForm = form;
		if (!form.isLinked()) {
			form.linkTo(this.plotInstance.getGraphPanel3D(), this.plotInstance);
		}
		this.add(form, this.gbc);
		
		this.repaint();
		this.revalidate();
		this.plotInstance.operationsPane.revalidate();
		form.setBorder(BorderFactory.createLineBorder(Color.CYAN));
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
