package game;

public class GameLoop implements Runnable {

    public static final int UPDATES_PER_SECOND = 60;

    private final Game game;

    @SuppressWarnings("FieldCanBeLocal")
    private boolean running;
    @SuppressWarnings("FieldCanBeLocal")
    private final double updateRate = 1.0d/UPDATES_PER_SECOND;

    private long nextStatTime;
    private int fps, ups;

    /**
     * The main GameLoop.
     *
     * @see Game
     */
    public GameLoop(Game game) {
        this.game = game;
    }

    @Override
    public void run() {
        running = true;
        double accumulator = 0;
        long currentTime, lastUpdate = System.currentTimeMillis();
        nextStatTime = System.currentTimeMillis() + 1000;

        while (running) {
            currentTime = System.currentTimeMillis();
            double lastRenderTimeInSeconds = (currentTime - lastUpdate) / 1000d;
            accumulator += lastRenderTimeInSeconds * game.getSettings().getGameSpeedMultiplier();
            lastUpdate = currentTime;

            if(accumulator >= updateRate) {
                while (accumulator >= updateRate) {
                    update();
                    accumulator -= updateRate;
                }
            }
            render();
            printStats();
        }
    }

    private void printStats() {
        if (System.currentTimeMillis() > nextStatTime) {
            System.out.printf("FPS: %d, UPS: %d%n", fps, ups);
            fps = 0;
            ups = 0;
            nextStatTime = System.currentTimeMillis() + 1000;
        }
    }

    private void update() {
        game.update();
        ups++;
    }

    private void render() {
        game.render();
        fps++;
    }
}
