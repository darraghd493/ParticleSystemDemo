package fun.doge2018.particleSystem;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;

import java.util.Random;

public class Particle {
    private static final Random random = new Random();

    private Vector2f velocity;
    private Vector2f position;
    private float size;
    private float alpha;

    public Particle() {
        velocity = new Vector2f((float) (Math.random() * 2.0f - 1.0f),
                (float) (Math.random() * 2.0f - 1.0f));
        position = new Vector2f(random.nextInt(Display.getWidth()),
                random.nextInt(Display.getHeight()));
        size = (float) (Math.random() * 4.0f) + 1.0f;
        alpha = (float) (Math.random() * 255.0f) + 1.0f;
    }

    public Particle(float x, float y) {
        velocity = new Vector2f((float) (Math.random() * 2.0f - 1.0f),
                (float) (Math.random() * 2.0f - 1.0f));
        position = new Vector2f(x, y);
        size = (float) (Math.random() * 4.0f) + 1.0f;
        alpha = (float) (Math.random() * 255.0f) + 1.0f;
    }

    public Vector2f getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2f velocity) {
        this.velocity = velocity;
    }

    public float getVelocityX() {
        return velocity.getX();
    }

    public void setVelocityX(float x) {
        velocity.setX(x);
    }

    public float getVelocityY() {
        return velocity.getY();
    }

    public void setVelocityY(float y) {
        velocity.setY(y);
    }

    public Vector2f getPosition() {
        return position;
    }

    public void setPosition(Vector2f position) {
        this.position = position;
    }

    public float getX() {
        return position.getX();
    }

    public void setX(float x) {
        position.setX(x);
    }

    public float getY() {
        return position.getY();
    }

    public void setY(float y) {
        position.setY(y);
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public float getAlpha() {
        return alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public void tick(int delta, float speed) {
        position.x += velocity.getX() * delta * speed;
        position.y += velocity.getY() * delta * speed;

        if (alpha < 255.0f)
            alpha += 0.05f * delta;

        if (position.getX() > Display.getWidth())
            position.setX(0);

        if (position.getX() < 0)
            position.setX(Display.getWidth());

        if (position.getY() > Display.getHeight())
            position.setY(0);

        if (position.getY() < 0)
            position.setY(Display.getHeight());
    }


}
