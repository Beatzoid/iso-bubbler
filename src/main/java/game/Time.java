package game;

public class Time {

    private int updatesSinceStart;

    public Time() {
        this.updatesSinceStart = 0;
    }

    /**
     * Get how many updates there are in a certain amount of seconds
     * @param seconds The seconds
     */
    public int getUpdatesFromSeconds(int seconds) {
        return seconds * GameLoop.UPDATES_PER_SECOND;
    }

    public void update() {
        updatesSinceStart++;
    }

    /**
     * Get the current game time formatted nicely
     */
    public String getFormattedTime() {
        StringBuilder stringBuilder = new StringBuilder();
        int minutes = updatesSinceStart / GameLoop.UPDATES_PER_SECOND / 60;
        int seconds = updatesSinceStart / GameLoop.UPDATES_PER_SECOND % 60;

        if (minutes < 10) {
            stringBuilder.append(0);
        }

        stringBuilder.append(minutes);
        stringBuilder.append(":");

        if (seconds < 10) {
            stringBuilder.append(0);
        }

        stringBuilder.append(seconds);
        return stringBuilder.toString();
    }
}
