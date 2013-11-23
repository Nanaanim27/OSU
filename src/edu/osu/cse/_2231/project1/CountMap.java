package edu.osu.cse._2231.project1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;

public class CountMap<K, V> extends HashMap<String, Integer> {

	@Override
	public Integer put(String key, Integer value) {
		if (this.containsKey(key)) {
			return super.put(key, this.get(key) + 1);
		}
		return super.put(key, value);
	}

	@Override
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
