package fun.doge2018.particleSystem.util;

public class MathUtil {
    public static double distance(float x, float y, float x1, float y1) {
        float xDistance = (x - x1);
        float yDistance = (y - y1);

        return Math.sqrt(
                xDistance * xDistance +
                yDistance * yDistance
        );
    }
}
