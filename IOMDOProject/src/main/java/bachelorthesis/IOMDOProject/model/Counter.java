package bachelorthesis.IOMDOProject.model;

public class Counter {

	private Integer value;

	public Counter(int value) {
		this.value = value;
	}

	public void increment() {
		value = value + 1;
	}

	// Returns the current counter value
	public int getValue() {
		return value;
	}
}
