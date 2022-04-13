package fun.doge2018.particleSystemDemo;

import fun.doge2018.particleSystem.Particle;
import fun.doge2018.particleSystem.ParticleSystem;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.*;

import javax.swing.*;

public class ParticalSystemDemo extends BasicGame {
    private ParticleSystem particleSystem;

    public ParticalSystemDemo() {
        super("Particle System Demo");
    }

    public static void main(String args[]) {
        try {
            ParticalSystemDemo particalSystemDemo = new ParticalSystemDemo();
            AppGameContainer appGameContainer = new AppGameContainer(particalSystemDemo);

            appGameContainer.setDisplayMode((int) appGameContainer.getScreenWidth()/2, (int) appGameContainer.getScreenWidth()/4, false);
            appGameContainer.setVSync(false);
            appGameContainer.setShowFPS(true);
            appGameContainer.start();
        } catch (SlickException e) {
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage(), "An error occurred!", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        particleSystem = new ParticleSystem(true);
    }
    @Override
    public void update(GameContainer gameContainer, int delta) throws SlickException {
        if (Mouse.isButtonDown(0))
            particleSystem.addCustomParticle(new Particle(Mouse.getX(),
                    Display.getHeight() - Mouse.getY()));
        particleSystem.tick(delta);
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        graphics.clearAlphaMap();
        graphics.clear();

        if (gameContainer.hasFocus()) {
            particleSystem.render();
        }
    }
}
