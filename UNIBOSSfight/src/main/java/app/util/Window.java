package app.util;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * This utility class models the game window.
 */
public final class Window {

    private static volatile Window instanceOfWindow;
    private double width;
    private double height;

    private Window() {
    }

    @SuppressFBWarnings(
            value = "ISC_INSTANTIATE_STATIC_CLASS",
            justification = "It is necessary for the motion of the game"
    )
    private static Window getInstanceOfWindow() {
        synchronized (Window.class) {
            if (instanceOfWindow == null) {
                instanceOfWindow = new Window();
            }
        }

        return instanceOfWindow;
    }

    /**
     * Sets the height of the window.
     *
     * @param height the height of the window
     */
    public static void setHeight(final double height) {
        Window.getInstanceOfWindow().height = height;
    }

    /**
     * Returns the height of the window.
     *
     * @return the height of the window
     */
    public static double getHeight() {
        return Window.getInstanceOfWindow().height;
    }

    /**
     * Sets the width of the window.
     *
     * @param width the width of the window
     */
    public static void setWidth(final double width) {
        Window.getInstanceOfWindow().width = width;
    }

    /**
     * Returns the width of the window.
     *
     * @return the width of the window
     */
    public static double getWidth() {
        return Window.getInstanceOfWindow().width;
    }
}
