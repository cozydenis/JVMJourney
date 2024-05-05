package test;

import static org.junit.jupiter.api.Assertions.*;

import ch.zhaw.it.pm2.jvmjourney.GameEngine.PositionVector;
import org.junit.jupiter.api.Test;

public class PositionVectorTest {

    @Test
    void testConstructorAndGetters() {
        PositionVector vector = new PositionVector(3.0, 4.0);
        assertEquals(3.0, vector.getX(), "X coordinate should match constructor argument.");
        assertEquals(4.0, vector.getY(), "Y coordinate should match constructor argument.");
    }

    @Test
    void testCopyConstructor() {
        PositionVector original = new PositionVector(5.0, 6.0);
        PositionVector copy = new PositionVector(original);
        assertEquals(original.getX(), copy.getX(), "Copied vector X should be the same as original");
        assertEquals(original.getY(), copy.getY(), "Copied vector Y should be the same as original");
    }

    @Test
    void testAdd() {
        PositionVector v1 = new PositionVector(1, 2);
        PositionVector v2 = new PositionVector(3, 4);
        PositionVector result = v1.add(v2);
        assertEquals(4, result.getX(), "X coordinates should be added");
        assertEquals(6, result.getY(), "Y coordinates should be added");
    }

    @Test
    void testSubtract() {
        PositionVector v1 = new PositionVector(5, 4);
        PositionVector v2 = new PositionVector(3, 2);
        PositionVector result = v1.subtract(v2);
        assertEquals(2, result.getX(), "X coordinates should be subtracted");
        assertEquals(2, result.getY(), "Y coordinates should be subtracted");
    }

    @Test
    void testParsePositionVector() {
        String input = "(X:10, Y:20)";
        PositionVector result = PositionVector.parsePositionVector(input);
        assertEquals(10, result.getX(), "Parsed X should equal input X");
        assertEquals(20, result.getY(), "Parsed Y should equal input Y");
    }

    @Test
    void testBounceOnTheGround() {
        PositionVector vector = new PositionVector(10, -20);
        PositionVector bounced = vector.bounceOnTheGround();
        assertEquals(10, bounced.getX(), "X should remain the same after bounce");
        assertEquals(20, bounced.getY(), "Y should be positive after bounce");
    }

    @Test
    void testBounceOnTheWall() {
        PositionVector vector = new PositionVector(-30, 40);
        PositionVector bounced = vector.bounceOntheWall();
        assertEquals(30, bounced.getX(), "X should be positive after wall bounce");
        assertEquals(40, bounced.getY(), "Y should remain the same after wall bounce");
    }

    @Test
    void testEqualsAndHashCode() {
        PositionVector v1 = new PositionVector(7, 8);
        PositionVector v2 = new PositionVector(7, 8);
        PositionVector v3 = new PositionVector(8, 7);
        assertEquals(v1, v2, "Vectors with the same coordinates should be equal");
        assertNotEquals(v1, v3, "Vectors with different coordinates should not be equal");
        assertEquals(v1.hashCode(), v2.hashCode(), "Hashcode should be the same for equal objects");
    }
}
