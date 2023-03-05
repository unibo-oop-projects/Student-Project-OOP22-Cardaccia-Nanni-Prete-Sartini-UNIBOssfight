package util;

public class Window {

    private static double width;
    private static double height;

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
