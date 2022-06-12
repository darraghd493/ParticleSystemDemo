package fun.doge2018.particleSystem;

import fun.doge2018.particleSystem.util.MathUtil;
import fun.doge2018.particleSystem.util.RenderUtil;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParticleSystem {
    private final boolean displayClosestLines;
    private final int lineRadius;
    private static final float particleSpeed = 0.125f;
    private static final List<Particle> particleList = new ArrayList<Particle>();

    public ParticleSystem() {
        displayClosestLines = true;
        lineRadius = 150;
        addParticles(200);
    }

    public ParticleSystem(boolean displayClosestLines) {
        this.displayClosestLines = displayClosestLines;
        lineRadius = 150;
        addParticles(200);
    }

    public ParticleSystem(int lineRadius, boolean displayClosestLines) {
        this.displayClosestLines = displayClosestLines;
        this.lineRadius = lineRadius;
        addParticles(200);
    }

    public ParticleSystem(int particleAmount, int lineRadius, boolean displayClosestLines) {
        this.displayClosestLines = displayClosestLines;
        this.lineRadius = lineRadius;
        addParticles(particleAmount);
    }

    public static void main(String args[]) {
    }

    public void addParticles(int particleAmount) {
        for (int i = 0; i < particleAmount; i++) {
            particleList.add(new Particle());
        }
    }

    public void addParticle() {
        particleList.add(new Particle());
    }

    public void addCustomParticle(Particle particle) {
        particleList.add(particle);
    }

    public void addCustomParticles(Particle[] particle) {
        particleList.addAll(Arrays.asList(particle));
    }

    public void addCustomParticles(List<Particle> particle) {
        particleList.addAll(particle);
    }

    public void removeParticle() {
        if (particleList.size() - 1 > 0)
            particleList.remove(0);
    }

    public void removeParticles(int particleAmount) {
        if (particleAmount > 0 && particleList.size() - particleAmount > 0) {
            particleList.subList(0, particleAmount).clear();
        }
    }

    public void removeCustomParticle(Particle particle) {
        if (particleList.size() - 1 > 0)
            particleList.remove(particle);
    }

    public void removeCustomParticles(Particle[] particles) {
        if (particleList.size() - particles.length > 0)
            particleList.removeAll(Arrays.asList(particles));
    }

    public void removeCustomParticles(List<Particle> particles) {
        if (particleList.size() - particles.size() > 0)
            particleList.removeAll(particles);
    }

    public void tick(int tickDelta) {
        for (Particle particle : particleList) {
            particle.tick(tickDelta, particleSpeed);
        }
    }

    public void render() {
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glEnable(GL11.GL_POINT_SMOOTH);

        for (Particle particle : particleList) {
            GL11.glColor4f(1.0f, 1.0f,1.0f, particle.getAlpha() / 255.0f);
            GL11.glPointSize(particle.getSize());
            GL11.glBegin(GL11.GL_POINTS);
            GL11.glVertex2f(particle.getX(), particle.getY());
            GL11.glEnd();

            if (displayClosestLines) {
                float nearestDistance = 0;
                Particle nearestParticle = null;

                for (Particle particle1 : particleList) {
                    float distance = (float) MathUtil.distance(
                            particle.getX(), particle.getY(),
                            particle1.getX(), particle1.getY()
                    );

                    if (distance <= lineRadius &&
                            (MathUtil.distance(
                                    Mouse.getX(),
                                    Display.getHeight() - Mouse.getY(),
                                    particle.getX(),
                                    particle.getY()) <= lineRadius ||
                                    MathUtil.distance(
                                            Mouse.getX(),
                                            Display.getHeight() - Mouse.getY(),
                                            particle1.getX(),
                                            particle1.getY()) <= lineRadius)
                            && (
                            nearestDistance <= 0 ||
                                    distance <= nearestDistance)
                    ) {
                        nearestDistance = distance;
                        nearestParticle = particle1;
                    }
                }

                if (nearestParticle != null) {
                    float particleAlpha = Math.min(1.0f,
                            Math.min(1.0f, 1.0f - nearestDistance / lineRadius));

                    RenderUtil.drawLine(particle.getX(),
                            particle.getY(),
                            nearestParticle.getX(),
                            nearestParticle.getY(),
                            particleAlpha);
                }
            }
        }

        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(GL11.GL_POINT_SMOOTH);
    }

    public void shutdown() {
        particleList.clear();
    }
}
