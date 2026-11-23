package il.ac.tau.cs.software1.inventory;

/*
 * A simple array-based Inventory (Question 6).
 */
public class NaiveInventory implements Inventory {

	private static final int INITIAL_CAPACITY = 100;

	private Collectible[] collArr;
	private int currentCount;

	public NaiveInventory() {
		collArr = new Collectible[INITIAL_CAPACITY];
		currentCount = 0;
	}

	@Override
	public void collect(Collectible collectible) {
		if (currentCount == collArr.length) {
			Collectible[] expanded = new Collectible[collArr.length * 2];
			System.arraycopy(collArr, 0, expanded, 0, collArr.length);
			collArr = expanded;
		}
		collArr[currentCount++] = collectible;
	}

	@Override
	public float getTotalWeight() {
		float totalWeight = 0.f;
		for (int i = 0; i < currentCount; i++) {
			totalWeight += collArr[i].getWeight();
		}
		return totalWeight;
	}

	@Override
	public int getCurrentCount() {
		return currentCount;
	}

	// Returns the array of Collectibles that is stored in the field of this class
	public Collectible[] getCollArr() {
		return collArr;
	}
}
