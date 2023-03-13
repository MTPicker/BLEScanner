package servers.ble;

public final class LedPattern {
    //bit values
    public static final int RED = 1;
    public static final int BLUE = 2;
    public static final int GREEN = 4;
    public static final int YELLOW = 8;


    public static int getPattern(PickletCommand pickletCommand) {
        int colorVal = (
                (pickletCommand.getRed() * LedPattern.RED)
                        + (pickletCommand.getBlue() * LedPattern.BLUE)
                        + (pickletCommand.getGreen() * LedPattern.GREEN)
                        + (pickletCommand.getYellow() * LedPattern.YELLOW)
        );
        return colorVal;
    }

}
