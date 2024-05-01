package ch.zhaw.it.pm2.jvmjourney.GameEngine;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Holds a position (vector to x,y-position of the car on the track grid)
 * or a velocity vector (x,y-components of the velocity vector of a car).<br/>
 * PositionVectors are immutable, which means they cannot be modified.<br/>
 * Vector operations like {@link #add(PositionVector)} and {@link #subtract(PositionVector)}
 * return a new PositionVector containing the result.
 *
 * @author mach
 * @version FS2023
 */
public final class PositionVector {
    /**
     * Format to print the position vector.
     */
    private static final String POSITION_VECTOR_FORMAT = "(X:%d, Y:%d)";

    /**
     * Pattern to parse a position vector from string format.
     */
    private static final Pattern POSITION_VECTOR_PATTERN = Pattern.compile("\\(X:(\\d+), Y:(\\d+)\\)");

    /**
     * horizontal value (position / velocity).
     */
    private double x;

    /**
     * vertical value (position / velocity).
     */
    private double y;


    /**
     * Base constructor, initializing the position using coordinates or a velocity vector.
     *
     * @param x horizontal value (position or velocity)
     * @param y vertical value (position or velocity)
     */
    public PositionVector(final double x, final double y) {
        this.y = y;
        this.x = x;
    }

    /**
     * Copy constructor, copying the values from another PositionVector.
     *
     * @param other position vector to copy from
     */
    public PositionVector(final PositionVector other) {
        this.x = other.getX();
        this.y = other.getY();
    }

    /**
     * @return the horizontal value (position or velocity)
     */
    public double getX() {
        return this.x;
    }

    /**
     * @return vertical value (position or velocity)
     */
    public double getY() {
        return this.y;
    }

    @Override
    public String toString() {
        return POSITION_VECTOR_FORMAT.formatted(this.x, this.y);
    }

    /**
     * Calculates the vector addition of the current vector with the given vector, e.g.
     * <ul>
     *   <li>if a velocity vector is added to a position, the next position is returned</li>
     *   <li>if a direction vector is added to a velocity, the new velocity is returned</li>
     * </ul>
     * The vectors values are not modified, but a new Vector containing the result is returned.
     *
     * @param vector a position or velocity vector to add
     * @return A new PositionVector holding the result of the addition.
     */
    public PositionVector add(final PositionVector vector) {
        return new PositionVector(this.getX() + vector.getX(), this.getY() + vector.getY());
    }

    /**
     * Calculates the vector difference of the current vector to the given vector,
     * i.e. subtracts the given from the current vectors coordinates. (e.g. car position and/or velocity vector) <br>
     * The vectors values are not modified, but a new Vector containing the result is returned.
     *
     * @param vector A position or velocity vector to subtract
     * @return A new PositionVector holding the result of the subtraction.
     */
    public PositionVector subtract(final PositionVector vector) {
        return new PositionVector(this.getX() - vector.getX(), this.getY() - vector.getY());
    }

    /**
     * Parses a position vector from a string in the format (X:1, Y:2).
     * This is the format produced by {@link #toString()}.
     *
     * @param positionString string to parse
     * @return parsed position vector
     */
    public static PositionVector parsePositionVector(String positionString) {
        Matcher matcher = POSITION_VECTOR_PATTERN.matcher(positionString);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("String does not match position vector pattern: " + positionString);
        }
        return new PositionVector(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
    }

    public Double distance(PositionVector a) {
        return Math.sqrt(Math.pow(a.getX() - this.getX(), 2) + Math.pow(a.getY() - this.getY(), 2));
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public PositionVector bounceOnTheGround () {
        return new PositionVector(this.getX(), -this.getY());
    }

    public PositionVector bounceOntheWall () {
        return new PositionVector(-this.getX(), this.getY());
    }
    /
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PositionVector that = (PositionVector) o;
        return Double.compare(x, that.x) == 0 && Double.compare(y, that.y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }


}