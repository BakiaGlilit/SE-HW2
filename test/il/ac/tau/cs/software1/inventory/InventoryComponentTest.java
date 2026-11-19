package il.ac.tau.cs.software1.inventory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import il.ac.tau.cs.software1.components.IComponent;
import il.ac.tau.cs.software1.math.Vector2;

/*
 * Sanity checks for Question 7. DO NOT CHANGE THIS FILE.
 */
@TestMethodOrder(MethodOrderer.MethodName.class)
class InventoryComponentTest {

    @Test
    void q7_isAComponentWithANaiveInventory() {
        InventoryComponent component = new InventoryComponent();

        assertInstanceOf(IComponent.class, component);
        assertInstanceOf(NaiveInventory.class, component.getInventory());
        assertSame(component.getInventory(), component.getInventory());
        assertEquals(0, component.getInventory().getCurrentCount());
    }

    @Test
    void q7_inventoryPositionIsOffScreen() {
        Vector2 position = new InventoryComponent().getInventoryPosition();

        assertEquals(-100.0f, position.x);
        assertEquals(-100.0f, position.y);
    }

    @Test
    void q7_maxWeight() {
        InventoryComponent component = new InventoryComponent();
        assertEquals(10.0f, component.getMaxWeight());

        component.setMaxWeight(42.5f);
        assertEquals(42.5f, component.getMaxWeight());
    }
}
