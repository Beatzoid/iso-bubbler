package display;

import state.State;
import input.Input;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Display extends JFrame {

    private final Canvas canvas;
    private final Renderer renderer;
    private DebugRenderer debugRenderer;

    /**
     * The Display class manages the display
     * @param width The width of the display
     * @param height The height of the display
     * @param input The Input
     *
     * @see Input
     */
    public Display(int width, int height, Input input) {
        setTitle("My 2D game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        this.renderer = new Renderer();
        this.debugRenderer = new DebugRenderer();

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setFocusable(false);
        canvas.addMouseListener(input);
        canvas.addMouseMotionListener(input);
        add(canvas);
        addKeyListener(input);
        pack();

        canvas.createBufferStrategy(2);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Render the Display
     * @param state The state
     * @param debugMode Whether or not to enable debug mode
     *
     * @see State
     */
    public void render(State state, boolean debugMode) {
        BufferStrategy bufferStrategy = canvas.getBufferStrategy();
        Graphics graphics = bufferStrategy.getDrawGraphics();

        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        renderer.render(state, graphics);

        if(debugMode) {
            debugRenderer.render(state, graphics);
        }

        graphics.dispose();
        bufferStrategy.show();
    }
}
