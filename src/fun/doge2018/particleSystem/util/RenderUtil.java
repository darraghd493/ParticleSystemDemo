package fun.doge2018.particleSystem.util;

import org.lwjgl.opengl.GL11;

public class RenderUtil {
    public static void drawLine(float x, float y, float x1, float y1, float alpha) {
        GL11.glColor4f(255.0f, 255.0f, 255.0f, alpha);
        GL11.glLineWidth(0.5f);
        GL11.glBegin(GL11.GL_LINES);

        GL11.glVertex2f(x, y);
        GL11.glVertex2f(x1, y1);
        GL11.glEnd();
    }
}
