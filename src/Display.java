import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Display extends JFrame {

    private Canvas canvas;

    public Display(int width, int height) {
        setTitle("My 2D Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setFocusable(false);
        add(canvas);
        pack();

        canvas.createBufferStrategy(3);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void render(Game game) {
        BufferStrategy bufferStrategy = canvas.getBufferStrategy();
        Graphics graphics = bufferStrategy.getDrawGraphics();

        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        graphics.dispose();
        bufferStrategy.show();
    }
}
