package edu.osu.cse._2231.project1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * I extended HashMap instead of the component's Map as Map has marked all of its useful methods final, thus
 * restricting the ability to override them, and ultimately rendering it useless to me. I wished to override 
 * certain methods in order to add more specific functionality for this project as you will see in the implementation 
 * of my overridden methods. 
 * 
 * @author Matt Weiss
 */
public class CountMap extends HashMap<String, Integer> {

	@Override
	public Integer put(String key, Integer value) {
		if (this.containsKey(key)) {
			//Map already contains the word, so increment its count.
			return super.put(key, this.get(key) + 1);
		}
		return super.put(key, value);
	}

	@Override
	/**
	 * Generates a custom, sorted set of words.
	 */
	public Set<String> keySet() {
		String[] keys = super.keySet().toArray(new String[this.size()]);
		Arrays.sort(keys, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareToIgnoreCase(o2);
			}
		});
		return new LinkedHashSet<String>(Arrays.asList(keys));
	}

}
