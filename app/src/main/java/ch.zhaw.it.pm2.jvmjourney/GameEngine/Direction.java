package ch.zhaw.it.pm2.jvmjourney.GameEngine;


/**
 * Enum representing a direction on the grid.
 * Also representing the possible acceleration values.
 */
public enum Direction {
    DOWN(new PositionVector(0, 1)),
    LEFT(new PositionVector(-1, 0)),
    NONE(new PositionVector(0, 0)),
    RIGHT(new PositionVector(1, 0));

    /**
     * PositionVector representing the direction velocity.
     */
    public final PositionVector vector;

    Direction(final PositionVector vector) {
        this.vector = vector;
    }
}
