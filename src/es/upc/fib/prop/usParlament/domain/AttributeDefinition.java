package es.upc.fib.prop.usParlament.domain;

/**
 * Created by ondrej on 20.4.15.
 */
public class AttributeDefinition {
	private String name;
	private int importance; //3: high; 2: medium; 1: low; 0: null

	public AttributeDefinition(String name, int importance) {
		this.name = name;
		this.importance = importance;
	}

	public int getImportance() {
		return importance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setImportance(int importance) {
		this.importance = importance;
	}
}
