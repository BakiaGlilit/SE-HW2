package il.ac.tau.cs.software1.pacman;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import il.ac.tau.cs.software1.inventory.Inventory;
import il.ac.tau.cs.software1.math.Vector2;

/*
 * Sanity checks for Questions 8 - 9. DO NOT CHANGE THIS FILE.
 *
 * These tests are "headless": they use the game objects directly, without opening a window
 * and without drawing anything. This is why they can run on a GitHub server that has no screen.
 */
@TestMethodOrder(MethodOrderer.MethodName.class)
class PacmanInventoryTest {

    private static final String SCENE = "resources/scenes/first.txt";

    private static PacmanPlayer findPlayer(List<PacmanGameObject> objects) {
        for (PacmanGameObject object : objects) {
            if (object instanceof PacmanPlayer) {
                return (PacmanPlayer) object;
            }
        }
        return null;
    }

    // This one passes even before you write any code: the existing game loads without a screen
    @Test
    void sceneLoadsWithoutAWindow() {
        List<PacmanGameObject> objects = PacmanSceneReader.readScene(SCENE);

        int bonuses = 0;
        for (PacmanGameObject object : objects) {
            if (object instanceof PacmanBonusDot) {
                bonuses++;
            }
        }
        assertNotNull(findPlayer(objects));
        assertEquals(244, bonuses);
    }

    // ------------------------------------------------------------ Question 8

    @Test
    void q8_bonusDotWeightAndPrice() {
        PacmanBonusDot dot = new PacmanBonusDot(new PacmanGridComponent(3, 4));

        assertEquals(1.0f, dot.getWeight());
        assertEquals(12.0f, dot.getPrice());
    }

    // ------------------------------------------------------------ Question 9

    @Test
    void q9_hittingABonusStashesIt() {
        PacmanPlayer player = new PacmanPlayer(new PacmanGridComponent(1, 1));
        PacmanBonusDot dot = new PacmanBonusDot(new PacmanGridComponent(1, 1));

        player.onHit(dot);

        Inventory inventory = player.getInventoryComponent().getInventory();
        Vector2 dotPosition = dot.getTransformComponent().position;
        assertEquals(1, inventory.getCurrentCount());
        assertEquals(10, player.getScore());
        assertEquals(-100.0f, dotPosition.x);
        assertEquals(-100.0f, dotPosition.y);
        assertFalse(dot.getShouldDelete(), "a collected bonus is moved away, not destroyed");
    }

    @Test
    void q9_playerStopsCollectingAtMaxWeight() {
        List<PacmanGameObject> objects = PacmanSceneReader.readScene(SCENE);
        PacmanPlayer player = findPlayer(objects);

        // Walk into every bonus of the scene: only 10 of them can be carried
        int leftOnTheBoard = 0;
        for (PacmanGameObject object : objects) {
            if (object instanceof PacmanBonusDot) {
                player.onHit(object);
                if (object.getTransformComponent().position.x > -50.0f) {
                    leftOnTheBoard++;
                }
                assertFalse(object.getShouldDelete(), "bonuses are never destroyed");
            }
        }

        Inventory inventory = player.getInventoryComponent().getInventory();
        assertEquals(10, inventory.getCurrentCount());
        assertEquals(10.0f, inventory.getTotalWeight());
        assertEquals(100, player.getScore());
        assertEquals(234, leftOnTheBoard);
    }
}
