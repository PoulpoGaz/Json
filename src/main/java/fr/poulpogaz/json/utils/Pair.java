package fr.poulpogaz.json.utils;

/**
 * A pair consisting of two elements ("left" and "right")
 * @author PoulpoGaz
 * @version 1.0
 *
 * @param <L> the left element type
 * @param <R> the right element type
 */
public class Pair<L, R> {

    /** Left element **/
    private final L left;

    /** Right element **/
    private final R right;

    /**
     * Creates a new pair
     *
     * @param left the left element
     * @param right the right element
     */
    public Pair(L left, R right) {
        this.left = left;
        this.right = right;
    }

    /**
     * @return the left element
     */
    public L getLeft() {
        return left;
    }

    /**
     * @return the right element
     */
    public R getRight() {
        return right;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }
}