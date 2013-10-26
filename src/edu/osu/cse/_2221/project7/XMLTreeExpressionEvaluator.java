package edu.osu.cse._2221.project7;

import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

public class XMLTreeExpressionEvaluator {

	public static void main(String[] args) {
		try (SimpleReader in = new SimpleReader1L(); SimpleWriter out = new SimpleWriter1L()) {
			out.println("Enter a target URI for an XML file:");
			String input = "expression.xml";//in.nextLine();
			XMLTree expressionTree = new XMLTree1(input);
			System.out.println(evaluate(expressionTree, 0));
		}
	}

	/**
	 * Evaluates an XML expression-tree recursively.
	 * 
	 * @param tree The root of an XML expression-tree
	 * @param value 0 by default. This value is modified within the scope of the method call and ultimately returned.
	 * @return The value at which the expression tree evaluates to.
	 */
	private static int evaluate(XMLTree tree, int value) {
		if (tree.label().equals("number")) {
			return Integer.parseInt(tree.attributeValue("value"));
		}

		Operator op = Operator.getOperatorFor(tree.label());
		
		if (op != null) { //These are children of an operator (should always be)
			int[] nums = new int[tree.numberOfChildren()];
			for (int i = 0; i < tree.numberOfChildren(); i++) {
				XMLTree child = tree.child(i);
				nums[i] = evaluate(child, value);
			}
			return value += evaluate(op, nums);
		}
		else {
			return evaluate(tree.child(0), value);
		}
	}

	/**
	 * Evaluates the provided integer values based on the given Operator as follows:<pre>
	 * PLUS: num1+num2+num3+...
	 * MINUS: num1-num2-num3-...
	 * TIMES: num1*num2*num3*...
	 * DIVIDE: num1/num2/num3/... </pre>
	 * The size of <tt>nums</tt> should in theory always be 2.
	 * 
	 * @param operator An Operator to use
	 * @param nums A list of integers
	 * @return The result of the evaluation as described above.
	 */
	private static int evaluate(Operator operator, int...nums) {
		if (nums.length == 0) {
			return 0;
		}
		int value = nums[0];
		for (int i = 1; i < nums.length; i++) {
			switch(operator) {
				case DIVIDE:
					value /= nums[i];
					break;
				case MINUS:
					value -= nums[i];
					break;
				case PLUS:
					value += nums[i];
					break;
				case TIMES:
					value *= nums[i];
					break;
				default:
					throw new RuntimeException("Invalid operator provided for evaluate(Operator, int, int): " + operator);
			}
		}
		return value;
	}

	private enum Operator {
		PLUS,
		MINUS,
		TIMES,
		DIVIDE;

		/**
		 * The way that <tt>this</tt> Operator would appear in an XML expression-tree
		 * 
		 * @return A String described above.
		 */
		private String getXmlRepresentation() {
			return this.toString().toLowerCase();
		}

		/**
		 * The Operator in which a given XML tree represents.
		 * <br />For example, "minus" would result in Operator.MINUS
		 * 
		 * @param xmlRepresentation A String operator from an XML tree.
		 * @return The Operator linked to the given XML tree expression label.
		 */
		private static Operator getOperatorFor(String xmlRepresentation) {
			for (Operator o : Operator.values()) {
				if (o.getXmlRepresentation().equals(xmlRepresentation)) {
					return o;
				}
			}
			return null;
		}

	}

}
