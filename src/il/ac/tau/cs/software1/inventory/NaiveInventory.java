package il.ac.tau.cs.software1.inventory;

/*
 * A simple array-based Inventory (Question 6).
 */
public class NaiveInventory implements Inventory {

	private static final int INITIAL_CAPACITY = 100;

	// TODO: add the fields:
	//       - an array of Collectibles (it may be larger than the number of items actually stored)
	//       - an int that counts how many items are actually stored

	public NaiveInventory() {
		// TODO: implement
	}

	@Override
	public void collect(Collectible collectible) {
		// TODO: implement
	}

	@Override
	public float getTotalWeight() {
		// TODO: implement
		return 0.f;
	}

	@Override
	public int getCurrentCount() {
		// TODO: implement
		return 0;
	}

	// Returns the array of Collectibles that is stored in the field of this class
	public Collectible[] getCollArr() {
		// TODO: implement
		return null;
	}
}
