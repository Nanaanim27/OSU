package edu.osu.cse._2221.homework19;

import components.sequence.Sequence;
import components.sequence.Sequence1L;


public class Methods extends Sequence1L<String> {
	
	 /**
	 * Reverses ("flips") {@code this}.
	 * 
	 * @updates {@code this}
	 * @ensures <pre>
	 * {@code this = rev(#this)}
	 * </pre>
	 */
	public void flip() {
		Stack<String> temp = new Stack1L<>();
		while (this.length() != 0) {
			temp.push(this.pop());
		}
		this.transferFrom(temp);
	}

	/**
	 * Reverses ("flips") {@code this}.
	 * 
	 * @updates {@code this}
	 * @ensures <pre>
	 * {@code this = rev(#this)}
	 * </pre>
	 */
	public void flip() {
		Sequence<String> temp = new Sequence1L<>();
		while (this.length() != 0) {
			temp.add(temp.length()-1, this.remove(this.length()-1));
		}
		this.transferFrom(temp);
	}

	public void flip2() {
		this.flip2(0);
	}

	private void flip2(int pos) {
		if (pos < this.length()/2) {
			String atPos = this.entry(pos);
			this.replaceEntry(pos, this.replaceEntry(this.length()-1-pos, atPos));
			flip2(pos + 1);
		}
	}

}
