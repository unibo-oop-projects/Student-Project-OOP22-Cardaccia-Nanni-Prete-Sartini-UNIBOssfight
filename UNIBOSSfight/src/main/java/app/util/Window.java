package app.util;

/**
 * This utility class models the game window.
 */
public final class Window {

    private static double width;
    private static double height;

    private Window() {
        throw new UnsupportedOperationException("This is a utility class"
                + " and cannot be instantiated");
    }

    /**
     * Sets the height of the window.
     *
     * @param height the height of the window
     */
    public static void setHeight(final double height) {
        Window.height = height;
    }

    /**
     * Returns the height of the window.
     *
     * @return the height of the window
     */
    public static double getHeight() {
        return Window.height;
    }

    /**
     * Sets the width of the window.
     *
     * @param width the width of the window
     */
    public static void setWidth(final double width) {
        Window.width = width;
    }

    /**
     * Returns the width of the window.
     *
     * @return the width of the window
     */
    public static double getWidth() {
        return Window.width;
    }
}
