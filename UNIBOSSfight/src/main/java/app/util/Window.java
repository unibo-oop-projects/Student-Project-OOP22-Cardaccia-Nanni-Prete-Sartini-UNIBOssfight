package app.util;

public final class Window {

    private static double width;
    private static double height;

    private Window() {
        throw new UnsupportedOperationException("This is a utility class" +
                " and cannot be instantiated");
    }

    public static void setHeight(final double height) {
        Window.height = height;
    }

    public static void setWidth(final double width) {
        Window.width = width;
    }

    public static double getHeight() {
        return Window.height;
    }

    public static double getWidth() {
        return Window.width;
    }
}
