import java.awt.*;

public class Game {

    private final Display display;
    private final Rectangle rectangle;

    public Game(int width, int height) {
        display = new Display(width, height);
        rectangle = new Rectangle(0, 0, 50, 50);
    }

    public void update() {
        rectangle.setLocation((int) rectangle.getX() + 1, (int) rectangle.getY());
    }

    public void render() {
        display.render(this);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
