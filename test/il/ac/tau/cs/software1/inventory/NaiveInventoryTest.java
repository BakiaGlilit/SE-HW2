package il.ac.tau.cs.software1.inventory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/*
 * Sanity checks for Question 6. DO NOT CHANGE THIS FILE.
 */
@TestMethodOrder(MethodOrderer.MethodName.class)
class NaiveInventoryTest {

    // A minimal Collectible, so that this test does not depend on the Pacman classes
    private static class Item implements Collectible {
        private final float weight;

        Item(float weight) {
            this.weight = weight;
        }

        @Override
        public float getWeight() {
            return weight;
        }

        @Override
        public float getPrice() {
            return 1.0f;
        }
    }

    @Test
    void q6_newInventoryIsEmpty() {
        NaiveInventory inventory = new NaiveInventory();

        assertEquals(0, inventory.getCurrentCount());
        assertEquals(0.0f, inventory.getTotalWeight());
        assertEquals(100, inventory.getCollArr().length);
    }

    @Test
    void q6_collectStoresItemsInOrder() {
        NaiveInventory inventory = new NaiveInventory();
        Item light = new Item(1.5f);
        Item heavy = new Item(4.0f);

        inventory.collect(light);
        inventory.collect(heavy);

        assertEquals(2, inventory.getCurrentCount());
        assertEquals(5.5f, inventory.getTotalWeight());
        assertSame(light, inventory.getCollArr()[0]);
        assertSame(heavy, inventory.getCollArr()[1]);
    }

    @Test
    void q6_arrayDoublesWhenFull() {
        NaiveInventory inventory = new NaiveInventory();
        Item first = new Item(1.0f);

        inventory.collect(first);
        for (int i = 0; i < 100; i++) {
            inventory.collect(new Item(1.0f));
        }

        assertEquals(101, inventory.getCurrentCount());
        assertEquals(101.0f, inventory.getTotalWeight());
        assertEquals(200, inventory.getCollArr().length);
        assertSame(first, inventory.getCollArr()[0]);
    }
}
