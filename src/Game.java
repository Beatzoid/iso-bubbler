public class Game {

    private Display display;

    public Game(int width, int height) {
        display = new Display(width, height);
    }

    public void update() {

    }

    public void render() {
        display.render(this);
    }
}
