package app.util;

/**
 * A standard generic Pair<X,Y>, with getters and toString.
 *
 * @param <X> the type of the first value
 * @param <Y> the type of the second value
 */
public class Pair<X, Y> {
    private final X firstValue;
    private final Y secondValue;

    /**
     * Creates a new instance of the class Pair.
     *
     * @param firstValue the first value of the Pair
     * @param secondValue the second value of the Pair
     */
    public Pair(final X firstValue, final Y secondValue) {
        super();
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    /**
     * @return the first value of the pair
     */
    public X getFirstValue() {
        return this.firstValue;
    }

    /**
     * @return the second value of the pair
     */
    public Y getSecondValue() {
        return this.secondValue;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Pair [first value = " + this.firstValue
                + ", second value = " + this.secondValue + "]";
    }
}
